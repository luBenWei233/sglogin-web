package org.sg.loginweb.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.sg.loginweb.entity.bo.SysDatabookBo;
import org.sg.loginweb.entity.sys.SysDatabook;

/**
* SysDatabook数据访问
* @author 帅哥
* @data 2022-06-21 05:21:07
 */

@Mapper
public interface SysDatabookMapper extends BaseMapper<SysDatabook>{
	public IPage<SysDatabook> selectSysDatabookByCon(IPage<SysDatabook> page,SysDatabookBo sysDatabookBo);
}
