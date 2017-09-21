<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<title>我的MyCloud</title>
</head>
<body>

	<div>
		<a href="${pageContext.request.contextPath}/user/loginOut">log
			out</a> &nbsp; <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
		&nbsp; <a href="${pageContext.request.contextPath}/help.jsp">帮助</a>
	</div>

	<div style="font-size: 24px; text-align: center">
		欢迎登陆MyCloud
		<div
			style="font-size: 20px; color: green; font-style: oblique; float: inherit;">${userName}</div>
	</div>

	<hr color="blue" size="2" />
	<br />

	<form action="/MyCloud/userFile/uploadFile" method="post"
		enctype="multipart/form-data" onsubmit="return check()">
		<input type="file" id="fileupload" name="file"
			onpropertychange="getFileSize(this.value)" /><br /> <input
			type="hidden" name="username" value="${userName}" /><br /> <input
			type="submit" value="上传文件" id="upload" />
	</form>
	<br />
	<hr color="red" size="2" />
	<br />

	<div style="text-align: center">
		<style type="text/css">
.even {
	background-color: pink
}

.old {
	background-color: yellow
}
</style>

		<br />
		<div style="font-size: 30px; color: blue; font-style: italic;">
			<font>Your Files In MyColud</font>
		</div>

		<br />
		<table frame="border" width="100%" align="center">
			<tr>
				<td>文件名</td>
				<td>文件大小</td>
				<td>上传日期</td>
				<td>下载文件</td>
				<td>是否共享</td>
				<td>操作</td>
			<tr>

				<c:forEach var="file" items="${fileList}" varStatus="stat">
					<tr class="${stat.count%2==0?'even':'old'}">
						<td>${file.fileName }</td>
						<td>${file.fileSize }</td>
						<td>${file.createTime }</td>
						<td><a
							href="${pageContext.request.contextPath}/userFile/download?fId=${file.fId }&fileName=${file.fileName }">下载</a>
						</td>
						<td>
							<form>
								<select id="${file.fId}" >
									<c:if test="${file.canShare==0 }">
										<option value="0">私有</option>
										<option value="1">共享</option>
									</c:if>
									<c:if test="${file.canShare==1 }">
										<option value="1" selected="selected">共享</option>
										<option value="0">私有</option>
									</c:if>
								</select>
							</form>
						</td>
						<td><a href="javascript:void(0)" class="delete" name="${file.fId}">删除文件</a>
						</td>
					<tr>
				</c:forEach>
		</table>
		<br /> 共[${page.total}]条记录, 共[${page.pages}]页,
		当前是第[${page.pageNum}]页, <a
			href="/MyCloud/userFile/queryUserFile?pageNum=1">首页</a> <a
			href="/MyCloud/userFile/queryUserFile?pageNum=${page.prePage}"
			id="start">上一页</a>
		<c:forEach var="p" begin="1" end="${page.pages}">
			<a href="/MyCloud/userFile/queryUserFile?pageNum=${p}">${p}</a>
		</c:forEach>
		<a href="/MyCloud/userFile/queryUserFile?pageNum=${page.nextPage}"
			id="end">下一页</a> <a
			href="/MyCloud/userFile/queryUserFile?pageNum=${page.pages}">尾页</a>

		<script type="text/javascript">
			$("#end").click(function() {
				if(${page.isLastPage}) {
					alert("已经是最后一页啦！");
					return false;
				}				
			});
			
			$("select").change(function() {
					var fId = $(this).attr("id");				
					var canshare = $(this).val();
					if(canshare == 1) {
				    	var r = confirm("如果设置共享，您的文件将可以被其他人搜索到");
					} else {
						var r = confirm("如果设置私有，您的文件将不能被其他人搜索到");
					}
					if (r == true){
						$.ajax({
							url:"/MyCloud/userFile/changeFileStatus",
							data:{fId:fId,canShare:canshare},
							dataType:"json",
							type:"post",
							success:function(result) {
								if(result) {
									alert("修改成功");
								}else{
									alert("修改失败");
								}
							}
						});									        
			    	}else{
			    		  location.reload();
			    	}
			});
		</script>
		<script type="text/javascript">
 		$(".delete").click(function() {
 			var fId = $(this).attr("name");
 			var r=confirm("确认删除文件？");
 	    	  if(r == true){
 	    		  $.post("/MyCloud/userFile/deleteFile",{fId:fId},function(result) {
 	    			  if(result) {
 	    				  alert("删除成功");
 	    				 location.reload();
 	    			  } else {
 	    				  alert("删除失败");
 	    			  }
 	    		  });
 	    	  }else{
 	    		  return false;
 	    	  }
 		});

		function check() {
			var t = $("#fileupload").val();
			if(t == "") {
				alert("请选择上传文件");
				return false;
			}
		}

  </script>
	</div>

</body>
</html>








