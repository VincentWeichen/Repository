<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>不相容岗位设置</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/Basic/Dialog.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Common.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/ExclusionRole.js"></script>
<!-- <script type="text/javascript" src="<%=path %>/Scripts/jquery.blockUI.js"></script> -->
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
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

function editExclusionrole(sn) {
	$.post(common.getProjectRootPath() + "/EditExclusionrole.action", { sn: sn}, function (result) {
	    getExclusionroleCallback(result)
		var allRole = result.roleAllName;
		$("#AllRole ul li").remove();
		$("#ExclusionRole ul li").remove();
		currentObj = $(this).parents("tr");
		var GroupCol = $(this).parents("tr").find("td");
		var roleItems = "";
		if(result.exclusionroleName.length > 0)
		{
			roleItems = result.exclusionroleName.substring(1).split(',');
		}	
		
		//alert(allRole + "-" + roleItems)
		$.each(roleItems, function(j, obj) {
			allRole = allRole.replace(","+obj, "");
		});
		allRole = allRole.substring(1);
		//alert(allRole.length)
		if (allRole != "") {
			var allRoleArr = allRole.split(',');
			//alert(allRole)
			$.each(allRoleArr, function(j, obj) {
				$("#AllRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
			});
		}
		$.each(roleItems, function(j, obj) {
			$("#ExclusionRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
		});
		
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits()";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		dialogObj.titleText = "编辑不相容岗位";
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		$(window.parent.bindArgs());
				
    }, "json");
	return false;
}

/*
function editExclusionrole(sn) {
	$.post(common.getProjectRootPath() + "/EditExclusionrole.action", { sn: sn}, function (result) {
		setTimeout(function() {
			var wTop = ($(window).height() - 370) / 2;
			var wLeft = ($(window).width() - 760) / 2;
			    getExclusionroleCallback(result)
				var allRole = result.roleAllName;
				$("#AllRole ul li").remove();
				$("#ExclusionRole ul li").remove();
				currentObj = $(this).parents("tr");
				var GroupCol = $(this).parents("tr").find("td");
				var roleItems = "";
				if(result.exclusionroleName.length > 0)
				{
					roleItems = result.exclusionroleName.substring(1).split(',');
				}	
				
				$.each(roleItems, function(j, obj) {
					allRole = allRole.replace("," +obj, "");
				});
				allRole = allRole.substring(1);
				var allRoleArr = allRole.split(',');
				$.each(allRoleArr, function(j, obj) {
					$("#AllRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
				});
				$.each(roleItems, function(j, obj) {
					$("#ExclusionRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
				});
				
				bindArgs();
				$.blockUI({
					message: $("#UserInfo"),
					css: { border:'none', width: '760', top : wTop, left : wLeft, textAlign : 'left' },
					growlCSS: { width: '760px'}
				});
		$.fn.Close(null, event);
		}, 1000);
    }, "json");
	return false;
}
*/

function AddFunc() {
	$.post(common.getProjectRootPath() + "/AddExclusionrole.action", null, function (result) {
		getExclusionroleCallback(result)
		var allRole = result.roleAllName;
		$("#AllRole ul li").remove();
		$("#ExclusionRole ul li").remove();
		
		allRole = allRole.substring(1);
		var allRoleArr = allRole.split(',');
		$.each(allRoleArr, function(j, obj) {
			$("#AllRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
		});
		/*
		$.each(roleItems, function(j, obj) {
			$("#ExclusionRole ul").append("<li><a href=\"javascript:void(0)\">" + obj + "<\/a><\/li>");
		});
		*/
		
		//bindArgs();
		
		var confirmObj = new Object();
		confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits()";
		confirmObj.isDisplay = true;
		
		var closeObj = new Object();
		closeObj.isDisplay = false;
		
		var dialogObj = new Object();
		dialogObj.funcName = "Users";
		dialogObj.titleText = "新增不相容岗位";
		dialogObj.message = $("#UserInfo").html();
		dialogObj.confirm = confirmObj;
		dialogObj.close = closeObj;
		
		$(window.parent.showUDefinedDialog(dialogObj));
		
		$(window.parent.bindArgs());
    }, "json");
	return false;
}

function getExclusionroleCallback(result) {
	$("#sn").val(result.sn);
}
function onsubmits() {
	
	//var typeVal = $(window.parent.document).find("#type").val();
	
	//$("#name").val(nameVal);
	
	var lists = $(window.parent.document).find("#ExclusionRole ul li a");
	var exclusionroleStr = "";
	$.each(lists, function(j, obj) {
		exclusionroleStr += "," +obj.innerText;
	});
	$("#exclusionrole").val(exclusionroleStr);
	$("form")[0].action=common.getProjectRootPath() + "/SaveExclusionrole.action";
	$("form")[0].submit();
	
	var msgObj = new Object();
	msgObj.message = "新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
function chkSubmit(frm){
	frm.action=common.getProjectRootPath() + "/SaveExclusionrole.action";
	frm.submit();
	
}

function deleteExclusionrole(sn){
	$(window.parent.showDialog("确定要删除该排斥组吗？", "execDelete(" + sn + ")"));
	//document.location.href='DeleteExclusionrole.action?sn=' +sn;
}

function execDelete(sn) {
	document.location.href='DeleteExclusionrole.action?sn=' +sn;
	
	var msgObj = new Object();
	msgObj.message = "排斥组删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function deleteExclusionroleInfo() {
	$("#id").val("");
	$("#code").val("");
	$("#name").val("");
	$("#alias").val("");
	
}
function deleteExclusionroleList(){
	//加一个删除确认提示框
	var ids = document.getElementsByName("id");
	var exclusionroleCodeStr = "";
	for(var i =0;i<ids.length;i++) {
	  if(ids[i].type == 'checkbox' && ids[i].checked && ids[i].name != 'allbox') {
		  exclusionroleCodeStr += '&sn='+ids[i].value;
	   }
    }
	if(exclusionroleCodeStr.length==0)
	{
		var msgObj = new Object();
		msgObj.message = "请选择要删除的岗位！";
		msgObj.messageType = "alert";
		$(window.parent.showMessage(msgObj));
		//$.fn.Dialog({ funcName: "Alert", message: "请选择要删除的岗位！", autoClose: 3 });
		return false;
	}
	
	//alert(exclusionroleCodeStr);
	
	$(window.parent.showDialog("确定要删除选中的排斥组吗？", "execDeleteList('" + exclusionroleCodeStr + "')"));
}

function execDeleteList(exclusionroleCodeStr) {
	//alert(exclusionroleCodeStr);
	document.location.href='DeleteExclusionroleList.action?exclusionroleCodeStr=' +exclusionroleCodeStr;
	
	var msgObj = new Object();
	msgObj.message = "排斥组删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
</script>
</head>

<body>

<div id="iframeContent">
	<!-- <div id="Content">
		<div id="contentBox">
			<div class="content-top">
				<ul id="content-nav">
					<li class="current"><a href="#" class="Home">不相容岗位设置</a></li>
				</ul>
				<div class="content-top-button">
					<a href="#" onclick="addExclusionrole()" class="Add">增加</a>
					<a href="#" onclick="deleteExclusionroleList()" class="Delete">删除</a>
				</div>
			</div> -->
			
			<div id="content-content">
				<div class="contentItem">
					<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="dataList" width="100%">
						<tr class="table-title">
							<th class="table-center"><input type="checkbox" class="checkBoxAll" /></th>
							<th>序号</th>
							<th>岗位</th>
							<th class="table-center">操作</th>
						</tr>
						<c:forEach items="${pageInfo.data}" var="exclusionrolesItem">
						<tr>
							<td class="table-center"><input type="checkbox" name="id" value='${exclusionrolesItem.sn}' /></td>
							<td>${exclusionrolesItem.sn}</td>
							<td><c:if test="${exclusionrolesItem.exclusionrole.length() > 0}">  
	                             <c:out value="${fn:substring(exclusionrolesItem.exclusionrole, 1,exclusionrolesItem.exclusionrole.length())}" />  
	                            </c:if>  
							</td>
							<td class="optionButtons table-center"><a href="#" onclick="editExclusionrole(${exclusionrolesItem.sn})" class="edit">编辑</a><a href="#" onclick="deleteExclusionrole(${exclusionrolesItem.sn})" class="delete">删除</a></td>
						</tr>			
						</c:forEach>
					</table>
					
					<div class="page-box">
					<div class="pagination">
						<a href="http://localhost:8080/VaultMan/GetExclusionroleList.Action?pageNo=1"  title="First Page">&laquo; First</a><a href="http://localhost:8080/VaultMan/GetExclusionroleList.Action?pageNo=<c:if test="${pageInfo.pageNo >1}">${pageInfo.pageNo-1}</c:if><c:if test="${pageInfo.pageNo<2}">1</c:if>" title="Previous Page">&laquo; Previous</a>
						<c:forEach begin="1" end="${pageInfo.pageCount}"  varStatus="status"> 
						<a href="http://localhost:8080/VaultMan/GetExclusionroleList.Action?pageNo=${status.index}" <c:if test="${pageInfo.pageNo == status.index}"> class="number current"</c:if><c:if test="${pageInfo.pageNo != status.index}"> class="number"</c:if>  title="${status.index}">${status.index}</a>
						</c:forEach>
						<a href="http://localhost:8080/VaultMan/GetExclusionroleList.Action?pageNo=<c:if test="${pageInfo.pageNo < pageInfo.pageCount}">${pageInfo.pageNo+1}</c:if><c:if test="${pageInfo.pageNo==pageInfo.pageCount}">${pageInfo.pageCount}</c:if>" title="Next Page">Next &raquo;</a><a href="http://localhost:8080/VaultMan/GetRoleList.Action?pageNo=${pageInfo.pageCount}" title="Last Page">Last &raquo;</a>
					</div> <!-- End .pagination -->
				</div>	
				</div>
				
			</div><!-- End #content-content -->
			
		<!-- </div>
		
		<div style="clear:both"></div>
	</div> -->
</div>

<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none">
	<form action="SaveExclusionrole" focus="id" method="post">
	    <input type="hidden" id="sn" name="sn"/>
		<input type="hidden" id="exclusionrole" name="exclusionrole"/>
		<!-- <div class="UserInfoTitle" style="width:754px;margin:2px 0 0 2px;height:27px;background-color:#3a4043;overflow:hidden;"><h1 class="PopTitle" style="float:left;margin:0 0 0 10px;padding:0;font-size:12px;color:#FFF;line-height:28px;">岗位信息</h1></div> -->
		<div class="UserInfoTable" style="width:740px;margin:0 0 0 10px;overflow:hidden;">
			<div class="ExclusionGroup" id="AllRole" style="float:left;height:298px;width:338px;margin-top:10px;overflow:auto;border:1px #555 solid;">
				<ul></ul>
			</div>
			<div class="moveButton" style="float:left;margin:125px 10px 0 10px;height:50px;width:35px;">
				<a href="javascript:void(0)" id="moveToRight" onclick="moveItemTo('toRight')">&gt;&gt;</a><a href="javascript:void(0)" id="moveToLeft" onclick="moveItemTo('toLeft')">&lt;&lt;</a>
			</div>
			<div class="ExclusionGroup" id="ExclusionRole" style="float:left;height:298px;width:338px;margin-top:10px;overflow:auto;border:1px #555 solid;">
				<ul></ul>
			</div>
		</div>
		<!-- 
		<div class="UserInfoBtn" style="margin:10px 10px 0px 8px;width:740px;">
			<a href="javascript:$.unblockUI();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">取消</a><a href="javascript:$.unblockUI();" onclick="onsubmits();" style="display:block;float:right;margin:10px 10px 10px 0;padding:3px 10px 2px 10px;background-color:#CCC;border:1px #a7b0b7 solid;color:#000;">保存</a>
		</div>
		 -->
	</form>
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
