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
import org.sg.loginweb.entity.sys.SysConfig;
import org.sg.loginweb.service.sys.SysConfigService;

/**
* SysConfig控制器层
* @author 帅哥
* @data 2022-06-21 05:20:52
 */

@Controller
@RequestMapping("/sysConfig")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	
	@GetMapping(value="/selectSysConfigByPage")
	public String selectSysConfigByPage(Model mode,SysConfig sysConfig) {
		mode.addAttribute("sysConfig", sysConfig);
		mode.addAttribute("datas", sysConfigService.selectSysConfigByPage(sysConfig));
		mode.addAttribute("active", 8);
		return "sysConfig/list";
	}
	
	@GetMapping(value="/selectSysConfigById")
	public String selectSysConfigById(Model mode,String id) {
		mode.addAttribute("sysConfig", sysConfigService.selectSysConfigById(id));
		mode.addAttribute("active", 8);
		return "sysConfig/update";
	}
	
	@ResponseBody
	@PostMapping(value="/updateSysConfig")
	public int updateSysConfig(HttpSession session,SysConfig sysConfig) {
		Player player=(Player)session.getAttribute("player");
		sysConfig.setUpdateplayerid(player.getId());
		return sysConfigService.updateSysConfig(sysConfig);
	}
	
	@GetMapping(value="/system")
	public String system(Model mode) {
		mode.addAttribute("active", 9);
		return "sysConfig/system";
	}

}
