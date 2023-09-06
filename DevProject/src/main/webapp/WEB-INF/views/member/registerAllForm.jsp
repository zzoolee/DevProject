<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h2>Register All Form</h2>
	<form action="/registerUser" method="post">
		<table>
			<tr>
				<th>유저ID</th>
				<td><input type="text" name="userId"/></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName"/></td>
			</tr>
			<tr>
				<th>E-MAIL</th>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" name="dateOfBirth" placeholder="2023년 08월 25일 "/></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="gender" value="male" checked="checked"/>Male
					<input type="radio" name="gender" value="female"/>Female
					<input type="radio" name="gender" value="other"/>Other
				</td>
			</tr>
			<tr>
				<th>개발자여부</th>
				<td><input type="checkbox" name="developer" value="Y"/></td>
			</tr>
			<tr>
				<th>외국인여부</th>
				<td><input type="checkbox" name="foreigner" value="true"/></td>
			</tr>
			<tr>
				<th>국적</th>
				<td>
				<select name="nationality" class="jobs">
					<option value="Korea">대한민국</option>
					<option value="Germany">독일</option>
					<option value="Austrailia">호주</option>
					<option value="Canada">캐나다</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>소유차량</th>
				<td>
					<select name="cars" multiple="multiple">
						<option value="">없음</option>
						<option value="VOLVO">Volvo</option>
						<option value="JEEP">Jeep</option>
						<option value="BMW">Bmw</option>
						<option value="AUDI">Audi</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<input type="checkbox" name="hobby" value="Sports"/>Sports<br/>
					<input type="checkbox" name="hobby" value="Music"/>Music<br/>
					<input type="checkbox" name="hobby" value="Movie"/>Movie
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="address.postCode"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address.location"/></td>
			</tr>
			<tr>
				<th>카드1 - 번호</th>
				<td><input type="text" name="cardList[0].no"/></td>
			</tr>
			<tr>
				<th>카드1 - 유효년월</th>
				<td><input type="text" name="cardList[0].validMonth" placeholder="2023년 08월 25일"/></td>
			</tr>
			<tr>
				<th>카드2 - 번호</th>
				<td><input type="text" name="cardList[1].no" /></td>
			</tr>
			<tr>
				<th>카드2 - 유효년월</th>
				<td><input type="text" name="cardList[1].validMonth" placeholder="2023년 08월 25일"/></td>
			</tr>
			<tr>
				<th>소개</th>
				<td>
					<textarea name="introduction" rows="6" cols="50"></textarea>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<input type="submit" name="btnSubmit" value="등록"/>
				</td>
				<td>
					<input type="reset" name="btnReset" value="리셋"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>