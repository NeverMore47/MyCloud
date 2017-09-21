package com.wjh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjh.dao.FileDao;
import com.wjh.entity.File;

@Transactional
@Service
public class FileService {

	@Autowired
	private FileDao dao;
	
	public List<File> queryFileByU_Name(String uName) {
		return dao.queryFileByU_Name(uName);
	}
	
	public int changeFileStatus(File file) {
		return dao.changeFileStatus(file);
	}
	
	public Integer uploadFile(File file) {
		return dao.uploadFile(file);
	}
	
	public String findPathByF_Id(Integer fId) {
		return dao.findPathByF_Id(fId);
	}
	
	public int deleteFile(Integer fId) {
		return dao.deleteFile(fId);
	}
	
	public String queryFileNameByF_Id(Integer fId) {
		return dao.queryFileNameByF_Id(fId);
	}
}
