package org.sg.loginweb.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.sg.loginweb.entity.bo.SysFileBo;
import org.sg.loginweb.entity.sys.SysFile;

/**
* SysFile数据访问
* @author 帅哥
* @data 2022-06-21 05:21:27
 */

@Mapper
public interface SysFileMapper extends BaseMapper<SysFile>{
	public IPage<SysFile> selectSysFileByPage(IPage<SysFile> page,SysFileBo sysFileBo);
}
