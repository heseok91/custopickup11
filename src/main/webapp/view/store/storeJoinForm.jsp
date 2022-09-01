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
		open("${pageContext.request.contextPath}/store/storePictureimgForm", "", op);
		
		
	}

</script>
</head>
<body>
	<form class="w3-container" action="${pageContext.request.contextPath}/store/storeJoinPro" name="f" method="post">
		<input type="hidden" name="storepicture">
		<table class="w3-table-all">
			<caption>가게정보입력</caption>
			<tr>
				<td rowspan="4" valign="bottom" width="100px"><img src=""
					width="100" height="120" id="pic"><br> <font size="1">
					<a class="w3-button w3-green"
						href="javascript:win_upload()">가게사진등록</a></font></td>
				<td>사업자 등록번호</td>
				<td><input type="text" name="storeid"></td>
			</tr>
			<tr>

				<td>가게이름</td>
				<td><input type="text" name="storename"></td>
			</tr>
			<tr>

				<td>전화번호</td>
				<td><input type="text" name="storetel"></td>
			</tr>
			<tr>
				<td>운영시간</td>
				<td colspan="2"><input type="time" name="storeopen" value="00:00:00" min="00:00:00" max="24:00:00" > ~ <input type="time" name="storeclose" value="12:00:00" min="00:00:00" max="24:00:00" ></td>
			</tr>
			<tr>
				<td>휴무일</td>
				<td colspan="2">
					<input type="checkbox" name="storedayoff" value="연중무휴" checked>연중무휴
					<input type="checkbox" name="storedayoff" value="월요일">월요일
					<input type="checkbox" name="storedayoff" value="화요일">화요일
					<input type="checkbox" name="storedayoff" value="수요일">수요일
					<input type="checkbox" name="storedayoff" value="목요일">목요일
					<input type="checkbox" name="storedayoff" value="금요일">금요일
					<input type="checkbox" name="storedayoff" value="토요일">토요일
					<input type="checkbox" name="storedayoff" value="일요일">일요일
				</td>
			</tr>
			<tr>

				<td>업종</td>
				<td><input type="radio" name="storetype" value="피자">피자 
					<input type="radio" name="storetype" value="햄버거">햄버거
					<input type="radio" name="storetype" value="마라탕">마라탕  
					<input type="radio" name="storetype" value="카페">카페</td>
			</tr>
			
			<tr>
				<td>가게주소</td>
				<td colspan="2"><input type="text" name="storeaddress"></td>
			</tr>
			
			<tr>
				<td colspan="3"><input type="submit" value="등록"></td>
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