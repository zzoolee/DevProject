<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Form Tag</title>
</head>
<body>
	<h2>Spring Form Tag</h2>
	<p>객체를 생성하여 값을 설정한 후 화면에 전달한다.</p>
	<p>넘겨받은 값을 가지고 입력값 검증을 진행한다.</p>
	
	<!-- 
		아이디 값을 비워놓고 등록을 눌렀을 때, 누락된 값이므로 누락이 되었다는 메세지가 출력되어야 하는데 출력디 되지 않습니다.
		그 이유는 validation을 하기 위한 설정이 form:error 만으로는 절대 이뤄지지 않는다는 것입니다.
		그렇다면 어떻게 설정해야 validation이 이뤄지는 걸까?
		
		패키지 validation안에 설정될 클래스를 참고하기 바람!
	 -->
	<form:form method="post" action="/formtag/validation/result" modelAttribute="member">
		<table>
			<tr>
				<td>유저ID</td>
				<td>
					<form:input path="userId"/>
					<font color="red">
						<form:errors path="userId"></form:errors>
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName"></form:errors>
					</font>
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<form:input path="email"/>
					<font color="red">
						<form:errors path="email"></form:errors>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>	
	</form:form>
</body>
</html>