package com.yuanyue.component.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringElEntity {
	//ע�벼��ֵ
	@Value(value="#{2>1}")
	private boolean express;
	
	private boolean express2;
	//ע����ֵͨ
	@Value(value="hensigui")
	private String name;

	//@Value(value="#{springElEntity.sayHello('everyone')}") //�ַ�����Ϊ��������
	//��bean����Ϊ��������ע��bean�������
	@Value(value="#{springElEntity.sayHello(springElEntity.name)}")
	private String say;
	//ע�뾲̬�������
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
	//ͨ���Զ�װ��ע�벼��ֵ
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
