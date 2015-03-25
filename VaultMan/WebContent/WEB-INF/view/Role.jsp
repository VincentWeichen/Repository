<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/Basic/Dialog.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Common.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script> -->
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Role.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script> -->

<script type="text/javascript">
var thisPage= "";
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
function editRole(roleCode) {
	//alert("1")
	$.post(common.getProjectRootPath() + "/EditRole.action", { roleCode: roleCode}, function (result) {
		//getRoleCallback(result);
		//alert("2")
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits()";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		dialogObj.titleText = "编辑岗位";
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		//alert("21")
		getRoleCallback(result);
		
		/*
		setTimeout(function() {
			getRoleCallback(result);
			$.fn.Close(null, event);
		}, 1000);
		*/
    }, "json");
	return false;
}
function getRoleCallback(result) {
	var parentObj = $(window.parent.document);
	//alert("22")
	setFunctionListTitle();
	//alert("23")
	parentObj.find("#id").val(result[0].id);
	parentObj.find("#code").val(result[0].code);
	parentObj.find("#name").val(result[0].name);
	parentObj.find("#alias").val(result[0].alias);
	parentObj.find("#type").val(result[0].type);
	//alert("3")
	//$(window.parent.setMenuRight(result[0].rolefunctionList));
	
	//alert(result[0].rolefunctionList.length)
	//调用AJAX取数据库记录，并绑定值
	for(var j =0;j<result[0].rolefunctionList.length;j++) {
		if(result[0].rolefunctionList[j].functioncode.length>0)
		{
			var rolefunctions = parentObj.find("[name='"+result[0].rolefunctionList[j].functioncode+"'");
			var funwrite = true;
			var funread = true;
			if(result[0].rolefunctionList[j].funwrite ==1)
			{
				funwrite = true;
			}else
			{
				funwrite = false;
			}
			if(result[0].rolefunctionList[j].funread ==1)
			{
				funread = true;
			}else
			{
				funread = false;
			}
			rolefunctions.eq(0).attr("checked",funread);
			rolefunctions.eq(1).attr("checked",funwrite);
		}
	}
	
	//为Checkbox绑定事件
	//alert("3")//parentObj.find("#functionList tr td input").length)
	parentObj.find("#functionList tr td input").each(function(i, item) {
		var thisObj = $(item);
		thisObj.bind("click", function() {
			checkBoxChecked(thisObj, i%2==0?0:1)
		});
	});
	
	parentObj.find("#functionList a.treeIcon").bind("click", function() {
		var thisObj = $(this);
		var nameFlg = thisObj.closest("tr").find("input:first").attr("name") + "_";
		var aboutTr = parentObj.find("#functionList tr td:last-child input[name*=" + nameFlg + "]:first-child").closest("tr");
		if (thisObj.is(".viewTree")) {
			aboutTr.hide();
			thisObj.removeClass("viewTree").addClass("hideTree");
		}
		else if (thisObj.is(".hideTree")) {
			aboutTr.show();
			thisObj.removeClass("hideTree").addClass("viewTree");
		}
		
		setFunctionListTitle();
		
		return false;
	});
	/*
	$("#id").val(result.id);
	$("#code").val(result.code);
	$("#name").val(result.name);
	$("#alias").val(result.alias);
	$("#type").val(result.type);
	*/
}

function setFunctionListTitle() {
	var parentObj = $(window.parent.document);
	
	var ths = parentObj.find("#functionListTitle th");
	if (parentObj.find("#functionList").height() < parentObj.find("#functionListBox").height()) {
		ths.eq(0).width("70%");
		ths.eq(1).width("30%");
	}
	else {
		ths.eq(0).width("68.4%");
		ths.eq(1).width("31.6%");
	}
}

//点击Checkbox的执行事件
//thisObj:当前操作的控件
//flg:当前操作的控件标识,0:只读;1:修改
function checkBoxChecked(thisObj, flg) {
	//alert("a")
	var name = thisObj.attr("name");
	var parentObj = $(window.parent.document);
	if (thisObj.attr("checked")) {
		var inputs = parentObj.find("#functionList tr td:last-child input:first-child");
		if (flg == 1) {
			thisObj.prev().attr("checked", true);
		}
		
		upConfig(name, inputs);
	}
	else {
		if (flg == 0) {
			//当取消选中时，向下更新状态，将下级的只读、修改状态更新为不选中
			name = name + "_";
			var aboutInputs = parentObj.find("#functionList tr td:last-child input[name*=" + name + "]:first-child");
			//alert(aboutInputs.length)
			aboutInputs.attr("checked", false).next().attr("checked", false);
			thisObj.next().attr("checked", false);
		}
	}
}

//递归向上更新状态
function upConfig(name, inputs) {
	var pos = name.lastIndexOf("_");
	if (pos != -1) {
		name = name.substring(0, pos);
		//alert(name)
		inputs.each(function(i, item) {
			var thisObj = $(item);
			if (thisObj.attr("name") == name) {
				thisObj.attr("checked", true);
				return false;
			}
		});
		upConfig(name, inputs);
	}
}

function onsubmits() {
	//var last = $("form").length - 1;
	//alert($("#name").val());
	var parentObj = $(window.parent.document);
	
	var nameVal = parentObj.find("#name").val();
	var idVal = parentObj.find("#id").val();
	var codeVal = parentObj.find("#code").val();
	var aliasVal = parentObj.find("#alias").val();
	var typeVal = parentObj.find("#type").val();
	
	$("#name").val(nameVal);
	$("#id").val(idVal);
	$("#code").val(codeVal);
	$("#alias").val(aliasVal);
	$("#type").val(typeVal);
	
	var functionInfoString = "";
	parentObj.find("#functionList tr td:last-child").each(function(i, item) {
		var thisObj = $(item);
		var inputs = thisObj.find("input");
		var visible = 0;
		inputs.eq(0).attr("checked") ? visible = 1 : visible = 0;
		var able = 0;
		inputs.eq(1).attr("checked") ? able = 1 : able = 0;
		var code = inputs.eq(0).attr("name");
		var name = thisObj.prev().html();
		name = name.substring(name.lastIndexOf(">") + 1);
		
		i > 0 ? functionInfoString += ";" + code + "," + name + "," + visible + "," + able : functionInfoString = code + "," + name + "," + visible + "," + able;
	});
	//alert(functionInfoString);
	$("#functionInfo").val(functionInfoString);
	
	//var formObj = $(window.parent.document).find("form:last");
	//alert(formObj.val());
	
	$("form")[0].action=common.getProjectRootPath() + "/SaveRole.action";
	$("form")[0].submit();
	
	var msgObj = new Object();
	msgObj.message = "新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
function chkSubmit(frm){
	frm.action=common.getProjectRootPath() + "/SaveUser.action";
	frm.submit();
	
}

function deleteRole(roleCode){
	$(window.parent.showDialog("确定要删除该岗位吗？", "execDelete('" + roleCode + "')"));
	//document.location.href='DeleteRole.action?roleCode=' +roleCode;
}
function addRoleInfo() {
	$("#id").val("");
	$("#code").val("");
	$("#name").val("");
	$("#alias").val("");
	$("#type").val("");
}
function deleteRoleList(){
	var checkedObjs = $("#dataList tr[class!='Info'][class!='table-title'][class!='Info-tr'] input[checked='checked']");
	if (checkedObjs.length == 0) {
		$(window.parent.showMessage("请选择要删除的岗位！"));
		return false;
	}
	
	var roleCodeStr = "";
	checkedObjs.each(function(i, obj) {
		var item = $(obj);
		roleCodeStr == "" ? roleCodeStr += item.val() : roleCodeStr += "&roleCodeStr=" + item.val();
		//roleCodeStr += ""
	});
	
	//alert(roleCodeStr);
	
	/*
	//加一个删除确认提示框
	var ids = document.getElementsByName("id");
	var roleCodeStr = "";
	for(var i =0;i<ids.length;i++) {
	  if(ids[i].type == 'checkbox' && ids[i].checked && ids[i].name != 'allbox') {
		  roleCodeStr += '&code='+ids[i].value;
	   }
    }
	if(roleCodeStr.length==0)
	{
		$.fn.Dialog({ funcName: "Alert", message: "请选择要删除的岗位！", autoClose: 3 });
		return false;
	}
	*/
	//document.location.href='DeleteRoleList.action?roleCodeStr=' +roleCodeStr;
	$(window.parent.showDialog("确定要删除该岗位吗？", "execDelete('" + roleCodeStr + "')"));
}

function execDelete(roleCodeStr) {
	//alert(roleCodeStr);
	document.location.href='DeleteRoleList.action?roleCodeStr=' +roleCodeStr;
	var msgObj = new Object();
	msgObj.message = "岗位删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
</script>
</head>
<body>

<div id="iframeContent">
	<!--
	<div class="content-top">
		<ul id="content-nav">
			<li class="current"><a href="#" class="Home">岗位信息</a></li>
		</ul>
		<div class="content-top-button">
			<a href="#" class="Add">新增</a>
			<a href="#" class="Delete">删除</a>
		</div>
	</div>
	-->
	<!-- 
	<div id="contentBox">
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" class="Home">岗位信息</a></li>
			</ul>
			<div class="content-top-button">
				<a href="#" onclick="deleteRoleInfo();" class="Add">新增</a>
				<a href="#" onclick="deleteRoleList();" class="Delete">删除</a>
			</div>
		</div> -->
	<div id="content-content">
		<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
			<tr class="title">
				<th width="5%"><input type="checkbox" class="checkBoxAll" /></th>
				<th width="15%">岗位名称</th>
				<th width="15%">岗位权限</th>
				<th width="50%">岗位描述</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${pageInfo.data}" var="rolesItem">
			<tr>
				<td><input type="checkbox" name="id" value='${rolesItem.code}'/></td>
				<td>${rolesItem.name} </td>
				<td></td>
				<td>${rolesItem.alias}</td>
				<td>
					<a href="#" onclick="editRole('${rolesItem.code}');" class="edit">编辑</a>
					<a href="#" onclick="deleteRole('${rolesItem.code}');" class="delete">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	
		<!-- 
		<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="dataList" width="100%">
			<tr class="table-title">
				<th class="table-center"><input type="checkbox" class="checkBoxAll" style=" vertical-align:text-top" /></th>
				<th>岗位名称</th>
				<th>岗位权限</th>
				<th>岗位描述</th>
				<th class="table-center">操作</th>
			</tr>
			<c:forEach items="${pageInfo.data}" var="rolesItem">
			<tr>
				<td class="table-center"><input type="checkbox" name="id" value='${rolesItem.code}'/></td>
				<td class="itenName">${rolesItem.name} </td>
				<td class=""></td>
				<td>${rolesItem.alias}</td>
				<td class="optionButtons table-center"><a href="#" onclick="editRole('${rolesItem.code}');" class="edit">编辑</a>
				<a href="#" onclick="deleteRole('${rolesItem.code}');" class="delete">删除</a></td>
			</tr>
			</c:forEach>
		</table>
		 -->
		
		<div class="page-box">
				<div class="pagination">
					<a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=1"  title="First Page">&laquo; First</a><a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=<c:if test="${pageInfo.pageNo >1}">${pageInfo.pageNo-1}</c:if><c:if test="${pageInfo.pageNo<2}">1</c:if>" title="Previous Page">&laquo; Previous</a>
					<c:forEach begin="1" end="${pageInfo.pageCount}"  varStatus="status"> 
					<a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=${status.index}" <c:if test="${pageInfo.pageNo == status.index}"> class="number current"</c:if><c:if test="${pageInfo.pageNo != status.index}"> class="number"</c:if>  title="${status.index}">${status.index}</a>
					</c:forEach>
					<a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=<c:if test="${pageInfo.pageNo < pageInfo.pageCount}">${pageInfo.pageNo+1}</c:if><c:if test="${pageInfo.pageNo==pageInfo.pageCount}">${pageInfo.pageCount}</c:if>" title="Next Page">Next &raquo;</a><a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=${pageInfo.pageCount}" title="Last Page">Last &raquo;</a>
				</div> <!-- End .pagination -->
			</div>	
	</div><!-- End #content-content -->
	<!-- </div> -->

	<div id="UserInfo" style="margin:0;width:758px;border:1px #FFF solid;padding:0;display:none;"><!--  -->
		<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div> -->
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;">
			<form action="SaveRole.action" focus="id" method="post" style="margin:0;">
				<input type="hidden" id="id" name="id"/>
				<input type="hidden" id="type" name="type"/>
				<input type="hidden" id="functionInfo" name="functionInfo" />
				<table cellpadding="0" cellspacing="0" border="0" id="OrganizationEdit" class="OrganizationEdit" width="100%" style="margin:0;">
					<tr>
						<td width="10%">岗位名称：</td>
						<td width="15%"><input type="text" id="name" Name="name"/><span class="key"></span></td>
						<td width="10%">岗位编码：</td>
						<td width="15%"><input type="text" id="code" Name="code"/><span class="key"></span></td>
						<td width="10%">岗位描述：</td>
						<td width="34%"><input type="text" id="alias" Name="alias"/></td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" border="0" id="functionListTitle" class="newdataList" width="99%">
					<tr class="title">
						<!-- <th width="5%" class="table-center"><input type="checkbox" class="checkBoxAll" /></th> -->
						<th width="68.4%">功能名称</th>
						<th width="31.6%" class="table-center">操作</th>
					</tr>
				</table>
				<div id="functionListBox" style="margin:0;width:99%; height:300px; overflow-y:auto;">
					<table cellpadding="0" cellspacing="0" border="0" id="functionList" class="newdataList" width="100%" style="margin:0px;height:auto">
						<tr>
							<td width="70%"><a href="#" class="treeIcon viewTree"></a>基础信息维护</td>
							<td width="30%"><input type="checkbox" name="01" />只读<input type="checkbox" name="01" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>机构设置维护</td>
							<td><input type="checkbox" name="01_11" />只读<input type="checkbox" name="01_11" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>机构基础信息维护</td>
							<td><input type="checkbox" name="01_11_111" />只读<input type="checkbox" name="01_11_111" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>机构归属信息维护</td>
							<td><input type="checkbox" name="01_11_112" />只读<input type="checkbox" name="01_11_112" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>岗位设置维护</td>
							<td><input type="checkbox" name="01_12" />只读<input type="checkbox" name="01_12" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>岗位基础信息维护</td>
							<td><input type="checkbox" name="01_12_121" />只读<input type="checkbox" name="01_12_121" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>不相容岗位设置维护</td>
							<td><input type="checkbox" name="01_12_122" />只读<input type="checkbox" name="01_12_122" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>人员设置维护</td>
							<td><input type="checkbox" name="01_13" />只读<input type="checkbox" name="01_13" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>工作流维护</td>
							<td><input type="checkbox" name="01_14" />只读<input type="checkbox" name="01_14" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>管制时间审批流程维护</td>
							<td><input type="checkbox" name="01_14_141" />只读<input type="checkbox" name="01_14_141" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>排班管理审批流程维护</td>
							<td><input type="checkbox" name="01_14_142" />只读<input type="checkbox" name="01_14_142" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>字典维护</td>
							<td><input type="checkbox" name="01_15" />只读<input type="checkbox" name="01_15" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>公告维护</td>
							<td><input type="checkbox" name="01_16" />只读<input type="checkbox" name="01_16" />修改</td>
						</tr>
						<tr>
							<td><a href="#" class="treeIcon viewTree"></a>系统查询</td>
							<td><input type="checkbox" name="02" />只读<input type="checkbox" name="02" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>金库事件查询</td>
							<td><input type="checkbox" name="02_21" />只读<input type="checkbox" name="02_21" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>管理操作查询</td>
							<td><input type="checkbox" name="02_22" />只读<input type="checkbox" name="02_22" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>软件日志查询</td>
							<td><input type="checkbox" name="02_22_221" />只读<input type="checkbox" name="02_22_221" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>指纹机日志查询</td>
							<td><input type="checkbox" name="02_22_222" />只读<input type="checkbox" name="02_22_222" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>信息查询</td>
							<td><input type="checkbox" name="02_23" />只读<input type="checkbox" name="02_23" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>设置查询</td>
							<td><input type="checkbox" name="02_24" />只读<input type="checkbox" name="02_24" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>机构设置查询</td>
							<td><input type="checkbox" name="02_24_241" />只读<input type="checkbox" name="02_22_241" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>岗位设置查询</td>
							<td><input type="checkbox" name="02_24_242" />只读<input type="checkbox" name="02_22_242" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>审批流程查询</td>
							<td><input type="checkbox" name="02_24_243" />只读<input type="checkbox" name="02_22_243" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon viewTree"></a>管制时间查询</td>
							<td><input type="checkbox" name="02_24_244" />只读<input type="checkbox" name="02_22_244" />修改</td>
						</tr>
						<tr>
							<td class="Level4"><a href="#" class="treeIcon endTree"></a>当前执行查询</td>
							<td><input type="checkbox" name="02_24_244_2441" />只读<input type="checkbox" name="02_24_244_2441" />修改</td>
						</tr>
						<tr>
							<td class="Level4"><a href="#" class="treeIcon endTree"></a>时间段查询</td>
							<td><input type="checkbox" name="02_24_244_2442" />只读<input type="checkbox" name="02_24_244_2442" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon viewTree"></a>排班查询</td>
							<td><input type="checkbox" name="02_24_245" />只读<input type="checkbox" name="02_22_245" />修改</td>
						</tr>
						<tr>
							<td class="Level4"><a href="#" class="treeIcon endTree"></a>当前执行查询</td>
							<td><input type="checkbox" name="02_24_245_2451" />只读<input type="checkbox" name="02_24_245_2451" />修改</td>
						</tr>
						<tr>
							<td class="Level4"><a href="#" class="treeIcon endTree"></a>时间段查询</td>
							<td><input type="checkbox" name="02_24_245_2452" />只读<input type="checkbox" name="02_24_245_2452" />修改</td>
						</tr>
						
						<tr>
							<td width="70%"><a href="#" class="treeIcon viewTree"></a>实时监控</td>
							<td width="30%"><input type="checkbox" name="03" />只读<input type="checkbox" name="03" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>全屏图标</td>
							<td><input type="checkbox" name="03_31" />只读<input type="checkbox" name="03_31" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>全屏列表</td>
							<td><input type="checkbox" name="03_32" />只读<input type="checkbox" name="03_32" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>报警短信</td>
							<td><input type="checkbox" name="03_33" />只读<input type="checkbox" name="03_33" />修改</td>
						</tr>
						
						<tr>
							<td width="70%"><a href="#" class="treeIcon viewTree"></a>管制时间</td>
							<td width="30%"><input type="checkbox" name="04" />只读<input type="checkbox" name="04" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>管制时间设置</td>
							<td><input type="checkbox" name="04_41" />只读<input type="checkbox" name="04_41" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>周循环</td>
							<td><input type="checkbox" name="04_41_411" />只读<input type="checkbox" name="04_42_421" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>年循环</td>
							<td><input type="checkbox" name="04_41_412" />只读<input type="checkbox" name="04_42_422" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>假期和调班</td>
							<td><input type="checkbox" name="04_41_413" />只读<input type="checkbox" name="04_42_423" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>启用时间</td>
							<td><input type="checkbox" name="04_41_414" />只读<input type="checkbox" name="04_42_424" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>首末操作分布图</td>
							<td><input type="checkbox" name="04_42" />只读<input type="checkbox" name="04_42" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>管制时间查询</td>
							<td><input type="checkbox" name="04_43" />只读<input type="checkbox" name="04_43" />修改</td>
						</tr>
						
						<tr>
							<td width="70%"><a href="#" class="treeIcon viewTree"></a>排班授权</td>
							<td width="30%"><input type="checkbox" name="05" />只读<input type="checkbox" name="05" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>指纹机用户匹配</td>
							<td><input type="checkbox" name="05_51" />只读<input type="checkbox" name="05_51" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>分组</td>
							<td><input type="checkbox" name="05_52" />只读<input type="checkbox" name="05_52" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon viewTree"></a>授权</td>
							<td><input type="checkbox" name="05_53" />只读<input type="checkbox" name="05_53" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>组授权</td>
							<td><input type="checkbox" name="05_53_531" />只读<input type="checkbox" name="05_53_531" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>倒班授权</td>
							<td><input type="checkbox" name="05_53_532" />只读<input type="checkbox" name="05_53_532" />修改</td>
						</tr>
						<tr>
							<td class="Level3"><a href="#" class="treeIcon endTree"></a>时段授权</td>
							<td><input type="checkbox" name="05_53_533" />只读<input type="checkbox" name="05_53_533" />修改</td>
						</tr>
						<tr>
							<td class="Level2"><a href="#" class="treeIcon endTree"></a>排班查询</td>
							<td><input type="checkbox" name="05_54" />只读<input type="checkbox" name="05_54" />修改</td>
						</tr>
						
						<tr>
							<td width="70%"><a href="#" class="treeIcon endTree"></a>帮助</td>
							<td width="30%"><input type="checkbox" name="06" />只读<input type="checkbox" name="06" />修改</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<div style="clear:both"></div>
		<!-- 
		<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
			<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a><a href="#" onclick="onsubmits();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
		</div>
		 -->
	</div>

</div>

</body>
</html>