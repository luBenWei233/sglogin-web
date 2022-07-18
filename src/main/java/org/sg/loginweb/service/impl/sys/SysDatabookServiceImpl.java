package org.sg.loginweb.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sg.loginweb.entity.bo.SysDatabookBo;
import org.sg.loginweb.entity.sys.SysDatabook;
import org.sg.loginweb.entity.vo.SysDatabookVo;
import org.sg.loginweb.mapper.sys.SysDatabookMapper;
import org.sg.loginweb.service.sys.SysDatabookService;
import org.sg.loginweb.util.Result;

/**
* SysDatabook业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:21:07
 */

@Service
public class SysDatabookServiceImpl extends ServiceImpl<SysDatabookMapper,SysDatabook> implements SysDatabookService{

	@Autowired
	private SysDatabookMapper sysDatabookMapper;

	@Override
	public Integer insertSysDatabook(SysDatabookBo sysDatabookBo) {
		SysDatabook sysDatabook=sysDatabookBoToSysDatabook(sysDatabookBo);
		sysDatabook.setCreateplayerid(sysDatabookBo.getCreateplayerid());
		sysDatabook.setCreatedate(new Date());
		return sysDatabookMapper.insert(sysDatabook);
	}
	
	@Override
	public Result selectSysDatabookByPage(SysDatabookBo sysDatabookBo) {
		IPage<SysDatabook> sysDatabooks = sysDatabookMapper.selectSysDatabookByCon(new Page<SysDatabook>(sysDatabookBo.getResPage(),sysDatabookBo.getResPageSize()),sysDatabookBo);
		List<SysDatabookVo> sysDatabookVos=new ArrayList<SysDatabookVo>();
		for(SysDatabook sysDatabook:sysDatabooks.getRecords()) {
			sysDatabookVos.add(sysDatabookToSysDatabookVo(sysDatabook));
		}
		return new Result(sysDatabooks,sysDatabookVos);
	}
	
	@Override
	public SysDatabookVo selectSysDatabookById(Integer id) {
		return sysDatabookToSysDatabookVo(sysDatabookMapper.selectById(id));
	}

	@Override
	public Integer updateSysDatabook(SysDatabookBo sysDatabookBo) {
		SysDatabook sysDatabook=sysDatabookBoToSysDatabook(sysDatabookBo);
		sysDatabook.setUpdateplayerid(sysDatabookBo.getUpdateplayerid());
		sysDatabook.setUpdatedate(new Date());
		return sysDatabookMapper.updateById(sysDatabook);
	}

	@Override
	public Integer deleteSysDatabookById(Integer id) {
		return sysDatabookMapper.deleteById(id);
	}
	
	@Override
	public List<SysDatabookVo> selectSysDatabookByList(Integer groupid) {
		QueryWrapper<SysDatabook> query=new QueryWrapper<SysDatabook>();
		query.eq("groupid", groupid).eq("valid", 1);
		List<SysDatabookVo> sysDatabookVos=new ArrayList<SysDatabookVo>();
		for(SysDatabook sysDatabook:sysDatabookMapper.selectList(query)) {
			SysDatabookVo sysDatabookVo=new SysDatabookVo();
			sysDatabookVo.setId(sysDatabook.getId());
			sysDatabookVo.setValue(sysDatabook.getValue());
			sysDatabookVos.add(sysDatabookVo);
		}
		return sysDatabookVos;
	}
	
	//SysDatabookBo转SysDatabook
	private SysDatabook sysDatabookBoToSysDatabook(SysDatabookBo sysDatabookBo) {
		SysDatabook sysDatabook=new SysDatabook();
		sysDatabook.setId(sysDatabookBo.getId());
		sysDatabook.setGroupid(sysDatabookBo.getGroupid());
		sysDatabook.setValue(sysDatabookBo.getValue());
		sysDatabook.setValid(sysDatabookBo.getValid());
		sysDatabook.setParentid(sysDatabookBo.getParentid());
		return sysDatabook;
	}
	
	//SysDatabook转SysDatabookVo
	private SysDatabookVo sysDatabookToSysDatabookVo(SysDatabook sysDatabook) {
		SysDatabookVo sysDatabookVo=new SysDatabookVo();
		sysDatabookVo.setId(sysDatabook.getId());
		sysDatabookVo.setGroupid(sysDatabook.getGroupid());
		sysDatabookVo.setValue(sysDatabook.getValue());
		sysDatabookVo.setValid(sysDatabook.getValid());
		sysDatabookVo.setParentid(sysDatabook.getParentid());
		sysDatabookVo.setCreatedate(sysDatabook.getCreatedate());
		sysDatabookVo.setCreatename(sysDatabook.getCreatename());
		sysDatabookVo.setSysgroupid(sysDatabook.getSysgroupid());
		sysDatabookVo.setSysgroupvalue(sysDatabook.getSysgroupvalue());
		if(sysDatabook.getValid()!=null) {
			sysDatabookVo.setValidshow(sysDatabook.getValid()==1?"显示":"隐藏");
		}
		return sysDatabookVo;
	}
}
