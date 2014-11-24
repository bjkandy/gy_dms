<!-- meta Config -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="mainNav">
   <div class="user"> <a title="" class="leftUserDrop"><img src="<%=basePath %>resources/bootstrap/images/user_default.png" alt="许颖超" /><span></span></a><span>康瑞伟</span>
       <ul class="leftUser">
           <li><a href="#" title="" class="sProfile">My profile</a></li>
           <li><a href="#" title="" class="sMessages">Messages</a></li>
           <li><a href="#" title="" class="sSettings">Settings</a></li>
           <li><a href="#" title="" class="sLogout">Logout</a></li>
       </ul>
   </div>
   <!-- Responsive nav -->
   <div class="altNav">
       <div class="userSearch">
           <form action="">
               <input type="text" placeholder="search..." name="userSearch" />
               <input type="submit" value="" />
           </form>
       </div>
       <!-- User nav -->
       <ul class="userNav">
           <li><a href="#" title="" class="profile"></a></li>
           <li><a href="#" title="" class="messages"></a></li>
           <li><a href="#" title="" class="settings"></a></li>
           <li><a href="#" title="" class="logout"></a></li>
       </ul>
   </div>
   <!-- Main nav -->
    <ul class="nav">
        <li><a href="<%=basePath %>" title="" class="active"><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/dashboard.png" alt="" /><span>综合信息</span></a></li>
        <li><a href="<%=basePath %>/daily/index.do" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/forms.png" alt="" /><span>创建日报</span></a></li>
        <li><a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/forms.png" alt="" /><span>创建周计划</span></a></li>
        <li><a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/messages.png" alt="" /><span>日报管理</span></a></li>
        <li><a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/statistics.png" alt="" /><span>周报管理</span></a></li>
        <li><a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/tables.png" alt="" /><span>统计分析</span></a></li>
        <li><a href="#" title=""><img src="<%=basePath %>resources/bootstrap/images/icons/mainnav/other.png" alt="" /><span>系统设置</span></a></li>
    </ul>
</div>