package com.wjh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wjh.entity.User;
import com.wjh.service.UserService;

@Controller
@RequestMapping("user")
public class LoginController {

	@Autowired
	private UserService service;
	
	@RequestMapping("checkLogin")
	public String checkLogin(User user,Model model,HttpSession session) {
		User u = service.checkLogin(user);
		if(u != null) {
			session.setAttribute("userName", u.getuName());
			return "redirect:/userFile/queryUserFile";
		} else {
			String msg = "用户名或密码错误";
			model.addAttribute("msg", msg);
			return "/login.jsp";
		}
	}
	
	@RequestMapping("loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "/index.jsp";
	}

}
