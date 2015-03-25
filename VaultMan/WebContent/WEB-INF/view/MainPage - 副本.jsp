<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  

<%
	String path = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String loginFlg = (String)session.getAttribute("loginFlg");
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
<script type="text/javascript" src="<%=path %>/Scripts/Tree.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/Scripts/Calendar/WdatePicker.js"></script>
<script type="text/javascript" language="javascript">

var loginFlg = <%=loginFlg %>;
/*
if (loginFlg != "1") {
	alert("跳转到登录页面")
}
else {
	alert("OK");
}
*/


var thisPage= "";
var lastUrl = "";
var organizationFullPath = "";
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

var treeNodes = [];
$.post(common.getProjectRootPath() + "/getOrganizationTrees.action", {  }, function (result) {
	treeNodes = result;
	$.fn.Tree({ data: treeNodes });
}, "json");

function onClick(treeItem) {
	var path = leftNavClick(treeItem.find("a:first")[0]);
	//alert(path)
	treeItem.find("a:first").attr("target", "contentIframe");
	treeItem.find("a:first").attr("href", path);
	//$("#contentIframe").attr("src", path);
	//alert("load");
	//$("#contentIframe")[0].contentWindow.location.reload(true);
}

$(document).ready(function(){
	if (thisPage == "") {
		$("#Content-Tree").css("display", "none");
		$("#pageTitle").html("公告");
		$("#contentIframe").attr("src", "/VaultMan/Index");
	}
	
	$('iframe#myId').load(function()
    {  
        callback(this);  
    });  
	//$.fn.Tree({ data: treeNodes });
});

/*
function aa(obj) {
	var thisObj = $(obj);
	thisPage = obj.href;
	
	//alert(obj.href);
	//obj.href = obj.href;
}
*/

/* 点击头部导航的执行方法 */
function topNavClick(obj) {
	/*
	setHasTree();
	if ($("#Content-Tree").css("display") == "none")
		$("#Content-Tree").css("display", "block");
	*/
	
	$(".content-top-button a.Add, .content-top-button a.Delete, .content-top-button a.Edit, .content-top-button a.Save").unbind("click");
	
	var thisObj = $(obj);
	var objId = thisObj.attr("id");
	thisPage = objId;
	
	var currentNode = $("#treeDemo a.TreeCurSelected")[0];
	
	//lastUrl = thisPage;
	$("#pageTitle").html(thisObj.html());
	var path;
	//alert(objId);
	switch(objId) {
		case "/VaultMan/GetUserList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.addUserInfo();
				//$.fn.Dialog({ funcName: "Users", titleText: "新增用户", message: $("#UserInfo", document.frames("contentIframe").document).html(), confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
				$.fn.Dialog({ funcName: "Users", titleText: "新增用户 - " + organizationFullPath, message: $("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML, confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
				$("#organization").val(organizationFullPath);
			});
			$(".content-top-button a.Delete").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.deleteUserList();
			});
			break;
		case "/VaultMan/GetFingerprintUserList.action":
			path = leftNavClick(currentNode);
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.synchronization();
				/*
				$.fn.Dialog({funcName: "Loading", titleText: "同步指令已发送成功，正在同步，请稍候..."});
				$("#contentIframe").attr("src", path);
				$("#contentIframe").ready(function(){
					var msgObj = new Object();
					msgObj.message = "同步完成！";
					msgObj.messageType = "success";
					showMessage(msgObj);
				});
				*/
			});
			$(".content-top-button a.Edit").bind("click", function(i, obj) {
				$.fn.Dialog({ funcName: "Users", titleText: "新增用户 - " + organizationFullPath, message: $("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML, confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
				bindDataListMouseArgs($("#fingerpringList"));
			});
			break;
		case "/VaultMan/Organization":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				var curOrganizationId = $("ul#treeDemo").find("li:first a[class='TreeCurSelected']").parent().attr("id");
				$("#contentIframe")[0].contentWindow.AddFunc(curOrganizationId.replace("tree", ""));
			});
			$(".content-top-button a.Delete").bind("click", function(i, obj) {
				var curOrganizationId = $("ul#treeDemo").find("li:first a[class='TreeCurSelected']").parent().attr("id");
				//alert(curOrganizationId);
				var thisLevels = $("#" + curOrganizationId).find("li");
				if (thisLevels.length > 0) {
					//$(window.parent.showMessage("该机构下还有子机构，无法删除！"));
					$.fn.Dialog({ funcName: "Alert", message: "该机构下还有子机构，无法删除！", autoClose: 3 });
					return false;
				}
				$("#contentIframe")[0].contentWindow.DelFunc(curOrganizationId.replace("tree", ""));
			});
			break;
		case "/VaultMan/GetRoleList":
			thisPage = "";
			path = objId;
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				//alert("a")
				//alert($("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML);
				//alert("b")
				//alert($("#UserInfo", document.frames("contentIframe").document).html());
				$("#contentIframe")[0].contentWindow.addRoleInfo();
				$.fn.Dialog({ funcName: "Users", titleText: "新增岗位", message: $("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML, confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
			});
			$(".content-top-button a.Delete").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.deleteRoleList();
			});
			break;
		case "/VaultMan/GetExclusionroleList":
			thisPage = "";
			path = objId;
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.AddFunc();
			});
			$(".content-top-button a.Delete").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.deleteExclusionroleList();
			});
			break;
		case "/VaultMan/GetGroupList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.AddFunc();
				//$.fn.Dialog({ funcName: "Users", titleText: "新增用户", message: $("#UserInfo", document.frames("contentIframe").document).html(), confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
				$.fn.Dialog({ funcName: "Users", titleText: "新增操作组", message: $("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML, confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
			});
			
			$(".content-top-button a.Delete").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.deleteGroupList();
			});
			
			$(".content-top-button a.Edit").bind("click", function(i, obj) {
				$.fn.Dialog({ funcName: "Users", titleText: "编辑操作组 - " + organizationFullPath, message: $("#contentIframe")[0].contentWindow.document.getElementById("UserInfo").innerHTML, confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
				$("#contentIframe")[0].contentWindow.editGroup();
			});
			
			break;
		case "/VaultMan/GetConfigGroupList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Save").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.dataSave();
			});
			break;
		case "/VaultMan/GetTimeIntervalList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Save").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.SaveTimeInterval();
			});
			$(".content-top-button a.Add").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.AddTimeIntervalDataTr();
			});
			break;
		case "/VaultMan/GetControllTimeList.action":
		case "/VaultMan/GetControllTimeByYearList.action":
		case "/VaultMan/GetControllTimeHolidayList.action":
			path = leftNavClick(null);	//获取左侧导航的机构代码
			break;
		case "/VaultMan/GetGroupauthorizeList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			$(".content-top-button a.Save").bind("click", function(i, obj) {
				$("#contentIframe")[0].contentWindow.saveGroupauthorize();
			});
			break;
		case "/VaultMan/GetTerminalList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			break;
		case "/VaultMan/GetTerminalAllList.action":
			path = leftNavClick(currentNode);	//获取左侧导航的机构代码
			break;
	}
	
	//alert(path);
	
	thisObj.attr("href", path);
	//$("#contentIframe").attr("src", path);
}

//var fullScreenList = "Index,GetRoleList";

var fullScreenList = new Array("Index", "GetRoleList", "GetExclusionroleList");

function validateTree(obj) {
	var flg = false;
	
	$.each(fullScreenList, function(i, item) {
		if (obj.id.indexOf(item) != -1) {
			setFullScreen();
			flg = true;
			return false;
		}
	});
	
	if (!flg)
		setHasTree();
	/*
	if (fullScreenList.indexOf(obj.id) != -1)
		setFullScreen();
	else
		setHasTree();
	*/
}

function leftNavClick(obj) {
	$(".content-top-button a.Add").html("添加");
	var contentNavOptions = $(".content-top-button a").show();
	$(".content-top-button a.Edit").hide();
	$(".content-top-button a.Save").hide();
	var thisObj;
	if (obj == null) {
		$("#treeDemo a.TreeCurSelected").removeClass("TreeCurSelected");
		thisObj = $("#treeDemo li:first a:first");
		thisObj.addClass("TreeCurSelected");
	}
	else
		thisObj = $(obj);
	
	//alert(thisObj)
	
	var liObj = thisObj.parent();
	
	var organizationCode = liObj.attr("id").replace("tree", "");
	var tId = liObj.attr("tid");
	var typeId = liObj.attr("typeId");
	
	var path = "";
	var currentLi = liObj;
	var liObjParent;
	//alert(liObjParent);
	do {
		liObjParent = currentLi.closest("ul");
		//alert(currentLi.html());
	 	path == "" ? path = currentLi.find("a:first").html() : path = currentLi.find("a:first").html() + " / " + path;
		currentLi = liObjParent.closest("li");
		//alert(liObjParent.prev().find("a:first")[0])
		//path += liObjParent.prev().find("a:first").html();
	}
	while(liObjParent.attr("id") != "treeDemo")
	
	organizationFullPath = path;
	//alert(path);
	var titleObj = $("#pageTitle");
	var titleTxt = titleObj.html();
	var spacePos = titleTxt.indexOf(" ");
	if (spacePos != -1)
		titleTxt = titleTxt.substring(0, spacePos);
	$("#pageTitle").html(titleTxt + " - " + path);
	
	switch(thisPage) {
		case "/VaultMan/GetGroupList.action":
		case "":
			contentNavOptions = $(".content-top-button a");
			if (typeId == 1) {
				contentNavOptions.hide();
			}
			else {
				contentNavOptions.show();
				$(".content-top-button a.Save").hide();
			}
			break;
		case "/VaultMan/GetFingerprintUserList.action":
			//alert("abc");
			var add = $(".content-top-button a.Add");
			var del = $(".content-top-button a.Delete");
			if (typeId == 1) {
				add.hide();
			}
			else {
				//alert(add.html());
				add.html("读取指纹机用户");
				//alert(add.html());
				add.show();
				$(".content-top-button a.Edit").show();
			}
			del.hide();
			break;
		case "/VaultMan/GetGroupauthorizeList.action":
		case "/VaultMan/GetConfigGroupList.action":
			var contentNavOptions = $(".content-top-button a");
			if (typeId == 1) {
				contentNavOptions.hide();
			}
			else {
				contentNavOptions.hide();
				var save = $(".content-top-button a.Save");
				//save.html("保存");
				save.show();
			}
			break;
		case "/VaultMan/GetTimeIntervalList.action":
			var contentNavOptions = $(".content-top-button a");
			if (typeId == 1) {
				contentNavOptions.hide();
			}
			else {
				contentNavOptions.hide();
				$(".content-top-button a.Save, .content-top-button a.Add").show();
			}
			break;
		case "/VaultMan/GetBulletinList.action":
			var contentNavOptions = $(".content-top-button a");
			if (typeId == 1) {
				contentNavOptions.hide();
			}
			else {
				contentNavOptions.hide();
				$(".content-top-button a.Save, .content-top-button a.Add").show();
			}
			break;
	}
	
	var path = thisPage + "?organizationCode=" + organizationCode + "&tid=" + tId;
	
	//alert(path);
	
	thisObj.attr("href", path);
	
	return path;
}

function reloadTree(flg, optionType) {
	if (optionType == "save")
		$.fn.Dialog({ funcName: "Alert", message: "机构保存成功！", messageType: "success", autoClose: 3 });
	else if (optionType == "delete") {
		$.fn.Dialog({ funcName: "Alert", message: "机构删除成功！", messageType: "success", autoClose: 3 });
	}
		
	//获取当前选中项，以便重新加载后设置
	var treeObj = $("ul#treeDemo");
	var item = treeObj.find("li:first a[class='TreeCurSelected']");
	var itemPId = item.parent().attr("id");
	//重新加载IFrame，解决缓存问题
	if (flg != "" && optionType == "save")
		$("#contentIframe")[0].contentWindow.location.reload(true);
	//重新加载Tree
	treeObj.html("");
	$.post(common.getProjectRootPath() + "/getOrganizationTrees.action", {  }, function (result) {
		treeNodes = result;
		$.fn.Tree({ data: treeNodes });
		
		//设置为默认选中状态
		if (optionType == "save")
			$("li#" + itemPId + " a:first").addClass("TreeCurSelected");
		else {
			var item = treeObj.find("li:first a:first");
			item.addClass("TreeCurSelected");
			$("#contentIframe").attr("src", item.attr("href"));
		}
		
	}, "json");
}

function showDialog(msgString, execFunc) {
	$.fn.Dialog({ funcName: "Confirm", message: msgString, confirm: { func: "$('#contentIframe')[0].contentWindow." + execFunc, isDisplay: true }, close: { isDisplay: false } });
}

function showMessage(msgObj) {
	var msgString = "";
	var autoCloseVal = 3;
	var messageTypeVal = "alert";
	
	if (typeof(msgObj) == "object") {
		if (msgObj.message)
			msgString = msgObj.message;
		if (msgObj.autoClose)
			autoClose = msgObj.autoClose;
		if (msgObj.messageType)
			messageTypeVal = msgObj.messageType;
	}
	else {
		msgString = msgObj;
	}
	
	$.fn.Dialog({ funcName: "Alert", message: msgString, messageType : messageTypeVal, autoClose: autoCloseVal });
}

function showUDefinedDialog(dialogObj) {
	$.fn.Dialog(dialogObj);
	bindDataListMouseArgs($("table.newdataList"))
	//$.fn.Dialog({ funcName: "Users", titleText: "新增岗位", message: $("#UserInfo", document.frames("contentIframe").document).html(), confirm: { func: "$('#contentIframe')[0].contentWindow.onsubmits()", isDisplay: true }, close: { isDisplay: false } });
}

function chooseRoles() {
	//alert("bbb")
	var roleListObj = $("#DialogRoleList tr[class!='Info'][class!='table-title'][class!='Info-tr']");
	var rolenames = "";
	var rolecodes = "";
	roleListObj.each(function(i, item) {
		//alert(i)
		var itemObj = $(item);
		var input = itemObj.find("input:first");
		if (input.attr("checked")) {
			if (rolenames == "") {
				rolenames += itemObj.find("td").eq(1).html();
				rolecodes += input.val();
			}
			else {
				rolenames += "," + itemObj.find("td").eq(1).html();
				rolecodes += "," + input.val();
			}
		}
	});
	$("#rolenames").val(rolenames);
	$("#rolecodes").val(rolecodes);
	//alert("a1");
	$.fn.Dialog({ funcName: "Close" });
	//alert("a2")
}

function bindArgs()
{
	var lists = $(".ExclusionGroup li a");
	//alert(lists.length)
	lists.each(function(i, obj) {
		var objItem = $(obj);
		objItem.bind("click", function() {
			$(this).toggleClass("checked");
			//$(this).attr("class") == "checked" ? $(this).removeClass("checked") : $(this).addClass("checked");
		}).bind("dblclick", function() {
			var flg;
			objItem.closest("div.ExclusionGroup").attr("id") == "AllRole" ? flg = "toRight" : flg = "toLeft";
			moveItemTo(flg);
		})
	});
}

function moveItemTo(flg)
{
	var currentObj = $("#AllRole");
	var target = $()
	if (flg == "toRight")
	{
		currentObj = $("#AllRole ul");
		target = $("#ExclusionRole ul");
	}
	else
	{
		currentObj = $("#ExclusionRole ul");
		target = $("#AllRole ul");
	}
	
	currentObj.find("li a[class='checked']").each(function(i, obj) {
		$(obj).removeClass("checked");
		target.append("<li>" + $(obj).parent().html() + "<\/li>");
		target.find("li a:last").bind("click", function() {
			$(this).toggleClass("checked");
		}).bind("dblclick", function() {
			var flg;
			$(this).closest("div.ExclusionGroup").attr("id") == "AllRole" ? flg = "toRight" : flg = "toLeft";
			moveItemTo(flg);
		})
		$(obj).parent().remove();
	});
}

function IsLogin(Obj) {
	var ckbDisabled = true;
	ckbDisabled = !Obj.checked;
	$("input#password").attr("disabled", ckbDisabled);
}

function setMenuRight(result) {
	var menus = $("#Header-Navigation-Bar li a");
	alert(lis.length);
	alert(result.length);
	$.each(result, function(i, item) {
		//var thisObj = $(item);
		//alert(item.functioncode);
		menus.each(function(j, menuItem) {
			if (menuItem.attr("right") == item.functioncode) {
				alert("a")
				return false;
			}
		});
		/*
		var menuObj = menus.find("[right='" + item.functioncode + "']");
		alert(menuObj[0])
		if (item.funread == "1") {
			menuObj.show();
		}
		else {
			menuObj.hide();
		}
		*/
	});
}
</script>
</head>
<body>

<div id="Header">
	<div id="Header-Top">
		<div id="Header-Logo"> 
			<a href="Index" id="Index" target="contentIframe"><img src="Images/Logo.png" alt="System Admin" /></a> 
		</div>
		<div id="Header-Meta">
			<ul>
				<li class="Header-UserName">${userName}</li>
				<li class="Header-Setting"><a href="#" class="Setting">修改密码</a></li>
				<li><a href="Login.html" class="Logout">退出</a></li>
			</ul>
			<p>Last Login: 6 October 2014</p>
		</div>
	</div>
	<div id="Header-Navigation-Bar">
		<ul class="Header-Navigation"><!--  style="display:none" -->
			<li>
				<span><a href="#" right="01">基础信息维护</a></span>
				<ul>
					<li>
						<a href="#" right="01_11">机构设置维护</a>
						<ul>
							<li><a href="<%=path %>/Organization" right="01_11_111" onclick="topNavClick(this)" id="<%=path %>/Organization" target="contentIframe">机构基础信息维护</a></li>
							<li><a href="#" right="01_112">机构归属信息维护</a></li>
						</ul>
					</li>
					<li>
						<a href="#" right="01_12">岗位设置维护</a>
						<ul>
							<li><a href="<%=path %>/GetRoleList" right="01_12_121" onclick="topNavClick(this)" id="<%=path %>/GetRoleList" target="contentIframe">岗位基础信息维护</a></li>
							<!--<li><a href="Views/RoleRight.html">岗位权限维护</a></li>-->
							<li><a href="<%=path %>/GetExclusionroleList" right="01_12_122" onclick="topNavClick(this)" id="<%=path %>/GetExclusionroleList" target="contentIframe">不相容岗位设置维护</a></li>
						</ul>
					</li>
					<li><a href="<%=path %>/GetUserList.action" right="01_13" onclick="topNavClick(this)" id="<%=path %>/GetUserList.action" target="contentIframe">人员设置维护</a></li>
					<li>
						<a href="#" right="01_14">工作流维护</a>
						<ul>
							<li><a href="#" right="01_14_141">管制时间审批流程维护</a></li>
							<li><a href="#" right="01_14_142">排班管理审批流程维护</a></li>
						</ul>
					</li>
					<li><a href="#" right="01_15">字典维护</a></li>
					<li><a href="<%=path %>/GetBulletinList" right="01_16" onclick="topNavClick(this)" id="<%=path %>/GetBulletinList" target="contentIframe">公告维护</a></li>
					<!-- <li><a href="#" right="01_16">公告维护</a></li> -->
				</ul>
			</li>
			<li>
				<span><a href="#" right="02">系统查询</a></span>
				<ul>
					<li><a href="#" right="02_21">金库事件查询</a></li>
					<li>
						<a href="#" right="02_22">管理操作查询</a>
						<ul>
							<li><a href="#" right="02_22_221">软件日志查询</a></li>
							<li><a href="#" right="02_22_222">指纹机日志查询</a></li>
						</ul>
					</li>
					<li><a href="#" right="02_23">信息查询</a></li>
					<li>
						<a href="#" right="02_24">设置查询</a>
						<ul>
							<li><a href="#" right="02_24_241">机构设置查询</a></li>
							<li><a href="#" right="02_24_242">岗位设置查询</a></li>
							<li><a href="#" right="02_24_243">审批流程查询</a></li>
							<li>
								<a href="#" right="02_24_244">管制时间查询</a>
								<ul>
									<li><a href="#" right="02_24_244_2441">当前执行查询</a></li>
									<li><a href="#" right="02_24_244_2442">时间段查询</a></li>
								</ul>
							</li>
							<li>
								<a href="#" right="02_24_245">排班查询</a>
								<ul>
									<li><a href="#" right="02_24_245_2451">当前执行查询</a></li>
									<li><a href="#" right="02_24_245_2452">时间段查询</a></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>
				<span><a href="#" right="03">实时监控</a></span>
				<ul>
					<li><a href="<%=path %>/GetTerminalList.action" right="03_31" onclick="topNavClick(this)" id="<%=path %>/GetTerminalList.action" target="contentIframe">全屏图标</a></li>
					<li><a href="<%=path %>/GetTerminalAllList.action" right="03_32" onclick="topNavClick(this)" id="<%=path %>/GetTerminalAllList.action" target="contentIframe">全屏列表</a></li>
					<li><a href="#" right="03_33">报警短信</a></li>
				</ul>
			</li>
			<li>
				<span><a href="#" right="04">管制时间</a></span>
				<ul>
					<li>
						<a href="#" right="04_41">管制时间设置</a>
						<ul>
							<li><a href="<%=path %>/GetControllTimeList.action" right="04_41_411" onclick="topNavClick(this)" id="<%=path %>/GetControllTimeList.action" target="contentIframe">周循环</a></li>
							<li><a href="<%=path %>/GetControllTimeByYearList.action" right="04_41_412" onclick="topNavClick(this)" id="<%=path %>/GetControllTimeByYearList.action" target="contentIframe">年循环</a></li>
							<li><a href="<%=path %>/GetControllTimeHolidayList.action" right="04_41_413"  onclick="topNavClick(this)" id="<%=path %>/GetControllTimeHolidayList.action" target="contentIframe">假期和调班</a></li>
							<li><a href="#" right="04_41_414">启用时间</a></li>
						</ul>
					</li>
					<li><a href="#" right="04_42">首末操作分布图</a></li>
					<li><a href="#" right="04_43">管制时间查询</a></li>
				</ul>
			</li>
			<li>
				<span><a href="#" right="05">排班授权</a></span>
				<ul>
					<li><a href="<%=path %>/GetFingerprintUserList.action" right="05_51" onclick="topNavClick(this)" id="<%=path %>/GetFingerprintUserList.action" target="contentIframe">指纹机用户匹配</a></li>
					<li><a href="<%=path %>/GetGroupList.action" right="05_52" onclick="topNavClick(this)" id="<%=path %>/GetGroupList.action" target="contentIframe">分组</a></li>
					<li>
						<a href="#" right="05_53">授权</a>
						<ul>
							<!--<li><a href="">组授权</a></li>-->
							<li><a href="<%=path %>/GetGroupauthorizeList.action" right="05_53_531" onclick="topNavClick(this)" id="<%=path %>/GetGroupauthorizeList.action" target="contentIframe">组授权</a></li>
							<li><a href="<%=path %>/GetConfigGroupList.action" right="05_53_532" onclick="topNavClick(this)" id="<%=path %>/GetConfigGroupList.action" target="contentIframe">倒班授权</a></li>
							<li><a href="<%=path %>/GetTimeIntervalList.action" right="05_53_533" onclick="topNavClick(this)" id="<%=path %>/GetTimeIntervalList.action" target="contentIframe">时段授权</a></li>
						</ul>
					</li>
					<li><a href="#" right="05_54">排班查询</a></li>
				</ul>
			</li>
			<li>
				<a href="#" right="06">帮助</a>
			</li>
		</ul>
		<div class="changeColor"><a href="#" class="base"></a><a href="#" class="Green"></a></div>
	</div>
</div>

<div id="Content">
	<div id="Content-Tree" class="manager">
		<div class="Tree-Title">
			<h1>机构信息</h1>
		</div>
		<ul id="treeDemo" class="Tree">
			
		</ul>
	</div>
	
	<div id="contentBox">
		<div class="content-top">
			<ul id="content-nav">
				<li class="current"><a href="#" id="pageTitle" class="Home">机构详细信息</a></li>
			</ul>
			
			<div class="content-top-button">
				<a href="#" class="Add">添加</a>
				<a href="#" class="Delete">删除</a>
				<a href="#" class="Edit" style="display:none">编辑</a>
				<a href="#" class="Save" style="display:none">保存</a>
				
			</div>
			
		</div>
		
		<div id="content-content">
			<iframe id="contentIframe" name="contentIframe" src="" frameborder="0"></iframe>
		</div>
		
	</div>
	
</div>
<div id="Footer">
	Copyright &copy; 2014-2015 System Admin. All Rights Reserved.
</div>

</body>
</html>