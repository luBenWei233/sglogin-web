package org.sg.loginweb.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.sg.loginweb.entity.bo.SysFileBo;
import org.sg.loginweb.entity.sys.SysFile;
import org.sg.loginweb.entity.vo.SysFileVo;
import org.sg.loginweb.mapper.sys.SysFileMapper;
import org.sg.loginweb.service.sys.SysFileService;
import org.sg.loginweb.util.MyEnum;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* SysFile业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:21:27
 */

@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper,SysFile> implements SysFileService{

	@Autowired
	private SysFileMapper sysFileMapper;

	@Override
	public Result insertSysFile(SysFileBo sysFileBo) {
		SysFile sysFile=new SysFile();
		sysFile.setFileid(UUID.randomUUID().toString());
		sysFile.setKeyid(sysFileBo.getKeyid());
		sysFile.setFilename(sysFileBo.getFilename());
		sysFile.setRealname(sysFileBo.getRealname().getBytes().length>300?sysFileBo.getRealname().substring(0, 140):sysFileBo.getRealname());
		sysFile.setPath(sysFileBo.getPath());
		sysFile.setUrl(sysFileBo.getUrl());
		sysFile.setServerid("default");
		sysFile.setCreatedate(new Date());
		sysFile.setCreateplayerid(sysFileBo.getCreateplayerid());
		return new Result(sysFileMapper.insert(sysFile),sysFile.getFileid());
	}

	@Override
	public Result selectSysFileByPage(SysFileBo sysFileBo) {
		if(sysFileBo.getCreatedate1()!=null && !"".equals(sysFileBo.getCreatedate1())) {
			sysFileBo.setCreatedate1_sql(MyUtil.StringToStringDate(sysFileBo.getCreatedate1()));
		}
		if(sysFileBo.getCreatedate2()!=null && !"".equals(sysFileBo.getCreatedate2())) {
			sysFileBo.setCreatedate2_sql(MyUtil.StringToStringDate(sysFileBo.getCreatedate2()));
		}
		IPage<SysFile> sysFiles = sysFileMapper.selectSysFileByPage(new Page<SysFile>(sysFileBo.getResPage(),sysFileBo.getResPageSize()), sysFileBo);
		List<SysFileVo> sysFileVos=new ArrayList<SysFileVo>();
		for(SysFile sysFile:sysFiles.getRecords()) {
			SysFileVo sysFileVo=new SysFileVo();
			sysFileVo.setFileid(sysFile.getFileid());
			sysFileVo.setFilename(sysFile.getFilename());
			sysFileVo.setServerid(sysFile.getServerid());
			sysFileVo.setCreatedate(MyUtil.DateToString(sysFile.getCreatedate()));
			sysFileVo.setCreateplayerid(sysFile.getCreateplayerid());
			sysFileVo.setCreateplayername(sysFile.getCreateplayername());
			sysFileVos.add(sysFileVo);
		}
		return new Result(sysFiles,sysFileVos);
	}

	@Override
	public SysFileVo selectSysFileById(String id) {
		SysFile sysFile=sysFileMapper.selectById(id);
		SysFileVo sysFileVo=new SysFileVo();
		sysFileVo.setFileid(sysFile.getFileid());
		sysFileVo.setFilename(sysFile.getFilename());
		sysFileVo.setServerid(sysFile.getServerid());
		sysFileVo.setCreatedate(MyUtil.DateToString(sysFile.getCreatedate()));
		sysFileVo.setKeyid(sysFile.getKeyid());
		sysFileVo.setPath(MyEnum.IMAGE_PATH.getValue()+sysFile.getPath());
		sysFileVo.setRealname(sysFile.getRealname());
		sysFileVo.setUrl(sysFile.getUrl()+sysFile.getPath());
		return sysFileVo;
	}

	@Override
	public int deleteSysFileById(String id) {
		return sysFileMapper.deleteById(id);
	}

}
