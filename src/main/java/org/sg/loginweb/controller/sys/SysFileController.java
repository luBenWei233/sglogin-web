package org.sg.loginweb.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sg.loginweb.entity.Player;
import org.sg.loginweb.entity.bo.SysFileBo;
import org.sg.loginweb.service.PlayerService;
import org.sg.loginweb.service.sys.SysFileService;
import org.sg.loginweb.util.Result;

/**
* SysFile控制器层
* @author 帅哥
* @data 2022-06-21 05:21:27
 */

@Controller
@RequestMapping("/sysFile")
public class SysFileController {

	@Autowired
	private SysFileService sysFileService;
	
	@Autowired
	private PlayerService playerService;
	
	@Value("${custom.root}")
	private String fileSavePath;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
	//公用的文件上传
	@ResponseBody
	@PostMapping(value="/insertSysFile")
	public Result insertSysFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,HttpSession session) {
		String filePath = "";
        String format = sdf.format(new Date());
        String oldName=null;
        String newName=null;
        File folder = new File(fileSavePath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
            oldName = file.getOriginalFilename();
            newName = UUID.randomUUID().toString() +
                    oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
            	file.transferTo(new File(folder, newName));
                filePath = request.getScheme() + "://" + request.getServerName() + ":" +
                		request.getServerPort() + "/uploadFile/" + format + newName;
            } catch (IOException e) {
                e.printStackTrace();
                return new Result(0,"后台发生异常，请检查！");
            }
        }
        oldName = file.getOriginalFilename();
        newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
        	file.transferTo(new File(folder, newName));
            filePath = format + newName;
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(0,"后台发生异常，请检查！");
        }
        SysFileBo sySFileBo=new SysFileBo();
        sySFileBo.setKeyid(UUID.randomUUID().toString());
        sySFileBo.setFilename(oldName);
        sySFileBo.setRealname(newName);
        sySFileBo.setPath(filePath);
        sySFileBo.setUrl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/");
        Player player=(Player)session.getAttribute("player");
        if(player!=null) {
        	sySFileBo.setCreateplayerid(player.getId());
        }
		return sysFileService.insertSysFile(sySFileBo);
	}
	
	@GetMapping(value="selectSysFileByPage")
	public String selectSysFileByPage(Model mode,SysFileBo sysFileBo) {
		mode.addAttribute("sysFileBo", sysFileBo);
		mode.addAttribute("datas", sysFileService.selectSysFileByPage(sysFileBo));
		mode.addAttribute("playerVos", playerService.selectPlayerByRelo(1));
		mode.addAttribute("active", 7);
		return "sysFile/list";
	}
	
	@GetMapping(value="selectSysFileById")
	public String selectSysFileById(Model mode,String id) {
		mode.addAttribute("sysFileVo", sysFileService.selectSysFileById(id));
		mode.addAttribute("active", 7);
		return "sysFile/update";
	}
	
	@ResponseBody
	@GetMapping(value="deleteSysFileById")
	public int deleteSysFileById(String id) {
		return sysFileService.deleteSysFileById(id);
	}
	
}
