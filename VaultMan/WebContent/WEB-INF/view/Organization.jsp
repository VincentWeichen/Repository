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
<!-- <script type="text/javascript" src="<%=path %>/Scripts/Common.js"></script> -->
<script type="text/javascript" src="<%=path %>/Scripts/Tree.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script type="text/javascript" language="javascript">

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

function btnAddClick(treeItem) {
	var currentNode = $("#tree" + treeItem.nodeId);
	
	var myDate = new Date();
	var newCode = myDate.getHours().toString() + myDate.getMinutes().toString() + myDate.getSeconds().toString() + myDate.getMilliseconds().toString()
	 
	treeNodes =[
		{ nodeName:"新建节点", nodeId: newCode,
			childrenTreeList: []
		}];
		
	treeItem.childrenTreeList.push(treeNodes);
	
	var newNodeParent = currentNode.find("ul:first");
	//alert(newNodeParent.html());
	if (treeItem.childrenTreeList.length < 2) {
		newNodeParent.find("ul:first").attr("class", "hasLine");
		//alert(newNodeParent.find("ul:first").attr("class"));
	}
	
	$.fn.Tree({ data: treeNodes, treeItemParent: newNodeParent, currentNum: treeItem.childrenTreeList.length, isParent: false });
	
	var curNode = $("#tree" + newCode).find("a:first");
	var oldAction = $("#treeDemo a.TreeCurSelected");
	oldAction.removeClass("TreeCurSelected");
	curNode.addClass("TreeNewSelected");
	
	$("#tempCode").val(newCode);
	$("#parentcode").val(treeItem.nodeId);
	//清空界面中的值
	$("#id").val("");
	$("#code").val("");
	$("#name").val("");
	$("#alias").val("");
	$("#address").val("");
	$("#telephone").val("");
	$("#manager").val("");
	$("#cellphone").val("");
	$("#tid").val("");
	$("#type").val("");
	//currentNode
	$("#submit")[0].disabled = false;
}

function submit_click() {
	var newCode = $.trim($("#code").val());
	var newName = $.trim($("#name").val());
	var newAddress = $.trim($("#address").val());
	if (newCode == "") {
		$(window.parent.showMessage("机构编码不能为空！"));
		//$.fn.Dialog({ funcName: "Alert", message: "机构编码不能为空！", autoClose: 3 });
		return false;
	}
	if (newName == "") {
		$(window.parent.showMessage("机构名称不能为空！"));
		//$.fn.Dialog({ funcName: "Alert", message: "机构名称不能为空！", autoClose: 3 });
		return false;
	}
	if (newAddress == "") {
		$(window.parent.showMessage("机构地址不能为空！"));
		//$.fn.Dialog({ funcName: "Alert", message: "机构地址不能为空！", autoClose: 3 });
		return false;
	}
	
	var organizationId = $.trim($("#id").val());
	
	var paramData = $("form").eq(0).serialize();
	
	$.post(common.getProjectRootPath() + "/organizationSave.action", paramData, function (result) {
		//alert(result);
		$(window.parent.reloadTree(organizationId, "save"));
    });
}

/*
function btnDelClick(nodeId) {
	var thisLevels = $("#tree" + nodeId).find("li");
	if (thisLevels.length > 0) {
		$(window.parent.showMessage("该机构下还有子机构，无法删除！"));
		//$.fn.Dialog({ funcName: "Alert", message: "该机构下还有子机构，无法删除！", autoClose: 3 });
		return false;
	}
	//alert("执行后台删除方法" + nodeId);
	
	//alert(nodeId);
	
	$(window.parent.showDialog("确定要删除该机构吗？", "execDelete(" + nodeId + ")"));
	
	
}
*/

function execDelete(nodeId) {
	//alert(nodeId);
	$.post(common.getProjectRootPath() + "/organizationDelete.action", { code: nodeId }, function (result) {
		//alert(result);
		var returnJson = eval(result);
		if (returnJson.returnVal)
		{
			conifrmCallback(nodeId);
		}
		else {
			$(window.parent.showMessage(returnJson.errorMessage));
			//$.fn.Dialog({ funcName: "Alert", message: returnJson.errorMessage, autoClose: 3 });
		}
		
    });
}

function conifrmCallback(nodeId) {
	$.fn.RemoveNode($("#tree" + nodeId));
	var msgObj = new Object();
	msgObj.message = "机构删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
	//$(window.parent.showMessage("机构删除成功！"));
	//$.fn.Dialog({ funcName: "Alert", message: "机构删除成功！", messageType: "success", autoClose: 3 });
}

$(document).ready(function(){
	//$.fn.Tree({ data: treeNodes });
});

function AddFunc(organizationId) {
	//alert(organizationId);
	window.location.href = "/VaultMan/addOrganization?organizationParentcode=" + organizationId;
}

function DelFunc(organizationId) {
	
	$(window.parent.showDialog("确定要删除该机构吗？", "execDelete('" + organizationId + "')"));
	
	//alert(nodeId);
	/*
	$.post(common.getProjectRootPath() + "/organizationDelete.action", { code: organizationId }, function (result) {
		var returnJson = eval(result);
		if (returnJson.returnVal)
		{
			//conifrmCallback(nodeId);
			$(window.parent.reloadTree(organizationId, "delete"));
		}
		else {
			$.fn.Dialog({ funcName: "Alert", message: returnJson.errorMessage, autoClose: 3 });
		}
    });
	*/
}

function ChangeType(Obj) {
	var tidObj = $("#tid");
	Obj.value != 1 ? tidObj.attr("disabled", false) : tidObj.attr("disabled", true);
}
</script>
<title>机构信息</title>
</head>
<body>

<div id="iframeContent">
		
	<div id="content-content">
		<form onsubmit="return false">
			<table cellpadding="0" cellspacing="0" border="0" id="OrganizationEdit" class="OrganizationEdit" width="100%">
				<tr>
					<td width="11%">机构编码：</td>
					<td width="22%"><input type="text" id="code" name="code" class="" value="${OrganizationInfo.code}" <c:if test="${OrganizationInfo.code.length() > 0}">readonly="readonly"</c:if> /><span class="key"></span><input type="hidden" id="id" name="id" value="${OrganizationInfo.id}" /><input type="hidden" id="tempCode" name="tempCode" /></td>
					<td width="12%">机构名称：</td>
					<td width="22%"><input type="text" id="name" name="name" class="" value="${OrganizationInfo.name}" /><span class="key"></span></td>
					<td width="11%">简称/别名：</td>
					<td width="22%"><input type="text" id="alias" name="alias" class="" value="${OrganizationInfo.alias}" /><input type="hidden" id="parentcode" name="parentcode" value="${OrganizationInfo.parentcode}" /></td>
				</tr>
				<!-- 
				<tr>
					<td>省份：</td>
					<td><select><option>北京</option><option>上海</option><option>天津</option><option>重庆</option><option selected="selected">辽宁省</option><option>山东省</option></select><span class="key"></span></td>
					<td>城市/地区：</td>
					<td><select><option selected="selected">沈阳市</option><option>大连市</option></select><span class="key"></span></td>
					<td>区/县：</td>
					<td><select><option selected="selected">和平区</option><option>沈河区</option><option>铁西区</option><option>大东区</option><option>皇姑区</option></select><span class="key"></span></td>
				</tr>
				-->
				<tr>
					<td>详细地址：</td>
					<td colspan="5"><input type="text" id="address" name="address" value="${OrganizationInfo.address}" class="colspan5" /><span class="key_colspan5"></span></td>
				</tr>
				<tr>
					<!-- <td>是否为库房：</td>
					<td><select><option>是</option><option>否</option></select></td> -->
					<td>库房类型：</td>
					<!-- <td><select id="type" name="type"><option value="0">管理机构</option><option value="1">现金库</option><option value="2">款包库</option><option value="3">凭证库</option></select></td> -->
					<td>
						<select name="type" onchange="ChangeType(this)">
						    <option value="1" <c:if test="${OrganizationInfo.type==1}">selected</c:if>>管理机构</option>
						    <option value="2" <c:if test="${OrganizationInfo.type==2}">selected</c:if>>现金库</option>
							<option value="3" <c:if test="${OrganizationInfo.type==3}">selected</c:if>>款包库</option>
							<option value="4" <c:if test="${OrganizationInfo.type==4}">selected</c:if>>凭证库</option>
						</select>
					</td>
					<td>终端设备：</td>
					<td><input value="${OrganizationInfo.tid}" <c:if test="${OrganizationInfo.type==1}">disabled="disabled"</c:if> type="text" id="tid" name="tid" /></td><!-- <select id="tid" name="tid"><option value="0001">0001</option><option value="0002">0002</option></select> -->
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" id="telephone" name="telephone" class="" value="${OrganizationInfo.telephone}" /></td>
					<td>负责人编号：</td>
					<td><input type="text" id="manager" name="manager" class="" value="${OrganizationInfo.manager}" /></td>
					<td>负责人手机：</td>
					<td><input type="text" id="cellphone" name="cellphone" class="" value="${OrganizationInfo.cellphone}" /></td>
				</tr>
				<!--
				<tr>
					<td colspan="6">
						<div>
							<textarea class="text-input textarea wysiwyg" id="textarea" name="textfield" cols="79" rows="15"></textarea>
						</div>
					</td>
				</tr>
				-->
				<tr>
					<td colspan="6"><input class="button" id="submit" style="width:auto;height:23px;" type="button" value="保存" onclick="submit_click()" /></td>
				</tr>
			</table>
		</form>
		
	</div><!-- End #content-content -->
	
</div>

</body>
</html>