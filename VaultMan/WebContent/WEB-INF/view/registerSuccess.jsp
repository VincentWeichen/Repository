<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>Welcome</title>  
</head>  
<body>  
<!-- 
用户添加成功！<br><br>
编     号:${code}<br>
用户名:${string}<br>
密     码:${password} (不要告诉我！)<br>
-->
<table>
<c:forEach items="${UserList}" var="UserItem">
	<tr>
		<td>
			<c:out value="${UserItem.name}"></c:out>
		</td>
	</tr>
</c:forEach>
</table>
用户数量：${UserList.size()}

</body>
</html>