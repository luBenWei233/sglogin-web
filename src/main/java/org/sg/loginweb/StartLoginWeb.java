package org.sg.loginweb;

import javax.annotation.PostConstruct;
import org.sg.loginweb.service.sys.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 我的世界登陆插件-用户管理系统web版
 * 作者：帅哥
 * email：1048885612@qq.com
 * 创建日期：2022-06-21 04:45
 */
@SpringBootApplication
public class StartLoginWeb {

    public static void main( String[] args ){
    	SpringApplication.run(StartLoginWeb.class,args);
    }
    
    /**
     * 项目初始化：生成数据库
     * 这里只检查player表，第一次执行前只建数据库名就好，别建表
     */
    
    @Autowired
	private SysConfigService sysConfigService;
    
    @PostConstruct
	public void init(){
		sysConfigService.getTableCreate();
	}
}
