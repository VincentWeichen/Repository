<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  

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
<script type="text/javascript" src="<%=path %>/Scripts/Tree.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/Dialog.js"></script>
<script type="text/javascript" src="<%=path %>/Scripts/WindowDrag.js"></script>
<script type="text/javascript" language="javascript">
var thisPage = "";
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


</script>
</head>
<body>

<div id="iframeContent">

<div id="content-content">
	<table cellpadding="0" cellspacing="0" border="0" id="dataList" class="newdataList" width="100%">
			<tr class="title">
				<th width="5%" class="table-center"><input type="checkbox" class="checkBoxAll" /></th>
				<th width="50%">主题</th>
				<th width="15%">发布日期</th>
				<th width="30%" class="table-center">操作</th>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" name="box" /></td>
				<td><a href="#">2014年金库卫生规定</a></td>
				<td>2014年10月6日 9:27:33</td>
				<td class="optionButtons"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库排班规则变更</a></td>
				<td>2014年10月6日 9:23:56</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">关于加强金库班组管理的规定</a></td>
				<td>2014年10月6日 8:16:12</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库卫生规定</a></td>
				<td>2014年10月6日 9:27:33</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库排班规则变更</a></td>
				<td>2014年10月6日 9:23:56</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">关于加强金库班组管理的规定</a></td>
				<td>2014年10月6日 8:16:12</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库卫生规定</a></td>
				<td>2014年10月6日 9:27:33</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库排班规则变更</a></td>
				<td>2014年10月6日 9:23:56</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">关于加强金库班组管理的规定</a></td>
				<td>2014年10月6日 8:16:12</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			<tr>
				<td class="table-center"><input type="checkbox" /></td>
				<td><a href="#">2014年金库卫生规定</a></td>
				<td>2014年10月6日 9:27:33</td>
				<td class="optionButtons table-center"><a href="#" class="view">查看</a><a href="#" class="save">下载</a><a href="#" class="edit">编辑</a><a href="#" class="delete">删除</a></td>
			</tr>
			
		</table>
	</div>
</div>

</body>
</html>