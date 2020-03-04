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
<title>비밀번호 변경</title>
<script language="javaScript" src="resources/js/script.js" charset="ECU-KR"></script>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">비밀번호 찾기</a></h4>
	</div>
	
<!-- *********************** 내용 ****************************  -->

<c:set var="ck" value="${ck}"></c:set>

 <div id="site_div">
<div id="find">
<c:if test="${ck==1||ck==0}">
		<table class="top">
		<tr>
		<td>
			<div class="left">
				<h1>비밀번호 찾기</h1>
			</div>
			<div class="right">
				<a href="findId.do">아이디 찾기  </a>| <a href="signUp.do">회원가입</a>
			</div>
			</td>
			</tr>
		</table>
		<hr />
	<form method="post" action="findPasswdCheck.do">
			<table class="id_form">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id"
						placeholder="아이디" /></td>
					<td rowspan="4">
					<input class="btn2" type="submit" value="비밀번호 찾기" >
					</td>	
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text"  name="user_name"
						placeholder="이름" /></td>	
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date"   name="user_birth"/></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text"   name="user_phone" 
					placeholder="전화번호" placeholder="000-0000-0000"  /></td>
				</tr>
			</table>
		</form>


<hr />
<c:if test="${ck==1}">
<div class="info">
	일치하는 정보가 없습니다.
</div>
<hr/>
</c:if>
</c:if>
<c:if test="${ck==2}">
<div class="find">
		<table class="top">
		<tr>
		<td>
			<div class="left">
				<h1>비밀번호 재설정</h1>
			</div>
			<div class="right">
				<a href="findId.do">아이디 찾기  </a>| <a href="signUp.do">회원가입</a>
			</div>
			</td>
			</tr>
		</table>
		<hr />
	<form name="regForm"  method="post" 
	action="changePasswdCheck.do" onsubmit="return equalPasswd();">
	<input name="id" type="hidden" value="${id}">
			<table class="id_form">
				<tr>
					<td>새로운 비밀번호</td>
					<td><input type="password"  size="17" name="passwd"
						placeholder="새로운 비밀번호 확인" /></td>
					<td rowspan="2">
					<input type="submit" value="비밀번호 변경" class="btn3" >
					</td>	
				</tr>
				<tr>
					<td>새로운 비밀번호 확인</td>
					<td><input type="password"  size="17" name="ck_passwd"
						placeholder="새로운 비밀번호 확인" /></td>	
				</tr>
			</table>
		</form>
</div>
</c:if>
</div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>