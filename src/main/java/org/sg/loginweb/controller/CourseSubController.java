package org.sg.loginweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.sg.loginweb.entity.bo.CourseSubBo;
import org.sg.loginweb.service.CourseSubService;
import org.sg.loginweb.util.Result;

/**
* CourseSub控制器层
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

@Controller
@RequestMapping("/courseSub")
public class CourseSubController {

	@Autowired
	private CourseSubService courseSubService;
	
	@ResponseBody
	@PostMapping(value="/insertCourseSub",produces = "application/json;charset=UTF-8")
	public Result insertCourseSub(Model mode,HttpSession session,CourseSubBo courseSubBo) {
		return courseSubService.insertCourseSub(courseSubBo);
	}
	
	@ResponseBody
	@PostMapping(value="/changeCourseSubLoc",produces = "application/json;charset=UTF-8")
	public int changeCourseSubLoc(Model mode,String[] id,Integer[] sort) {
		List<CourseSubBo> courseSubBos=new ArrayList<CourseSubBo>();
		for(int i=0;i<id.length;i++) {
			CourseSubBo courseSubBo=new CourseSubBo();
			courseSubBo.setId(id[i]);
			courseSubBo.setSort(sort[i]);
			courseSubBos.add(courseSubBo);
		}
		return courseSubService.changeCourseSubLoc(courseSubBos);
	}
	
	@ResponseBody
	@GetMapping(value="/deleteCourseSubLoc")
	public int deleteCourseSubLoc(String id) {
		return courseSubService.deleteCourseSubLoc(id);
	}
}
