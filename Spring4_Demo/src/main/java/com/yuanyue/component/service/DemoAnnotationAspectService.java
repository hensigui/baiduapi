package com.yuanyue.component.service;

import org.springframework.stereotype.Service;

import com.yuanyue.annotation.Action;

@Service
public class DemoAnnotationAspectService {
	@Action(name="ͨ��ע������")
	public void add() {
		System.out.println("Anno��add����ִ��");
	}
}
