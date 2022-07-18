package org.sg.loginweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sg.loginweb.entity.CourseSub;

/**
* CourseSub数据访问
* @author 帅哥
* @data 2022-06-21 05:16:30
 */

@Mapper
public interface CourseSubMapper extends BaseMapper<CourseSub>{
	public int changeCourseSubLoc(List<CourseSub> courseSubs);
}
