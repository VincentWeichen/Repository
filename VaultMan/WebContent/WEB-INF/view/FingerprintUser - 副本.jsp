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
<title>机构与指纹机关系维护</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/FingerprintUser.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
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

function editFingerprint(tUserName, tUserId, usercodesys) {
	var confirmObj = new Object();
	confirmObj.func = "$('#contentIframe')[0].contentWindow.onsubmits('" + usercodesys + "')";
	confirmObj.isDisplay = true;
	
	var closeObj = new Object();
	closeObj.isDisplay = false;
	
	var dialogObj = new Object();
	dialogObj.funcName = "Users";
	dialogObj.titleText = "为 " + tUserName + " 指定系统用户";
	dialogObj.message = $("#UserInfo").html();
	dialogObj.confirm = confirmObj;
	dialogObj.close = closeObj;
	
	$(window.parent.showUDefinedDialog(dialogObj));
	
	editCallback(tUserId, usercodesys);
}

function editCallback(tUserId, usercodesys) {
	
	$(window.parent.document).find("#tusercode").val(tUserId);
	var trObj = $(window.parent.document).find("#fingerpringList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	trObj.each(function(i, tr) {
		var trObj = $(tr);
		var checkboxObj = trObj.find("td:first input[type='checkbox']");
		if (checkboxObj.val() == usercodesys) {
			checkboxObj.attr("checked", true);
		}
	});
}

function onsubmits(usercodesys) {
	
	
	var trObjList = $(window.parent.document).find("#fingerpringList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	var userNameString = "";
	var isRepeat = false;
	
	trObjList.find("input[name='usercodesys']").each(function(i, input) {
		var val = $(input).val();
		if (userNameString.indexOf("," + val + ",") != -1 && val != "") {
			isRepeat = true;
			//userNameString = val;
			//alert(userNameString + "：" + val)
			return false;
		}
		userNameString += "," + val + ",";
	});
	
	if (isRepeat) {
		$(window.parent.showMessage("系统用户不能重复指定！"));
		return false;
	}
	
	var thisTrObjList = $("#fingerpringList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	
	trObjList.each(function(i, tr) {
		var trObj = $(tr);
		var tdObjList = trObj.find("td");
		
		var thisTdObjList = thisTrObjList.eq(i).find("td");
		
		tdObjList.each(function(j, td) {
			if (j == 4) {
				thisTdObjList.eq(j).find("select:first").val($(td).find("select:first").val());
			}
			//else {
			thisTdObjList.eq(j).find("input:first").val($(td).find("input:first").val());
			//}
		});
	})
	
	$("form")[0].action=common.getProjectRootPath() + "/SaveFingerprint.action";
	$("form")[0].submit();
	
	var msgObj = new Object();
	msgObj.message = "保存成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}

function ChangeName(obj) {
	$(obj).next().val($(obj).find("option:selected").text().replace("请选择", ""));
	$(obj).parent().next().find("input").val(obj.value)
}

function synchronization() {
	var tid = ${tid};
	//alert(tid);
	$.post(common.getProjectRootPath() + "/Synchronization.action", { tid: tid}, function (result) {
		//alert("ok");
		var msgObj = new Object();
		msgObj.message = "同步指令已发送成功，正在同步，请稍候...";
		msgObj.messageType = "success";
		$(window.parent.showMessage(msgObj));
    }, "json");
	return false;
}
</script>
</head>
<body>

<div id="iframeContent">
	
	<div id="content-content">
		<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
			<tr class="title">
				<th>人员编号</th>
				<th>代号</th>
				<th>类型</th>
				<th>密钥数</th>
				<th>姓名</th>
				<!-- <th class="table-center">操作</th> -->
			</tr>
			<c:forEach items="${fingerprintuserList}" var="Item">
				<tr>
					<td>${Item.tusercode}<input type="hidden" value="${Item.tusercode}" /></td>
					<td>${Item.tusername}</td>
					<td>${Item.userType}</td>
					<td>${Item.fingerAmount}</td>
					<td>${Item.usernamesys}<input type="hidden" value="${Item.usercodesys}" class="usercodesys" /></td>
					<!-- 
					<td class="optionButtons table-center">
						<a href="#" onclick="editFingerprint('${Item.tusername}', '${Item.tusercode}', '${Item.usercodesys}');"  class="edit">编辑</a>
					</td>
					 -->
				</tr>
			</c:forEach>
		</table>
		
	</div>
	
	<div id="UserInfo" style="float:left;width:758px;border:1px #FFF solid;padding:0;display:none">
		<form action="SaveFingerprint.action" focus="id" method="post" style="margin:0;">
			<div class="UserInfoTable" style="float:left;width:740px;margin:0 10px 0 10px;">
				<table cellpadding="0" cellspacing="0" border="0" id="fingerpringList" class="newdataList" width="100%">
					<tr class="ttitle">
						<th>指纹机人员编号</th>
						<th>指纹机人员代号</th>
						<th>类型</th>
						<th>密钥数</th>
						<th>系统人员姓名</th>
						<th>系统人员编号</th>
						<!-- <th class="table-center">操作</th> -->
					</tr>
					<c:forEach items="${fingerprintuserList}" var="Item">
						<tr>
							<td>${Item.tusercode}<input type="hidden" name="tusercode" value="${Item.tusercode}" /></td>
							<td>${Item.tusername}<input type="hidden" name="tusername" value="${Item.tusername}" /></td>
							<td>${Item.type}<input type="hidden" name="type" value="${Item.type}" /></td>
							<td>${Item.encrykeyamount}<input type="hidden" name="encrykeyamount" value="${Item.encrykeyamount}" /></td>
							<td>
								<select onchange="$('#contentIframe')[0].contentWindow.ChangeName(this)">
									<option value="">请选择</option>
									<c:forEach items="${userInfomation.userList}" var="userItem">
										<option value="${userItem.code}" <c:if test="${userItem.code == Item.usercodesys}">selected</c:if>>${userItem.name}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="usernamesys" value="${Item.usernamesys}" />
							</td>
							<td><input type="text" value="${Item.usercodesys}" name="usercodesys" readonly="readonly" /></td>
							<!-- 
							<td class="optionButtons table-center">
								<a href="#" onclick="editFingerprint('${Item.tusername}', '${Item.tusercode}', '${Item.usercodesys}');"  class="edit">编辑</a>
							</td>
							 -->
						</tr>
					</c:forEach>
				</table>
			</div>
		
			<!-- 
			<input type="hidden" id="tusercode" name="tusercode" />
			<input type="hidden" id="usercodesys" name="usercodesys" />
			<input type="hidden" id="usernamesys" name="usernamesys" />
			<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
			<input type="hidden" id="tid" name="tid" value="${tid}"/>
			 -->
			<input type="hidden" id="organizationCode" name="organizationCode" value="${organizationCode}"/>
			<input type="hidden" id="tid" name="tid" value="${tid}"/>
		</form>
		<div style="clear:both"></div>
	</div>
	
</div>

</body>
</html>