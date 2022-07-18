package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sg.loginweb.entity.IpLimit;

/**
* IpLimit业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:15:54
 */

public interface IpLimitService extends IService<IpLimit>{
	public int selectIpLimitByNowDayCount(String ip);
	public int insertIpLimit(IpLimit ipLimit);
	public int deleteIpLimitByOld();
}
