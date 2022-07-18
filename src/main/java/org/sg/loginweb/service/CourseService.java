package org.sg.loginweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import org.sg.loginweb.entity.Course;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.CourseBo;
import org.sg.loginweb.entity.vo.CourseVo;
import org.sg.loginweb.entity.vo.SysDatabookVo;
import org.sg.loginweb.util.Result;

/**
* Course业务逻辑接口
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

public interface CourseService extends IService<Course>{
	public Result insertCourse(CourseBo courseBo,Player player);
	public CourseVo selectCourseById(String id);
	public CourseVo selectCourseInfoById(String id);
	public CourseVo selectCourseInfoByIdForPlayer(Player player,String id);
	public Result selectCourseByPage(CourseBo courseBo);
	public Result updateCourse(CourseBo courseBo,Player player);
	public int deleteCourse(String id);
	public List<SysDatabookVo> selectAllCourseByHelp();
}
