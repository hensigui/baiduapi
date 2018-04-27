package com.yuanyue.webservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.yuanyue.entity.Role;
import com.yuanyue.entity.User;
import com.yuanyue.webservice.HelloWorld;
@Component("helloWorld")
@WebService
public class HelloWorldImpl implements HelloWorld {

	public String say(String str) {
		return "hello,"+str;
	}

	public List<Role> getRoleByUser(User user) {
		List<Role> roleList=new ArrayList<Role>();
        // ģ�� ֱ��д��
        if(user!=null){
            if(user.getUserName().equals("yuanyue") && user.getPassword().equals("123")){
                roleList.add(new Role(1,"�����ܼ�"));
                roleList.add(new Role(2,"�ܹ�ʦ"));
            }else if(user.getUserName().equals("jack") && user.getPassword().equals("123")){
                roleList.add(new Role(3,"����Ա"));
            }
            return roleList;
        }else{
            return null;          
        }
	}

	public Map<String, List<Role>> getAllUserInfo() {
		 Map<String,List<Role>> map=new HashMap<String,List<Role>>();
	        List<Role> roleList1=new ArrayList<Role>();
	        roleList1.add(new Role(1,"�����ܼ�"));
	        roleList1.add(new Role(2,"�ܹ�ʦ"));
	        map.put("java1234", roleList1);
	        List<Role> roleList2=new ArrayList<Role>();
	        roleList2.add(new Role(1,"����Ա"));
	        map.put("jack", roleList2);
	        return map;
	}

}
