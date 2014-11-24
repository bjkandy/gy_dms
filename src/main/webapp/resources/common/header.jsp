<!-- meta Config -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="wrapper">
	<a href="<%=basePath %>" title="光宇游戏" class="logo"><img src="<%=basePath %>resources/bootstrap/images/logo.png" alt="" /></a> 
	
	<!-- Right top nav -->
	<div class="topNav">
		<ul class="userNav">
			<li><a title="" class="search"></a></li>
			<li><a href="#" title="全屏" class="screen"></a></li>
			<li style="display:none;"><a href="#" title="" class="settings"></a></li>
			<li><a href="#" title="退出系统" class="logout"></a></li>
			<li class="showTabletP"><a href="#" title="" class="sidebar"></a></li>
		</ul>
		
		<a title="" class="iButton"></a> <a title="" class="iTop"></a>
		<div class="topSearch">
			<div class="topDropArrow"></div>
			<form action="">
				<input type="text" placeholder="search..." name="topSearch" />
				<input type="submit" value="" />
			</form>
		</div>
	</div>
	
	<div class="clear"></div>
</div>