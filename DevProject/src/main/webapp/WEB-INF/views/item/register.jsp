<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item Register</title>
</head>
<body>
	<h2>REGISTER</h2>
	<form action="/item/register" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>상품명</td>
				<td><input type="text" name="itemName" id="itemName"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="picture" id="picture"/></td>
			</tr>
			<tr>
				<td>개요</td>
				<td><textarea rows="10" cols="30" name="description"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit">등록</button>
			<button type="button" onclick="javascript:location.href='/item/list'">목록</button>
		</div>
	</form>
</body>
</html>