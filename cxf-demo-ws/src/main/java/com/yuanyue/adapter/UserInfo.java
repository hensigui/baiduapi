package com.yuanyue.adapter;

import java.util.List;

import com.yuanyue.entity.Role;

public class UserInfo {
	private String key;
    private List<Role> value;
     
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public List<Role> getValue() {
        return value;
    }
    public void setValue(List<Role> value) {
        this.value = value;
    }
}
