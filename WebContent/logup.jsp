<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/login.jsp">sign in</a>
		&nbsp; <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
	</div>
	<center>
		<form action="${pageContext.request.contextPath}/user/registered"
			method="post" style="margin: 200px">
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="uName" id="uName"></td>
					<td><span id="msg"></span></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="uPwd"></td>
				</tr>
				<tr>
					<td><input type="submit" value="注册"></td>
				</tr>
			</table>
		</form>
	</center>
	<script type="text/javascript">
		$("#uName").blur(function() {
			var uName = $("#uName").val();
			if(uName != "") {
				$.ajax({
					url:"/MyCloud/user/checkIsExist",
					data:{uName:uName},
					dataType:"json",
					type:"post",
					success:function(result) {
						if(result) {
							$("#msg").html("用户名可用");
							$("#msg").css("color","green");
						} else {
							$("#msg").html("用户名已被注册");
							$("#msg").css("color","red");
						}
					}
				});
			}
		});
	</script>
</body>
</html>