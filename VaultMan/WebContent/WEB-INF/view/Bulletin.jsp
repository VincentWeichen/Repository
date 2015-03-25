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
	$.post(common.getProjectRootPath() + "/EditRole.action", { roleCode: roleCode}, function (result) {
		//getRoleCallback(result);
		
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
	parentObj.find("#id").val(result.id);
	parentObj.find("#code").val(result.code);
	parentObj.find("#name").val(result.name);
	parentObj.find("#alias").val(result.alias);
	parentObj.find("#type").val(result.type);
	/*
	$("#id").val(result.id);
	$("#code").val(result.code);
	$("#name").val(result.name);
	$("#alias").val(result.alias);
	$("#type").val(result.type);
	*/
}
/*
function onsubmits() {
	//var last = $("form").length - 1;
	//alert($("#name").val());
	
	var nameVal = $(window.parent.document).find("#name").val();
	var idVal = $(window.parent.document).find("#id").val();
	var codeVal = $(window.parent.document).find("#code").val();
	var aliasVal = $(window.parent.document).find("#alias").val();
	var typeVal = $(window.parent.document).find("#type").val();
	
	$("#name").val(nameVal);
	$("#id").val(idVal);
	$("#code").val(codeVal);
	$("#alias").val(aliasVal);
	$("#type").val(typeVal);
	
	//var formObj = $(window.parent.document).find("form:last");
	//alert(formObj.val());
	
	$("form")[0].action=common.getProjectRootPath() + "/SaveRole.action";
	$("form")[0].submit();
	
	var msgObj = new Object();
	msgObj.message = "新增岗位成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
*/
function Submit(type){
	if(type == 1)
	{
		var frm = $("form")[1];
		frm.action=common.getProjectRootPath() + "/UploadFileBullet.action";
		frm.submit();
	}else if(type == 2)
	{
		var frm = $("form")[0];
		frm.action=common.getProjectRootPath() + "/SaveBulletin.action";
		frm.submit();
	}
	
}

function deleteBulletinFile(bulletinFileid){
	$(window.parent.showDialog("确定要删除该附件吗？", "execDelete('" + roleCode + "')"));
	//document.location.href='DeleteRole.action?roleCode=' +roleCode;
}
function addRoleInfo() {
	$("#id").val("");
	$("#code").val("");
	$("#name").val("");
	$("#alias").val("");
	$("#type").val("");
}

function execDelete(roleCodeStr) {
	//alert(roleCodeStr);
	document.location.href='DeleteRoleList.action?roleCodeStr=' +roleCodeStr;
	var msgObj = new Object();
	msgObj.message = "岗位删除成功！";
	msgObj.messageType = "success";
	$(window.parent.showMessage(msgObj));
}
function DownFile(id){
	var frm = $("form")[0];
	frm.action=common.getProjectRootPath() + "/DownFile.action?id="+id;
	frm.submit();
}
</script>
</head>
<body>

<div id="iframeContent">
	<div id="content-content">
	<form action="" method="post" name="form1" focus="id"  target="_self">
		<input type="hidden" id="sn" name="sn"/>
		<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
			<tr class="title">
				<td colspan="3">
                                                   公告名称： <input type="text" name="title" id="title" size="30" maxlength="20"> 
                </td>
			</tr>
			<tr class="title">
				<td colspan="3">
                 <input type="text" name="content" id="content" size="30" maxlength="20"> 
                </td>
			</tr>
			<tr class="title">
				<th width="15%">文件名称</th>
				<th width="15%">大小</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${bulletinfileList}" var="bulletinfileItem">
			<tr>
				<td><a href="${bulletinfileItem.filepath}">${bulletinfileItem.filename}</a><a href="/VaultMan/upload/3cd1d810-4ec0-429c-8eb9-bcee54481121.txt">test.txt</a> </td>
				<td>${bulletinfileItem.filetsize}</td>
				<td>
				<a href="#" onclick="DownFile('${bulletinfileItem.id}');" class="delete">下载</a>
					<a href="#" onclick="deleteBulletinFile('${bulletinfileItem.id}');" class="delete">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		</form>
	<form action="" method="post" enctype="multipart/form-data" name="form2" onSubmit="return checkF(this)" target="_self">
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  
    <tr>
      <td colspan="2" class="WarningMsg"></td>
    </tr>
    <tr> 
      <td colspan="2" bgcolor="#FFFFFF"> 
		<c:forEach items="${appendImgs}" var="img">
			<c:if test="${img ne ''}">
        <DIV style="FLOAT: left;width:115px; TEXT-ALIGN: center;"> 
          <DIV class="impc"><img src="${root}/upload/${img}" width="75" height="75"></div>
          <DIV class="impc"><a onClick="return del()" target="_self" href="upload_append_img.jsp?method=delImgByName&name=${img}"><font color="#FF0000">删除</font></a></div>
        </div>
       </c:if>
    </c:forEach>
    	</td>
    </tr>
    <tr> 
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr> 
      <td width="59%" style="padding-left:5px;padding-bottom:10px;"><input type="file" name="append_img" > 
        &nbsp; &nbsp; <input type="button" class="short-button" onclick="Submit(1)" value=" 上 传 ">
        <input type="button" class="short-button" onclick="Submit(2)" value="保存"></td>
    </tr>
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