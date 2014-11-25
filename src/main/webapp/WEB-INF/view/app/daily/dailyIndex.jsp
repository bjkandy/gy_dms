<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
</head>
<body >
	<script type="text/javascript">
	//向工作总结中添加一行
	function addRows(){
		
		var table = document.getElementById("workContentTable");
		var oldTr = table.rows[1];
		var newTr = oldTr.cloneNode(true);
		newTr.style.display = "";
		var tBody = document.getElementById("bodyContent");
		tBody.insertBefore(newTr, null);
		
	}
	
	//删除工作总结中一行
	function deleteRow(obj){

		var trNode = obj.parentNode.parentNode;//获得当前所在行节点
		var tBody = trNode.parentNode;//获取当前表格的tbody

		//判断当前是否只有一行
		if(tBody.children.length == 1){
			alert("此内容只有一行，不能删除！");
			return false;
		}else{
			tBody.removeChild(trNode);
			return true;
		}
		
	}
	
	//将周计划添加到工作内容
	function addPlanToContent(obj,taskId){
		var planContent = obj.innerHTML;//获取一条周计划内容
		var workContentNodes = document.getElementsByName("workContent");
		for(var i = 1;i < workContentNodes.length;i++){
			if(workContentNodes[i].value == ""){
				workContentNodes[i].value = planContent;
				workContentNodes[i].readOnly = true;
				var inputNode = workContentNodes[i].parentNode.getElementsByTagName("input");
				inputNode[0].value = taskId;
				break;
			}
			
		}
	}
	//提交表单
	function submitDailyForm(){

		var workTypes = document.getElementsByName("workType");
		var workContents = document.getElementsByName("workContent");
		var workResults = document.getElementsByName("workResult");
		var workHours = document.getElementsByName("workHour");
		var workPercentages = document.getElementsByName("workPercentage");
		var taskIds = document.getElementsByName("taskId");
		
		var dailyProblem = document.getElementById("dailyProblem").value;
		var dailyLearned = document.getElementById("dailyProblem").value;
		var dailyNextPlan = document.getElementById("dailyProblem").value;
		var str = "[{\"dailyProblem\":\""+dailyProblem+"\",\"dailyLearned\":\""+dailyLearned+"\",\"dailyNextPlan\":\""+dailyNextPlan+"\",";
		
		var works = new Array();
		for(var i = 0;i < workTypes.length-1;i++){
			works[i] = "{\"workType\":\"" + workTypes[i+1].value + "\",\"workContent\":\""
			+  workContents[i+1].value + "\",\"workResult\":\"" +  workResults[i+1].value 
			+ "\",\"workHour\":\"" + workHours[i+1].value + "\",\"workPercentage\":\"" + workPercentages[i+1].value;
			
			if(taskIds[i+1].value != ""){
				works[i] = works[i] + "\",\"taskId\":\"" + taskIds[i+1].value;
			}
			works[i] = works[i] + "\"}";
		}
		var basePath = $('#basePath').attr("value");
		var jsonData = str + "\"works\":["+works.join(",")+"]}]";
		
		$.post(basePath+"daily/addNewDaily.do",{jsonData:jsonData},function(data){

			var flag = window.confirm("提交成功！");
			if(flag){
				window.location.reload(false);
			}

		});		
	}
	</script>
	<%@ include file="/WEB-INF/view/app/daily/subMenu.jsp" %>
	<input type="hidden" value="<%=basePath %>" id="basePath"/>
	<div id="contentTop">
		<p style="font-size: 0.8cm; font-weight:bolder; text-align: center; padding-top: 20px; padding-bottom: 10px;">工作日报</p>
	</div>	
	
	<div class="breadLine">
		<div class="div-inline">部门：系统管理部部  应用开发组</div>
		<div class="div-inline">姓名：李小柱</div>
		<div class="div-inline">岗位：JAVA开发工程师</div>
		<div class="div-inline">日期：2014-11-20</div>
	</div>
	
	<form action="<%=basePath%>daily/createNewDaily.do" method="post" id="dailyForm" name="dailyForm">
		<input type="hidden" id="employeeId" name="employeeId" value="1"/>
	<!-- 工作总结 -->
	<div class="wrapper" style="margin-left: 340px">
		<div class="widget">
			<div class="whead" style="height: 40px">
				<h6 style="font-size: 0.5cm">工作总结</h6>
					<div align="right" style="padding: 8px 10px 7px 0px;">
						<input type="button" class="buttonS bGreen" value="添加" onclick="addRows();"/>
					</div>
				<div class="clear"></div>
			</div>
			<div id="dyn" class="hiddenpars">
				<table class="tableWorkContent" id="workContentTable">
					<thead id="headContent">
						<tr class="columnWorkContent">
							<th class="thWorkType">类型</th>
							<th class="thWorkTask" style="vertical-align: middle;">工作任务</th>
							<th class="thWorkResult" style="vertical-align: middle;">完成结果</th>
							<th class="thWorkHour">工作量(h)</th>
							<th class="thWorkPercentage">完成比例(100%)</th>
							<th class="thWorkOperate">操作</th>
						</tr>
					</thead>
					<tbody id="bodyContent">
						<!-- 隐藏一行，用于添加 -->
						<tr class="columnWorkContent" style="display: none;">
							<td class="thWorkType">
								<select id="workType" name="workType">
									<option value="0" selected="selected">指派</option>
									<option value="1">非指派</option>
								</select>
							</td>
							<td class="thWorkTask">
								<textarea id="workContent" name="workContent" cols="5" rows="3"></textarea>
								<input type="hidden"  name="taskId" id="taskId"/>
							</td>
							<td class="thWorkResult">
								<textarea id="workResult" name="workResult" cols="5" rows="3"></textarea>
							</td>
							<td class="thWorkHour">
								<input type="text" id="workHour" name="workHour" style="text-align: center;"/>
							</td>
							<td class="thWorkPercentage">
								<select id="workPercentage" name="workPercentage">
									<option value="100" selected="selected">100%</option>
									<option value="90">90%</option>
									<option value="80">80%</option>
									<option value="70">70%</option>
									<option value="60">60%</option>
									<option value="50">50%</option>
									<option value="40">40%</option>
									<option value="30">30%</option>
									<option value="20">20%</option>
									<option value="10">10%</option>
									<option value="0">0</option>
								</select>
							</td>
							<td class="thWorkOperate">
								
								<input type="button" class="buttonS bRed" value="删除" onclick="return deleteRow(this);" />
							</td>
						</tr>
						<tr class="columnWorkContent">
							<td class="thWorkType">
								<select id="workType" name="workType">
									<option value="0" selected="selected">指派</option>
									<option value="1">非指派</option>
								</select>
							</td>
							<td class="thWorkTask">
								<textarea id="workContent" name="workContent" cols="5" rows="3"></textarea>
								<input type="hidden"  name="taskId" id="taskId"/>
							</td>
							<td class="thWorkResult">
								<textarea id="workResult" name="workResult" cols="5" rows="3"></textarea>
							</td>
							<td class="thWorkHour">
								<input type="text" id="workHour" name="workHour" style="text-align: center;"/>
							</td>
							<td class="thWorkPercentage">
								<select id="workPercentage" name="workPercentage">
									<option value="100" selected="selected">100%</option>
									<option value="90">90%</option>
									<option value="80">80%</option>
									<option value="70">70%</option>
									<option value="60">60%</option>
									<option value="50">50%</option>
									<option value="40">40%</option>
									<option value="30">30%</option>
									<option value="20">20%</option>
									<option value="10">10%</option>
									<option value="0">0</option>
								</select>
							</td>
							<td class="thWorkOperate">
								
								<input type="button" class="buttonS bRed" value="删除" onclick="return deleteRow(this);" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	

	<div class="wrapper" style="margin-left: 340px">
		<div class="widget">
			<div class="whead" style="height: 40px">
				<h6 style="font-size: 0.5cm">遇到的问题与困难</h6>
				<div class="clear"></div>
			</div>
		</div>
		
		<div id="dyn" class="hiddenpars">
			<textarea id="dailyProblem" name="dailyProblem" rows="6" cols="10"></textarea>
		</div>
	</div>	
	
	<div class="wrapper" style="margin-left: 340px">
		<div class="widget">
			<div class="whead" style="height: 40px">
				<h6 style="font-size: 0.5cm">心得与建议</h6>
				<div class="clear"></div>
			</div>
		</div>
		
		<div id="dyn" class="hiddenpars">
			<textarea id="dailyLearned" name="dailyLearned" rows="6" cols="10"></textarea>
		</div>
	</div>	
	
	<div class="wrapper" style="margin-left: 340px">
		<div class="widget">
			<div class="whead" style="height: 40px">
				<h6 style="font-size: 0.5cm">明日安排</h6>
				<div class="clear"></div>
			</div>
		</div>
		
		<div id="dyn" class="hiddenpars">
			<textarea id="dailyNextPlan" name="dailyNextPlan" rows="6" cols="10"></textarea>
		</div>
	</div>
	
	<div align="center" style="padding-top: 30px">
		<input type="button" id="submitDaily" name="submitDaily" class="buttonS bGreen" style="width: 4cm; height: 1cm; font-size: 0.6cm;" 
		value="提交" onclick="submitDailyForm()"/>
	</div>
	
	</form>
</body>

</html>