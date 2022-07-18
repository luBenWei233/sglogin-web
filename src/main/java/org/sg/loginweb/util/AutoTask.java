package org.sg.loginweb.util;

import org.sg.loginweb.service.IpLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SpringBoot定时任务
 * @author 帅哥
 * @date 2022-07-12 06:25
 */

@Configuration
@EnableScheduling   // 2.开启定时任务
public class AutoTask {
	
	private static final Log LOG= LogFactory.getLog(AutoTask.class);
	
	@Autowired
	private IpLimitService ipLimitService;
	
	//添加定时任务
    //秒 分 时 日 月 周几（0-7） 年
    //每到0秒执行一次
	//@Scheduled(cron="0 * * * * *")
	//30 15 10 * * ? 每天10点15分30秒执行一次
	//30 0/5 10,18 * * ?  每天10点和18点，每隔5分钟的30秒执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    private void configureTasks() {
    	int count = ipLimitService.deleteIpLimitByOld();
    	LOG.info("每日0点自动清理无效IP数据，数量："+count);
    }
}
