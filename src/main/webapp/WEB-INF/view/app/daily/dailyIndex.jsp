<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/resources/common/commonJsAndCss.jsp" %>
	
	<script type="text/javascript" src="<%=basePath %>resources/ui/js/login.js"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body>
<div id="content">
	<div class="content_left" style="background:green;">
		本周计划
	</div>
	<div class="content_body" style="background:red;">工作日报</div>
</div>
</body>
</html>