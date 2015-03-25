<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>人员信息</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/UserInfomation.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/Scripts/Calendar/WdatePicker.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script> -->
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
function deleteUser(userCode){
	//加一个删除确认提示框
	var organizationCode = ${organizationCode};
	//document.location.href='DeleteUser.action?userCode=' +userCode+'&organizationCode='+organizationCode;
	$(window.parent.showDialog("确定要删除该用户吗？", "execDelete('" + userCode + "', '" + organizationCode + "')"));
}

function deleteUserList(){
	var checkedObjs = $("#dataList tr[class!='Info'][class!='table-title'][class!='Info-tr'] input[checked='checked']");
	if (checkedObjs.length == 0) {
		$(window.parent.showMessage("请选择要删除的用户！"));
		return false;
	}
	
	var userCodeStr = "";
	checkedObjs.each(function(i, obj) {
		var item = $(obj);
		userCodeStr == "" ? userCodeStr += item.val() : userCodeStr += "&roleCodeStr=" + item.val();
	});
	
	/*
	//加一个删除确认提示框
	var ids = document.getElementsByName("id");
	var userCodeStr = "";
	for(var i =0;i<ids.length;i++) {
	  if(ids[i].type == 'checkbox' && ids[i].checked && ids[i].name != 'allbox') {
		  userCodeStr += '&code='+ids[i].value;
	   }
    }
	if(userCodeStr.length==0)
	{
		$.fn.Dialog({ funcName: "Alert", message: "请选择要删除的用户！", autoClose: 3 });
		return false;
	}
	*/
	var organizationCode = ${organizationCode};
	$(window.parent.showDialog("确定要删除该用户吗？", "execDelete('" + userCodeStr + "', '" + organizationCode + "')"));
	//document.location.href='DeleteUserList.action?userCodeStr=' +userCodeStr+'&organizationCode='+organizationCode;
}

function execDelete(userCodeStr, organizationCode) {
	//alert(userCodeStr);
	document.location.href='DeleteUserList.action?userCodeStr=' +userCodeStr+'&organizationCode='+organizationCode;
	var msgObj = new Object();
	msgObj.message = "用户删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function onsubmits() {
	//alert("aaaa")
	var nameVal = $(window.parent.document).find("#name").val();
	var idVal = $(window.parent.document).find("#id").val();
	var userCodeVal = $(window.parent.document).find("#userCode").val();
	
	var codeVal = $(window.parent.document).find("#code").val();
	var ableLoginVal = $(window.parent.document).find("#ableLogin").attr("checked");
	//alert(ableLoginVal);
	var passwordVal = "";
	if (ableLoginVal) {
		passwordVal = $(window.parent.document).find("#password").val();
		//alert(passwordVal);
	}
	//var codeVal = $(window.parent.document).find("#code").val();
	//var passwordVal = $(window.parent.document).find("#password").val();
	var typeVal = $(window.parent.document).find("#type").val();
	var sexVal = $(window.parent.document).find("#sex").val();
	var telephoneVal = $(window.parent.document).find("#telephone").val();
	var cellphoneVal = $(window.parent.document).find("#cellphone").val();
	var titleVal = $(window.parent.document).find("#title").val();
	var cardnumberVal = $(window.parent.document).find("#cardnumber").val();
	var addressVal = $(window.parent.document).find("#address").val();
	var positionVal = $(window.parent.document).find("#position").val();
	var rolenamesVal = $(window.parent.document).find("#rolenames").val();
	var rolecodesVal = $(window.parent.document).find("#rolecodes").val();
	
	$("#name").val(nameVal);
	$("#id").val(idVal);
	$("#userCode").val(userCodeVal);
	$("#code").val(codeVal);
	$("#password").val(passwordVal);
	//alert($("#password").val());
	$("#type").val(typeVal);
	$("#sex").val(sexVal);
	$("#telephone").val(telephoneVal);
	$("#cellphone").val(cellphoneVal);
	$("#title").val(titleVal);
	$("#cardnumber").val(cardnumberVal);
	$("#address").val(addressVal);
	$("#position").val(positionVal);
	$("#rolenames").val(rolenamesVal);
	$("#rolecodes").val(rolecodesVal);
	
	$("#ableLogin").attr("checked", ableLoginVal);
	
	//alert($("#rolecodes").val());
	
	//alert($("#rolenames").val())
	
	$("#rolenames").attr("disabled", false);
	
	chkSubmit($("form")[0]);
}
function chkSubmit(frm){
	var idCode = $.trim($("#id").val());
	var newName = $.trim($("#name").val());
	var newTelephone = $.trim($("#telephone").val());
	var newRole = $.trim($("#roleCode").val());
	var newCardnumber = $.trim($("#cardnumber").val());
	//var newOrganization = $.trim($("#organization").val());
	var newAddress = $.trim($("#address").val());
	//alert("ccc")
	if (newName == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "姓名不能为空！", autoClose: 3 });
		$(window.parent.showMessage("姓名不能为空！"));
		return false;
	}
	if (newTelephone == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "电话不能为空！", autoClose: 3 });
		$(window.parent.showMessage("电话不能为空！"));
		return false;
	}
	/*
	if (newRole == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "岗位不能为空！", autoClose: 3 });
		$(window.parent.showMessage("岗位不能为空！"));
		return false;
	}
	*/
	if (newCardnumber == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "身份证号码不能为空！", autoClose: 3 });
		$(window.parent.showMessage("身份证号码不能为空！"));
		return false;
	}
	/*
	if (newOrganization == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "机构不能为空！", autoClose: 3 });
		$(window.parent.showMessage("机构不能为空！"));
		return false;
	}
	*/
	if (newAddress == "") {
		//$.fn.Dialog({ funcName: "Alert", message: "家庭住址不能为空！", autoClose: 3 });
		$(window.parent.showMessage("家庭住址不能为空！"));
		return false;
	}
	/*
	if (newOrganization == "") {
		$.fn.Dialog({ funcName: "Alert", message: "机构不能为空！", autoClose: 3 });
		$(window.parent.showMessage("姓名不能为空！"));
		return false;
	}
	*/
	
	if ($("#ableLogin").attr("disabled")) {
		var newCode = $.trim($("#code").val());
		var newPassword = $.trim($("#password").val());
		if (newCode == "") {
			//$.fn.Dialog({ funcName: "Alert", message: "登陆账号不能为空！", autoClose: 3 });
			$(window.parent.showMessage("登陆账号不能为空！"));
			return false;
		}
		if (newPassword == "") {
			//$.fn.Dialog({ funcName: "Alert", message: "登陆密码不能为空！", autoClose: 3 });
			$(window.parent.showMessage("登陆密码不能为空！"));
			return false;
		}
	}
	
	var paramData = $("form").eq(0).serialize();
	//alert("a")
	$.post(common.getProjectRootPath() + "/CheckUserCode.action", paramData, function (result){
		//alert("b")
			if (result == '1') {
				$("#password").attr("disabled", false);
				frm.action=common.getProjectRootPath() + "/SaveUser.action";
				frm.submit();
				
				var msgObj = new Object();
				idCode == "" ? msgObj.message = "新增用户成功！" : msgObj.message = "编辑用户成功！"
				//msgObj.message = "新增用户成功！";
				msgObj.messageType = "success";
				$(window.parent.showMessage(msgObj));
				$("#rolenames").attr("disabled", true);
			}
			else
				$.fn.Dialog({ funcName: "Alert", message: "登陆账号已存在！", autoClose: 3 });
    });
}

var oPath = "";

function editUser(userCode, userPwd) {
	var titleTxt = $(window.parent.document).find("#pageTitle").html();
	var orgPath = titleTxt.substr(titleTxt.indexOf("- ") + 2);
	oPath = orgPath;
	//alert(orgPath)
	
	if (userPwd == "" || userPwd == null) {
		$("#ableLogin").attr("checked", false);
		$("#password").attr("disabled", true);
	}
	else {
		$("#ableLogin").attr("checked", true);
		$("#password").attr("disabled", false);
	}
	
	var organizationCode = $("#organizationCode").val();
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditUser.action", { userCode: userCode, organizationCode:organizationCode}, function (result) {
		//alert("b")
		//getUserCallback(result);
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits()";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		dialogObj.titleText = "编辑用户 - " + orgPath;
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		//alert("aa")
		getUserCallback(result);
		
		/*
		setTimeout(function() {
			getUserCallback(result);
			$.fn.Close(null, event);
		}, 1000);
		*/
    }, "json");
	return false;
}
function getUserCallback(result) {
	var parentObj = $(window.parent.document);
	//parentObj.find("#organizationCode").val($("#organizationCode").val());
	//alert("a");
	//alert($("#organizationCode").val());
	parentObj.find("#id").val(result.id);
	parentObj.find("#userCode").val(result.code);
	parentObj.find("#code").val(result.code);
	parentObj.find("#name").val(result.name);
	parentObj.find("#password").val(result.password);
	parentObj.find("#type").val(result.type);
	parentObj.find("#sex").val(result.sex);
	parentObj.find("#telephone").val(result.telephone);
	parentObj.find("#cellphone").val(result.cellphone);
	parentObj.find("#title").val(result.title);
	parentObj.find("#cardnumber").val(result.cardnumber);
	parentObj.find("#address").val(result.address);
	//parentObj.find("#id").val(result.id);
	parentObj.find("#rolenames").val(result.rolenames);
	
	parentObj.find("#organization").val(oPath);
	
	if (result.rolenames != "" && result.rolenames != null) {
		var roleNameArr = result.rolenames.split(',');
		var rolecodes = "";
		var roleListObj = $("#DialogRoleList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
		//roleListObj.find("input:first").attr("checked", false);
		$.each(roleNameArr, function(i, roleName) {
			roleListObj.each(function(i, item) {
				var itemObj = $(item);
				var input = itemObj.find("input:first");
				//alert(itemObj.find("td").eq(1).html() + " - " + roleName)
				if (itemObj.find("td").eq(1).html() == roleName) {
					//alert("same")
					//input.attr("checked", true);
					rolecodes == "" ? rolecodes += input.val() : rolecodes += "," + input.val();
					return false;
				}
			});
		});
		
		parentObj.find("#rolecodes").val(rolecodes);
	}
	
	/*
	$("#id").val(result.id);
	$("#userCode").val(result.code);
	$("#code").val(result.code);
	$("#name").val(result.name);
	$("#password").val(result.password);
	$("#type").val(result.type);
	$("#sex").val(result.sex);
	$("#telephone").val(result.telephone);
	$("#cellphone").val(result.cellphone);
	$("#title").val(result.title);
	$("#cardnumber").val(result.cardnumber);
	$("#address").val(result.address);
	$("#cardnumber").val(result.cardnumber);
	$("#rolenames").val(result.rolenames);
	
	if (result.rolenames != "" && result.rolenames != null) {
		var roleNameArr = result.rolenames.split(',');
		var rolecodes = "";
		var roleListObj = $("#DialogRoleList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
		//roleListObj.find("input:first").attr("checked", false);
		$.each(roleNameArr, function(i, roleName) {
			roleListObj.each(function(i, item) {
				var itemObj = $(item);
				var input = itemObj.find("input:first");
				//alert(itemObj.find("td").eq(1).html() + " - " + roleName)
				if (itemObj.find("td").eq(1).html() == roleName) {
					//alert("same")
					//input.attr("checked", true);
					rolecodes == "" ? rolecodes += input.val() : rolecodes += "," + input.val();
					return false;
				}
			});
		});
		
		$("#rolecodes").val(rolecodes);
	}
	*/
	
	
	//$("#rolecodes").val(result.rolecodes);
	
	//$("#roleCode").val(result.role.code); 
	
	
	//$("#sn").val(sn);
}
function addUserInfo() {
	var titleTxt = $(window.parent.document).find("#pageTitle").html();
	var orgPath = titleTxt.substr(titleTxt.indexOf("- ") + 2);
	
	$("#id").val("");
	$("#userCode").val("");
	$("#code").val("");
	$("#name").val("");
	$("#password").val("");
	$("#type").val("");
	$("#sex").val("");
	$("#telephone").val("");
	$("#cellphone").val("");
	$("#title").val("");
	$("#cardnumber").val("");
	$("#address").val("");
	$("#cardnumber").val("");
	$("#roleCode").val("");
	$("#rolenames").val("");
	$("#rolecodes").val("");
	//$("#sn").val(sn);
}

function editRoleUser() {
	/*
	$.post(common.getProjectRootPath() + "/GetRoleUserByUserCode.action", { usercode: userCode}, function (result) {
		
	});
	*/
	//var rolecodesVal = $(window.parent.document).find("#rolecodes").val();
	var roleNames = $(window.parent.document).find("#rolenames").val();
	if (roleNames != "") {
		var roleNameArr = roleNames.split(',');
		//var rolecodes = "";
		var roleListObj = $("#DialogRoleList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
		roleListObj.find("input:first").attr("checked", false);
		$.each(roleNameArr, function(i, roleName) {
			roleListObj.each(function(i, item) {
				var itemObj = $(item);
				var input = itemObj.find("input:first");
				//alert(itemObj.find("td").eq(1).html() + " - " + roleName)
				if (itemObj.find("td").eq(1).html() == roleName) {
					//alert("same")
					input.attr("checked", true);
					//rolecodes == "" ? rolecodes += input.val() : rolecodes += "," + input.val();
					return false;
				}
			});
		});
	}
	/*
	var organizationCode = $("#organizationCode").val();
	//alert("a")
	$.post(common.getProjectRootPath() + "/EditUser.action", { userCode: userCode, organizationCode:organizationCode}, function (result) {
		//alert("b")
		getUserCallback(result);
	*/
		var roleConfirmObj = new Object();
		roleConfirmObj.func = "chooseRoles()";
		roleConfirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var roleDialogObj = new Object();
		roleDialogObj.funcName = "Role";
		roleDialogObj.titleText = "岗位列表";
		roleDialogObj.message = $("#OrganizationSelectList").html();
		roleDialogObj.confirm = roleConfirmObj;
		roleDialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(roleDialogObj));
		
    /*}, "json");*/
	return false;
}
</script>
</head>
<body>

<div id="iframeContent">
	
	<!-- <div id="contentBox">
		
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Home">人员信息 - 鞍山现金运营中心</a></li>
			</ul>
			<div class="content-top-button">
				<a href="#" onclick="deleteUserInfo();" class="Add">新增</a>
				<a href="#" class="Delete" onclick="deleteUserList();">删除</a>
			</div>
		</div>
		 -->
		
		<div id="content-content">
			<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
				<tr class="title">
					<th width="5%"><input type="checkbox" name="allbox" class="checkBoxAll" /></th>
					<th width="8%">姓名</th>
					<th width="5%">性别</th>
					<th width="15%">电话</th>
					<th width="7%">职务</th>
					<th width="20%">岗位</th>
					<th width="15%">指纹机用户代号</th>
					<th width="25%">操作</th>
				</tr>
				<c:forEach items="${pageInfo.data}" var="userItem">
				<tr>
					<td><input type="checkbox" value='${userItem.code}' name="id"/></td>
					<td>${userItem.name}</td>
					<td>${userItem.sex}</td>
					<td>${userItem.telephone}</td>
					<td>${userItem.title}</td>
					<td></td>
					<td></td>
					<td>
						<a href="#" onclick="editUser('${userItem.code}', '${userItem.password}');"  class="edit">编辑</a>
						<a href='#' onclick="deleteUser('${userItem.code}');" class="delete">删除</a>
						<a href="#" class="info">详情</a>
						<a href='ProductAction.do?method=list&columnId=${row.column.id}'></a>
					</td>
				</tr>
				</c:forEach>
				
			</table>
		
			<!-- 
			<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="dataList" width="100%">
				<tr class="table-title">
					<th class="table-center"><input type="checkbox" name="allbox"  class="checkBoxAll" /></th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>职务</th>
					<th>岗位</th>
					<th>指纹机用户代号</th>
					<th class="table-center">操作</th>
				</tr>
				<c:forEach items="${pageInfo.data}" var="userItem">
				<tr>
					<td class="table-center"><input type="checkbox" value='${userItem.code}' name="id"/></td>
					<td class="itenName">${userItem.name}</td>
					<td>${userItem.sex}</td>
					<td>${userItem.telephone}</td>
					<td>${userItem.title}</td>
					<td></td>
					<td></td>
					<td class="optionButtons table-center">
					<a href="#" onclick="editUser('${userItem.code}', '${userItem.password}');"  class="edit">编辑</a>
					<a href='#' onclick="deleteUser('${userItem.code}');" class="delete">删除</a>
					<a href="#" class="info">详情</a>
					<a href='ProductAction.do?method=list&columnId=${row.column.id}'></a></td>
				</tr>
				</c:forEach>
				
			</table>
			 -->
			
			<div class="page-box">
				<div class="pagination">
					<a href="http://localhost:8080/VaultMan/GetUserList.Action?organizationCode=111&pageNo=1"  title="First Page">&laquo; First</a><a href="http://localhost:8080/VaultMan/GetUserList.Action?organizationCode=111&pageNo=<c:if test="${pageInfo.pageNo >1}">${pageInfo.pageNo-1}</c:if><c:if test="${pageInfo.pageNo<2}">1</c:if>" title="Previous Page">&laquo; Previous</a>
					<c:forEach begin="1" end="${pageInfo.pageCount}"  varStatus="status"> 
					<a href="http://localhost:8080/VaultMan/GetUserList.Action?organizationCode=111&pageNo=${status.index}" <c:if test="${pageInfo.pageNo == status.index}"> class="number current"</c:if><c:if test="${pageInfo.pageNo != status.index}"> class="number"</c:if>  title="${status.index}">${status.index}</a>
					</c:forEach>
					<a href="http://localhost:8080/VaultMan/GetUserList.Action?organizationCode=111&pageNo=<c:if test="${pageInfo.pageNo < pageInfo.pageCount}">${pageInfo.pageNo+1}</c:if><c:if test="${pageInfo.pageNo==pageInfo.pageCount}">${pageInfo.pageCount}</c:if>" title="Next Page">Next &raquo;</a><a href="http://localhost:8080/VaultMan/GetUserList.Action?organizationCode=111&pageNo=${pageInfo.pageCount}" title="Last Page">Last &raquo;</a>
				</div> <!-- End .pagination -->
			</div>		
		</div><!-- End #content-content -->
		
	<!-- </div>
	
	<div style="clear:both"></div> -->
	<!-- </div> -->

	<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none">
		<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">人员信息</h1></div> -->
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<form action="SaveUser.action" focus="id" method="post" style="margin:0;">
				<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
				<input type="hidden" id="id" name="id"/>
				<input type="hidden" id="userCode" name="userCode"/>
				<table cellpadding="0" cellspacing="0" border="0" id="OrganizationEdit" class="OrganizationEdit" width="100%" style="margin:0;">
					<tr>
						<td>是否登录：</td>
						<td><input type="checkbox" id="ableLogin" onchange="IsLogin(this)" /></td>
						<td>登录帐户：</td>
						<td><input type="text" id="code" name="code" class="" /><!-- <span class="key"></span> --></td>
						<td>登录密码：</td>
						<td><input type="text" id="password" name="password" class="" /><!-- <span class="key"></span> --></td>
						<!--
						<td>指纹机用户代号：</td>
						<td><input type="text" id="SS" name="SS" /></td>
						-->
					</tr>
					<tr>
						<td width="11%">姓名：</td>
						<td width="22%"><input type="text" id="name" name="name" class="" /><span class="key"></span></td>
						<td width="12%">性别：</td>
						<td width="22%"><select id="sex" name="sex"><option selected="selected">男</option><option>女</option></select></td>
						<td width="11%">职务：</td>
						<td width="22%"><select id="title" name="title">
						<c:forEach items="${lookupoptionList}" var="lookupoption">
										  	<option value="${lookupoption.optionvalue}">${lookupoption.option}</option>
									</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td>电话：</td>
						<td><input type="text" id="telephone" name="telephone" class="" /><span class="key"></span></td>
						<td>手机：</td>
						<td><input type="text" id="cellphone" name="cellphone" class="" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>岗位：</td>
						<td colspan="5">
							<input type="text" id="rolenames" name="rolenames" class="colspan5C" disabled="disabled" />
							<input type="text" id="rolecodes" name="rolecodes" style="display:none" />
							<!--
							<select id="roleCode" name="roleCode">
									<c:forEach items="${roleList}" var="role">
										  	<option value="${role.code}">${role.name}</option>
									</c:forEach>
							</select>
							-->
							<span class="key_colspan5C"><a href="#" class="Edit" onclick="$('#contentIframe')[0].contentWindow.editRoleUser()"></a></span></td>
					</tr>
					<tr>
						<td>身份证号码：</td>
						<td colspan="5"><input type="text" id="cardnumber" name="cardnumber" class="" /><span class="key"></span></td>
						<!-- <select id="organization" name="organization" disabled="disabled">
							<option value="${userInfomation.organization.code}" selected="selected">${userInfomation.organization.name}</option>
						</select>
						<span class="key"></span> -->
					</tr>
					<tr>
						<td>机构信息</td>
						<td colspan="5"><input type="text" id="organization" name="organization" disabled="disabled" /></td>
					</tr>
					<tr>
						<td>家庭住址：</td>
						<td colspan="5"><input type="text" id="address" name="address"  class="colspan5" /><span class="key_colspan5"></span></td>
					</tr>
				</table>
				
			</form>
		</div>
		<div style="clear:both"></div>
		<!-- 
		<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;border-top:1px #CCC solid;width:740px;">
			<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a><a href="#" onclick="onsubmits();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
		</div>
		 -->
	</div>
	
	<div id="OrganizationSelectList" style="float:left;width:758px;padding:0;display:none">
		<div id="DialogRoleList" style="margin:0 10px;padding-top:10px;width:500px;">
			
			<div id="content-content">
				<table cellpadding="0" cellspacing="0" border="0" id="RoleList" class="newdataList" width="100%">
					<tr class="title">
						<th width="5%"><!-- <input type="checkbox" class="checkBoxAll" /> --></th>
						<th width="45%">岗位名称</th>
						<th width="50%">岗位描述</th>
						<!-- <th>到岗日期</th> -->
					</tr>
					<c:forEach items="${roleList}" var="roleItem">
					<tr>
						<td><input type="checkbox" name="id" value='${roleItem.code}'/></td>
						<td>${roleItem.name}</td>
						<td>${roleItem.alias}</td>
						<!-- 
						<td>
							<input type="text" name="expired" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>
						 -->
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
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
</div>

</body>

</html>