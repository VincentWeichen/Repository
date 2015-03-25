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
<script type="text/javascript" src="<%=path %>/Scripts/Groupauthorize.js"></script>
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

function AddFunc() {
	editGroup();
}

function saveGroupauthorize(){
	var groupId = "";
	var checkedList = $("#dataList tr[class!='title'] input[checked='checked']");
	if (checkedList.length > 0) {
		checkedList.each(function(i, obj) {
			groupId += "groupId=" + $(obj).val();
		});
	}
		$("form")[0].action=common.getProjectRootPath() + "/SaveGroupauthorize.action?"+groupId;
		$("form")[0].submit();
		var msgObj = new Object();
		msgObj.message = "保存成功！";
		msgObj.messageType = "success";
		$(window.parent.showMessage(msgObj));
	
}
function selectChange() {
	var ids = document.getElementsByName("id");
	for(var i =0;i<ids.length;i++) {
	  if(ids[i].type == 'checkbox' && ids[i].checked && ids[i].name != 'allbox') {
		  groupId = 'groupId='+ids[i].value;
	   }
    }
}

function FindGroupList(){
	var corganizationCode = ${organizationCode};
	document.location.href='GetGroupList.action?organizationCode='+corganizationCode;
}

</script>
</head>

<body>

<div id="iframeContent">
		
		<div id="content-content">
		<form action="SaveGroupauthorize.action" focus="id" method="post">
		
		<input type="hidden" id="tid" name="tid" value="${tid}"/>
		<input type="hidden" id="organizationcode" name="organizationcode" value="${organizationCode}"/>
			<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
				<tr class="title">
					<th width="5%">授权</th>
					<th width="25%">组名</th>
					<th width="70%">班组成员</th>
				</tr>
				<c:forEach items="${groupList}" var="groupItem" varStatus="status">
				<tr>
					<td><input type="checkbox" <c:if test="${groupItem.groupauthorize == 'true'}">checked="checked"</c:if> value='${groupItem.id}' id="${status.index+1}_id" name="${status.index+1}_id"/></td>
					<td>${groupItem.name}</td>
					<td>${groupItem.usernames}</td>
				</tr>
				</c:forEach>
			</table>
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
</div>
</body>
</html>
