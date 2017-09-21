<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<title>MyCloud搜索结果</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
	<div style="text-align: center">
		<style type="text/css">
.even {
	background-color: pink
}

.old {
	background-color: yellow
}
</style>

		<br /> <font size="60px">MyCloud搜索结果</font><br /> <br />

		<table frame="border" width="100%" align="center">
			<tr>
				<td>文件名</td>
				<td>文件大小</td>
				<td>创建日期</td>
				<td>下载文件</td>
			<tr>

				<c:forEach var="c" items="${listFile}" varStatus="stat">
					<tr class="${stat.count%2==0?'even':'old'}">
						<td>${c.fileName }</td>
						<td>${c.fileSize }kb</td>
						<td>${c.createTime }</td>
						<td><a
							href="${pageContext.request.contextPath}/userFile/download?fId=${c.fId }&fileName=${c.fileName }">下载</a>
						</td>
					<tr>
				</c:forEach>
		</table>
		<br /> 共[${page.total}]条记录, 共[${page.pages}]页,
		当前是第[${page.pageNum}]页, <a
			href="/MyCloud/search/searchFiles?pageNum=1">首页</a> <a
			href="/MyCloud/search/searchFiles?pageNum=${page.prePage}" id="start">上一页</a>
		<c:forEach var="p" begin="1" end="${page.pages}">
			<a href="/MyCloud/search/searchFiles?pageNum=${p}">${p}</a>
		</c:forEach>
		<a href="/MyCloud/search/searchFiles?pageNum=${page.nextPage}"
			id="end">下一页</a> <a
			href="/MyCloud/search/searchFiles?pageNum=${page.pages}">尾页</a> <input
			type="hidden" id="searchcontent" value="${searchcontent}">

		<script type="text/javascript">
		$("#end").click(function() {
			if(${page.isLastPage}) {
				alert("已经是最后一页啦！");
				return false;
			}				
		});
		</script>
	</div>
</body>
</html>
