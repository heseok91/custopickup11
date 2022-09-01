<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>

<div class="w3-container">
<table class="w3-table-all w3-bordered w3-striped w3-border test w3-hoverable" style="color:#000">
<tbody><tr class="w3-grey">
  <th>아이디</th>
  <th>비밀번호</th>
  <th>이름</th>
  <th>구분</th>
  <th>전화번호</th>
  <th>사진명</th>
</tr>
</tbody><tbody>
<c:forEach var="m" items="${li}">

<tr>
  <td>${m.userid}</td>
  <td>${m.userpass}</td>
  <td>${m.username}</td>
  <td>${m.usertype eq "user" ? "회원" : "사장"}</td>
  <td>${m.usertel}</td>
  <td>${m.userpicture}</td>
  
</tr>

</c:forEach>

</tbody>
</table>

</div>

</body>
</html>