<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item3 Remove</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<body>
	<h2>REMOVE</h2>
	<form action="/item3/remove" method="post" id="item">
		<input type="hidden" name="itemId" value="${item.itemId }"/>
		<table>
			<tr>
				<td>상품명</td>
				<td><input type="text" name="itemName" id="itemName" value="${item.itemName }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price" value="${item.price }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td>파일</td>
				<td>
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td>개요</td>
				<td><textarea rows="10" cols="30" name="description" disabled="disabled">${item.description }</textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit">삭제</button>
			<button type="button" onclick="javascript:location.href='/item3/list'">목록</button>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function(){
	var item = $("#item");
	
	var itemId = ${item.itemId};
	console.log("itemId : " + itemId);
	
	$.getJSON("/item3/getAttach/" + itemId, function(list){
		$(list).each(function(){
			console.log("list : " + list);
			console.log("this : " + this);
			var data = this;
			var str = "";
			if(checkImageType(data)){ // 이미지면 이미지 태그를 이용하여 출력
				str = "<div>";
				str += "	<a href='/item3/displayFile?fileName=" + data + "'>"; // 경로(원본파일명 포함)
				str += "		<img src='/item3/displayFile?fileName=" + getThumnailName(data) + "'/>"; // 썸네일이미지
				str += "	</a>";
				str += "	<span>X</span>";
				str += "</div>";
			}else{					  // 파일이면 파일명에 대한 링크로만 출력
				str = "<div>";
				str += "	<a href='/item3/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a>"; // 경로 / 원본파일명
				str += "	<span>X</span>";
				str += "</div>";
			}
			$(".uploadedList").append(str);
		});
	});
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumnailName(fileName){
		var front = fileName.substr(0, 12); // 2023/09/04 폴더
		var end = fileName.substr(12);		// 뒤 파일명
		
		console.log("front" + front);
		console.log("end" + end);
		
		return front + "s_" + end;
	}
	
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		
		var idx = fileName.indexOf("_") + 1;
		// 자르고 오른쪽에 있는 원본파일명
		return fileName.substr(idx);
	}
	
	// 이미지 파일인지 확인
	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern); // 패턴과 일치하면 true(이미지구나!)
	}
	
});
</script>
</html>