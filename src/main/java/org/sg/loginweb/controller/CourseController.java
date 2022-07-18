package org.sg.loginweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.CourseBo;
import org.sg.loginweb.entity.vo.CourseVo;
import org.sg.loginweb.service.CourseService;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.service.sys.SysDatabookService;
import org.sg.loginweb.util.Result;

/**
* Course控制器层
* @author 帅哥
* @data 2022-06-21 05:16:43
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SysDatabookService sysDatabookService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@GetMapping(value = "/addPage")
	public String addPage(Model mode,String id) {
		mode.addAttribute("sysDatabookBos", sysDatabookService.selectSysDatabookByList(1));
		mode.addAttribute("courseVo", courseService.selectCourseById(id));
		mode.addAttribute("active", 3);
		return "course/add";
	}
	
	@ResponseBody
	@PostMapping(value="/insertCourse",produces = "application/json;charset=UTF-8")
	public Result insertCourse(Model mode,HttpSession session,CourseBo courseBo) {
		Player player=(Player)session.getAttribute("player");
		return courseService.insertCourse(courseBo,player);
	}
	
	@GetMapping(value = "/addNext")
	public String editPage(Model mode,String id) {
		mode.addAttribute("courseVo", courseService.selectCourseInfoById(id));
		mode.addAttribute("active", 3);
		return "course/addNext";
	}
	
	@GetMapping(value = "/selectCourseByPage")
	public String selectCourseByPage(Model mode,CourseBo courseBo) {
		mode.addAttribute("courseBo",courseBo);
		mode.addAttribute("datas", courseService.selectCourseByPage(courseBo));
		mode.addAttribute("playerVos", playerService.selectPlayerByRelo(1));
		mode.addAttribute("sysDatabookBos", sysDatabookService.selectSysDatabookByList(1));
		mode.addAttribute("active", 3);
		return "course/list";
	}
	
	@ResponseBody
	@GetMapping(value = "/deleteCourse")
	public Integer deleteCourse(String id) {
		return courseService.deleteCourse(id);
	}
	
	@GetMapping(value = "/help")
	public String help(Model mode,String id) {
		mode.addAttribute("datas", courseService.selectAllCourseByHelp());
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache(
				"releasedate","helppagename","helpmenu1","helpmenu2","helpmenu3",
				"webname","syserror","helpmenuwidth","clipboardnot","copyright"
				));
		return "course/help";
	}
	
	@ResponseBody
	@GetMapping(value = "/selectCourseInfoByIdForPlayer")
	public CourseVo selectCourseInfoByIdForPlayer(HttpSession session,String id) {
		Player player=(Player)session.getAttribute("player");
		return courseService.selectCourseInfoByIdForPlayer(player,id);
	}
	
}
