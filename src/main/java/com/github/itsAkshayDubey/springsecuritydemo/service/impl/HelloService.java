package com.github.itsAkshayDubey.springsecuritydemo.service.impl;

import org.springframework.stereotype.Service;

import com.github.itsAkshayDubey.springsecuritydemo.service.intf.HelloServiceInterface;

@Service
public class HelloService implements HelloServiceInterface{

	@Override
	public String sayHello(String name) {
		return "Hello "+name;
	}

	@Override
	public String sayAdminHello(String name) {
		return "Hello Admin "+name;
	}

}
