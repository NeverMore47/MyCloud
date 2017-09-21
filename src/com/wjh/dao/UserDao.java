package com.wjh.dao;


import com.wjh.entity.User;

public interface UserDao {

	User checkLogin(User user);
	int insertUser(User user);
	User selectUserName(String uName);
}
