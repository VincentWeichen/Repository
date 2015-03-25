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
<script type="text/javascript" src="<%=path %>/Scripts/Common.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/ControlTime.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<!-- 
<script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Calendar.js"></script>
 -->
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


/* 删除管制时间段 */
function DeletecontrolltimeHoliday(id){
	$(window.parent.showDialog("确定要删除该管制时间段吗？", "ExeDeletecontrolltimeHoliday(" + id + ")"));
}
function ExeDeletecontrolltimeHoliday(id) {
	                        
	document.location.href='DeleteControlltimeHoliday.action?id=' +id+"&organizationCode="+${organizationCode};
	var msgObj = new Object();
	msgObj.message = "管制时段删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
/* 删除End */

function DeleteControlltimeercisedate(id){
	document.location.href='DeleteControlltimeercisedate.action?id=' +id+"&organizationCode="+${organizationCode};
}



function AddType(id) {
	var confirmObj = new Object();
	confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeHoliday()";
	confirmObj.isDisplay = true;
	
	var closeObj = new Object();
	closeObj.isDisplay = false;
	
	var dialogObj = new Object();
	dialogObj.funcName = "Users";
	dialogObj.titleText = "新增假期管制时段";
	//dialogObj.titleText = "新增工作日管制时段";
	dialogObj.message = $("#UserInfo").html();
	dialogObj.confirm = confirmObj;
	dialogObj.close = closeObj;
	addCallback(id);
	$(window.parent.showUDefinedDialog(dialogObj));
}

//向子窗体赋值和设置时间控件事件方法
function addCallback(id) {
	$("#controlltimeId").val(id);
}

//新增 
function SaveControlltimeHoliday() {
	var parentObj = $(window.parent.document);
	var controlltimeercisedateIdVal = parentObj.find("#controlltimeercisedateId").val();
	var controlltimedetailIdVal = $("#controlltimedetailId").val();
	var controlltimeIdVal = $("#controlltimeId").val();
	var begindateVal = parentObj.find("#begindate").val();
	var enddateVal = parentObj.find("#enddate").val();
	
	$("#controlltimeercisedateId").val(controlltimeercisedateIdVal);
	$("#controlltimedetailId").val(controlltimedetailIdVal);
	$("#controlltimeId").val(controlltimeIdVal);
	$("#begindate").val(begindateVal);
	$("#enddate").val(enddateVal);
	
	var descriptionVal = $("#description"+controlltimeIdVal).val();
	var begindateHolidayVal = $("#begindateHoliday"+controlltimeIdVal).val();
	var enddateHolidayVal = $("#enddateHoliday"+controlltimeIdVal).val();
	var frm = $("form")[0];
	
	var msg = "";
	
	//flag == 1 ? msg += "新增" : msg += "编辑";
	
	msg += "保存假期管制时段成功！";
	
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveControlltime.action?controlltimeType=3&organizationCode="+${organizationCode}+"&begindateHoliday="+begindateHolidayVal+"&enddateHoliday="+enddateHolidayVal+"&description="+descriptionVal;
	frm.submit();
	
	
	//type % 2 != 0 ? msg = "新增" : msg = "编辑";
	
	var msgObj = new Object();
	msgObj.message = msg;	//"新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
/* 新增 周循环  工作日和休息日时间段方法 End */


function EditcontrolltimeHoliday(id,type) {
	var organizationCode = $("#organizationCode").val();
	//$("#controlltimedetailZhouType").val(type);
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditcontrolltimeHoliday.action", { id: id, organizationCode:organizationCode}, function (result) {
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeHoliday()";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		type == 1 ? dialogObj.titleText = "编辑工作日管制时段" : dialogObj.titleText = "编辑休息日管制时段";
		//dialogObj.titleText = "编辑工作日管制时段";
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		getControlltimeWeeksWorKDayCallback(result);
    }, "json");
	return false;
}
function getControlltimeWeeksWorKDayCallback(result) {
	var parentObj = $(window.parent.document);
	parentObj.find("#begindate").val(result.begindate);
	parentObj.find("#enddate").val(result.enddate);
	$("#controlltimedetailId").val(result.id);
	$("#controlltimeId").val(result.controlltimeid);

}
/* 删除周循环组 */
function DeleteGroup(id){
	$(window.parent.showDialog("确定要删除该周循环管制组吗？", "ExeDeleteGroup(" + id + ")"));
}
function ExeDeleteGroup(id) {
	//这里编写调用后台删除组方法（下面的注视是删除管制时间的方法）
	var organizationCode = $("#organizationCode").val();
 	document.location.href='DeleteControlltime.action?type=3&id=' +id+"&organizationCode="+${organizationCode};
 	var msgObj = new Object();
 	msgObj.message = "周循环管制组删除成功！";
 	msgObj.messageType = "success";
 	$(window.parent.showMessage(msgObj));
}
</script>
</head>

<body>

<div id="iframeContent">
	<div id="content-content">

			<div class="contentItem" >
				<div class="newdataTitle">
					<h1>假期管制时段</h1>
					<div class="newdataTitleOption"><a href="#" class="add" onclick="AddGroupHoliday()">新增管制组</a></div>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
					<tr class="table-title">
						<!-- <th class="table-center"><input type="checkbox" /></th> -->
						<th width="5%">序号</th>
						<!-- <th width="20%">管制原因</th> -->
						<!-- <th>开始时间</th>
						<th>结束时间</th> -->
						<th width="15%">开始时段</th>
						<th width="15%">结束时段</th>
						<th class="table-center">操作</th>
					</tr>
					<c:forEach items="${controlltimeHolidayList}" var="item" varStatus="statusItem">
						<c:forEach items="${item.controlltimeercisedateWorkList}" var="dateItem" varStatus="status">
							<tr class="table-title-type">
								<th colspan="3">${dateItem.begindate} - ${dateItem.enddate}</th>
								<th class="btnOption">
									<div class="newdataTitleOption">
										<div style="float:right;">
										开始时间：<input type="text" style="width:100px" id="begindateHoliday${item.id}" name="begindateHoliday${item.id}" value="${dateItem.begindate}" onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
										结束时间：<input type="text" style="width:100px" id="enddateHoliday${item.id}" name="enddateHoliday${item.id}" value="${dateItem.enddate}" onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
										<a href="#" class="delete" onclick="DeleteGroup(${item.id});">删除该组</a><a href="#" class="add" onclick="AddType(${item.id});">新增管制时段</a>
										</div>
										<div style="float:right;margin-top:5px;padding-right:8px">管制原因：<input id="description${item.id}" name="description${item.id}" style="width:450px" value="${dateItem.description}" /></div>
									</div>
								</th>
							</tr>
						</c:forEach>
						<c:forEach items="${item.controlltimedetailWorkList}" var="dateItem" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>${dateItem.begindate}</td>
								<td>${dateItem.enddate}</td>
								<td class="optionButtons table-center">
									<a href="#" class="edit" onclick="EditcontrolltimeHoliday(${dateItem.id},2);">编辑</a>
									<a href="#" class="delete" onclick="DeletecontrolltimeHoliday(${dateItem.id});">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</table>
				
			</div>
			
		</div>	<!-- End #content-content -->
</div>	<!-- IFrame -->



<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<form action="SaveControlltime" id="controlltimeHoliday" focus="id" method="post"  style="margin:0;padding:0;">
		<input type="hidden" id="controlltimeId" name="controlltimeId" value="${controlltimeId}"/>
		<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
		<input type="hidden" id="controlltimedetailId" name="controlltimedetailId"/>
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%" style="margin:0;">
				<tr>
					<td width="15%">开始时间：</td>
					<td width="35%"><input type="text" id="begindate" name="begindate"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" /></td>
					<td width="15%">结束时间：</td>
					<td width="35%"><input type="text" id="enddate" name="enddate"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" /></td>
				</tr>
			</table>
					
		</div>
	</form>
</div>

</body>
</html>
