package org.sg.loginweb.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.SysDatabookBo;
import org.sg.loginweb.service.sys.SysDatabookService;
import org.sg.loginweb.service.sys.SysGroupService;

/**
* SysDatabook控制器层
* @author 帅哥
* @data 2022-06-21 05:21:07
 */

@Controller
@RequestMapping("/sysDatabook")
public class SysDatabookController {

	@Autowired
	private SysDatabookService sysDatabookService;
	
	@Autowired
	private SysGroupService sysGroupService;
	
	@GetMapping(value="/addPage")
	public String addPage(Model mode) {
		mode.addAttribute("sysGroupVos", sysGroupService.getgetSysGroupVoByAll());
		mode.addAttribute("active", 6);
		return "sysDatabook/add";
	}
	
	@GetMapping(value="/sysDatabookList")
	public String sysDatabookList(Model mode,SysDatabookBo sysDatabookBo) {
		mode.addAttribute("sysGroupVos", sysGroupService.getgetSysGroupVoByAll());
		mode.addAttribute("datas", sysDatabookService.selectSysDatabookByPage(sysDatabookBo));
		mode.addAttribute("sysDatabookBo",sysDatabookBo);
		mode.addAttribute("active", 6);
		return "sysDatabook/list";
	}

	@ResponseBody
	@PostMapping(value="/addSysDatabook",produces = "application/json;charset=UTF-8")
	public Integer addSysDatabook(HttpSession session,SysDatabookBo sysDatabookBo) {
		Player player = (Player)session.getAttribute("player");
		sysDatabookBo.setCreateplayerid(player.getId());
		return sysDatabookService.insertSysDatabook(sysDatabookBo);
	}
	
	@GetMapping(value="/selectSysDatabookById")
	public String selectSysDatabookById(Model mode,Integer id) {
		mode.addAttribute("sysGroupVos", sysGroupService.getgetSysGroupVoByAll());
		mode.addAttribute("sysDatabookVo", sysDatabookService.selectSysDatabookById(id));
		mode.addAttribute("active", 6);
		return "sysDatabook/update";
	}
	
	@ResponseBody
	@PostMapping(value="/updateSysDatabook",produces = "application/json;charset=UTF-8")
	public Integer updateSysDatabook(HttpSession session,SysDatabookBo sysDatabookBo) {
		Player player = (Player)session.getAttribute("player");
		sysDatabookBo.setUpdateplayerid(player.getId());
		return sysDatabookService.updateSysDatabook(sysDatabookBo);
	}
	
	@ResponseBody
	@GetMapping(value="/deleteSysDatabookById")
	public Integer deleteSysDatabookById(Integer id) {
		return sysDatabookService.deleteSysDatabookById(id);
	}
}
