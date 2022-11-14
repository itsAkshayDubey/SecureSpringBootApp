package com.github.itsAkshayDubey.springsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.itsAkshayDubey.springsecuritydemo.service.impl.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@GetMapping("/hello")
	public String sayHello(String name) {
		return helloService.sayHello(name);
	}
	
	@GetMapping("/admin/hello")
	public String sayAdminHello(String name) {
		return helloService.sayAdminHello(name);
	}
}
