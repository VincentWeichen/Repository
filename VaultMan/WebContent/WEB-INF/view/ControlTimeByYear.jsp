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
<title>管制时间</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/Styles/DateTime/jquery.datetimepicker.css">
<!-- <link href="<%=path %>/style/Calendar_blue.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/ControlTime.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>

<script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Calendar.js"></script>

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

/* 新增年循环 */
/* 执行日操作 */
function AddYearZhixingWindow(type, id) {
	var confirmObj = new Object();
	confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKYearZhixing(1, " + type + ", " + id + ")";
	confirmObj.isDisplay = true;
	
	var closeObj = new Object();
	closeObj.isDisplay = false;
	
	var dialogObj = new Object();
	dialogObj.funcName = "Users";
	type == 1 ? dialogObj.titleText = "新增工作执行日管制" : dialogObj.titleText = "新增休息执行日管制";
	//dialogObj.titleText = "新增工作日管制时段";
	dialogObj.message = $("#UserInfo").html();
	dialogObj.confirm = confirmObj;
	dialogObj.close = closeObj;
	
	$(window.parent.showUDefinedDialog(dialogObj));
	
	addYearCallback(type,id);
}

//向子窗体赋值和设置时间控件事件方法
function addYearCallback(type,id) {
	var parentObj = $(window.parent.document);
	parentObj.find("#controlltimeYearId").val(id);
	parentObj.find("#controlltimedetailYearZhixingType").val(type);
}

function EditcontrolltimeYearZhixing(id,type) {
	var organizationCode = $("#organizationCode").val();
	//$("#controlltimedetailZhouType").val(type);
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditcontrolltimeZhixingYear.action", { id: id, organizationCode:organizationCode}, function (result) {
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKYearZhixing(2, " + type + ", " + id + ")";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		type == 1 ? dialogObj.titleText = "编辑工作执行日管制时段" : dialogObj.titleText = "编辑休息执行日管制时段";
		//dialogObj.titleText = "编辑工作日管制时段";
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		ControlltimeWeeksWorKYearZhixingCallback(result,type);
    }, "json");
	return false;
}

function ControlltimeWeeksWorKYearZhixingCallback(result, type) {
	var parentObj = $(window.parent.document);
	parentObj.find("#controlltimedetailYearZhixingType").val(type);
	parentObj.find("#begindateYear").val(result.begindate);
	parentObj.find("#enddateYear").val(result.enddate);
	parentObj.find("#controlltimedetailYearZhixingId").val(result.id);
	parentObj.find("#controlltimeYearId").val(result.controlltimeid);
	//AddYearZhixingWindow(type);
	/*
	$("#begindate").val(result.begindate);
	$("#enddate").val(result.enddate);
	$("#controlltimedetailId").val(result.id);
	$("#controlltimeId").val(result.controlltimeid);
	*/
}

//新增 周循环下工作日和休息日时间段的保存方法
function SaveControlltimeWeeksWorKYearZhixing(flag, type, id) {
	var parentObj = $(window.parent.document);
	var controlltimedetailYearZhixingTypeVal = parentObj.find("#controlltimedetailYearZhixingType").val();
	
	//var controlltimedetailIdVal = parentObj.find("#controlltimedetailId").val();
	var controlltimedetailYearZhixingIdVal = parentObj.find("#controlltimedetailYearZhixingId").val();
	
	var begindateYearVal = parentObj.find("#begindateYear").val();
	var enddateYearVal = parentObj.find("#enddateYear").val();
	var controlltimeYearIdVal = parentObj.find("#controlltimeYearId").val();
	$("#controlltimedetailYearZhixingType").val(controlltimedetailYearZhixingTypeVal);
	
	//$("#controlltimedetailId").val(controlltimedetailIdVal);
	$("#controlltimedetailYearZhixingId").val(controlltimedetailYearZhixingIdVal);
	
	$("#begindateYear").val(begindateYearVal);
	$("#enddateYear").val(enddateYearVal);
	
	var frm = $("form")[0];
	
	var msg = "";
	
	var begindates = begindateYearVal.split("年");
	var enddates = enddateYearVal.split("年");
	var begindatesYue = begindates[0].split("月");
	var enddatesYue = enddates[0].split("月");
	/**
	if(begindates[0]>enddates[0])
	{
		msg+="开始时间不能大于结束时间！";
	}else  if(begindates[0]==enddates[0])
	{
		if(begindatesYue[0]>enddatesYue[0])
		{
			msg+="开始时间不能大于结束时间！";
		}else if(begindatesYue[0]==enddatesYue[0])
		{
			if(begindatesYue[1]>enddatesYue[1])
			{
				msg+="开始时间不能大于结束时间！";
			}
		}
	}
	if(msg !=null && msg.length > 0 )
	{
		$(window.parent.showMessage(msg));
		return false;
	}
	**/
	flag == 1 ? msg += "新增" : msg += "编辑";
	type == 1 ? msg += "工作执行日" : msg += "休息执行日";
	
	msg += "管制成功！";
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveControlltime.action?controlltimeType=2&organizationCode="+${organizationCode}+"&controlltimeYearId="+controlltimeYearIdVal;
	frm.submit();
	
	
	//type % 2 != 0 ? msg = "新增" : msg = "编辑";
	
	var msgObj = new Object();
	msgObj.message = msg;	//"新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function DeleteControlltimeercisedate(id){
	$(window.parent.showDialog("确定要删除该管制时间段吗？", "ExeDeleteControlltimeercisedate(" + id + ")"));
}
function ExeDeleteControlltimeercisedate(id) {
	document.location.href='DeleteControlltimeercisedate.action?id=' +id+"&organizationCode="+${organizationCode};
	var msgObj = new Object();
	msgObj.message = "管制时段删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

/* 执行日操作 End */

/* 管制操作 */
/* 新增管制 */
function AddYearGuanzhiWindow(type, id) {
	var confirmObj = new Object();
	confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKYearGuanzhi(1, " + type + ", " + id + ")";
	confirmObj.isDisplay = true;
	
	var closeObj = new Object();
	closeObj.isDisplay = false;
	
	var dialogObj = new Object();
	dialogObj.funcName = "Users";
	type == 1 ? dialogObj.titleText = "新增工作日管制时段" : dialogObj.titleText = "新增休息日管制时段";
	//dialogObj.titleText = "新增工作日管制时段";
	dialogObj.message = $("#YearUserInfoGuanzhi").html();
	dialogObj.confirm = confirmObj;
	dialogObj.close = closeObj;
	
	$(window.parent.showUDefinedDialog(dialogObj));
	
	addYearGuanzhiCallback(type,id);
}

//向子窗体赋值和设置时间控件事件方法
function addYearGuanzhiCallback(type,id) {
	var parentObj = $(window.parent.document);
	$("#controlltimeYearId").val(id);
	parentObj.find("#controlltimedetailYearGuanzhiType").val(type);
}
/* 新增管制 End */

function EditcontrolltimeYearGuanzhi(id,type) {
	var organizationCode = $("#organizationCode").val();
	//$("#controlltimedetailZhouType").val(type);
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditcontrolltimeGuanzhiYear.action", { id: id, organizationCode:organizationCode}, function (result) {
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKYearGuanzhi(2, " + type + ", " + id + ")";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		type == 1 ? dialogObj.titleText = "编辑工作日管制时段" : dialogObj.titleText = "编辑休息日管制时段";
		//dialogObj.titleText = "编辑工作日管制时段";
		dialogObj.message = $("#YearUserInfoGuanzhi").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		ControlltimeWeeksWorKYearGuanzhiCallback(result,type);
    }, "json");
	return false;
}
function ControlltimeWeeksWorKYearGuanzhiCallback(result, type) {
	var parentObj = $(window.parent.document);
	parentObj.find("#controlltimedetailYearGuanzhiType").val(type);
	parentObj.find("#begindateYearGuanzhi").val(result.begindate);
	parentObj.find("#enddateYearGuanzhi").val(result.enddate);
	$("#controlltimeYearId").val(result.controlltimeid);
	parentObj.find("#controlltimedetailYearGuanzhiId").val(result.id);
}

function SaveControlltimeWeeksWorKYearGuanzhi(flag, type, id) {
	var parentObj = $(window.parent.document);
	var controlltimedetailYearGuanzhiTypeVal = parentObj.find("#controlltimedetailYearGuanzhiType").val();
	
	//var controlltimedetailIdVal = parentObj.find("#controlltimedetailId").val();
	var controlltimedetailYearGuanzhiIdVal = parentObj.find("#controlltimedetailYearGuanzhiId").val();
	
	var begindateYearGuanzhiVal = parentObj.find("#begindateYearGuanzhi").val();
	var enddateYearGuanzhiVal = parentObj.find("#enddateYearGuanzhi").val();
	$("#controlltimedetailYearGuanzhiType").val(controlltimedetailYearGuanzhiTypeVal);
	
	//$("#controlltimedetailId").val(controlltimedetailIdVal);
	$("#controlltimedetailYearGuanzhiId").val(controlltimedetailYearGuanzhiIdVal);
	
	$("#begindateYearGuanzhi").val(begindateYearGuanzhiVal);
	$("#enddateYearGuanzhi").val(enddateYearGuanzhiVal);
	
	var frm = $("form")[1];
	
	var msg = "";
	var begindates = begindateYearGuanzhiVal.split(":");
	var enddates = enddateYearGuanzhiVal.split(":");
	if(begindates[0]>enddates[0])
	{
		msg+="开始时间不能大于结束时间！";
	}else if(begindates[0]==enddates[0])
	{
		if(begindates[1]>enddates[1])
		{
			msg+="开始时间不能大于结束时间！";
		}
	}
	if(msg !=null && msg.length > 0 )
	{
		$(window.parent.showMessage(msg));
		return false;
	}
	
	flag == 1 ? msg += "新增" : msg += "编辑";
	type == 1 ? msg += "工作日" : msg += "休息日";
	
	msg += "管制时段成功！";
	var controlltimeYearIdVal = $("#controlltimeYearId").val();
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveControlltime.action?controlltimeType=2&organizationCode="+${organizationCode}+"&controlltimeYearId="+controlltimeYearIdVal;
	frm.submit();
	
	
	//type % 2 != 0 ? msg = "新增" : msg = "编辑";
	
	var msgObj = new Object();
	msgObj.message = msg;	//"新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function DeletecontrolltimeZhou(id){
	$(window.parent.showDialog("确定要删除该管制时间段吗？", "ExeDeletecontrolltimeZhou(" + id + ")"));
}
function ExeDeletecontrolltimeZhou(id) {
	document.location.href='DeletecontrolltimeByYear.action?id=' +id+"&organizationCode="+${organizationCode};
	var msgObj = new Object();
	msgObj.message = "管制时段删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function DeleteGroupYear(id) {
	$(window.parent.showDialog("确定要删除该年循环管制组吗？", "ExeDeleteGroupYear(" + id + ")"));
}
function ExeDeleteGroupYear(id) {
	if (id == 0) {
		var table = $("#dataList" + id);
		table.prev().remove();
		table.remove();
		//return false;
	}
	else {
		//这里编写调用后台删除组方法（下面的注视是删除管制时间的方法）
 		document.location.href='DeleteControlltime.action?type=2&id=' +id+"&organizationCode="+${organizationCode};
	}
	
 	var msgObj = new Object();
 	msgObj.message = "年循环管制组删除成功！";
 	msgObj.messageType = "success";
 	$(window.parent.showMessage(msgObj));
}

</script>
</head>

<body>

<div id="iframeContent">

<!-- 
<div id="Content">
	<div id="contentBox">
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Home">周循环</a></li>
				<li><a href="#" class="Project">年循环</a></li>
				<li><a href="#" class="Project">假期/调班</a></li>
			</ul>
		</div> -->
		
		<div id="content-content">
			
			<div class="contentItem">
				<c:forEach items="${controlltimeYearList}" var="item">
					<div class="newdataTitle">
						<h1>年循环管制组</h1>
						<div class="newdataTitleOption" style="margin-top:10px"><a href="#" class="delete" onclick="DeleteGroupYear(${item.id});">删除管制组</a><a href="#" class="add" onclick="AddGroupYear();">新增管制组</a></div>
					</div>
					<table cellpadding="0" cellspacing="0" border="0" id="dataList${item.id}" class="newdataList" style="margin-bottom:15px" width="100%">
						<tr class="table-title">
							<th>序号</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th class="table-center">操作</th>
						</tr>
						<c:if test="${item.controlltimeercisedateWorkList != null}">
							<tr class="table-title-type">
								<th colspan="3">执行日</th>
								<th class="btnOption"><a href="#" class="add" onclick="AddYearZhixingWindow(1, ${item.id});">新增执行日</a></th>
							</tr>
							<c:forEach items="${item.controlltimeercisedateWorkList}" var="controlltimeItem" varStatus="status">
								<tr>
									<!-- <td>执行日</td> -->
									<td>${status.index+1}</td>
									<td class="TimeStart">${controlltimeItem.begindate}</td>
									<td class="TimeEnd">${controlltimeItem.enddate}</td>
									<td class="optionButtons table-center">
									<a href="#" class="edit" onclick="EditcontrolltimeYearZhixing(${controlltimeItem.id},1);">编辑</a>
									<a href="#" class="delete" onclick="DeleteControlltimeercisedate(${controlltimeItem.id});">删除</a>
									</td>
								</tr>
							</c:forEach>	
						</c:if>
						<c:if test="${item.controlltimedetailWorkList != null}">
							<tr class="table-title-type">
								<th colspan="3">管制时段</th>
								<th class="btnOption"><a href="#" class="add" onclick="AddYearGuanzhiWindow(1, ${item.id});">新增时段</a></th>
							</tr>
							<c:forEach items="${item.controlltimedetailWorkList}" var="controlltimeItem" varStatus="statusGuanzhi">
								<tr>
									<td>${statusGuanzhi.index+1}</td>
									<td class="TimeStart">${controlltimeItem.begindate}</td>
									<td class="TimeEnd">${controlltimeItem.enddate}</td>
									<td class="optionButtons table-center">
										<a href="#" class="edit" onclick="EditcontrolltimeYearGuanzhi(${controlltimeItem.id},1);">编辑</a>
										<a href="#" class="delete" onclick="DeletecontrolltimeZhou(${controlltimeItem.id});">删除</a>
									</td>
								</tr>
							</c:forEach>	
						</c:if>
					</table>
			
				</c:forEach>
			</div>	<!-- End .contentItem -->
		</div>	<!-- End #content-content -->
		
	<!-- </div>
	
	<div style="clear:both"></div>
</div> -->

</div>	<!-- IFrame -->

<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div> -->
	<input type="hidden" id="controlltimeYearId" name="controlltimeYearId" value="${controlltimeYearId}"/>
	<form action="SaveControlltime" id="controlltimeZhixingYear" focus="id" method="post" style="margin:0;padding:0;">
		<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
		<input type="hidden" id="controlltimedetailYearZhixingId" name="controlltimedetailYearZhixingId"/>
		<input type="hidden" id="controlltimedetailYearZhixingType" name="controlltimedetailYearZhixingType"/>
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%" style="margin:0;">
				<tr>
					<td width="11%">开始时间：</td>
					<td width="22%">
					<input type="text" id="begindateYear" name="begindateYear"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
					<td width="12%">结束时间：</td>
					<td width="22%">
					<input type="text" id="enddateYear" name="enddateYear"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
				</tr>
			</table>
		</div>
	</form>
	<!-- 
	<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
		<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a>
		<a href="#" onclick="SaveControlltimeWeeksWorKDay();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
	</div> -->

</div>

<!-- 
<div id="YearUserInfoZhixing" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div>
	<input type="hidden" id="controlltimeYearId" name="controlltimeYearId" value="${controlltimeYearId}"/>
	<form action="SaveControlltime" id="controlltimeZhixingYear" focus="id" method="post" style="margin:0;">
	<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
	
	<input type="hidden" id="controlltimedetailYearZhixingId" name="controlltimedetailYearZhixingId"/>
	<input type="hidden" id="controlltimedetailYearZhixingType" name="controlltimedetailYearZhixingType"/>
	<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
		<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%">
			<tr>
				<td width="11%">开始时间：</td>
				<td width="22%">
				<input type="text" id="begindateYear" name="begindateYear"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
				<td width="12%">结束时间：</td>
				<td width="22%">
				<input type="text" id="enddateYear" name="enddateYear"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
			</tr>
		</table>
	</div>
	</form>
	<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
		<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a>
		<a href="#" onclick="SaveControlltimeWeeksWorKYearZhixing();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
	</div>

</div>
-->

<div id="YearUserInfoGuanzhi" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div> -->
	<form action="SaveControlltime" id="controlltimeGuanzhiYear" focus="id" method="post" style="margin:0;">
		<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
		<input type="hidden" id="controlltimedetailYearGuanzhiId" name="controlltimedetailYearGuanzhiId"/>
		<input type="hidden" id="controlltimedetailYearGuanzhiType" name="controlltimedetailYearGuanzhiType"/>
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%" style="margin:0">
				<tr>
					<td width="11%">开始时间：</td>
					<td width="22%">
					<input type="text" id="begindateYearGuanzhi" name="begindateYearGuanzhi"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" />
					<td width="12%">结束时间：</td>
					<td width="22%">
					<input type="text" id="enddateYearGuanzhi" name="enddateYearGuanzhi"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" />
				</tr>
			</table>
		</div>
	</form>
	<!-- 
	<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
		<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a>
		<a href="#" onclick="SaveControlltimeWeeksWorKYearGuanzhi();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
	</div>
	-->
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
