package org.sg.loginweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sg.loginweb.entity.bo.PlayerBanBo;
import org.sg.loginweb.service.PlayerBanService;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysDatabookService;

/**
* PlayerBan控制器层
* @author 帅哥
* @data 2022-06-21 05:10:23
 */

@Controller
@RequestMapping("/playerBan")
public class PlayerBanController {

	@Autowired
	private PlayerBanService playerBanService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SysDatabookService sysDatabookService;
	
	@GetMapping(value = "/selectPlayerBanByPage")
	public String selectPlayerBanByPage(Model mode,PlayerBanBo playerBanBo) {
		mode.addAttribute("playerBanBo", playerBanBo);
		mode.addAttribute("datas", playerBanService.selectPlayerBanByPage(playerBanBo));
		mode.addAttribute("playerVos", playerService.selectPlayerByRelo(1));
		mode.addAttribute("sysDatabookBos", sysDatabookService.selectSysDatabookByList(2));
		mode.addAttribute("active", 2);
		return "playerBan/list";
	}
	
	@GetMapping(value = "/selectPlayerBanById")
	public String selectPlayerBanById(Model mode,String id) {
		mode.addAttribute("playerBanVo", playerBanService.selectPlayerBanById(id));
		mode.addAttribute("active", 2);
		return "playerBan/update";
	}

}
