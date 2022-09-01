<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<table class="w3-table-all">
			<caption>MODEL1으로 구현한 회원가입</caption>
			<tr>
				<td rowspan="4" valign="bottom" width="100px">
				<img src="${pageContext.request.contextPath}/view/user/picture/${m.userpicture}"
					width="100" height="120" ><br> </td>
				<td>아이디</td>
				<td>${sessionScope.userid}</td>
			</tr>
			<tr>

				<td>이름</td>
				<td>${m.username}</td>
			</tr>
			<tr>

				<td>구분</td>
				<td>${m.usertype eq "user" ? "회원" : "사장"}
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2">${m.usertel}</td>
			</tr>
			
			<tr>
				<td colspan="3" class="w3-center">
				<a class="w3-button w3-black" href="${pageContext.request.contextPath}/user/userUpdateForm">회원정보수정</a>
				<a class="w3-button w3-black" href="${pageContext.request.contextPath}/user/userDelete">회원탈퇴</a>
				<a class="w3-button w3-black" href="${pageContext.request.contextPath}/user/userPassUpdate">비밀번호수정</a></td>
			</tr>

		</table>

</body>
</html>