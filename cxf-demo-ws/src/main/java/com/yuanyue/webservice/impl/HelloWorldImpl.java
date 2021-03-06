package com.yuanyue.webservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.yuanyue.entity.Role;
import com.yuanyue.entity.User;
import com.yuanyue.webservice.HelloWorld;

@WebService
public class HelloWorldImpl implements HelloWorld {

	public String say(String str) {
		return "hello,"+str;
	}

	public List<Role> getRoleByUser(User user) {
		List<Role> roleList=new ArrayList<Role>();
        // 模拟 直接写死
        if(user!=null){
            if(user.getUserName().equals("yuanyue") && user.getPassword().equals("123")){
                roleList.add(new Role(1,"技术总监"));
                roleList.add(new Role(2,"架构师"));
            }else if(user.getUserName().equals("jack") && user.getPassword().equals("123")){
                roleList.add(new Role(3,"程序员"));
            }
            return roleList;
        }else{
            return null;          
        }
	}

	public Map<String, List<Role>> getAllUserInfo() {
		 Map<String,List<Role>> map=new HashMap<String,List<Role>>();
	        List<Role> roleList1=new ArrayList<Role>();
	        roleList1.add(new Role(1,"技术总监"));
	        roleList1.add(new Role(2,"架构师"));
	        map.put("java1234", roleList1);
	        List<Role> roleList2=new ArrayList<Role>();
	        roleList2.add(new Role(1,"程序员"));
	        map.put("jack", roleList2);
	        return map;
	}

}
