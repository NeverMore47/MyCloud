package com.wjh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjh.entity.File;
import com.wjh.service.SearchService;

@Controller
@RequestMapping("search")
public class SearchController {

	@Autowired
	private SearchService service;

	@RequestMapping("searchFiles")
	public String searchFilesByCondition(String condition, Model model,
			@RequestParam(required = true, defaultValue = "1") int pageNum) {
		if (pageNum == 0) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, 10);
		List<File> listFile = service.searchFilesByCondition(condition);
		model.addAttribute("listFile", listFile);
		PageInfo<File> info = new PageInfo<File>(listFile);
		model.addAttribute("page", info);
		return "/showsearchfiles.jsp";
	}
}
