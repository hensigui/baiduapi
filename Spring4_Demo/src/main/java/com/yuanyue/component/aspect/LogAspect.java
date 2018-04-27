package com.yuanyue.component.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.yuanyue.annotation.Action;

@Aspect
@Component
public class LogAspect {
	private String beanName = "LogAspectBean";
	
	@Pointcut("@annotation(com.yuanyue.annotation.Action)")
	public void annotationPointCut() {
		
	}
	@After(value="annotationPointCut()")
	public void after(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Action action = method.getAnnotation(Action.class);
		System.out.println("ע������׼����ʼ");
		System.out.println("ע�ⷽʽ���룺"+action.name());
	}
	@Before(value="execution(* com.yuanyue.component.service.DemoMethodAspectService.*(..))")
	public void before(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		System.out.println("��������ʽע��׼����ʼ");
		System.out.println("��������ʽע�룺"+method.getName());
	}
	
	public String getBeanName() {
		return beanName;
	}
	
	
}
