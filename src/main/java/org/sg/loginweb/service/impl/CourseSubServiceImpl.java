package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.sg.loginweb.entity.CourseSub;
import org.sg.loginweb.entity.bo.CourseSubBo;
import org.sg.loginweb.mapper.CourseSubMapper;
import org.sg.loginweb.service.CourseSubService;
import org.sg.loginweb.util.Result;

/**
* CourseSub业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:16:30
 */

@Service
public class CourseSubServiceImpl extends ServiceImpl<CourseSubMapper,CourseSub> implements CourseSubService{

	@Autowired
	private CourseSubMapper courseSubMapper;

	@Override
	@Transactional
	public Result insertCourseSub(CourseSubBo courseSubBo) {
		if(courseSubBo.getId()!=null && !"".equals(courseSubBo.getId())) {
			return new Result(updateCourseSub(courseSubBo),courseSubBo.getId());
		}
		//位置调整
		QueryWrapper<CourseSub> query=new QueryWrapper<CourseSub>();
		query.select("id","sort").eq("courseid", courseSubBo.getCourseid());
		List<CourseSub> courseSubs = courseSubMapper.selectList(query);
		List<CourseSubBo> courseSubBos=new ArrayList<CourseSubBo>();
		for(CourseSub courseSub:courseSubs) {
			if(courseSub.getSort()>=courseSubBo.getSort()) {
				CourseSubBo courseSubBo2=new CourseSubBo();
				courseSubBo2.setId(courseSub.getId());
				courseSubBo2.setSort(courseSub.getSort()+1);
				courseSubBos.add(courseSubBo2);
			}
		}
		if(courseSubBos.size()!=0) {
			changeCourseSubLoc(courseSubBos);
		}
		CourseSub courseSub=new CourseSub();
		courseSub.setId(UUID.randomUUID().toString());
		courseSub.setContent(courseSubBo.getContent());
		courseSub.setType(courseSubBo.getType());
		courseSub.setSort(courseSubBo.getSort());
		courseSub.setCourseid(courseSubBo.getCourseid());
		return new Result(courseSubMapper.insert(courseSub),courseSub.getId());
	}

	@Override
	public int updateCourseSub(CourseSubBo courseSubBo) {
		CourseSub courseSub=new CourseSub();
		courseSub.setId(courseSubBo.getId());
		courseSub.setContent(courseSubBo.getContent());
		courseSub.setType(courseSubBo.getType());
		courseSub.setSort(courseSubBo.getSort());
		return courseSubMapper.updateById(courseSub);
	}

	@Override
	public int changeCourseSubLoc(List<CourseSubBo> courseSubBos) {
		List<CourseSub> courseSubs=new ArrayList<CourseSub>();
		for(CourseSubBo courseSubBo:courseSubBos) {
			CourseSub courseSub=new CourseSub();
			courseSub.setId(courseSubBo.getId());
			courseSub.setSort(courseSubBo.getSort());
			courseSubs.add(courseSub);
		}
		return courseSubMapper.changeCourseSubLoc(courseSubs);
	}

	@Override
	public int deleteCourseSubLoc(String id) {
		return courseSubMapper.deleteById(id);
	}

	@Override
	public int deleteCourseSubLocs(String id) {
		QueryWrapper<CourseSub> query=new QueryWrapper<CourseSub>();
		query.eq("courseid", id);
		return courseSubMapper.delete(query);
	}

}
