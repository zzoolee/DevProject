<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0901</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>JSTL 태그들의 Exam</p>
	<c:redirect url="http://localhost/board/list"/>
	<h2>redirect 이후의 코드는 실행되지 않습니다.</h2>
</body>
</html>