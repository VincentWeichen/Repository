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
<title>实时监控</title>
<link href="<%=path %>/Styles/Common.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/Styles/BasicStyle.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path %>/Scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Supervise.js"></script>
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
	AddTerminalInterval();

})();
function AddTerminalInterval() {
	timename=setInterval("GetTerminalListJson();",5000);
}
function GetTerminalListJson() {
	var organizationCode = ${organizationCode};
	$.post(common.getProjectRootPath() + "/GetTerminalAllListJson.action", { organizationCode: organizationCode}, function (result) {
		var content = "";
		for(var j =0;j<result.length;j++) {
			if(result[j].status == 5)
			{
				content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog("+result[j].tid+");\"><img class=\"normal\" src=\"<%=path %>/Images/Basic/Icon_storehouse_Green.png\" /><span>金库门号："+result[j].tid+"</span></a></div>"
			}
			if(result[j].status == 4)
			{
				content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog("+result[j].tid+");\"><img class=\"opening\" src=\"<%=path %>/Images/Basic/Icon_storehouse_Yellow.png\" /><span>金库门号："+result[j].tid+"</span></a></div>"
			}
			if(result[j].status == 1)
			{
				content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog("+result[j].tid+");\"><img class=\"error\" src=\"<%=path %>/Images/Basic/Icon_storehouse_Red.png\" /><span>金库门号："+result[j].tid+"</span></a></div>"
			}
			if(result[j].status == 2)
			{
				content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog("+result[j].tid+");\"><img class=\"lose\" src=\"<%=path %>/Images/Basic/Icon_storehouse_Grey.png\" /><span>金库门号："+result[j].tid+"</span></a></div>"
			}
			if(result[j].status == 3)
			{
				content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog("+result[j].tid+");\"><img class=\"opening\" src=\"<%=path %>/Images/Basic/Icon_storehouse.png\" /><span>金库门号："+result[j].tid+"</span></a></div>"
			}
		}
		//for(var i=0;i<5;i++)
			//content +="<div class=\"SuperviseItem\"><a href=\"#\" onclick=\"UserInfoDialog();\"><img class=\"lose\" src=\"<%=path %>/Images/Icon_storehouse_Grey.png\" /><span>金库门号：3</span></a></div>"
		document.getElementById("SuperviseList").innerHTML = content;
		SetSuperviseLayout();
    }, "json");
	return false;
}
function UserInfoDialog(tid)
{
	GetTerminalAndVaultlog(tid);
	return false;
}

function GetTerminalAndVaultlog(tid) {
	RemoveVaultlogInterval();
	$("#logIndex").val("");
	var tableTrObj = $("#Vaultlog tr");
	for(var i=1;i <= tableTrObj.length-1;i++)
	{
		tableTrObj.eq(i).remove();
	}	
	$.post(common.getProjectRootPath() + "/GetTerminalAndVaultlogAll.action", { tid: tid}, function (result) {
		getTerminalCallback(result);
		AddVaultlogInterval(tid);
    }, "json");
	return false;
}
function getTerminalCallback(result) {
	var content = "";
	var trInfo0 = $("#terminalTable tr").eq(0);
	var trInfo1 = $("#terminalTable tr").eq(1);
	var trInfo2 = $("#terminalTable tr").eq(2);
	
	var tds = $("#terminalTable td");
	
	tds.eq(1).text("金库门号："+result[0].tid);
	tds.eq(3).text(result[0].organizationList[0].name);
	tds.eq(5).text(result[0].organizationList[0].address);
	tds.eq(7).text(result[0].organizationList[0].manager);
	tds.eq(9).text(result[0].organizationList[0].telephone);
	tds.eq(11).text(result[0].organizationList[0].cellphone);
	//for(var j =0;j<result[0].vaultlogList.length;j++) {
		//content+= "\r\n"+result[0].vaultlogList[j].entryDate.toString();
	//}
	//$(window.parent.document).find("#Vaultlog").val(content)
	
	var tableObj = $("#Vaultlog");
	for(var j =0;j<result[0].vaultlogList.length;j++) {
		
		var trHtml = "<tr><td style=\"text-align:center\">"+result[0].vaultlogList[j].logIndex+"</td>";
		trHtml += "<td style=\"text-align:center\">"+result[0].vaultlogList[j].entryDate.substr(0,19); +"</td>";
		trHtml += "<td style=\"text-align:center\">"+result[0].vaultlogList[j].inOutIndication+"</td>";
		trHtml += "<td style=\"text-align:center\">"+result[0].vaultlogList[j].verificationSource+"</td>";
		trHtml += "<td style=\"text-align:center\">"+result[0].vaultlogList[j].userId+"</td>";
		trHtml += "<td style=\"text-align:center\">"+result[0].vaultlogList[j].eventAlarmCodeDecode+"</td>";
		trHtml += "</tr>";
		tableObj.append(trHtml);
		if(j+1==result[0].vaultlogList.length)
		{
			$("#logIndex").val(result[0].vaultlogList[j].logIndex);
		}
	}
}
function AddVaultlogInterval(tid) {
	var timename=setInterval("GetVaultlogList("+tid+");",5000);
	$("#timename").val(timename);
}
function RemoveVaultlogInterval() {
	var timename = $("#timename").val();	
	clearTimeout(timename);
}


function GetVaultlogList(tid) {
	var intervalFlag = $("#interval").val();
	if(intervalFlag=='false')
	{
		return false;
	}
	var e=$("#Vaultlog");
	e.scrollTop="100";
	var logIndex = $("#logIndex").val();
	$.post(common.getProjectRootPath() + "/GetVaultlogListNowDateAll.action", { tid: tid,logIndex:logIndex}, function (result) {
		 
		var tableObj = $("#Vaultlog");
		for(var j =0;j<result.length;j++) {
			
			var trHtml = "<tr><td style=\"text-align:center\">"+result[j].logIndex+"</td>";
			trHtml += "<td style=\"text-align:center\">"+result[j].entryDate.substr(0,19); +"</td>";
			trHtml += "<td style=\"text-align:center\">"+result[j].inOutIndication+"</td>";
			trHtml += "<td style=\"text-align:center\">"+result[j].verificationSource+"</td>";
			trHtml += "<td style=\"text-align:center\">"+result[j].userId+"</td>";
			trHtml += "<td style=\"text-align:center\">"+result[j].eventAlarmCodeDecode+"</td>";
			trHtml += "</tr>";
			tableObj.append(trHtml);
			if(j==result.length-1)
			{
				$("#logIndex").val(result[j].logIndex);
			}
		}
		
    }, "json");
	return false;
}

function ChangeInterval(flag) {
	 $("#interval").val(flag);
	 if(flag==false)
		{
			$(window.parent.document).find("#jixu").show();
			$(window.parent.document).find("#zanting").hide();
		}else
	    {
			$(window.parent.document).find("#jixu").hide();
			$(window.parent.document).find("#zanting").show();
	    }
}
</script>
</head>
<body>
<div id="iframeContent">
	<div id="SuperviseList">
		<input type="hidden" id="organizationcode" name="organizationcode" value="${organizationCode}"/>
		<c:forEach items="${terminalList}" var="terminalItem">
			<c:if test="${terminalItem.status == 5}">
				<div class="SuperviseItem">
					<a href="#" onclick="UserInfoDialog(${terminalItem.tid});"><img class="normal" src="<%=path %>/Images/Basic/Icon_storehouse_Green.png" /><span>金库门号：${terminalItem.tid}</span></a>
				</div>
			</c:if>
			<c:if test="${terminalItem.status == 4}">
				<div class="SuperviseItem">
					<a href="#" onclick="UserInfoDialog(${terminalItem.tid});"><img class="opening" src="<%=path %>/Images/Basic/Icon_storehouse_Yellow.png" /><span>金库门号：${terminalItem.tid}</span></a>
				</div>
			</c:if>
			<c:if test="${terminalItem.status == 1}">
				<div class="SuperviseItem">
					<a href="#" onclick="UserInfoDialog(${terminalItem.tid});"><img class="error" src="<%=path %>/Images/Basic/Icon_storehouse_Red.png" /><span>金库门号：${terminalItem.tid}</span></a>
				</div>
			</c:if>
			<c:if test="${terminalItem.status == 2}">
				<div class="SuperviseItem">
					<a href="#" onclick="UserInfoDialog(${terminalItem.tid});"><img class="lose" src="<%=path %>/Images/Basic/Icon_storehouse_Grey.png" /><span>金库门号：${terminalItem.tid}</span></a>
				</div>
			</c:if>
			<c:if test="${terminalItem.status == 3}">
				<div class="SuperviseItem">
					<a href="#" onclick="UserInfoDialog(${terminalItem.tid});"><img class="opening" src="<%=path %>/Images/Basic/Icon_storehouse.png" /><span>金库门号：${terminalItem.tid}</span></a>
				</div>
			</c:if>			
		</c:forEach>
	</div>
	
	<div id="terminalinfo">
		<div class="UserInfoTable">
			<input type="hidden" id="timename" name="timename"/>
			<table cellpadding="0" cellspacing="0" border="0" id="terminalTable" name="terminalTable" class="OrganizationEdit" width="100%">
				<!-- <tr>
					<td colspan="6" class="tit"></td>
				</tr> -->
				<tr>
					<td width="11%">金库门号：</td>
					<td width="22%"></td>
					<td width="12%">管辖单位：</td>
					<td width="22%"></td>
					<td width="11%">金库地址：</td>
					<td width="22%" colspan="3"></td>
				</tr>
				<tr>
					<td width="11%">责任人：</td>
					<td width="22%"></td>
					<td width="12%">联系电话：</td>
					<td width="22%"></td>
					<td width="11%">手机：</td>
					<td width="22%"></td>
				</tr>
				<tr>
				<td width="100%"  colspan="6"><a href="#" onclick="$('#contentIframe')[0].contentWindow.ChangeInterval(false);" id="zanting">暂停监控</a>
				<a href="#" onclick="$('#contentIframe')[0].contentWindow.ChangeInterval(true);" id="jixu" style="display:none">继续监控</a></td></td>
				</tr>
			</table>
			<div class="UserInfoTable" style="margin:10px 0 0 10px;height:225px; overflow-x:hidden;overflow-y:scroll">
				<input type="hidden" id="logIndex" name="logIndex"/>
				<input type="hidden" id="interval" name="interval" value="true"/>
				<table cellpadding="0" cellspacing="0" border="0" id="Vaultlog" name="Vaultlog" class="newdataList" width="100%">
					<tr class="title">
						<th width="8%">序列</th>
						<th width="15%">事件时间</th>
						<th width="15%">出入指示</th>
						<th width="12%">验证方式</th>
						<th width="15%">用户名称</th>
						<th width="35%">事件码解码</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div style="clear:both"></div>
</div>



</body>
</html>
