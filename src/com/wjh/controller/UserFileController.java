package com.wjh.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjh.entity.File;
import com.wjh.service.FileService;

@Controller
@RequestMapping("userFile")
public class UserFileController {

	@Autowired
	private FileService service;

	@RequestMapping("queryUserFile")
	public String queryUserFile(Model model, HttpSession session,
			@RequestParam(required = true, defaultValue = "1") int pageNum) {
		String uName = (String) session.getAttribute("userName");
		if (uName != null) {
			if (pageNum == 0) {
				pageNum = 1;
			}
			PageHelper.startPage(pageNum, 5);
			List<File> fileList = service.queryFileByU_Name(uName);
			model.addAttribute("fileList", fileList);
			PageInfo<File> info = new PageInfo<File>(fileList);
			model.addAttribute("page", info);
			return "/userhome.jsp";
		}
		return "redirect:/login.jsp";
	}

	@RequestMapping("changeFileStatus")
	@ResponseBody
	public String changeFileStatus(int fId, int canShare) {
		File file = new File();
		file.setCanShare(canShare);
		file.setfId(fId);
		int i = service.changeFileStatus(file);
		if (i > 0) {
			return "true";
		}
		return "false";
	}

	@RequestMapping("uploadFile")
	public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session,
			Model model) {
		if (file != null) {
			String uName = (String) session.getAttribute("userName");
			String fileName = file.getOriginalFilename();
			String path = "/Users/wanjiahuan/Documents/MyCloudUpLoad/" + uName;
			java.io.File newFile = new java.io.File(path, fileName);

			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			/* java对象File */
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			File myFile = new File();
			myFile.setFileName(fileName);
			myFile.setFilePath(uName);
			myFile.setFileSize(String.valueOf(file.getSize()));
			myFile.setCreateTime(sdf.format(d));
			String msg = null;
			try {
				file.transferTo(newFile);
				service.uploadFile(myFile);
				msg = "上传成功";
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				msg = "上传失败";
			}
			model.addAttribute("message", msg);
		}
		return "/message.jsp";
	}

	/* 下载文件 */
	@RequestMapping("download")
	public String download(Integer fId, Model model, HttpServletRequest request, HttpServletResponse response,
			String fileName) {
		FileInputStream is = null;
		String path = service.findPathByF_Id(fId);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		if (path == null || path.equals("")) {
			model.addAttribute("message", "对不起,您要下载的资源已被删除");
			return "/message.jsp";
		}
		path = "/Users/wanjiahuan/Documents/MyCloudUpLoad/" + java.io.File.separator + path;
		java.io.File file = new java.io.File(path + java.io.File.separator, fileName);
		
		try {
			is = new FileInputStream(file);
			int len = 0;
			byte buffer[] = new byte[1024];
			OutputStream out = response.getOutputStream();
			while ((len = is.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "下载失败");
			return "/message.jsp";
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*删除文件*/
	@RequestMapping("deleteFile")
	@ResponseBody
	public String delete(Integer fId,HttpSession session) {
		String loginUser = (String) session.getAttribute("userName");
		String userName = service.findPathByF_Id(fId);
		if(loginUser != null && loginUser.equals(userName)) {
			String fileName = service.queryFileNameByF_Id(fId);
			String filePath = "/Users/wanjiahuan/Documents/MyCloudUpLoad/" + userName + "/" + fileName;
			java.io.File file = new java.io.File(filePath);
			if(file.exists()) {
				file.delete();
			} else {
				session.setAttribute("message", "该文件已不存在");
			}
			int flag = service.deleteFile(fId);
			if(flag > 0) {
				return "true";
			}
			return "false";
		}else {
			session.setAttribute("message", "该文件可能不属于你");
			return "/message.jsp";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
