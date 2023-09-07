<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item2 Modify</title>
</head>
<body>
	<h2>MODIFY</h2>
	<form action="/item2/modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId }"/>
		<input type="hidden" name="pictureUrl" value="${item.pictureUrl }"/>
		<input type="hidden" name="pictureUrl2" value="${item.pictureUrl2 }"/>
		<table>
			<tr>
				<td>상품명</td>
				<td><input type="text" name="itemName" id="itemName" value="${item.itemName }"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price" value="${item.price }"/>&nbsp;원</td>
			</tr>
			<tr>
				<!-- 하나의 비동기처리 -->
				<td>파일</td>
				<td><img src="/item2/display?itemId=${item.itemId }" width="210" height="240"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="pictures" id="pictures1"/></td>
			</tr>
			<tr>
				<!-- 하나의 비동기처리 -->
				<td>파일</td>
				<td><img src="/item2/display2?itemId=${item.itemId }" width="210" height="240"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="pictures" id="pictures2"/></td>
			</tr>
			<tr>
				<td>개요</td>
				<td><textarea rows="10" cols="30" name="description">${item.description }</textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit">수정</button>
			<button type="button" onclick="javascript:location.href='/item2/list'">목록</button>
		</div>
	</form>
</body>
</html>