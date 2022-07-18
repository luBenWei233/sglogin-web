package org.sg.loginweb.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.sg.loginweb.entity.bo.SysGroupBo;
import org.sg.loginweb.entity.sys.SysGroup;
import org.sg.loginweb.entity.vo.SysGroupVo;
import org.sg.loginweb.mapper.sys.SysGroupMapper;
import org.sg.loginweb.service.sys.SysGroupService;
import org.sg.loginweb.util.Result;

/**
* SysGroup业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:22:18
 */

@Service
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper,SysGroup> implements SysGroupService{

	@Autowired
	private SysGroupMapper sysGroupMapper;

	@Override
	public Result getSysGroupVoByListPage(SysGroupBo sysGroupBo) {
		IPage<SysGroup> page = new Page<>(sysGroupBo.getResPage(),sysGroupBo.getResPageSize());
		QueryWrapper<SysGroup> wrapper=new QueryWrapper<SysGroup>();
		if(sysGroupBo.getValue()!=null && !"".equals(sysGroupBo.getValue())) {
			wrapper.like("value", sysGroupBo.getValue());
		}
		wrapper.orderByAsc("sort");
		IPage<SysGroup> selectPage = sysGroupMapper.selectPage(page, wrapper);
		List<SysGroupVo> sysGroupVos=new ArrayList<SysGroupVo>();
		for(SysGroup sys:selectPage.getRecords()) {
			sysGroupVos.add(sysGroupToSysGroupVo(sys));
		}
		return new Result(selectPage,sysGroupVos);
	}

	@Override
	public Integer updateSysGroupVoById(SysGroupBo sysGroupBo) {
		return sysGroupMapper.updateById(sysGroupBoToSysGroup(sysGroupBo));
	}
	
	@Override
	public SysGroupVo getgetSysGroupVoById(Integer id) {
		return sysGroupToSysGroupVo(sysGroupMapper.selectById(id));
	}

	@Override
	public List<SysGroupVo> getgetSysGroupVoByAll() {
		List<SysGroupVo> sysGroupVos=new ArrayList<SysGroupVo>();
		for(SysGroup sysGroup:sysGroupMapper.selectList(new QueryWrapper<SysGroup>())) {
			sysGroupVos.add(sysGroupToSysGroupVo(sysGroup));
		}
		return sysGroupVos;
	}
	
	//SysGroup转SysGroupVo
	private SysGroupVo sysGroupToSysGroupVo(SysGroup sysGroup) {
		SysGroupVo sysGroupVo=new SysGroupVo();
		sysGroupVo.setId(sysGroup.getId());
		sysGroupVo.setValue(sysGroup.getValue());
		sysGroupVo.setSort(sysGroup.getSort());
		return sysGroupVo;
	}
	
	//SysGroupBo转SysGroup
	private SysGroup sysGroupBoToSysGroup(SysGroupBo sysGroupBo) {
		SysGroup sysGroup=new SysGroup();
		sysGroup.setId(sysGroupBo.getId());
		sysGroup.setValue(sysGroupBo.getValue());
		sysGroup.setSort(sysGroupBo.getSort());
		return sysGroup;
	}
}
