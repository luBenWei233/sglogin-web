package org.sg.loginweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.PlayerBo;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.service.sys.SysDatabookService;

/**
* Player控制器层
* @author 帅哥
* @data 2022-06-21 05:08:04
 */

@Controller
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SysDatabookService sysDatabookService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@PostMapping(value = "/insertPlayerForPlayer")
	@ResponseBody
	public Integer insertPlayerForPlayer(HttpSession session,HttpServletRequest request,PlayerBo playerBo) {
		return playerService.insertPlayerForPlayer(session, request, playerBo);
	}
	
	@GetMapping(value = "/playerExist")
	@ResponseBody
	public Integer playerExist(String username) {
		return playerService.playerExist(username);
	}
	
	@GetMapping(value = "/selectPlayerByPage")
	public String selectPlayerByPage(Model mode,PlayerBo playerBo) {
		mode.addAttribute("playerBo", playerBo);
		mode.addAttribute("datas", playerService.selectPlayerByPage(playerBo));
		mode.addAttribute("active", 1);
		return "player/list";
	}
	
	// 玩家和管理员登陆
	@ResponseBody
	@PostMapping(value="/playerSign",produces = "application/json;charset=UTF-8")
	public Integer playerSign(HttpSession session,PlayerBo playerBo,String code) {
		return playerService.playerSign(session, playerBo,code);
	}
	
	@ResponseBody
	@PostMapping(value="/updatePlayerByid",produces = "application/json;charset=UTF-8")
	public Integer updatePlayerByid(HttpSession session,PlayerBo playerBo) {
		Player player=(Player)session.getAttribute("player");
		return playerService.updatePlayerByid(playerBo,player);
	}
	
	@GetMapping(value = "/selectPlayerById")
	public String selectPlayerById(Model mode,String id) {
		mode.addAttribute("sysDatabookBos", sysDatabookService.selectSysDatabookByList(2));
		mode.addAttribute("playerVo", playerService.selectPlayerById(id));
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache(
			"passlength1","passlength2","passlengthalert","passnotspace","pass","passalert"));
		mode.addAttribute("active", 1);
		return "player/update";
	}
	
	@ResponseBody
	@GetMapping(value = "/clearPlayerLoc")
	public Integer clearPlayerLoc(String id) {
		return playerService.clearPlayerLoc(id);
	}
	
	@GetMapping(value = "/addPage")
	public String clearPlayerLoc(Model mode) {
		//配置缓存
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache(
			"pass2title","passalert","passlength1","passlength2","passlengthalert","passtitle"
			,"playerlength1","playerlength2","playerlengthalert","playername","playernamealert","playertitle"
			,"regbtn","regfaildalert","regsuccessalert","regsuccessshow"
			,"result3","result4","result400","result401","result402","result408","successalert"
			,"syserror","pass","playerexist","passnotspace","checkpwd2","regvalidtitle","reglimittitle"
			));
		mode.addAttribute("active", 1);
		return "player/add";
	}
	
	@ResponseBody
	@PostMapping(value="/changePlayerPass",produces = "application/json;charset=UTF-8")
	public Integer changePlayerPass(HttpSession session,PlayerBo playerBo) {
		Player player=(Player)session.getAttribute("player");
		return playerService.changePlayerPass(playerBo,player);
	}
	
	@GetMapping(value = "/exitLogin")
	public String exitLogin(HttpSession session) {
		session.removeAttribute("player");
		return "redirect:/admin";
	}
	
}
