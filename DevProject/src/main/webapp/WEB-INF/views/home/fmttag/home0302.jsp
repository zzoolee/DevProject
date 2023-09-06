<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0302</title>
</head>
<body>
	<p>4) type 속성을 time로 지정하여 날짜 포맷팅을 한다.</p>
	<p>now : ${now }</p>
<!-- 	now : Mon Aug 28 16:59:59 KST 2023 -->
	<p>time : <fmt:formatDate value="${now }" type="time"/> </p>
<!-- 	time : 오후 4:59:59 -->
	<p>time default : <fmt:formatDate value="${now }" type="time" timeStyle="default"/> </p>
<!-- 	time default : 오후 4:59:59 -->
	<p>time short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/> </p>
<!-- 	time short : 오후 4:59 -->
	<p>time medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/> </p>
<!-- 	time medium : 오후 4:59:59 -->
	<p>time long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/> </p>
<!-- 	time long : 오후 4시 59분 59초 -->
	<p>time full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/> </p>
<!-- 	time full : 오후 4시 59분 59초 KST -->
</body> 
</html>