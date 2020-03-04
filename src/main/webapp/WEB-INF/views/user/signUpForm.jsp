<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script language="javaScript" src="resources/js/script.js" charset="ECU-KR"></script>
<title>회원가입</title>
</head>
<%
String uid=(String)session.getAttribute("uid");

%>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">회원가입</a></h4>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<div id="signup_form">
<h1>회원가입</h1>
<div class="signup_in">
<sf:form  commandName="boardBean"  name="regForm" method="POST" 
action="doSignUp.do" onsubmit="return inputCheck()">
	<table>
		<tr>
			<td>
				<ul>
					<li><p>아이디</p></li>
					<li><p>비밀번호</p></li>
					<li><p>비밀번호 확인</p></li>
				</ul>
			</td>
			
			<td>
				<ul>
					<li>
					<!-- 중복확인 처리 필요함!!!!!!!!!!!!!!!!!!!!!! -->
						<sf:input id="id" path="id"  maxlength="50" placeholder="아이디를 입력해주세요"></sf:input>
						<input class="button" type="button" value="중복확인" onclick="idCheck(this.form.id.value)" >
						<input type="hidden" id="id_check" value="0">
					</li>
					<!-- 비밀번호 유효성 검사 해야함!!!!!!!!!!!!!!! -->
					<li><sf:input type="password" path="passwd"  maxlength="50" placeholder="비밀번호를 입력해주세요"></sf:input></li>
					<li><input type="password" name="ck_passwd"  placeholder="비밀번호 확인을 입력해주세요"></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li><p>이름</p></li>
					<li><p>생년월일</p></li>
					<li><p>휴대폰 번호</p></li>
					<li><p>주소(선택)</p></li>
					<li><p>이메일</p></li>
				</ul>
			</td>
				<td>
				<ul>
					<li><sf:input path="user_name"  maxlength="50" placeholder="이름을 입력해주세요"></sf:input></li>
					<li><sf:input type="date" path="user_birth" maxlength="50" placeholder="생일을 입력해주세요"></sf:input></li>
					<li><sf:input path="user_phone"  maxlength="50" placeholder="000-0000-0000"></sf:input></li>
					<li><sf:input class="big" path="user_address" 
					id="user_address"  maxlength="50" readonly="true" placeholder="주소를 입력해주세요"></sf:input>
						<input class="button" type="button" value="주소찾기" onclick="findAdress()"></li>
					
					<li><sf:input class="big" path="user_email" size="50" maxlength="50" placeholder="이메일을 입력해주세요"></sf:input></li>
				</ul>
			</td>
		</tr>
		<c:if test="${uid=='admin'}">
		<!--관리자인 경우에만--> 
		
		<tr><td colspan="2" align="right">관리자일 경우 체크<input type="checkbox"></td></tr>
		</c:if>
		<tr> 
		<td colspan="2" align="center">
		<input class="button" type="submit" value="회원가입" >
		<input class="button" type="button" onclick="history.back()" value="취소">
		</td>
		</tr>
	</table>
</sf:form>
</div>
</div>

	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>