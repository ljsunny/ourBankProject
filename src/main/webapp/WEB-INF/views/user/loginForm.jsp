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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<title>로그인</title>
<script language="javaScript" src="resources/js/script.js" charset="EUC-KR"></script>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="loginForm.do">로그인</a></h4>
	</div>
	
<!-- *********************** 내용 ****************************  -->

 <div id="site_div">
<div id="login_form">
<h1 >로그인</h1>
<form  name="form" method="post" onSubmit="return blankCheck();" action="loginPro.do">
<table>
	<tr>
	<td><input name="id" size="40" maxlength="50" placeholder="아이디를 입력해주세요" ></input></td>
	</tr>
	<tr>
	<td><input type="password" name="passwd" size="40" maxlength="50" placeholder="비밀번호를 입력해주세요"></input></td>
	</tr>
	<tr><td align="center" rowspan="2">
	<input class="submit" type="submit" value="로그인"  width="30px"/></td></tr>
</table>
</form>
<c:if test="${noinfo==true}">
	<div align="center">
	<p> 아이디/비밀번호를 확인해 주세요 </p>
	</div>
</c:if>
<a href="findId.do">아이디</a> / <a href="findPasswd.do" style="padding-right: 10px"> 비밀번호 찾기</a>| 
<a  href="signUp.do" style="padding-left: 10px">회원가입</a>
</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</div>
</html>