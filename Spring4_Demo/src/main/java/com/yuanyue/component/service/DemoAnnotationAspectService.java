package com.yuanyue.component.service;

import org.springframework.stereotype.Service;

import com.yuanyue.annotation.Action;

@Service
public class DemoAnnotationAspectService {
	@Action(name="通过注解切入")
	public void add() {
		System.out.println("Anno的add方法执行");
	}
}
