package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sg.loginweb.entity.IpLimit;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.PlayerBan;
import org.sg.loginweb.entity.bo.PlayerBo;
import org.sg.loginweb.entity.vo.PlayerVo;
import org.sg.loginweb.mapper.PlayerMapper;
import org.sg.loginweb.service.IpLimitService;
import org.sg.loginweb.service.PlayerBanService;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* Player业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:08:04
 */

@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper,Player> implements PlayerService{
	
	private static final Log LOG= LogFactory.getLog(PlayerServiceImpl.class);

	@Autowired
	private PlayerMapper playerMapper;
	
	@Autowired
	private PlayerBanService playerBanService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private IpLimitService ipLimitService;

	@Override
	public int insertPlayerForPlayer(HttpSession session,HttpServletRequest request,PlayerBo playerBo) {
		System.out.println(MyUtil.getDataBaseType());
		Map<String,String> config=sysConfigService.sysConfigCache(
				"playername","pass","playerlength1","playerlength2","passlength1","passlength2","reglimit","regvalid"
				);
		String ip=MyUtil.getIpAddress(request);
		if(ip==null) {
			LOG.info("玩家："+playerBo.getRealname()+" 没有IP！");
			return 3;
		}
		
		Player loginPlayer=(Player)session.getAttribute("player");
		//管理员添加账号不用验证码、注册数量、是否开启注册
		if(loginPlayer==null) {
			Object getCode=session.getAttribute("imgCode");
			if(getCode!=null) {
				if(playerBo.getCode()==null) {
					//没传验证码
					return 401;
				}
				if(!getCode.toString().toLowerCase().equals(playerBo.getCode().toLowerCase())) {
					//验证码比较错误
					return 400;
				}
				if(!"1".equals(config.get("regvalid").toString())) {
					// 服务器关闭了注册
					return 411;
				}
				if(ipLimitService.selectIpLimitByNowDayCount(ip)>=Integer.parseInt(config.get("reglimit"))) {
					// 超出每人每天注册量
					LOG.info("玩家："+playerBo.getRealname()+" IP："+ip+" 试图多次注册！");
					return 412;
				}
			}else {
				return 408;
			}
			playerBo.setAdminif(0);
		} else {
			if(loginPlayer.getAdminif()!=1) {
				return 410;
			}
		}
		//有值为空
		if(playerBo.getRealname()==null || playerBo.getPassword()==null || playerBo.getPassword2()==null) {
			return 402;
		}
		//验证帐号
		if(playerBo.getRealname().length()<Integer.parseInt(config.get("playerlength1")) || playerBo.getRealname().length()>Integer.parseInt(config.get("playerlength2"))) {
			return 403;
		}
		//验证帐号
		Pattern p = Pattern.compile(config.get("playername").toString());
		Matcher matcher =p.matcher(playerBo.getRealname());
		if(!matcher.matches()) {
			return 404;
		}
		//验证密码
		p = Pattern.compile(config.get("pass").toString());
		matcher =p.matcher(playerBo.getPassword());
		if(!matcher.matches()) {
			return 405;
		}
		
		if(!playerBo.getPassword().equals(playerBo.getPassword2())) {
			return 406;
		}
		
		if(playerBo.getPassword().length()<Integer.parseInt(config.get("passlength1")) || playerBo.getPassword().length()>Integer.parseInt(config.get("passlength2"))) {
			return 407;
		}
		if(playerExist(playerBo.getRealname())>=1) {
			return 409;
		}
		playerBo.setRealip(ip);
		Player player=new Player();
		player.setId(UUID.randomUUID().toString());
		player.setUsername(playerBo.getRealname().toLowerCase());
		player.setRealname(playerBo.getRealname());
		player.setRegDate(new Date());
		player.setRealip(ip);
		player.setOnline(0);
		player.setBanif(0);
		player.setCountTime(0);
		player.setPassword(playerBo.getPassword());
		player.setEmail(playerBo.getEmail());
		player.setAdminif(playerBo.getAdminif());
		if(playerMapper.insert(player)>=1) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						IpLimit ipLimit=new IpLimit();
						ipLimit.setIp(ip);
						ipLimit.setPlayername(playerBo.getRealname());
						ipLimitService.insertIpLimit(ipLimit);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			session.removeAttribute("imgCode");
			LOG.info("玩家："+playerBo.getRealname()+" 注册成功！");
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int playerExist(String username) {
		return playerMapper.selectCount(new QueryWrapper<Player>().eq("username", username.toLowerCase()));
	}

	@Override
	public int playerSign(HttpSession session, PlayerBo playerBo,String code) {
		Player player=playerMapper.selectOne(new QueryWrapper<Player>().eq("username", playerBo.getUsername().toLowerCase()));
		if(session.getAttribute("imgCode")==null) {
			return 1;
		}
		if(!session.getAttribute("imgCode").toString().toLowerCase().equals(code.toLowerCase())) {
			return 2;
		}
		if(player!=null && player.getPassword().equals(playerBo.getPassword())) {
			session.setAttribute("player", player);
			return 200;
		}
		//登陆失败，账号或密码错误
		return 0;
	}

	@Override
	public Result selectPlayerByPage(PlayerBo playerBo) {
		IPage<Player> page=new Page<Player>(playerBo.getResPage(),playerBo.getResPageSize());
		if(playerBo.getRegDate1()!=null && !"".equals(playerBo.getRegDate1())) {
			playerBo.setRegDate1_sql(MyUtil.StringToStringDate(playerBo.getRegDate1()));
		}
		if(playerBo.getRegDate2()!=null && !"".equals(playerBo.getRegDate2())) {
			playerBo.setRegDate2_sql(MyUtil.StringToStringDate(playerBo.getRegDate2()));
		}
		if(playerBo.getLastDate1()!=null && !"".equals(playerBo.getLastDate1())) {
			playerBo.setLastDate1_sql(MyUtil.StringToStringDate(playerBo.getLastDate1()));
		}
		if(playerBo.getLastDate2()!=null && !"".equals(playerBo.getLastDate2())) {
			playerBo.setLastDate2_sql(MyUtil.StringToStringDate(playerBo.getLastDate2()));
		}
		if(playerBo.getUsername()!=null) {
			playerBo.setUsername(playerBo.getUsername().toLowerCase());
		}
		IPage<Player> players = playerMapper.selectPlayerByPage(page, playerBo);
		List<PlayerVo> playerVos=new ArrayList<PlayerVo>();
		for(Player player:players.getRecords()) {
			//id,nickname,realname,regDate,realip,lastDate,banif,online,adminif
			PlayerVo playerVo=new PlayerVo();
			playerVo.setId(player.getId());
			playerVo.setNickname(player.getNickname());
			playerVo.setRealname(player.getRealname());
			playerVo.setRegDate(player.getRegDate());
			playerVo.setRealip(MyUtil.getIpAtCityName(player.getRealip()));
			if(player.getLastDate()!=null) {
				playerVo.setLastDateShow(MyUtil.DateToString(player.getLastDate()));
			}
			String banifshow=null;
			switch (player.getBanif()) {
			case 1:
				banifshow="封禁中";
				break;
			case 2:
				banifshow="永封";
				break;
			default:
				banifshow="正常";
				break;
			}
			playerVo.setBanifshow(banifshow);
			playerVo.setOnlineshow(player.getOnline()==1?"在线":"离线");
			playerVo.setAdminshow(player.getAdminif()==1?"管理员":"玩家");
			playerVos.add(playerVo);
		}
		return new Result(players, playerVos);
	}

	@Override
	public PlayerVo selectPlayerById(String id) {
		PlayerVo playerVo=new PlayerVo();
		Player player=playerMapper.selectById(id);
		playerVo.setId(id);
		playerVo.setNickname(player.getNickname());
		playerVo.setRealname(player.getRealname());
		playerVo.setRegDate(player.getRegDate());
		playerVo.setEmail(player.getEmail());
		playerVo.setX(player.getX());
		playerVo.setY(player.getY());
		playerVo.setZ(player.getZ());
		playerVo.setYaw(player.getYaw());
		playerVo.setPitch(player.getPitch());
		playerVo.setWorld(player.getWorld());
		playerVo.setRealip(player.getRealip());
		playerVo.setRealipname(MyUtil.getIpAtCityName(player.getRealip()));
		if(player.getLastDate()!=null) {
			playerVo.setLastDateShow(MyUtil.DateToString(player.getLastDate()));
		}
		playerVo.setLastip(player.getLastip());
		playerVo.setLastipname(MyUtil.getIpAtCityName(player.getLastip()));
		if(player.getExitDate()!=null) {
			playerVo.setExitDateShow(MyUtil.DateToString(player.getExitDate()));
		}
		playerVo.setBanif(player.getBanif());
		if(player.getBanstart()!=null) {
			playerVo.setBanstartshow(MyUtil.DateToTString(player.getBanstart()));
		}
		if(player.getBanend()!=null) {
			playerVo.setBanendshow(MyUtil.DateToTString(player.getBanend()));
			playerVo.setBanTime(MyUtil.getTwoDateTimes(player.getBanstart(), player.getBanend()));
			playerVo.setSurbanTime(MyUtil.getTwoDateTimes(new Date(), player.getBanend()));
		}
		playerVo.setAdminif(player.getAdminif());
		playerVo.setCountTime(player.getCountTime());
		playerVo.setBanid(player.getBanid());
		return playerVo;
	}

	@Override
	public int updatePlayerByid(PlayerBo playerBo,Player playerhande) {
		//是否要记录
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(playerBo.getBanif()>=1) {
					PlayerVo playerVo=selectPlayerById(playerBo.getId());
					boolean iflog=false;
					//被办理由是否与原来不同
					if(playerVo.getBanif()==null || playerVo.getBanif()!=playerBo.getBanif()) {
						iflog=true;
						//被办理由是否是永封
					}else if(playerVo.getBanif()==playerBo.getBanif() && playerBo.getBanif()==2) {
						//永封的情况下是否开始时间有变
						if(!MyUtil.StringToStringDate(playerBo.getBanstartshow()).equals(playerVo.getBanstartshow())) {
							iflog=true;
						}else {
							iflog=false;
						}
						//封禁时间有变
					}else if(!MyUtil.StringToStringDate(playerBo.getBanstartshow()).equals(playerVo.getBanstartshow())
							|| !MyUtil.StringToStringDate(playerBo.getBanendshow()).equals(playerVo.getBanendshow())){
						iflog=true;
					}
					if(iflog) {
						PlayerBan playerBan=new PlayerBan();
						playerBan.setStartdate("".equals(playerBo.getBanstartshow())?new Date():MyUtil.StringToDate(playerBo.getBanstartshow()));
						if(playerBo.getBanif()==1) {
							playerBan.setEnddate(MyUtil.StringToDate(playerBo.getBanendshow()));
						}
						playerBan.setPlayerid(playerBo.getId());
						playerBan.setHandleid(playerhande.getId());
						playerBan.setReasonid(playerBo.getBanid());
						playerBanService.insertPlayerBan(playerBan);
					}
				}
			}
		}).start();
		Player player=new Player();
		player.setAdminif(playerBo.getAdminif());
		player.setBanif(playerBo.getBanif());
		UpdateWrapper<Player> update=new UpdateWrapper<Player>();
		//封禁中
		if(playerBo.getBanif()==1) {
			player.setBanstart(MyUtil.StringToDate(playerBo.getBanstartshow()));
			player.setBanend(MyUtil.StringToDate(playerBo.getBanendshow()));
		}else if(playerBo.getBanif()==2) {
			//永封
			player.setBanstart("".equals(playerBo.getBanstartshow())?new Date():MyUtil.StringToDate(playerBo.getBanstartshow()));
			update.set("banend", null);
		}else if(playerBo.getBanif()==0) {
			//将玩家账号恢复正常状态
			update.set("banstart", null);
			update.set("banend", null);
			update.set("banid", null);
		}
		if(playerBo.getBanif()>0) {
			player.setBanid(playerBo.getBanid());
		}
		update.eq("id", playerBo.getId());
		return playerMapper.update(player, update);
	}

	@Override
	public int clearPlayerLoc(String id) {
		if(id==null) {
			return 0;
		}
		UpdateWrapper<Player> update=new UpdateWrapper<Player>();
		update.eq("id", id);
		update.set("world", null);
		update.set("yaw", null);
		update.set("pitch", null);
		update.set("x", null);
		update.set("z", null);
		update.set("y", null);
		return playerMapper.update(new Player(), update);
	}

	/**
	 * 如果player为null，那则可以任何修改密码
	 */
	@Override
	public int changePlayerPass(PlayerBo playerBo,Player player) {
		//能否修改密码
		boolean flag=false;
		if(player!=null) {
			if(player.getAdminif()==1) {
				flag=true;
			}else if(player.getId().equals(playerBo.getId())) {
				flag=true;
			}
		}else {
			flag=true;
		}
		if(flag) {
			Player update=new Player();
			update.setId(playerBo.getId());
			update.setPassword(playerBo.getPassword());
			return playerMapper.updateById(update);
		}
		return 0;
	}

	@Override
	public List<PlayerVo> selectPlayerByRelo(Integer relo) {
		if(relo==null) {
			return null;
		}
		QueryWrapper<Player> query=new QueryWrapper<Player>();
		query.select("id","realname");
		query.eq("adminif", relo);
		List<PlayerVo> playerVos=new ArrayList<PlayerVo>();
		for(Player player:playerMapper.selectList(query)) {
			PlayerVo playerVo=new PlayerVo();
			playerVo.setId(player.getId());
			playerVo.setRealname(player.getRealname());
			playerVos.add(playerVo);
		}
		return playerVos;
	}

}
