package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sg.loginweb.entity.PlayerBan;
import org.sg.loginweb.entity.bo.PlayerBanBo;
import org.sg.loginweb.entity.vo.PlayerBanVo;
import org.sg.loginweb.util.Result;

/**
* PlayerBan业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:10:23
 */

public interface PlayerBanService extends IService<PlayerBan>{
	public int insertPlayerBan(PlayerBan playerBan);
	public Result selectPlayerBanByPage(PlayerBanBo playerBanBo);
	public PlayerBanVo selectPlayerBanById(String id);
}
