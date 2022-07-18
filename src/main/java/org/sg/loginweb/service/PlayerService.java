package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.PlayerBo;
import org.sg.loginweb.entity.vo.PlayerVo;
import org.sg.loginweb.util.Result;

/**
* Player业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:08:04
 */

public interface PlayerService extends IService<Player>{
	//玩家注册
	public int insertPlayerForPlayer(HttpSession session,HttpServletRequest request,PlayerBo playerBo);
	//玩家是否存在
	public int playerExist(String username);
	//玩家登陆
	public int playerSign(HttpSession session,PlayerBo playerBo,String code);
	//玩家列表查询
	public Result selectPlayerByPage(PlayerBo playerBo);
	//玩家详情
	public PlayerVo selectPlayerById(String id);
	//玩家角色、封号修改
	public int updatePlayerByid(PlayerBo playerBo,Player playerhande);
	//清理玩家坐标
	public int clearPlayerLoc(String id);
	//密码修改
	public int changePlayerPass(PlayerBo playerBo,Player player);
	//获取同类型用户，最好是查询管理员，不然全表查，很慢
	public List<PlayerVo> selectPlayerByRelo(Integer relo);
}
