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
<title>Index</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/Common.js"></script> -->
<script type="text/javascript" src="<%=path %>/Scripts/Group.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script> -->


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

function AddFunc() {
	editGroup();
}

function deleteGroup(groupCode){
	$(window.parent.showDialog("确定要删除该组吗？", "execDelete('" + groupCode + "')"));
	/*
	//加一个删除确认提示框
	var corganizationCode = ${organizationCode};
	document.location.href='DeleteGroup.action?groupCode=' +groupCode+'&corganizationCode='+corganizationCode;
	*/
}

function deleteGroupList() {
	var checkedObjs = $("#dataList tr[class!='Info'][class!='table-title'][class!='Info-tr'] input[checked='checked']");
	if (checkedObjs.length == 0) {
		$(window.parent.showMessage("请选择要删除的组！"));
		return false;
	}
	
	var groupCodeStr = "";
	checkedObjs.each(function(i, obj) {
		var item = $(obj);
		groupCodeStr == "" ? groupCodeStr += item.val() : groupCodeStr += "&groupCodeStr=" + item.val();
	});
	
	$(window.parent.showDialog("确定要删除该组吗？", "execDelete('" + groupCodeStr + "')"));
}

function execDelete(groupCodeStr) {
	var corganizationCode = ${organizationCode};
	var tid = ${tid};
	//document.location.href='DeleteGroupList.action?groupCodeStr=' +groupCodeStr+'&corganizationCode='+corganizationCode;
	
	var msgObj = new Object();
	msgObj.message = "组删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function onsubmits() {
	var organizationcodeVal = $(window.parent.document).find("#organizationcode").val();
	var tidVal = $(window.parent.document).find("#tid").val();
	var datalistObj = $("#OrganizationEdit tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	var ifrmDatalistObj = $(window.parent.document).find("#OrganizationEdit tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	
	ifrmDatalistObj.each(function(i, obj) {
		var ifrmTrItem = $(obj);
		var trItem = datalistObj.eq(i);
		var ifrmTdList = ifrmTrItem.find("td");
		var tdList = trItem.find("td");
		ifrmTdList.each(function(j, td) {
			var ifrmTdItem = $(td);
			var tdItem = tdList.eq(j);
			
			var ifrmEleObjs = ifrmTdItem.find("input,select");
			var eleObjs = tdItem.find("input,select");
			eleObjs.each(function(k, ele) {
				$(ele).val(ifrmEleObjs.eq(k).val());
			});
			//tdItem.find("input,select").val(ifrmTdItem.find("input,select").val());
		});
	});
	
	chkSubmit($("form")[0]);
}
function chkSubmit(frm){
	//验证用户人数在2-5之前，否则清空用户，验证用户重复
	for(var j =1;j<=15;j++) {
		var userNum = ${userNum};
		var num = 0;
		for(var m =1;m<=userNum;m++) {
			var user = $(window.parent.document).find("#"+j+"_user"+m).val();
			if(user.length>0)
			{
				num++;
			}
		}
		if(num>0 && num<userNum)
		{
			var msgObjError = new Object();
			msgObjError.message = "新增"+j+"分组的人员不全！";
			msgObjError.messageType = "false";
			$(window.parent.showMessage(msgObjError));
			return false;
		}
	}
	frm.action=common.getProjectRootPath() + "/SaveGroup.action?groupauthorizeStr=1";
	frm.submit();
	
	var msgObj = new Object();
	msgObj.message = "新增分组成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
function editGroup() {
	var organizationCode = ${organizationCode};
	var tid = ${tid};
	$.post(common.getProjectRootPath() + "/EditGroup.action", { organizationcode:organizationCode,tid:tid}, function (result) {
		getGroupCallback(result);
		
    }, "json");
	return false;
}
function getGroupCallback(result) {
	/*
	for(var i =0;i<result.length;i++) {
		var groupauthorize = false;
		if(result[i].groupauthorize=="true")
		{
			groupauthorize = true;
		}
		$("#"+result[i].code+"_groupauthorize").attr("checked",groupauthorize);
	}
	*/
	for(var n =1;n<=15;n++) {
		for(var m =1;m <=5;m++)
		{
			var userNum = ${userNum};
			if(m>userNum)
			{
				$(window.parent.document).find("#"+n+"_user"+m).attr("disabled","disabled");
			}
		}
	}
	for(var j =0;j<result.length;j++) {
		if(result[j].tusercode1.length>0)
			$(window.parent.document).find("#"+result[j].code+"_user1").val(result[j].tusercode1+";"+result[j].usernamesys1);
		if(result[j].tusercode2.length>0)
			$(window.parent.document).find("#"+result[j].code+"_user2").val(result[j].tusercode2+";"+result[j].usernamesys2);
		if(result[j].tusercode3.length>0)
			$(window.parent.document).find("#"+result[j].code+"_user3").val(result[j].tusercode3+";"+result[j].usernamesys3);
		if(result[j].tusercode4.length>0)
			$(window.parent.document).find("#"+result[j].code+"_user4").val(result[j].tusercode4+";"+result[j].usernamesys4);
		if(result[j].tusercode5.length>0)
			$(window.parent.document).find("#"+result[j].code+"_user5").val(result[j].tusercode5+";"+result[j].usernamesys5);
		
	}
}

function deleteGroupInfo(index) {
	$(window.parent.document).find("#"+index+"_user1").val("");
	$(window.parent.document).find("#"+index+"_user2").val("");
	$(window.parent.document).find("#"+index+"_user3").val("");
	$(window.parent.document).find("#"+index+"_user4").val("");
	$(window.parent.document).find("#"+index+"_user5").val("");

}
function FindGroupList(){
	var corganizationCode = ${organizationCode};
	var tid = ${tid};
	document.location.href='GetGroupList.action?organizationCode='+corganizationCode+"&tid"+tid;
}

</script>
</head>

<body>

<div id="iframeContent">
	<!-- <div id="contentBox">
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Home">班组设置</a></li>
			</ul>
			<div class="content-top-button">
				<a href="#" class="Add" >新增班组</a>
				<a href="#" class="Delete" onclick="deleteGroupList();">删除班组</a>
			</div>
		</div> -->
		
		<div id="content-content">
			<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
				<tr class="title">
					<th width="5%"><input type="checkbox" name="allbox" class="checkBoxAll" /></th>
					<th width="15%">组名</th>
					<th width="15%">组号</th>
					<th width="15%">类别</th>
					<th width="15%">班组成员</th>
					<th width="15%">授权</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach items="${pageInfo.data}" var="groupItem">
				<tr>
					<td><input type="checkbox" value='${groupItem.code}' name="id" /></td>
					<td>${groupItem.name}</td>
					<td>${groupItem.code}</td>
					<td>${groupItem.type}</td>
					<td>${groupItem.usernames}</td>
					<td><c:if test="${groupItem.groupauthorize==true}">是</c:if><c:if test="${groupItem.groupauthorize==false}">否</c:if></td>
					<td><a href="#" class="delete" onclick="deleteGroup(${groupItem.code});">删除</a></td>
				</tr>
				</c:forEach>
			</table>
			
			<!-- 
			<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="dataList" width="100%">
				<tr class="table-title">
					<th class="table-center"><input type="checkbox" name="allbox" class="checkBoxAll" /></th>
					<th>组名</th>
					<th>组号</th>
					<th>类别</th>
					<th>班组成员</th>
					<th>授权</th>
					<th class="table-center">操作</th>
				</tr>
				<c:forEach items="${pageInfo.data}" var="groupItem">
				<tr>
					<td class="table-center"><input type="checkbox" value='${groupItem.code}' name="id"/></td>
					<td class="itenName">${groupItem.name}</td>
					<td>${groupItem.code}</td>
					<td>${groupItem.type}</td>
					<td>${groupItem.usernames}</td>
					<td><c:if test="${groupItem.groupauthorize==true}">是</c:if><c:if test="${groupItem.groupauthorize==false}">否</c:if></td>
					<td class="optionButtons table-center">
					<a href="#" class="delete" onclick="deleteGroup(${groupItem.code});">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			 -->
		</div>
		<input type="hidden" value="${tid}" id="hideTid" />
<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none;">
	<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">班组信息</h1></div> -->
	<div class="UserInfoTable" style="overflow-x:hidden;overflow-y:scroll;margin:0 0 0 10px;width:740px;height:350px;">
		<form action="SaveGroup.action" focus="id" method="post">
			<!-- 
			<div class="content-top-button">
				<a href="#" onclick="onsubmits();">确定</a>
				<a href="#" class="Delete" onclick="FindGroupList();">返回</a>
			</div> -->
			<input type="hidden" id="organizationcode" name="organizationcode" value="${organizationCode}"/>
			<input type="hidden" id="tid" name="tid" value="${tid}"/>
			
			<table cellpadding="0" cellspacing="0" border="0" id="OrganizationEdit" class="newdataList" width="100%">
				<tr class="table-title">
					<th width="10%">操作组</th>
					<th width="8%">组号</th>
					<th width="8%">组名</th>
					<th width="60%" colspan="5">班组人员</th>
					<th width="14%">操作</th>
				</tr>
				<c:forEach items="${numGroup}" var="item" varStatus="status">
				<tr>
					<td><input type="hidden" id="${status.index+1}_id" name="${status.index+1}_id"/><input type="hidden" id="${status.index+1}_type" name="${status.index+1}_type" value="<c:if test="${status.index+1<=15}">操作组</c:if><c:if test="${status.index+1>15}">管理组</c:if>"/><c:if test="${status.index+1<=15}">操作组</c:if><c:if test="${status.index+1>15}">管理组</c:if></td>
					<td>${status.index+1}<input type="hidden" id="${status.index+1}_code" name="${status.index+1}_code" value="${status.index+1}"/></td>
					<td>
					<c:if test="${status.index+1<=15}">${item}组</c:if>
					<c:if test="${status.index+1>15}">${status.index-14}组</c:if><input type="hidden" id="${status.index+1}_name" name="${status.index+1}_name" value="<c:if test="${status.index+1<=15}">${item}组</c:if><c:if test="${status.index+1>15}">${status.index-14}组</c:if>"/></td>
					<td width="12%">
					<select id="${status.index+1}_user1" name="${status.index+1}_user1" >
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item" >
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%">
					<select id="${status.index+1}_user2" name="${status.index+1}_user2">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%">
					<select id="${status.index+1}_user3" name="${status.index+1}_user3">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%">
					<select id="${status.index+1}_user4" name="${status.index+1}_user4">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%">
					<select id="${status.index+1}_user5" name="${status.index+1}_user5">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td>
						<a href="#" class="delete" onclick="deleteGroupInfo(${status.index+1});">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<!-- 
			<table cellpadding="0" cellspacing="0" border="0" id="OrganizationEdit" class="dataList" width="100%">
				<tr class="table-title">
					<th width="8%">操作组</th>
				    
					<th width="5%">组号</th>
					<th width="5%">组名</th>
					<th width="60%" class="table-center" colspan="5">班组人员</th>
					<th width="12%" class="table-center">操作</th>
				</tr>
				<c:forEach items="${numGroup}" var="item" varStatus="status">
				<tr>
					<td width="8%"><input type="hidden" id="${status.index+1}_id" name="${status.index+1}_id"/><input type="hidden" id="${status.index+1}_type" name="${status.index+1}_type" value="<c:if test="${status.index+1<=15}">操作组</c:if><c:if test="${status.index+1>15}">管理组</c:if>"/><c:if test="${status.index+1<=15}">操作组</c:if><c:if test="${status.index+1>15}">管理组</c:if></td>
					<td width="5%" class="table-center">${status.index+1}<input type="hidden" id="${status.index+1}_code" name="${status.index+1}_code" value="${status.index+1}"/></td>
					<td width="5%" class="table-center">
					<c:if test="${status.index+1<=15}">${item}组</c:if>
					<c:if test="${status.index+1>15}">${status.index-14}组</c:if><input type="hidden" id="${status.index+1}_name" name="${status.index+1}_name" value="<c:if test="${status.index+1<=15}">${item}组</c:if><c:if test="${status.index+1>15}">${status.index-14}组</c:if>"/></td>
					<td width="12%" style="text-align:center">
					<select id="${status.index+1}_user1" name="${status.index+1}_user1" >
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item" >
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%" style="text-align:center">
					<select id="${status.index+1}_user2" name="${status.index+1}_user2">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%" style="text-align:center">
					<select id="${status.index+1}_user3" name="${status.index+1}_user3">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%" style="text-align:center">
					<select id="${status.index+1}_user4" name="${status.index+1}_user4">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%" style="text-align:center">
					<select id="${status.index+1}_user5" name="${status.index+1}_user5">
					<option value="">请选择</option>
					<c:forEach items="${fingerprintuserList}" var="item">
						<option value="${item.tusercode};${item.usernamesys}">${item.usernamesys}</option>
					</c:forEach>
					</select>
					</td>
					<td width="12%" class="table-center">
						<a href="#" class="delete" onclick="deleteGroupInfo(${status.index+1});">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			 -->
		</form>
	</div>
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
</div>
</body>
</html>
