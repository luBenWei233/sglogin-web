package org.sg.loginweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sg.loginweb.entity.bo.PlayerLogBo;
import org.sg.loginweb.service.PlayerLogService;

/**
* PlayerLog控制器层
* @author 帅哥
* @data 2022-06-21 05:13:02
 */

@Controller
@RequestMapping("/playerLog")
public class PlayerLogController {

	@Autowired
	private PlayerLogService playerLogService;
	
	@GetMapping(value = "/selectPlayerLogByPage")
	public String selectPlayerLogByPage(Model mode,PlayerLogBo playerLogBo) {
		mode.addAttribute("playerLogBo", playerLogBo);
		mode.addAttribute("datas", playerLogService.selectPlayerLogByPage(playerLogBo));
		mode.addAttribute("active", 1);
		return "playerLog/list";
	}

}
