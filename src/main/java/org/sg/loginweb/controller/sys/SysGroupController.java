package org.sg.loginweb.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sg.loginweb.entity.bo.SysGroupBo;
import org.sg.loginweb.service.sys.SysGroupService;

/**
* SysGroup控制器层
* @author 帅哥
* @data 2022-06-21 05:22:18
 */

@Controller
@RequestMapping("/sysGroup")
public class SysGroupController {

	@Autowired
	private SysGroupService sysGroupService;
	
	@GetMapping(value = "/getSysGroupVoByListPage")
	public String getSysGroupVoByListPage(Model mode,SysGroupBo sysGroupBo) {
		mode.addAttribute("datas", sysGroupService.getSysGroupVoByListPage(sysGroupBo));
		mode.addAttribute("sysGroupBo", sysGroupBo);
		mode.addAttribute("active", 5);
		return "sysGroup/list";
	}
	
	@ResponseBody
	@PostMapping(value = "/updateSysGroupVoById")
	public Integer updateSysGroupVoById(SysGroupBo sysGroupBo) {
		return sysGroupService.updateSysGroupVoById(sysGroupBo);
	}
	
	@GetMapping(value = "/getSysGroupVoById")
	public String getgetSysGroupVoById(Model mode,Integer id) {
		mode.addAttribute("sysGroupVo", sysGroupService.getgetSysGroupVoById(id));
		mode.addAttribute("active", 5);
		return "sysGroup/update";
	}

}
