package org.sg.loginweb.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.sg.loginweb.service.sys.SysFileBakService;

/**
* SysFileBak控制器层
* @author 帅哥
* @data 2022-06-21 05:21:51
 */

@SuppressWarnings("all")
@Controller
@RequestMapping("/SysFileBak")
public class SysFileBakController {
	
	@Autowired
	private SysFileBakService sysFileBakService;

}
