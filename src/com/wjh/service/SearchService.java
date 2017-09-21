package com.wjh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjh.dao.SearchDao;
import com.wjh.entity.File;

@Service
@Transactional
public class SearchService {

	@Autowired
	private SearchDao dao;
	
	public List<File> searchFilesByCondition(String condition) {
		return dao.searchFilesByCondition(condition);
	}
	
}
