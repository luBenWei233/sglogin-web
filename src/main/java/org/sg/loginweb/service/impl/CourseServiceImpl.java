package org.sg.loginweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.sg.loginweb.entity.Course;
import org.sg.loginweb.entity.CourseSub;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.CourseBo;
import org.sg.loginweb.entity.sys.SysDatabook;
import org.sg.loginweb.entity.vo.CourseSubVo;
import org.sg.loginweb.entity.vo.CourseVo;
import org.sg.loginweb.entity.vo.SysDatabookVo;
import org.sg.loginweb.mapper.CourseMapper;
import org.sg.loginweb.service.CourseService;
import org.sg.loginweb.service.CourseSubService;
import org.sg.loginweb.util.MyEnum;
import org.sg.loginweb.util.MyUtil;
import org.sg.loginweb.util.Result;

/**
* Course业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper,Course> implements CourseService{

	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CourseSubService courseSubService;

	@Override
	public Result insertCourse(CourseBo courseBo,Player player) {
		if(courseBo.getId()!=null && !"".equals(courseBo.getId().trim())) {
			return updateCourse(courseBo,player);
		}
		Course course=new Course();
		course.setCoursetype(courseBo.getCoursetype());
		course.setTitle(courseBo.getTitle());
		course.setValid(courseBo.getValid());
		course.setId(UUID.randomUUID().toString());
		course.setDefaultif(courseBo.getDefaultif());
		course.setCreateplayerid(player.getId());
		course.setCreatedate(new Date());
		course.setReleasedate(MyUtil.StringToDate(courseBo.getReleasedate()));
		return new Result(courseMapper.insert(course),course.getId());
	}

	@Override
	public CourseVo selectCourseById(String id) {
		CourseVo courseVo=new CourseVo();
		if(id==null) {
			courseVo.setId(null);
			courseVo.setValid(0);
			courseVo.setDefaultif(0);
			return courseVo;
		}
		Course course=courseMapper.selectCourseById(id);
		courseVo.setId(course.getId());
		courseVo.setTitle(course.getTitle());
		courseVo.setCoursetype(course.getCoursetype());
		courseVo.setCreatedate(course.getCreatedate());
		courseVo.setDefaultif(course.getDefaultif());
		courseVo.setCreateplayername(course.getCreateplayername());
		courseVo.setReleasedateT(MyUtil.DateToTString(course.getReleasedate()));
		courseVo.setValid(course.getValid());
		return courseVo;
	}

	@Override
	public CourseVo selectCourseInfoById(String id) {
		Course course = courseMapper.selectCourseInfoById(id);
		CourseVo courseVo=new CourseVo();
		courseVo.setId(course.getId());
		courseVo.setTitle(course.getTitle());
		courseVo.setDefaultif(course.getDefaultif());
		courseVo.setCoursetype(course.getCoursetype());
		courseVo.setCreatedate(course.getCreatedate());
		courseVo.setCreateplayername(course.getCreateplayername());
		courseVo.setReleasedateT(MyUtil.DateToTString(course.getReleasedate()));
		List<CourseSubVo> courseSubVos=new ArrayList<CourseSubVo>();
		for(CourseSub courseSub:course.getCourseSubs()) {
			CourseSubVo courseSubVo=new CourseSubVo();
			courseSubVo.setId(courseSub.getId());
			courseSubVo.setContent(courseSub.getContent());
			courseSubVo.setType(courseSub.getType());
			courseSubVo.setSort(courseSub.getSort());
			courseSubVo.setCourseid(courseSub.getCourseid());
			if(courseSub.getImgpath()!=null) {
				courseSubVo.setImgpath(MyEnum.IMAGE_PATH.getValue()+courseSub.getImgpath());
			}
			courseSubVos.add(courseSubVo);
		}
		courseVo.setCourseSubVos(courseSubVos);
		return courseVo;
	}

	@Override
	public Result selectCourseByPage(CourseBo courseBo) {
		if(courseBo.getReleasedate1()!=null && !"".equals(courseBo.getReleasedate1())) {
			courseBo.setReleasedate1_sql(MyUtil.StringToStringDate(courseBo.getReleasedate1()));
		}
		if(courseBo.getReleasedate2()!=null && !"".equals(courseBo.getReleasedate2())) {
			courseBo.setReleasedate2_sql(MyUtil.StringToStringDate(courseBo.getReleasedate2()));
		}
		IPage<Course> courses = courseMapper.selectCourseByPage(new Page<Course>(courseBo.getResPage(),courseBo.getResPageSize()), courseBo);
		List<CourseVo> courseVos=new ArrayList<CourseVo>();
		for(Course course:courses.getRecords()) {
			CourseVo courseVo=new CourseVo();
			courseVo.setId(course.getId());
			courseVo.setDefaultif(course.getDefaultif());
			courseVo.setTitle(course.getTitle());
			courseVo.setCoursetypename(course.getCoursetypename());
			courseVo.setCreatedate(course.getCreatedate());
			courseVo.setCreateplayerid(course.getCreateplayerid());
			courseVo.setCreateplayername(course.getCreateplayername());
			courseVo.setValidname(course.getValid()==1?"已发布":"未发布");
			courseVo.setReleasedate(MyUtil.DateToString(course.getReleasedate()));
			courseVos.add(courseVo);
		}
		return new Result(courses,courseVos);
	}

	@Transactional
	@Override
	public Result updateCourse(CourseBo courseBo, Player player) {
		if(courseBo.getDefaultif()!=null && courseBo.getDefaultif()==1) {
			UpdateWrapper<Course> update=new UpdateWrapper<Course>();
			update.set("defaultif",0).eq("defaultif",1);
			courseMapper.update(new Course(), update);
		}
		Course course=new Course();
		course.setCoursetype(courseBo.getCoursetype());
		course.setTitle(courseBo.getTitle());
		course.setValid(courseBo.getValid());
		course.setId(courseBo.getId());
		course.setDefaultif(courseBo.getDefaultif());
		course.setUpdateplayerid(player.getId());
		course.setUpdatedate(new Date());
		if(courseBo.getReleasedate()!=null && !"".equals(courseBo.getReleasedate())) {
			course.setReleasedate(MyUtil.StringToDate(courseBo.getReleasedate()));
		}
		return new Result(courseMapper.updateById(course),courseBo.getId());
	}

	@Override
	@Transactional
	public int deleteCourse(String id) {
		int i=courseMapper.deleteById(id);
		return courseSubService.deleteCourseSubLocs(id)+i;
	}

	@Override
	public List<SysDatabookVo> selectAllCourseByHelp() {
		List<SysDatabook> sysDatabooks = courseMapper.selectAllCourseByHelp();
		List<SysDatabookVo> sysDatabookVos=new ArrayList<SysDatabookVo>();
		for(SysDatabook sysDatabook:sysDatabooks) {
			SysDatabookVo sysDatabookVo=new SysDatabookVo();
			sysDatabookVo.setValue(sysDatabook.getValue());
			List<Course> courses=new ArrayList<Course>();
			for(Course course:sysDatabook.getCourses()) {
				CourseVo CourseVo=new CourseVo();
				CourseVo.setId(course.getId());
				CourseVo.setTitle(course.getTitle());
				courses.add(course);
			}
			sysDatabookVo.setCourses(courses);
			sysDatabookVos.add(sysDatabookVo);
		}
		return sysDatabookVos;
	}

	@Override
	public CourseVo selectCourseInfoByIdForPlayer(Player player,String id) {
		Course course = courseMapper.selectCourseInfoById(id);
		if(course==null) {
			return null;
		}
		if(course.getValid()==0) {
			if(player==null || player.getAdminif()!=1) {
				return null;
			}
		}
		CourseVo courseVo=new CourseVo();
		courseVo.setId(course.getId());
		courseVo.setTitle(course.getTitle());
		courseVo.setCoursetype(course.getCoursetype());
		courseVo.setReleasedate(MyUtil.DateToString(course.getReleasedate()));
		StringBuffer buffer=new StringBuffer();
		for(CourseSub courseSub:course.getCourseSubs()) {
			if(courseSub.getType()==1) {
				buffer.append("<div class='showDiv'>"+courseSub.getContent()+"</div>");
			} else {
				buffer.append(MyUtil.imageToHtml(MyEnum.IMAGE_PATH.getValue()+courseSub.getImgpath()));
			}
		}
		courseVo.setCoursecontent(buffer.toString());
		return courseVo;
	}

}
