package com.yuanyue.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.yuanyue.adapter.MapAdapter;
import com.yuanyue.entity.Role;
import com.yuanyue.entity.User;

@WebService
public interface HelloWorld {
	String say(String str);
	/**
	 * 根据用户获得相应角色
	 * @param user
	 * @return
	 */
	List<Role> getRoleByUser(User user);
	/**
	 * 获取全部用户信息,配置适用于map的适配器类
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	Map<String,List<Role>> getAllUserInfo();
}
