package org.sg.loginweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.sg.loginweb.entity.Course;
import org.sg.loginweb.entity.bo.CourseBo;
import org.sg.loginweb.entity.sys.SysDatabook;

/**
* Course数据访问
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

@Mapper
public interface CourseMapper extends BaseMapper<Course>{
	public Course selectCourseById(String id);
	public Course selectCourseInfoById(String id);
	public IPage<Course> selectCourseByPage(IPage<Course> page,CourseBo courseBo);
	public List<SysDatabook> selectAllCourseByHelp();
}
