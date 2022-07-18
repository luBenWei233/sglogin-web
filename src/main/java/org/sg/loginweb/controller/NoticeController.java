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
import org.sg.loginweb.entity.bo.NoticeBo;
import org.sg.loginweb.service.NoticeService;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysConfigService;

/**
* Notice控制器层
* @author 帅哥
* @data 2022-06-21 05:15:22
 */

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@GetMapping(value="/addPage")
	public String addPage(Model mode) {
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache("releasedate"));
		mode.addAttribute("active", 4);
		return "notice/add";
	}
	
	@ResponseBody
	@PostMapping(value="/insertNotice")
	public int insertNotice(HttpSession session,NoticeBo noticeBo) {
		Player player=(Player)session.getAttribute("player");
		noticeBo.setCreateplayerid(player.getId());
		return noticeService.insertNotice(noticeBo);
	}
	
	@ResponseBody
	@PostMapping(value="/updateNotice")
	public int updateNotice(HttpSession session,NoticeBo noticeBo) {
		Player player=(Player)session.getAttribute("player");
		noticeBo.setUpdateplayerid(player.getId());
		return noticeService.updateNotice(noticeBo);
	}
	
	@GetMapping(value="/selectNoticeByPage")
	public String selectNoticeByPage(Model mode,NoticeBo noticeBo) {
		mode.addAttribute("playerVos", playerService.selectPlayerByRelo(1));
		mode.addAttribute("noticeBo", noticeBo);
		mode.addAttribute("datas", noticeService.selectNoticeByPage(noticeBo));
		mode.addAttribute("active", 4);
		return "notice/list";
	}
	
	@GetMapping(value="/selectNoticeById")
	public String selectNoticeById(Model mode,String id) {
		mode.addAttribute("noticeVo", noticeService.selectNoticeById(id));
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache("releasedate"));
		mode.addAttribute("active", 4);
		return "notice/update";
	}
	
	@ResponseBody
	@GetMapping(value="/deleteNotice")
	public int deleteNotice(String id) {
		return noticeService.deleteNotice(id);
	}
	
	@GetMapping(value="/noticePage")
	public String noticePage(Model mode,String id) {
		mode.addAttribute("id",id);
		mode.addAttribute("pageCache", sysConfigService.sysConfigCache(
				"noticenotmore","noticepagename","noticemore","webname","syserror","copyright"
				));
		return "notice/notice";
	}
	
	@ResponseBody
	@GetMapping(value="/noticeShow")
	public String noticePage(HttpSession session,Integer page,String id) {
		Player player=(Player)session.getAttribute("player");
		return noticeService.noticeShow(player, page, id);
	}

}
