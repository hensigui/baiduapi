package com.yuanyue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.yuanyue.component.aspect.LogAspect;
import com.yuanyue.component.entity.SpringElEntity;
import com.yuanyue.component.service.DemoAnnotationAspectService;
import com.yuanyue.component.service.DemoMethodAspectService;
import com.yuanyue.component.taskexecutor.AsyncTaskService;
import com.yuanyue.conf.JavaConfig;
import com.yuanyue.conf.TaskExecutorConfig;

public class Test {

	public static void main(String[] args) {
		//aopTest();
		//asyncTest();
		elTest();
	}
	/**
	 * spring el
	 */
	private static void elTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		SpringElEntity springElEntity = context.getBean(SpringElEntity.class);
		boolean flag = springElEntity.isExpress();
		boolean flag3 = springElEntity.isExpress2();
		System.out.println("属性注入结果为"+flag);
		System.out.println("bean方法结果注入："+springElEntity.getSay());
		System.out.println("静态方法结果注入:"+springElEntity.getRandomNum());
		System.out.println("自动装配方法参数注入结果为："+flag3);
		
		context.close();
	}
	/**
	 * spring异步
	 */
	@SuppressWarnings("unused")
	private static void asyncTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
		for(int i=0;i<100;i++) {
			asyncTaskService.executeAsyncTask(i);
			//asyncTaskService.executeAsyncTaskPlus(i);
		}
		context.close();
		context.destroy();
		System.out.println("spring容器销毁");
	}
	/**
	 * spring aop
	 */
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
