package org.sg.loginweb.controller.sys;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpSession;
import org.sg.loginweb.util.CreateCheckCode;
import org.sg.loginweb.util.MyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("all")
@RestController
@RequestMapping("/util")
public class UtilController {
	
	/**
	 * 验证码的公共类
	 * @param HttpSession
	 * @return
	 */
	@PostMapping(value = "/getCheckCode")
	public String getCheakCode(HttpSession session) {
		try {
			CreateCheckCode ccc = new CreateCheckCode();
			Image image = ccc.getImage();
			session.setAttribute("imgCode", ccc.getText());
			BufferedImage bufferedImage = new BufferedImage(90, 40, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = bufferedImage.createGraphics();
			//重构图片
			graphics.drawImage(image, 0, 0, 90, 40, null);
			ByteArrayOutputStream bs =new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(bufferedImage,"jpg",imOut);
	        //转换成JSON返回给前端
	        return JSONObject.toJSONString(bs.toByteArray());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(value = "/getSystemInfo")
	public Map<String,Object> getSystemInfo() throws IOException{
		Map<String,Object> map=new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("#0.00");
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		// 系统名称
		map.put("osname", osmxb.getName());
		// 系统版本
		map.put("version", osmxb.getVersion());
		// CPU架构
		map.put("arch", osmxb.getArch());
		// CPU核心数
		map.put("processNum", osmxb.getAvailableProcessors());
		// CPU使用率
		map.put("cpuLoad", df.format(osmxb.getSystemCpuLoad()*100));
		// 内存总数
		map.put("memoryTotal", transformation(osmxb.getTotalPhysicalMemorySize()));
		// 空闲内存总数
		map.put("memoryFree", transformation(osmxb.getFreePhysicalMemorySize()));
		// 服务器时间
		map.put("serverDate", MyUtil.DateToString(new Date()));
		// 磁盘空间
		List<HashMap<String, Object>> disksList=new ArrayList<HashMap<String,Object>>();
        File[] disks = File.listRoots();
        for (File file : disks) {
        	HashMap<String, Object> disk=new HashMap<String, Object>();
            // 获取盘符
        	disk.put("path", file.getCanonicalPath());
            // 获取总容量
        	long totalSpace = file.getTotalSpace();
        	disk.put("totalSpace",transformation(totalSpace));
            // 获取剩余容量
            long usableSpace = file.getUsableSpace();
            disk.put("usableSpace",transformation(usableSpace));
            // 获取已经使用的容量
            long freeSpace = totalSpace - usableSpace;
            disk.put("freeSpace",transformation(freeSpace));
            // 获取使用率
            float useRate = (float)((freeSpace * 1.0 / totalSpace) * 100);
            disk.put("useRate",Double.parseDouble(df.format(useRate)) + "%");
            disksList.add(disk);
        }
        map.put("disks", disksList);
		return map;
	}
	
	private String transformation(long size){
    	if(size / 1024 / 1024 / 1024>0) {
    		long gb = size / 1024 / 1024 / 1024;
    		if(size / 1024 / 1024 % 1024>0) {
    			String mb=String.valueOf((size / 1024 / 1024-gb*1024*1.0)/1024*1.0*100).split("\\.")[0];
    			return gb+"."+mb+"GB";
    		}else {
    			return gb+"GB";
    		}
    	} else {
    		return size / 1024 / 1024 + "MB";
    	}
    }
	
}
