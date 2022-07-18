package org.sg.loginweb.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;

import org.sg.loginweb.entity.bo.SysFileBo;
import org.sg.loginweb.entity.sys.SysFile;
import org.sg.loginweb.entity.vo.SysFileVo;
import org.sg.loginweb.util.Result;

/**
* SysFile业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:21:27
 */

public interface SysFileService extends IService<SysFile>{
	public Result insertSysFile(SysFileBo sysFileBo);
	public Result selectSysFileByPage(SysFileBo sysFileBo);
	public SysFileVo selectSysFileById(String id);
	public int deleteSysFileById(String id);
}
