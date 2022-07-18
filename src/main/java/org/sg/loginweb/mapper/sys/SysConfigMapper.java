package org.sg.loginweb.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sg.loginweb.entity.sys.SysConfig;

/**
* SysConfig数据访问
* @author 帅哥
* @data 2022-06-21 05:20:52
 */

@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig>{
	public SysConfig selectSysConfigById(String id);
	public String getTableExists();
	public int createTableBySgLogin(String sql);
	public int insertInitData(String sql);
}
