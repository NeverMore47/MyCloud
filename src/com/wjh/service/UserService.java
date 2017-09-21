package com.wjh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjh.dao.UserDao;
import com.wjh.entity.User;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public User checkLogin(User user) {
		User u = dao.checkLogin(user);
		return u;
	}
	
	public int insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public boolean checkUserName(String uName) {
		User u = dao.selectUserName(uName);
		if(u == null) {
			return true;
		}
		return false;
	}
}
