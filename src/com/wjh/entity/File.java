package com.wjh.entity;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class File {

	private Integer fId;
	private String fileName;
	private String filePath;
	private String fileSize;
	private String createTime;
	private Integer canShare;

	public Integer getfId() {
		return fId;
	}

	public void setfId(Integer fId) {
		this.fId = fId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCanShare() {
		return canShare;
	}

	public void setCanShare(Integer canShare) {
		this.canShare = canShare;
	}
}
