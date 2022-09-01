<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Insert title here</title>
</head>
<body>


<c:if test="${param.userid eq 'login'}">
<script> alert("login 하세요")</script>
</c:if>
<c:if test="${param.usertype eq 'forbidden'}">
<script> alert("금지되어 있습니다")</script>
</c:if>


<div class="w3-container w3-blue">
  <h2>로그인</h2>
</div>

<form class="w3-container" action="${pageContext.request.contextPath}/user/userLoginPro" method="post">
  <p>
  <label>회원 ID</label>
  <input class="w3-input" type="text" name="userid"></p>
  <p>
  <label>비밀번호</label>
  <input class="w3-input" type="password" name="userpass"></p>
 
  <p>
  <input class="w3-input" type="submit"  value="확인"></p>
</form>

</body>
</html>