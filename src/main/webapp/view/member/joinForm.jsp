<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script>
	function win_upload() {
		
		
		var op = "width=500, height=150, left=50, top=150";
		open("${pageContext.request.contextPath}/member/pictureimgForm", "", op);
		
		
	}

</script>
</head>
<body>
	<form class="w3-container" action="${pageContext.request.contextPath}/member/joinPro" name="f" method="post">
		<input type="hidden" name="picture">
		<table class="w3-table-all">
			<caption>MODEL1으로 구현한 회원가입</caption>
			<tr>
				<td rowspan="4" valign="bottom" width="100px"><img src=""
					width="100" height="120" id="pic"><br> <font size="1">
					<a class="w3-button w3-green"
						href="javascript:win_upload()">사진등록</a></font></td>
				<td>아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>

				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>

				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>

				<td>성별</td>
				<td><input type="radio" name="gender" value="1" checked>
					남 <input type="radio" name="gender" value="2"> 여</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2"><input type="text" name="tel"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td colspan="2"><input type="text" name="email"></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="회원가입"></td>
			</tr>

<!-- create table member (
	id varchar(20) primary key,
	pass varchar(20),
	name varchar(20),
	gender number(1),
	tel varchar(20),
	email varchar(50),
	picture varchar(200)
); -->

		</table>
	</form>
</body>
</html>