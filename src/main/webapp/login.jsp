<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>光宇游戏--工作日报管理系统[登陆]</title>
	<%@ include file="/resources/common/commonJsAndCss.jsp" %>
</head>
<body>
<!-- Top line begins -->
<div id="top">
	<div class="wrapper">
		<a href="#" title="" class="logo"><img src="<%=basePath %>resources/bootstrap/images/logo.png" alt="" /></a>

		<!-- Right top nav -->
		<div class="topNav">
			<ul class="userNav">
				<li><a href="#" title="" class="screen"></a></li>
				<li><a href="#" title="" class="settings"></a></li>
				<li><a href="#" title="" class="logout"></a></li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!-- Top line ends -->

<!-- Login wrapper begins -->
<div class="loginWrapper">
	<form action="<%=basePath %>/securityLogin/login.do" id="login" method="post">
		<div class="loginPic">
			<a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/userLogin2.png" alt="" /></a>
		</div>
		<input type="text" name="eamil" placeholder="请输入登录邮箱" class="loginUsername" />
		<input type="password" name="emailPasswd" placeholder="请输入登录密码" class="loginPassword" />
		<div class="logControl">
			<div class="memory"><input type="checkbox" checked="checked" class="check" id="remember3" />记录登录用户名</div>
			<input type="submit" name="submit" value="登录" class="buttonM bBlue" />
		</div>
	</form>
</div>
<!-- Login wrapper ends -->
</body>
</html>