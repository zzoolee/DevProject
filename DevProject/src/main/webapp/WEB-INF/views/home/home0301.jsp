<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home0301</title>
</head>
<body>
	<h3>4. 표현언어(EL)</h3>
	<p>empty 연산자</p>
	<table border="1">
		<tr>
			<td>\${empty member }</td>
			<td>${empty member }</td> <!-- false -->
		</tr>
		<tr>
			<td>\${empty member.userId }</td>
			<td>${empty member.userId }</td> <!-- false: vo객체에 기본으로 세팅되어 있음 -->
		</tr>
	</table>
</body>
</html>