package com.github.itsAkshayDubey.springsecuritydemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry controllerRegistry) {
		controllerRegistry.addViewController("/mylogin").setViewName("mylogin");
	}

}
