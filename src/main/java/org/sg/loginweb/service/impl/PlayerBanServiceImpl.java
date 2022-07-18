package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.sg.loginweb.entity.PlayerBan;
import org.sg.loginweb.entity.bo.PlayerBanBo;
import org.sg.loginweb.entity.vo.PlayerBanVo;
import org.sg.loginweb.mapper.PlayerBanMapper;
import org.sg.loginweb.service.PlayerBanService;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* PlayerBan业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:10:23
 */

@Service
public class PlayerBanServiceImpl extends ServiceImpl<PlayerBanMapper,PlayerBan> implements PlayerBanService{

	@Autowired
	private PlayerBanMapper playerBanMapper;

	@Override
	public int insertPlayerBan(PlayerBan playerBan) {
		playerBan.setBantime(MyUtil.getTwoDateTimes(playerBan.getStartdate(), playerBan.getEnddate()));
		playerBan.setCreatedate(new Date());
		playerBan.setId(UUID.randomUUID().toString());
		return playerBanMapper.insert(playerBan);
	}

	@Override
	public Result selectPlayerBanByPage(PlayerBanBo playerBanBo) {
		if(playerBanBo.getCreatedate1()!=null && !"".equals(playerBanBo.getCreatedate1())) {
			playerBanBo.setCreatedate1_sql(MyUtil.StringToStringDate(playerBanBo.getCreatedate1()));
		}
		if(playerBanBo.getCreatedate2()!=null && !"".equals(playerBanBo.getCreatedate2())) {
			playerBanBo.setCreatedate2_sql(MyUtil.StringToStringDate(playerBanBo.getCreatedate2()));
		}
		IPage<PlayerBan> playerBans = playerBanMapper.selectPlayerBanByPage(new Page<PlayerBan>(playerBanBo.getResPage(),playerBanBo.getResPageSize()), playerBanBo);
		List<PlayerBanVo> playerBanVos=new ArrayList<PlayerBanVo>();
		for(PlayerBan playerBan:playerBans.getRecords()) {
			playerBanVos.add(playerBanToPlayerBanVo(playerBan));
		}
		return new Result(playerBans,playerBanVos);
	}

	@Override
	public PlayerBanVo selectPlayerBanById(String id) {
		return playerBanToPlayerBanVo(playerBanMapper.selectPlayerBanById(id));
	}
	
	// PlayerBan转PlayerBanVo
	private PlayerBanVo playerBanToPlayerBanVo(PlayerBan playerBan) {
		PlayerBanVo playerBanVo=new PlayerBanVo();
		playerBanVo.setId(playerBan.getId());
		playerBanVo.setPlayerid(playerBan.getPlayerid());
		playerBanVo.setPlayeridshow(playerBan.getPlayeridshow());
		playerBanVo.setHandleid(playerBan.getHandleid());
		playerBanVo.setHandleidshow(playerBan.getHandleidshow());
		playerBanVo.setStartdate(playerBan.getStartdate()!=null?MyUtil.DateToString(playerBan.getStartdate()):null);
		playerBanVo.setEnddate(playerBan.getEnddate()!=null?MyUtil.DateToString(playerBan.getEnddate()):null);
		playerBanVo.setBantime(playerBan.getBantime());
		playerBanVo.setReasonid(playerBan.getReasonid());
		playerBanVo.setReasonidshow(playerBan.getReasonidshow());
		playerBanVo.setCreatedate(playerBan.getCreatedate()!=null?MyUtil.DateToString(playerBan.getCreatedate()):null);
		return playerBanVo;
	}

}
