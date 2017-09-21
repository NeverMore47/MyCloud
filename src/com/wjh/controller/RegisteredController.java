package com.wjh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjh.entity.User;
import com.wjh.service.UserService;

@Controller
@RequestMapping("user")
public class RegisteredController {

	@Autowired
	private UserService service;
	
	@RequestMapping("registered")
	public String insertUser(User user,Model model,HttpSession session) {
		int flag = service.insertUser(user);
		String message = "";
		if(flag > 0) {
			message = "注册成功";
			user = service.checkLogin(user);
			session.setAttribute("userInfo", user);
		} else {
			message = "注册失败";
		}
		model.addAttribute("message", message);
		return "/message.jsp";
	}
	@RequestMapping("checkIsExist")
	@ResponseBody
	public String checkUserName(String uName) {
		if(service.checkUserName(uName)) {
			return "true";
		}
		return "false";
	}
	
	
}
