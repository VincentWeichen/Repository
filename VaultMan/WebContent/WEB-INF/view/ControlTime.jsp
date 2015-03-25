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
function DeletecontrolltimeZhou(id){
	$(window.parent.showDialog("确定要删除该管制时间段吗？", "ExeDeletecontrolltimeZhou(" + id + ")"));
}
function ExeDeletecontrolltimeZhou(id) {
	document.location.href='DeletecontrolltimeZhou.action?id=' +id+"&organizationCode="+${organizationCode};
	var msgObj = new Object();
	msgObj.message = "管制时段删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
/* 删除End */

function DeleteControlltimeercisedate(id){
	document.location.href='DeleteControlltimeercisedate.action?id=' +id+"&organizationCode="+${organizationCode};
}


/* 新增 周循环  工作日和休息日时间段方法 */
/* type:1，工作日；2，休息日  */
function AddType(controlltimeId) {
	//var parentObj = $(window.parent.document);
	//$("#controlltimedetailZhouType").val(type);
	
	var confirmObj = new Object();
	confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKDay(1, " + controlltimeId + ")";
	confirmObj.isDisplay = true;
	
	var closeObj = new Object();
	closeObj.isDisplay = false;
	
	var dialogObj = new Object();
	dialogObj.funcName = "Users";
	controlltimeId == 1 ? dialogObj.titleText = "新增工作日管制时段" : dialogObj.titleText = "新增休息日管制时段";
	//dialogObj.titleText = "新增工作日管制时段";
	dialogObj.message = $("#UserInfo").html();
	dialogObj.confirm = confirmObj;
	dialogObj.close = closeObj;
	$(window.parent.showUDefinedDialog(dialogObj));
	//调用向子窗体赋值和设置时间控件事件方法
	addCallback(controlltimeId);
}

//向子窗体赋值和设置时间控件事件方法
function addCallback(controlltimeId) {
	var parentObj = $(window.parent.document);
	parentObj.find("#controlltimeId").val(controlltimeId);
	parentObj.find("#controlltimeZhou input[type='text']").bind("focus", function() {
		WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})
	});
}

//新增 周循环下工作日和休息日时间段的保存方法
function SaveControlltimeWeeksWorKDay(flag, type) {
	var parentObj = $(window.parent.document);
	var controlltimedetailZhouTypeVal = parentObj.find("#controlltimedetailZhouType").val();
	
	var controlltimedetailIdVal = parentObj.find("#controlltimedetailId").val();
	var controlltimeIdVal = parentObj.find("#controlltimeId").val();
	
	var begindateVal = parentObj.find("#begindate").val();
	var enddateVal = parentObj.find("#enddate").val();
	$("#controlltimedetailZhouType").val(controlltimedetailZhouTypeVal);
	
	$("#controlltimedetailId").val(controlltimedetailIdVal);
	$("#controlltimeId").val(controlltimeIdVal);
	
	$("#begindate").val(begindateVal);
	$("#enddate").val(enddateVal);
	
	var weekday = $("#hfTest"+controlltimeIdVal+"").val();
	if(controlltimeIdVal == 0)
	{
		weekday = $("#hfTest0").val();
	}
	var frm = $("form")[0];
	var msg = "";
	/*
	var begindates = begindateVal.split(":");
	var enddates = enddateVal.split(":");
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
	*/
	flag == 1 ? msg += "新增" : msg += "编辑";
	//type == 1 ? msg += "工作日" : msg += "休息日";
	
	msg += "管制时段成功！";
	//验证日期信息
	frm.action=common.getProjectRootPath() + "/SaveControlltime.action?controlltimeType=1&organizationCode="+${organizationCode}+"&weekday="+weekday;
	frm.submit();
	
	
	//type % 2 != 0 ? msg = "新增" : msg = "编辑";
	
	var msgObj = new Object();
	msgObj.message = msg;	//"新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
/* 新增 周循环  工作日和休息日时间段方法 End */
function EditcontrolltimeZhou(id,type) {
	var organizationCode = $("#organizationCode").val();
	//$("#controlltimedetailZhouType").val(type);
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditcontrolltimeZhou.action", { id: id, organizationCode:organizationCode}, function (result) {
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.SaveControlltimeWeeksWorKDay(2, " + type + ")";
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
		
		getControlltimeWeeksWorKDayCallback(result, type);
    }, "json");
	return false;
}
function getControlltimeWeeksWorKDayCallback(result, type) {
	var parentObj = $(window.parent.document);
	parentObj.find("#controlltimedetailZhouType").val(type);
	parentObj.find("#begindate").val(result.begindate);
	parentObj.find("#enddate").val(result.enddate);
	parentObj.find("#controlltimedetailId").val(result.id);
	parentObj.find("#controlltimeId").val(result.controlltimeid);
	/*
	$("#begindate").val(result.begindate);
	$("#enddate").val(result.enddate);
	$("#controlltimedetailId").val(result.id);
	$("#controlltimeId").val(result.controlltimeid);
	*/
}

/* 删除周循环组 */
function DeleteGroup(id){
	$(window.parent.showDialog("确定要删除该周循环管制组吗？", "ExeDeleteGroup(" + id + ")"));
}
function ExeDeleteGroup(id) {
	//这里编写调用后台删除组方法（下面的注视是删除管制时间的方法）
	var organizationCode = $("#organizationCode").val();
 	document.location.href='DeleteControlltime.action?type=1&id=' +id+"&organizationCode="+${organizationCode};
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
			<div class="contentItem">
			
				<div class="newdataTitle">
					<h1>周循环</h1>
					<div class="newdataTitleOption"><a href="#" class="add" onclick="AddGroup()">新增管制组</a></div>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
					<tr class="table-title">
						<th>序号</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th class="table-center">操作</th>
					</tr>
					<c:forEach items="${controlltimeZhouList}" var="item" varStatus="status" >
						<tr class="table-title-type">
							<th colspan="3"></th>
							<th class="btnOption">
								<div class="newdataTitleOption">
									执行日：<input type="text" class="Multiple" name="${item.id}" style="width: 350px;" <c:if test="${item.controlltimeercisedateWorkList != null}">value="${item.controlltimeercisedateWorkList[0].weekday}"</c:if> />
												<input id="hfTest${item.id}" type="hidden" name="hfTest" <c:if test="${item.controlltimeercisedateWorkList != null}">value="${item.controlltimeercisedateWorkList[0].weekday}"</c:if> />
												<a href="#" class="delete" onclick="DeleteGroup(${item.id});">删除改该组</a><a href="#" class="add" onclick="AddType(${item.id});">新增管制时段</a>
									<ul id="list${item.id}">
										<li><input type="checkbox" id="CKB${item.id}1" value="1" /><label for="CKB${item.id}1">星期一</label></li>
										<li><input type="checkbox" id="CKB${item.id}2" value="2" /><label for="CKB${item.id}2">星期二</label></li>
										<li><input type="checkbox" id="CKB${item.id}3" value="3" /><label for="CKB${item.id}3">星期三</label></li>
										<li><input type="checkbox" id="CKB${item.id}4" value="4" /><label for="CKB${item.id}4">星期四</label></li>
										<li><input type="checkbox" id="CKB${item.id}5" value="5" /><label for="CKB${item.id}5">星期五</label></li>
										<li><input type="checkbox" id="CKB${item.id}6" value="6" /><label for="CKB${item.id}6">星期六</label></li>
										<li><input type="checkbox" id="CKB${item.id}7" value="7" /><label for="CKB${item.id}7">星期日</label></li>
									</ul>
								</div>
							</th>
						</tr>
						<c:if test="${item.controlltimedetailWorkList != null}">
						<c:forEach items="${item.controlltimedetailWorkList}" var="controlltimedetailItem" varStatus="statusDetail">
						<tr>
							<td>${statusDetail.index+1}</td>
							<td class="TimeStart">${controlltimedetailItem.begindate}</td>
							<td class="TimeEnd">${controlltimedetailItem.enddate}</td>
							<td class="optionButtons table-center">
							<a href="#" class="edit" onclick="EditcontrolltimeZhou(${controlltimedetailItem.id},1);">编辑</a>
							<a href="#" class="delete" onclick="DeletecontrolltimeZhou(${controlltimedetailItem.id});">删除</a>
							</td>
						</tr>
						</c:forEach>
						</c:if>		
					</c:forEach>
				</table>
			</div>

		</div>

</div>

<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div> -->
	<form action="SaveControlltime" id="controlltimeZhou" focus="id" method="post" style="margin:0;padding:0;">
		<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
		<input type="hidden" id="controlltimeId" name="controlltimeId" value="${controlltimeId}"/>
		<input type="hidden" id="controlltimedetailId" name="controlltimedetailId"/>
		<input type="hidden" id="controlltimedetailZhouType" name="controlltimedetailZhouType"/>
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<table cellpadding="0" cellspacing="0" border="0" id="controlltimeZhou" class="OrganizationEdit" width="100%" style="margin:0;">
				<tr>
					<td width="11%">开始时间：</td>
					<td width="22%">
					<input type="text" id="begindate" name="begindate"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" />
					<td width="12%">结束时间：</td>
					<td width="22%">
					<input type="text" id="enddate" name="enddate"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'HH:mm',alwaysUseStartDate:true})" />
				</tr>
			</table>
		</div>
	</form>
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

<div id="YearUserInfoGuanzhi" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div>
	<form action="SaveControlltime" id="controlltimeGuanzhiYear" focus="id" method="post" style="margin:0;">
	<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
	<input type="hidden" id="controlltimedetailYearGuanzhiId" name="controlltimedetailYearGuanzhiId"/>
	<input type="hidden" id="controlltimedetailYearGuanzhiType" name="controlltimedetailYearGuanzhiType"/>
	<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
		<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%">
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
	<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
		<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a>
		<a href="#" onclick="SaveControlltimeWeeksWorKYearGuanzhi();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
	</div>

</div>

<div id="UserInfoHoliday" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div>
	<input type="hidden" id="controlltimeYearId" name="controlltimeYearId" value="${controlltimeYearId}"/>
	<form action="SaveControlltime" id="controlltimeHoliday" focus="id" method="post" style="margin:0;">
	<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
	<input type="hidden" id="controlltimeHolidayId" name="controlltimeHolidayId"/>
	<input type="hidden" id="controlltimedetailHolidayType" name="controlltimedetailHolidayType"/>
	<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
		<table cellpadding="0" cellspacing="0" border="0" id="controlltimeYear" class="OrganizationEdit" width="100%">
			<tr>
				<td width="11%">管制原因：</td>
				<td width="22%">
				<input type="text" id="description" name="description" />
				<td width="11%">开始时间：</td>
				<td width="22%">
				<input type="text" id="begindateYear" name="begindateHoliday"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
				<td width="12%">结束时间：</td>
				<td width="22%">
				<input type="text" id="enddateYear" name="enddateHoliday"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" id="controlltimeHoliday" class="OrganizationEdit" width="100%">
			<tr>
				<td width="30%">管制开始时间：</td>
				<td width="30%">管制结束时间：</td>
			</tr>
			<tr>
				<td width="30%">
				<input type="text" id="begindateHoliday" name="begindateHoliday"  onFocus="WdatePicker({begindate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
				<td width="30%">
				<input type="text" id="enddateHoliday" name="enddateHoliday"  onFocus="WdatePicker({enddate:'%y-%M-01 00:00',dateFmt:'yyyy年MM月dd',alwaysUseStartDate:true})" />
			</tr>
		</table>
	</div>
	</form>
	<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
		<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a>
		<a href="#" onclick="SaveControlltimeHoliday();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
	</div>

</div>
 -->
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
