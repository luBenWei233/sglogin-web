package org.sg.loginweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.sg.loginweb.service.sys.SysConfigService;
import org.sg.loginweb.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@RequestMapping(value="/")
	public String index(Model mode) {
		//配置缓存
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache(
				"checkpwd2","code","codetitle","contact","faildalert1","faildalert2","helpbtn"
				,"pass2title","passalert","passlength1","passlength2","passlengthalert","passtitle"
				,"playerlength1","playerlength2","playerlengthalert","playername","playernamealert","playertitle"
				,"regbtn","regfaildalert","regpagename","regsuccessalert","regsuccessshow"
				,"result3","result4","result400","result401","result402","result408","successalert"
				,"syserror","webname","joinqq","pass","playerexist","passnotspace","regvalidtitle","reglimittitle"
				));
		return "index";
	}
	
	@RequestMapping(value="/admin")
	public String admin(Model mode,HttpServletRequest request) {
		mode.addAttribute("ip", MyUtil.getIpAddress(request));
		return "admin";
	}
}
