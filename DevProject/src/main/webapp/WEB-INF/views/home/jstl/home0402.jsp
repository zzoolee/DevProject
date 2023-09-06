<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0402</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>JSTL 태그들의 Exam</p>
	<!-- 
		EL에서 발생한 에러가 아니라, 자바식에서 발생한 에러 정보기 때문에 var 속성에 담긴 에러정보를 확인할 수 있다.
	 -->
	<%
		String[] hobbyArray = {"Music", "Movie"};
	%>
	<c:catch var="ex">
		<%=hobbyArray[3] %>
	</c:catch>
	<p>
		<c:if test="${ex != null }">
			${ex } <!-- java.lang.ArrayIndexOutOfBoundsException: 3 -->
		</c:if>
	</p>
</body>
</html>