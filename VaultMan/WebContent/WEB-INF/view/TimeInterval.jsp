<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时段授权</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/Styles/DateTime/jquery.datetimepicker.css">
<link href="<%=path %>/style/Calendar_blue.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/ConfigGroup.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/Scripts/Calendar/WdatePicker.js"></script>
<script type="text/javascript">
(function(){
	window['common']={};

	function method01(){
		alert("method01");
	}
	window["common"]["m1"]=method01;

	function method02(){
		alert("mehtod02");
	}
	window["common"]["m2"]=method02;
	
	/**
	 * 获得项目跟路径
	 */
	function getProjectRootPath(){
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		var localhostPath = curWwwPath.substring(0,pos);
		var projectName = pathName.substring(0,pathName.substr(1).indexOf("/") + 1);
		
		return (localhostPath + projectName);
	}
	window["common"]["getProjectRootPath"]=getProjectRootPath;

})();

function DeleteTimeInterval(num){
	//加一个删除确认提示框
	$(window.parent.showDialog("确定要删除该时段授权吗？", "execDelete(" + num + ")"));
}

function execDelete(num){
	$("#timeIntervalList tr").eq(num).remove();
	var rowNum = $("#timeIntervalList tr").length;
	
	for(var i=num;i <= rowNum;i++)
	{
		var numNew = i+1;
		var trInfo = $("#timeIntervalList tr").eq(i);
		trInfo.find("td").eq(0).text(i);
		var subgroupcode = $("#subgroupcode"+numNew);
		subgroupcode.attr("id","subgroupcode"+i);
		subgroupcode.attr("name","subgroupcode"+i);
		subgroupcode.attr("onchange","TimeIntervaldata(this.value,"+i+")");
		
		var begindate = $("#begindate"+numNew);
		begindate.attr("id","begindate"+i);
		begindate.attr("name","begindate"+i);
		
		var enddate = $("#enddate"+numNew);
		enddate.attr("id","enddate"+i);
		enddate.attr("name","enddate"+i);
		
		var deleteTimeInterval = $("#deleteTimeInterval"+numNew);
		deleteTimeInterval.attr("id","deleteTimeInterval"+i);
		deleteTimeInterval.attr("name","deleteTimeInterval"+i);
		deleteTimeInterval.attr("onclick","DeleteTimeInterval("+i+")");
	}
	
	var msgObj = new Object();
	msgObj.message = "时段授权删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
function SaveTimeInterval() {
	var rowNum = $("#timeIntervalList tr").length-1;
	var frm = $("form")[0];
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveTimeInterval.action?type=1&organizationCode="+${organizationCode}+"&timeIntervalNum="+rowNum;
	frm.submit();
}

function AddTimeIntervalDataTr(){
	var groupJson = ${groupJson};
	var tableObj = $("#timeIntervalList");
	var rowNum = $("#timeIntervalList tr").length;
	if(rowNum>30)
	{
		//alert("时段权限最多可设置30组！");
		$(window.parent.showMessage("时段权限最多可设置30组！"));
		return false;
	}
	var trHtml = "<tr><td>"+rowNum+"</td>";
	trHtml += "<td><select id=\"subgroupcode"+rowNum+"\" name=\"subgroupcode"+rowNum+"\" onchange=\"TimeIntervaldata(this.value,"+rowNum+")\" ><option value=\"\">请选择</option>";
	for(var i =0;i<groupJson.length;i++) {
		trHtml += "<option value=\""+groupJson[i].code+"\">"+groupJson[i].name+"</option>";
	}
	trHtml += "</select></td>";
	
	//alert(trHtml);
	
	trHtml += "<td></td>";
	trHtml += "<td><input type=\"text\" id=\"begindate"+rowNum+"\" name=\"begindate"+rowNum+"\"  onFocus=\"WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})\" /></td>";
	trHtml += "<td><input type=\"text\" id=\"enddate"+rowNum+"\" name=\"enddate"+rowNum+"\" onFocus=\"WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})\" /></td>";
	trHtml += "<td><a href=\"#\" class=\"delete\" onclick=\"DeleteTimeInterval("+rowNum+");\" name=\"deleteTimeInterval"+rowNum+"\" id=\"deleteTimeInterval"+rowNum+"\">删除</a></td>";
	trHtml += "</tr>";
	tableObj.append(trHtml);
	//$("#zhixingdataNum").val(parseInt($("#zhixingdataNum").val())+1);
}

function TimeIntervaldata(groupCode,rowNum){
	var groupJson = ${groupJson};
	for(var i =0;i<groupJson.length;i++) {
		if(groupJson[i].code == groupCode)
		{
			var a = $("#timeIntervalList tr").eq(rowNum);
			a.find("td").eq(2).text(groupJson[i].usernames);
		}
	}
	
}
</script>
</head>

<body>

<div id="iframeContent">
	<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
	<form action="SaveTimeInterval" id="timeIntervalId" focus="id" method="post" style="margin:0;">
		<input type="hidden" id="tid" name="tid" value="${tid}"/>
		<!-- 
		<div id="contentBox">
			<div class="content-top">
				<ul id="content-nav">
					<li class="current"><a href="#" class="Project">时段授权</a></li>
				</ul>
				<div class="content-top-button">
				    <a href="#" class="Save" onclick="SaveTimeInterval()">保存</a>
					<a href="#" class="Add" onclick="AddTimeIntervalDataTr();">增加</a>
				</div>
			</div>
		 -->
			
			<div id="content-content">
				<!-- <div class="contentItem"> -->
				<table cellpadding="0" cellspacing="0" border="0" id="timeIntervalList" name="timeIntervalList" class="newdataList" width="100%">
					<tr>
						<th width="10%">序号</th>
						<th width="15%">操作组</th>
						<th width="20%">人员</th>
						<th width="20%">开始时间</th>
						<th width="20%">结束时间</th>
						<th width="25%">操作</th>
					</tr>	
					<c:forEach items="${controllTimeList}" var="controllTimeItem">
					<tr>
						<td>${controllTimeItem.sequence}</td>
						<td>
							<select id="subgroupcode${controllTimeItem.sequence}" name="subgroupcode${controllTimeItem.sequence}" onchange="TimeIntervaldata(this.value,${controllTimeItem.sequence});" >
								<c:forEach items="${groupList}" var="groupItem">
									<option value="${groupItem.code}"  <c:if test="${groupItem.code == controllTimeItem.subgroupcode}">selected="selected"</c:if>>${groupItem.name}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<c:forEach items="${groupList}" var="groupItem">
								<c:if test="${groupItem.code == controllTimeItem.subgroupcode}">${groupItem.usernames}</c:if>
							</c:forEach>
						</td>
						<td>
						    <input type="text" id="begindate${controllTimeItem.sequence}" name="begindate${controllTimeItem.sequence}" value="${controllTimeItem.begindateStr}" onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
						</td>
						<td>
						    <input type="text" id="enddate${controllTimeItem.sequence}" name="enddate${controllTimeItem.sequence}" value="${controllTimeItem.enddateStr}" onFocus="WdatePicker({startDate:'%y-%M-01 00:00',dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
						</td>
						<td><a href="#" onclick="DeleteTimeInterval(${controllTimeItem.sequence});" name="deleteTimeInterval${controllTimeItem.sequence}" id="deleteTimeInterval${controllTimeItem.sequence}" class="delete">删除</a></td>
					</tr>
					</c:forEach>
				</table>
				<!-- </div> -->
				
			</div>
		<!-- </div> -->
	</form>
	<div style="clear:both"></div>
</div>
<!-- 
<div id="MessageBox">
	<div class="UserInfoTitle"><h1 class="PopTitle">消息提示</h1></div>
	<div class="UserInfoTable">
		<p></p>
	</div>
	<div class="UserInfoBtn">
		<a href="javascript:$.unblockUI();" style="">取消</a><a id="MsgOK" href="javascript:void(0)" style="">确定</a>
	</div>
</div>
 -->
</body>
</html>
