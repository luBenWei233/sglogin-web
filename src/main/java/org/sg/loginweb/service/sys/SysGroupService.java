package org.sg.loginweb.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.sg.loginweb.entity.bo.SysGroupBo;
import org.sg.loginweb.entity.sys.SysGroup;
import org.sg.loginweb.entity.vo.SysGroupVo;
import org.sg.loginweb.util.Result;

/**
* SysGroup业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:22:18
*/

public interface SysGroupService extends IService<SysGroup>{
	
	public Result getSysGroupVoByListPage(SysGroupBo sysGroupBo);
	
	public Integer updateSysGroupVoById(SysGroupBo sysGroupBo);
	
	public SysGroupVo getgetSysGroupVoById(Integer id);
	
	public List<SysGroupVo> getgetSysGroupVoByAll();
	
}
