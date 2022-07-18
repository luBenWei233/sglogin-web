package org.sg.loginweb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;

@Component
public class MyUtil {
	
	@Value("${spring.datasource.url}")
	private String dataBaseType;
	
	@Value("${custom.dataurl}")
	private String dataurl;
	
	@Value("${custom.gmt}")
	private int gmt;
	
	@Autowired
    private Ip2regionSearcher ip2regionSearcher;
	
	private static MyUtil myUtil;
	
	@PostConstruct
    public void init() {
		myUtil = this;
		myUtil.ip2regionSearcher = this.ip2regionSearcher;
		myUtil.dataBaseType = this.dataBaseType;
		myUtil.dataurl = this.dataurl;
		myUtil.gmt = this.gmt;
    }
	public static String getDataBaseUrl() {
		return myUtil.dataBaseType.split(myUtil.dataurl)[1];
	}
	public static String getDataBaseType() {
		if(myUtil.dataBaseType.contains(MyEnum.MYSQL.getValue())) {
			return MyEnum.MYSQL.getValue();
		}else {
			return MyEnum.SQLITE.getValue();
		}
	}
	public static int getGmt() {
		return myUtil.gmt;
	}
	
	/** 
	 *       * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	 *  
	      * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	      * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	      *  
	      * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	      * 192.168.1.100 
	      *  
	      * 用户真实IP为： 192.168.1.110 
	      *  
	      * @param request 
	      * @return 
	      */  
	//取IP
	public static String getIpAddress(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
		
	//日期转换
	public static Date StringToDate(String date) {
		date=date.replace("T", " ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public static String StringToStringDate(String date) {
		date=date.replace("T", " ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf1.format(sdf1.parse(date)).replace("-", "");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public static String DateToTString(Date date) {
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf1.format(date).replace(" ", "T");
	}
		
	public static String DateToString(Date date) {
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf1.format(date);
	}
		
	//取两个日期的总天数
	public static List<String> getBetweenDays(String starttime, String endtime) {
	    List<String> betweenTime = new ArrayList<String>();
	    try {
	    	Date sdate= new SimpleDateFormat("yyyyMMdd").parse(starttime);
	        Date edate= new SimpleDateFormat("yyyyMMdd").parse(endtime);
	 
	        SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM-dd");
	 
	        Calendar sCalendar = Calendar.getInstance();
	        sCalendar.setTime(sdate);
	        int year = sCalendar.get(Calendar.YEAR);
	        int month = sCalendar.get(Calendar.MONTH);
	        int day = sCalendar.get(Calendar.DATE);
	        sCalendar.set(year, month, day, 0, 0, 0);
	 
	        Calendar eCalendar = Calendar.getInstance();
	        eCalendar.setTime(edate);
	        year = eCalendar.get(Calendar.YEAR);
	        month = eCalendar.get(Calendar.MONTH);
	        day = eCalendar.get(Calendar.DATE);
	        eCalendar.set(year, month, day, 0, 0, 0);
	 
	        while (sCalendar.before(eCalendar)){
	            betweenTime.add(outformat.format(sCalendar.getTime()));
	            sCalendar.add(Calendar.DAY_OF_YEAR, 1);
	        }
	        betweenTime.add(outformat.format(eCalendar.getTime()));
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return betweenTime;
	}
	
	//取两个日期间的天时分
	public static String getTwoDateTimes(Date startTime, Date endTime) {
		if(startTime==null || endTime==null) {
			return null;
		}
		if(startTime.compareTo(endTime)>0) {
			return "0天0小时0分钟0秒";
		}
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		diff = endTime.getTime() - startTime.getTime();
		day = diff / nd;// 计算差多少天
		hour = diff % nd / nh + day * 24;// 计算差多少小时
		min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		sec = diff % nd % nh % nm / ns;// 计算差多少秒
		// 输出结果
		return day + "天" + (hour - day * 24) + "小时"+ (min - day * 24 * 60) + "分钟" + sec + "秒";
	}
		
	//文件转流
	public static String fileToByte(String imgPath) {
		InputStream in = null;
		byte[] data = null;
		try {
			// 读取图片字节数组
			in = new FileInputStream(imgPath);
			data = new byte[in.available()];
			in.read(data);
			return "data:image/png;base64,"+Base64.getEncoder().encodeToString(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//根据ip返回国省市
	public static String getIpAtCityName(String ip) {
		if(ip!=null) {
			IpInfo info = myUtil.ip2regionSearcher.memorySearch(ip);
			String address="";
			if(info.getCountry()!=null) {
				address=address+info.getCountry();
			}
			if(info.getProvince()!=null) {
				address=address+info.getProvince();
			}
			return address+info.getCity();
		}
		return null;
	}
	
	//图片转html
	public static String imageToHtml(String path) {
		return "<img src=\""+path+"\" style=\"width: 100%;height: 100%;object-fit: cover;\">";
	}
	
	//MD5加密
	public static String passToMd5(String pass) {
		try {
			return DigestUtils.md5DigestAsHex(pass.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//读取文本文件中的内容
	public static String readTextFileToString(String path) {
		try {
			File file = new File(path);
			FileInputStream readIn = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			StringBuffer buffer=new StringBuffer();
			while((oneLine= bufferedReader.readLine()) != null){
				buffer.append(oneLine);
			}
			read.close();
			return buffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	public static String readTextFileToString(File file) {
		try {
			FileInputStream readIn = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			StringBuffer buffer=new StringBuffer();
			while((oneLine= bufferedReader.readLine()) != null){
				buffer.append(oneLine);
			}
			read.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String readTextFileToString(InputStream readIn) {
		try {
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			StringBuffer buffer=new StringBuffer();
			while((oneLine= bufferedReader.readLine()) != null){
				buffer.append(oneLine);
			}
			read.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> readTextFileToList(File file) {
		try {
			FileInputStream readIn = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			List<String> list=new ArrayList<String>();
			while((oneLine= bufferedReader.readLine()) != null){
				list.add(oneLine);
			}
			read.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> readTextFileToList(InputStream readIn) {
		try {
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			List<String> list=new ArrayList<String>();
			while((oneLine= bufferedReader.readLine()) != null){
				list.add(oneLine);
			}
			read.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//将文件写到本地
	public static void writeFileToLoaction(File file,String path) throws IOException {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		InputStream input=new FileInputStream(file);
		byte[] bytes = new byte[1024];
		FileOutputStream downloadFile = new FileOutputStream(path);
		int index;
		while ((index = input.read(bytes)) != -1) {
			downloadFile.write(bytes, 0, index);
			downloadFile.flush();
		}
		downloadFile.close();
		input.close();
    }
	
	//将文件写到本地
	public static void writeFileToLoaction(InputStream input,String path) throws IOException {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		byte[] bytes = new byte[1024];
		FileOutputStream downloadFile = new FileOutputStream(path);
		int index;
		while ((index = input.read(bytes)) != -1) {
			downloadFile.write(bytes, 0, index);
			downloadFile.flush();
		}
		downloadFile.close();
		input.close();
	}
	
	// unix时间戳转人类能看懂的格式
	public static String timeStamp2Date(String timestampString) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(timestampString) * 1000));
	}
}
