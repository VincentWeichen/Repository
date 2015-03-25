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
<title>排班设置</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/ConfigGroup.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/Scripts/Calendar/WdatePicker.js"></script>
<script type="text/javascript">
//var thisPage= "";
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

function DeleteConfigGroup(num, type) {
	$(window.parent.showDialog("确定要删除该时段授权吗？", "execDelete(" + num + ", " + type + ")"));
}

function execDelete(num,type){
	
	var msgObj = new Object();
	msgObj.messageType = "success";
	
	
	if(type==1)
	{
		$("#zhixingdataList tr").eq(num).remove();
		var rowNum = $("#zhixingdataList tr").length;
		for(var i=num;i <= rowNum;i++)
		{
			var numNew = i+1;
			var trInfo = $("#zhixingdataList tr").eq(i);
			trInfo.find("td").eq(0).text(i);
			var subgroupcode = $("#subgroupcode_Zhixing"+numNew);
			subgroupcode.attr("id","subgroupcode_Zhixing"+i);
			subgroupcode.attr("name","subgroupcode_Zhixing"+i);
			subgroupcode.attr("onchange","Zhixingdata(this.value,"+i+")");
			var timeduration = $("#timeduration_Zhixing"+numNew);
			timeduration.attr("id","timeduration_Zhixing"+i);
			timeduration.attr("name","timeduration_Zhixing"+i);
			
			var deleteConfigGroup1 = $("#deleteConfigGroup1"+numNew);
			deleteConfigGroup1.attr("id","deleteConfigGroup1"+i);
			deleteConfigGroup1.attr("name","deleteConfigGroup1"+i);
			deleteConfigGroup1.attr("onclick","DeleteConfigGroup("+i+",1)");
			
		}
		
		msgObj.message = "执行循环删除成功！";
	}
	if(type==2)
	{
		$("#yuyuedataList tr").eq(num).remove();
		var rowNum = $("#yuyuedataList tr").length;
		for(var i=num;i <= rowNum;i++)
		{
			var numNew = i+1;
			var trInfo = $("#yuyuedataList tr").eq(i);
			trInfo.find("td").eq(0).text(i);
			var subgroupcode = $("#subgroupcode_Yuyue"+numNew);
			subgroupcode.attr("id","subgroupcode_Yuyue"+i);
			subgroupcode.attr("name","subgroupcode_Yuyue"+i);
			subgroupcode.attr("onchange","Yuyuedata(this.value,"+i+")");
			var timeduration = $("#timeduration_Yuyue"+numNew);
			timeduration.attr("id","timeduration_Yuyue"+i);
			timeduration.attr("name","timeduration_Yuyue"+i);
			
			var deleteConfigGroup2 = $("#deleteConfigGroup2"+numNew);
			deleteConfigGroup2.attr("id","deleteConfigGroup2"+i);
			deleteConfigGroup2.attr("name","deleteConfigGroup2"+i);
			deleteConfigGroup2.attr("onclick","DeleteConfigGroup("+i+",2)");
		}
		msgObj.message = "预约循环删除成功！";
	}
	
	$(window.parent.showMessage(msgObj));
}
function SaveZhixingGroup() {
	var rowNum = $("#zhixingdataList tr").length-1;
	var frm = $("form")[0];
	var tid = ${tid};
	//alert(frm);
	//alert(rowNum);
	//alert(tid);
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveConfigGroup.action?type=1&organizationCode="+${organizationCode}+"&zhixingdataNum="+rowNum+"&tid="+${tid};
	frm.submit();
}

function SaveYuyueGroup() {
	var rowNum = $("#yuyuedataList tr").length-1;
	var frm = $("form")[1];
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveConfigGroup.action?type=2&organizationCode="+${organizationCode}+"&yuyuedataNum="+rowNum+"&tid="+${tid};
	frm.submit();
}
function AddZhixingdataTr(){
	var groupJson = ${groupJson};
	var tableObj = $("#zhixingdataList");
	if (isCreate(tableObj)) {
		var rowNum = $("#zhixingdataList tr").length;
		var trHtml = "<tr><td>"+rowNum+"</td>";
		trHtml += "<td><select id=\"subgroupcode_Zhixing"+rowNum+"\" name=\"subgroupcode_Zhixing"+rowNum+"\" onchange=\"Zhixingdata(this.value,"+rowNum+");\" ><option value=\"\">请选择</option>";
		for(var i =0;i<groupJson.length;i++) {
			trHtml += "<option value=\""+groupJson[i].code+"\">"+groupJson[i].name+"</option>";
		}
		trHtml += "</select></td>";
		
		trHtml += "<td></td>";
		trHtml += "<td><select id=\"timeduration_Zhixing"+rowNum+"\" name=\"timeduration_Zhixing"+rowNum+"\" >";
		for(var i =1;i<=41;i++) {
			trHtml += "<option value=\""+24*i+"\">"+24*i+"</option>";
		}
		trHtml += "</select></td>";
		trHtml += "<td><a href=\"#\" class=\"delete\" onclick=\"DeleteConfigGroup("+rowNum+",1);\" name=\"deleteConfigGroup1"+rowNum+"\" id=\"deleteConfigGroup1"+rowNum+"\">删除</a></td>";
		trHtml += "</tr>"
		tableObj.append(trHtml);
	}
	else
		$(window.parent.showMessage("执行循环有空白信息，请全部制定后再添加！"));
}
function AddYuyuedataListTr(){
	var groupJson = ${groupJson};
	var tableObj = $("#yuyuedataList");
	if (isCreate(tableObj)) {
		var rowNum = $("#yuyuedataList tr").length;
	
		var trHtml = "<tr><td>"+rowNum+"</td>";
		trHtml += "<td class=\"itenName\"><select id=\"subgroupcode_Yuyue"+rowNum+"\" name=\"subgroupcode_Yuyue"+rowNum+"\" onchange=\"Yuyuedata(this.value,"+rowNum+");\" ><option value=\"\">请选择</option>";
		for(var i =0;i<groupJson.length;i++) {
			trHtml += "<option value=\""+groupJson[i].code+"\">"+groupJson[i].name+"</option>";
		}
		trHtml += "</select></td>";
		
		trHtml += "<td></td>";
		trHtml += "<td><select id=\"timeduration_Yuyue"+rowNum+"\" name=\"timeduration_Yuyue"+rowNum+"\" >";
		for(var i =1;i<=41;i++) {
			trHtml += "<option value=\""+24*i+"\">"+24*i+"</option>";
		}
		trHtml += "</select></td>";
		trHtml += "<td><a href=\"#\" class=\"delete\" onclick=\"DeleteConfigGroup("+rowNum+",2);\" name=\"deleteConfigGroup2"+rowNum+"\" id=\"deleteConfigGroup2"+rowNum+"\">删除</a></td>";
		trHtml += "</tr>"
		tableObj.append(trHtml);
	}
	else
		$(window.parent.showMessage("预约循环有空白信息，请全部制定后再添加！"));
}

function isCreate(tableObj) {
	var retVal = true;
	var groupBylastTr = tableObj.find("tr:last select:first");
	if (groupBylastTr.val() == "")
		retVal = false;
	return retVal;
}

function Zhixingdata(groupCode,rowNum){
	var groupJson = ${groupJson};
	for(var i =0;i<groupJson.length;i++) {
		if(groupJson[i].code == groupCode)
		{
			var trInfo = $("#zhixingdataList tr").eq(rowNum);
			trInfo.find("td").eq(2).text(groupJson[i].usernames);
		}
	}
	
}
function Yuyuedata(groupCode,rowNum){
	var groupJson = ${groupJson};
	for(var i =0;i<groupJson.length;i++) {
		if(groupJson[i].code == groupCode)
		{
			var trInfo = $("#yuyuedataList tr").eq(rowNum);
			trInfo.find("td").eq(2).text(groupJson[i].usernames);
		}
	}
	
}

function dataSave() {
	//SaveYuyueGroup();
	//SaveZhixingGroup();

	var zhixingRowNum = $("#zhixingdataList tr").length-1;
	var yuyueRowNum = $("#yuyuedataList tr").length-1;	

	var frm = $("form")[0];
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveConfigGroup.action?type=1&organizationCode="+${organizationCode}+"&zhixingdataNum="+zhixingRowNum+"&yuyuedataNum="+yuyueRowNum+"&tid="+${tid};
	frm.submit();
	
	
	var msgObj = new Object();
	msgObj.message = "保存成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

</script>
</head>

<body>

<div id="iframeContent">
<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
<input type="hidden" id="tid" name="tid" value="${tid}"/>

	<!-- <div id="contentBox-Zhixingdata"> -->
		<!-- 
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Home">执行循环</a></li>
			</ul>
			<div class="content-top-button">
			开始时间：<input type="text" id="begindate_Zhixing" name="begindate_Zhixing" value="${begindate_Zhixing}" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
			时<input type="text" id="switchtime_Zhixing_H" name="switchtime_Zhixing_H" value="${switchtime_Zhixing_H}" class="" />
			 ： 分<input type="text" id="switchtime_Zhixing_M" name="switchtime_Zhixing_M" value="${switchtime_Zhixing_M}" class="" />  
			    <a href="#" class="Save" onclick="SaveZhixingGroup()">保存</a>
				<a href="#" onclick="AddZhixingdataTr();" class="Add">增加</a>
			</div>
		</div>
		 -->
		
		<div id="content-content">
			<form action="SaveConfigGroup" id="ZhixingId" focus="id" method="post" style="margin:0;">
				<div class="contentItem">
					<!--<form action="SaveConfigGroup" id="ZhixingId" focus="id" method="post" style="margin:0;">-->
						<div class="newdataTitle">
							<h1>执行循环</h1>
							<div class="newdataTitleOption">
								开始时间：<input type="text" id="begindate_Zhixing" name="begindate_Zhixing" value="${begindate_Zhixing}" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
								时<input type="text" id="switchtime_Zhixing_H" name="switchtime_Zhixing_H" value="${switchtime_Zhixing_H}" class="" />
							 	： 分<input type="text" id="switchtime_Zhixing_M" name="switchtime_Zhixing_M" value="${switchtime_Zhixing_M}" class="" />  
							    	<!-- <a href="#" class="Save" onclick="SaveZhixingGroup()">保存</a> -->
									<a href="#" onclick="AddZhixingdataTr();" class="add">增加</a>
							</div>
						</div>
						<table cellpadding="0" cellspacing="0" border="0" id="zhixingdataList" class="newdataList" width="100%">
							<tr class="title">
								<th width="10%">序号</th>
								<th width="15%">组编号</th>
								<th width="30%">人员</th>
								<th width="25%">持续时间</th>
								<th width="20%">操作</th>
							</tr>	
						<c:forEach items="${zhixingdataList}" var="configGroupItem">
							<tr>
								<td>${configGroupItem.sequence}</td>
								<td>
									<select id="subgroupcode_Zhixing${configGroupItem.sequence}" name="subgroupcode_Zhixing${configGroupItem.sequence}" onchange="Zhixingdata(this.value,${configGroupItem.sequence});" >
										<option value="">请选择</option>
										<c:forEach items="${groupList}" var="groupItem">
										<option value="${groupItem.code}"  <c:if test="${groupItem.code == configGroupItem.subgroupcode}">selected="selected"</c:if>>${groupItem.name}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<c:forEach items="${groupList}" var="groupItem">
										<c:if test="${groupItem.code == configGroupItem.subgroupcode}">${groupItem.usernames}</c:if>
									</c:forEach>
								</td>
								<td>
									<select id="timeduration_Zhixing${configGroupItem.sequence}" name="timeduration_Zhixing${configGroupItem.sequence}" >";
										<c:forEach begin="1" end="41" step="1" varStatus="status">
											<option value="${status.index*24}" <c:if test="${status.index*24 == configGroupItem.timeduration}">selected="selected"</c:if>>${status.index*24}</option>
										</c:forEach>
									</select>
								</td>
								<td><a href="#" onclick="DeleteConfigGroup(${configGroupItem.sequence},1);" name="deleteConfigGroup1${configGroupItem.sequence}" id="deleteConfigGroup1${configGroupItem.sequence}" class="delete">删除</a></td>
							</tr>
						</c:forEach>
						
						</table>
					<!--</form>-->
				</div>
			
				<div class="contentItem">
					<!--<form action="SaveConfigGroup" id="YuyuedataId" focus="id" method="post" style="margin:0;">-->
						<div class="newdataTitle">
							<h1>预约循环</h1>
							<div class="newdataTitleOption">
								开始时间：<input type="text" id="begindate_Yuyue" name="begindate_Yuyue" value="${begindate_Yuyue}" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
								时<input type="text" id="switchtime_Yuyue_H" name="switchtime_Yuyue_H" value="${switchtime_Yuyue_H}" class="" />
								 ： 分<input type="text" id="switchtime_Yuyue_M" name="switchtime_Yuyue_M" value="${switchtime_Yuyue_M}" class="" /> 
									<a href="#" class="Add" onclick="AddYuyuedataListTr();">增加</a>
							</div>
						</div>
						<table cellpadding="0" cellspacing="0" border="0" id="yuyuedataList" class="newdataList" width="100%">
							<tr>
								<th width="10%">序号</th>
								<th width="15%">组编号</th>
								<th width="30%">人员</th>
								<th width="25%">持续时间</th>
								<th width="20%">操作</th>
							</tr>	
							<c:forEach items="${yuyuedataList}" var="configGroupItem">
							<tr>
								<td>${configGroupItem.sequence}</td>
								<td>
									<select id="subgroupcode_Yuyue${configGroupItem.sequence}" name="subgroupcode_Yuyue${configGroupItem.sequence}" onchange="Yuyuedata(this.value,${configGroupItem.sequence});" >
									<option value="">请选择</option>
									<c:forEach items="${groupList}" var="groupItem">
									<option value="${groupItem.code}"  <c:if test="${groupItem.code == configGroupItem.subgroupcode}">selected="selected"</c:if>>${groupItem.name}</option>
									</c:forEach>
									</select>
								</td>
								<td>
									<c:forEach items="${groupList}" var="groupItem">
										<c:if test="${groupItem.code == configGroupItem.subgroupcode}">${groupItem.usernames}</c:if>
									</c:forEach>
								</td>
								<td>
									<select id="timeduration_Yuyue${configGroupItem.sequence}" name="timeduration_Yuyue${configGroupItem.sequence}" >";
										<c:forEach begin="1" end="41" step="1" varStatus="status">
											<option value="${status.index*24}" <c:if test="${status.index*24 == configGroupItem.timeduration}">selected="selected"</c:if>>${status.index*24}</option>
										</c:forEach>
									</select>
								</td>
								<td><a href="#" onclick="DeleteConfigGroup(${configGroupItem.sequence},2);" name="deleteConfigGroup2${configGroupItem.sequence}" id="deleteConfigGroup2${configGroupItem.sequence}"  class="delete">删除</a></td>
							</tr>
						</c:forEach>		
						</table>
					<!--</form>-->
				</div>
			</form>
		</div>
		<!-- </div> -->
	
	
	
	<!-- <div id="contentBox-YuyuedataList"> -->
		<!-- 
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Project">预约循环</a></li>
			</ul>
			<div class="content-top-button">	
			开始时间：<input type="text" id="begindate_Yuyue" name="begindate_Yuyue" value="${begindate_Yuyue}" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
			时<input type="text" id="switchtime_Yuyue_H" name="switchtime_Yuyue_H" value="${switchtime_Yuyue_H}" class="" />
			 ： 分<input type="text" id="switchtime_Yuyue_M" name="switchtime_Yuyue_M" value="${switchtime_Yuyue_M}" class="" /> 
			    <a href="#" class="Save" onclick="SaveYuyueGroup()">保存</a>
				<a href="#" class="Add" onclick="AddYuyuedataListTr();">增加</a>
			</div>
		</div>
		 -->
		
		
	<!-- </div> -->
	
	<div style="clear:both"></div>
</div>
<div id="MessageBox">
	<div class="UserInfoTitle"><h1 class="PopTitle">消息提示</h1></div>
	<div class="UserInfoTable">
		<p></p>
	</div>
	<div class="UserInfoBtn">
		<a href="javascript:$.unblockUI();" style="">取消</a><a id="MsgOK" href="javascript:void(0)" style="">确定</a>
	</div>
</div>

</body>
</html>
