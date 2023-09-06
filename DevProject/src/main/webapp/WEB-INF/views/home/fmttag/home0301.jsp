<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0301</title>
</head>
<body>
	<p>4) type 속성을 date로 지정하여 날짜 포맷팅을 한다.</p>
	<p>now : ${now }</p>
<!-- 	now : Mon Aug 28 16:38:07 KST 2023 -->
	<p>date default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/> </p>
<!-- 	date default : 2023. 8. 28 -->
	<p>date short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/> </p>
<!-- 	date short : 23. 8. 28 -->
	<p>date medium : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/> </p>
<!-- 	date medium : 2023. 8. 28 -->
	<p>date long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/> </p>
<!-- 	date long : 2023년 8월 28일 (월) -->
	<p>date full : <fmt:formatDate value="${now }" type="date" dateStyle="full"/> </p>
<!-- 	date full : 2023년 8월 28일 월요일 -->

	<!-- 
		dateStyle은 총 5가지로 각 스타일에 따라 출력 형태가 정해져있다.
		정해진 출력 형태에 따라서 parsing할때도 해당 형식으로 구성된 문자열이라면 같은 스타일을 가진 Date 타입 형태의 데이터로 파싱이 가능하다.
	 -->
</body> 
</html>