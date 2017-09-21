package com.wjh.dao;

import java.util.List;

import com.wjh.entity.File;

public interface FileDao {

	List<File> queryFileByU_Name(String uName);
	Integer changeFileStatus(File file);
	Integer uploadFile(File file);
	String findPathByF_Id(Integer fId);
	int deleteFile(Integer fId);
	String queryFileNameByF_Id(Integer fId);
}
