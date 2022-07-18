package org.sg.loginweb.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

import org.sg.loginweb.entity.sys.SysConfig;
import org.sg.loginweb.util.Result;

/**
* SysConfig业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:20:52
 */

public interface SysConfigService extends IService<SysConfig>{
	
	//系统配置缓存
	public Map<String,String> sysConfigCache(String... key);
	public Result selectSysConfigByPage(SysConfig sysConfig);
	public int updateSysConfig(SysConfig sysConfig);
	public SysConfig selectSysConfigById(String id);
	public void getTableCreate();
	
}
