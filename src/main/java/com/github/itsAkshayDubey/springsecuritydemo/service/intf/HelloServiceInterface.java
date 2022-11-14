package com.github.itsAkshayDubey.springsecuritydemo.service.intf;

import org.springframework.stereotype.Component;

@Component
public interface HelloServiceInterface {
	
	public String sayHello(String name);
	public String sayAdminHello(String name);

}
