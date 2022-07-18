package org.sg.loginweb.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sg.loginweb.entity.Player;
import org.sg.loginweb.util.MyEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;


@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer{
	
	@Value("${custom.root}")
	private String fileSavePath;
	
	// Mybatis-plus 分页配置
	@Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor page=new PaginationInnerInterceptor(DbType.H2);
        page.setMaxLimit(50L);
        interceptor.addInnerInterceptor(page);
        return interceptor;
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(MyEnum.IMAGE_REQUEST_URL.getValue()).addResourceLocations("file:"+fileSavePath,"classpath:/static/","classpath:/templates/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				Player player = (Player)request.getSession().getAttribute("player");
		        if (player == null)  {
		            response.sendRedirect("/");
		            return false;  
		        }
		        return true;
			}
		}).addPathPatterns("/**").excludePathPatterns(
				"/","/player/playerSign","/admin","/course/help","/course/selectCourseInfoByIdForPlayer",
				"/notice/noticePage","/notice/noticeShow","/util/getCheckCode","/player/insertPlayerForPlayer",
				"/script/**","/bootstrap/**","/templates/**","/css/**",MyEnum.IMAGE_REQUEST_URL.getValue()
				);
	}
}
