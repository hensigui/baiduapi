package com.yuanyue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.yuanyue.component.aspect.LogAspect;
import com.yuanyue.component.service.DemoAnnotationAspectService;
import com.yuanyue.component.service.DemoMethodAspectService;
import com.yuanyue.component.taskexecutor.AsyncTaskService;
import com.yuanyue.conf.JavaConfig;
import com.yuanyue.conf.TaskExecutorConfig;

public class Test {

	public static void main(String[] args) {
		//aopTest();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
		for(int i=0;i<100;i++) {
			asyncTaskService.executeAsyncTask(i);
			//asyncTaskService.executeAsyncTaskPlus(i);
		}
		context.close();
		context.destroy();
		System.out.println("springÈÝÆ÷Ïú»Ù");
	}

	@SuppressWarnings("unused")
	private static void aopTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		DemoAnnotationAspectService annoBean = context.getBean(DemoAnnotationAspectService.class);
		annoBean.add();
		System.out.println("-----------------------");
		
		DemoMethodAspectService methodBean = context.getBean(DemoMethodAspectService.class);
		methodBean.add();
		System.out.println("-----------------------");
		
		LogAspect bean = context.getBean(LogAspect.class);
		System.out.println(bean.getBeanName());
		context.close();
	}

}
