<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>光宇游戏--日报管理系统</title>
	<%@ include file="/resources/common/commonJsAndCss.jsp" %>
	<script type="text/javascript">
	//显示或隐藏左侧菜单
	function leftmenutoggle(){
		if("hidden" == $("#leftMenu_div").attr("flag")){
			//显示
			$("#leftMenu_div").attr("flag","");
			$("#leftMenu_div").attr("style","");
			$("#rightContent_div").attr("style","margin-left:213px;");
		}else{
			//隐藏
			$("#leftMenu_div").attr("flag","hidden");
			$("#leftMenu_div").attr("style","display:none;");
			$("#rightContent_div").attr("style","margin-left:3px;");
		}
	}
	document.ondblclick=leftmenutoggle;
	</script>
</head>
<body>
	<!-- Top line begins -->
	<div id="top">
		<%@ include file="/resources/common/header.jsp" %>
	</div>
	<!-- Top line ends -->
	
	<!-- Sidebar begins -->
	<div id="sidebar">
		<%@ include file="/resources/common/mainMenu.jsp" %>
	</div>
	
	<!-- 页面内容体 -->
	<sitemesh:write property="body"/>
</body>
</html>
