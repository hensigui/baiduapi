package com.yuanyue.component.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringElEntity {
	//注入布尔值
	@Value(value="#{2>1}")
	private boolean express;
	
	private boolean express2;
	//注入普通值
	@Value(value="hensigui")
	private String name;

	//@Value(value="#{springElEntity.sayHello('everyone')}") //字符串作为方法参数
	//以bean属性为方法参数注入bean方法结果
	@Value(value="#{springElEntity.sayHello(springElEntity.name)}")
	private String say;
	//注入静态方法结果
	@Value(value="#{T(java.lang.Math).random() * 100.0}")
	private int randomNum;
	
	public boolean isExpress() {
		return express;
	}

	public void setExpress(boolean express) {
		this.express = express;
	}

	public boolean isExpress2() {
		return express2;
	}
	//通过自动装配注入布尔值
	@Autowired
	public void setExpress2(@Value(value="#{3>1}")boolean express2) {
		this.express2 = express2;
	}
	
	public String sayHello(String name) {
		return "Hello,"+name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}

	public int getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(int randomNum) {
		this.randomNum = randomNum;
	}
	
	
}
