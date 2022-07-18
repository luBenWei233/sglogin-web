package org.sg.loginweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;

//单元测试

@SpringBootTest
public class AppTest{
	
	@Autowired
    private Ip2regionSearcher ip2regionSearcher;
	
	@Test
	public void test() {
		//IpInfo(cityId=xxx, country=中国, region=null, province=山西省, city=长治市, isp=xxx, dataPtr=xxx)
		System.out.println(ip2regionSearcher.memorySearch("192.168.0.10"));
		System.out.println(ip2regionSearcher.memorySearch("127.0.0.1"));
	}
}
