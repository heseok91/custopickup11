<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function inputCheck(form) {
	if (form.pass.value == "") {
		alert("비밀번호 입력 하세요")
		form.pass.focus();
		return false
	}
	
	return true;
}

function win_upload() {
	
	
	var op = "width=500, height=150, left=50, top=150";
	open("${pageContext.request.contextPath}/user/userPictureimgForm", "", op);
	
	
}
</script>
</head>
<body>

	<form class="w3-container" action="${pageContext.request.contextPath}/user/userUpdatePro" name="f" method="post" onsubmit="return inputCheck(this)">
		<input type="hidden" name="userpicture" value="${m.userpicture}">
		<table class="w3-table-all">
			<caption>MODEL1으로 구현한 회원가입</caption>
			<tr>
				<td rowspan="4" valign="bottom" width="100px">
				<img src="${pageContext.request.contextPath}/view/user/picture/${m.userpicture}"
					width="100" height="120" id="pic"><br> <font size="1"><a
						href="javascript:win_upload()">사진등록</a></font></td>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${sessionScope.userid}" readonly></td>
			</tr>
			<tr>

				<td>비밀번호</td>
				<td><input type="password" name="userpass"></td>
			</tr>
			<tr>

				<td>이름</td>
				<td><input type="text" name="username" value="${m.username}"></td>
			</tr>
			<tr>

				<td>구분</td>
				<td><input type="radio" name="usertype" value="1" ${m.usertype  eq "user"? "checked" : ""}>
					회원 <input type="radio" name="usertype" value="2" ${m.usertype eq "admin"? "checked" : ""}> 사장</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2"><input type="text" name="usertel" value="${m.usertel}"></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit" value="회원정보수정"></td>
			</tr>

		</table>
	</form>
	

</body>
</html>