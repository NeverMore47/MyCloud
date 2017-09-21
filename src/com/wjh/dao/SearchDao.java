package com.wjh.dao;

import java.util.List;

import com.wjh.entity.File;

public interface SearchDao {

	List<File> searchFilesByCondition(String condition);
}
