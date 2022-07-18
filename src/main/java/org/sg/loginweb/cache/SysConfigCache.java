package org.sg.loginweb.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置缓存类
 * @author 帅哥
 * @Date 2022-06-22 02:04
 */
public class SysConfigCache {
	
	private static Map<String,String> map;
	
	public static Map<String,String> getMap(){
		return map;
	}
	
	public static void newMap() {
		if(map==null) {
			map=new HashMap<String, String>();
		}
	}
	
}
