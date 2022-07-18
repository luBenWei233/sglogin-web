package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import org.sg.loginweb.entity.CourseSub;
import org.sg.loginweb.entity.bo.CourseSubBo;
import org.sg.loginweb.util.Result;

/**
* CourseSub业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:16:30
 */

public interface CourseSubService extends IService<CourseSub>{
	public Result insertCourseSub(CourseSubBo courseSubBo);
	public int updateCourseSub(CourseSubBo courseSubBo);
	public int changeCourseSubLoc(List<CourseSubBo> courseSubBos);
	public int deleteCourseSubLoc(String id);
	public int deleteCourseSubLocs(String id);
}
