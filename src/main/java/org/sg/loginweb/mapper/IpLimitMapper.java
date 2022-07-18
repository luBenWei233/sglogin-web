package org.sg.loginweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sg.loginweb.entity.IpLimit;

/**
* IpLimit数据访问
* @author 帅哥
* @data 2022-06-21 05:15:54
 */

@Mapper
public interface IpLimitMapper extends BaseMapper<IpLimit>{
	public int selectIpLimitByNowDayCount(String ip,String dataBaseType,int gmt);
	public int deleteIpLimitByOld(String dataBaseType,int gmt);
}
