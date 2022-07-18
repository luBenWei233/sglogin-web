package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import org.sg.loginweb.entity.IpLimit;
import org.sg.loginweb.mapper.IpLimitMapper;
import org.sg.loginweb.service.IpLimitService;
import org.sg.loginweb.util.MyUtil;

/**
* IpLimit业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:15:54
*/

@Service
public class IpLimitServiceImpl extends ServiceImpl<IpLimitMapper,IpLimit> implements IpLimitService{

	@Autowired
	private IpLimitMapper ipLimitMapper;

	@Override
	public int selectIpLimitByNowDayCount(String ip) {
		return ipLimitMapper.selectIpLimitByNowDayCount(ip,MyUtil.getDataBaseType(),MyUtil.getGmt());
	}

	@Override
	public int insertIpLimit(IpLimit ipLimit) {
		ipLimit.setRegdate(new Date());
		return ipLimitMapper.insert(ipLimit);
	}

	@Override
	public int deleteIpLimitByOld() {
		return ipLimitMapper.deleteIpLimitByOld(MyUtil.getDataBaseType(),MyUtil.getGmt());
	}

}
