package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sg.loginweb.entity.PlayerLog;
import org.sg.loginweb.entity.bo.PlayerLogBo;
import org.sg.loginweb.util.Result;

/**
* PlayerLog业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:13:02
 */

public interface PlayerLogService extends IService<PlayerLog>{
	
	public Result selectPlayerLogByPage(PlayerLogBo playerLogBo);

}
