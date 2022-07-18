package org.sg.loginweb.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import org.sg.loginweb.entity.bo.SysDatabookBo;
import org.sg.loginweb.entity.sys.SysDatabook;
import org.sg.loginweb.entity.vo.SysDatabookVo;
import org.sg.loginweb.util.Result;

/**
* SysDatabook业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:21:07
 */

public interface SysDatabookService extends IService<SysDatabook>{
	
	public Integer insertSysDatabook(SysDatabookBo sysDatabookBo);
	
	public Result selectSysDatabookByPage(SysDatabookBo sysDatabookBo);
	
	public SysDatabookVo selectSysDatabookById(Integer id);
	
	public Integer updateSysDatabook(SysDatabookBo sysDatabookBo);
	
	public Integer deleteSysDatabookById(Integer id);
	
	public List<SysDatabookVo> selectSysDatabookByList(Integer groupid);
	
}
