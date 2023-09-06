<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0301</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>JSTL 태그들의 Exam</p>
	<c:set var="memberId" value="${member.userId }"/>
	<table border="1">
		<tr>
			<td>memberId</td>
			<td>${memberId }</td>
		</tr>
	</table><br/><hr/>
	
	<c:remove var="memberId"/>
	
	<table border="1">
		<tr>
			<td>memberId</td>
			<td>${memberId }</td> <!-- 삭제되어 빈칸으로 보여짐 -->
			<td>${member.userId }</td> <!-- 그대로 잘 나온다 -->
		</tr>
	</table><br/><hr/>
</body>
</html>