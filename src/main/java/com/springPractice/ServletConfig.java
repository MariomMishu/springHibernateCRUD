package com.springPractice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableWebMvc
@ComponentScan(basePackages = {"com.springPractice.controllers"})
public class ServletConfig implements WebMvcConfigurer{

	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		registry.jsp("/WEB-INF/views/",".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//register resource handle for css js images
		registry.addResourceHandler("/dist/**").addResourceLocations("/WEB-INF/resources/dist/");
		
		//register resource handle for plugins
		registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/resources/plugins/");
		//WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	

}
