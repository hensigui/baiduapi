package com.yuanyue.service;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
	        context.start();
	        DemoProviderService demoProviderService=(DemoProviderService) context.getBean("demoProviderService");
	        String result=demoProviderService.sayHello("����Ա2��ȡ");
	        System.out.println("Զ�̵��ý����"+result);
	        try {
	            System.in.read();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        context.close();
	}
}
