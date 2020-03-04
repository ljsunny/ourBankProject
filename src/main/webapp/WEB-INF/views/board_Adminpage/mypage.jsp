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
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Page</title>
<script type="text/javascript">
function goToMyInfo(){
	location.href='myInfo.do';
}
function goToMyBoard() {
	location.href='myBoardList.do?current_page=1';
}
function goToMyProduct(){
	location.href='myProductList.do';
}
function goToMyWant(){
	location.href='myWantList.do?current_page=1';
}
</script>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> 개인정보관리 </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> 내가 작성한 글 </a></li>
				<li>- <a href="myProductList.do"> 가입한 상품 조회 </a></li>
				<li>- <a href="myWantList.do?current_page=1"> 관심상품 </a></li>
				<li>- <a href="manageDeposit.do"> 예금상품 관리 </a></li>
				<li>- <a href="manageSaving.do"> 적금상품 관리 </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
	<div id="my_wrap">
	<table >
		<tr>
			<td><input type="button" value="개인정보관리"
				class="mybtn"
				 onclick="goToMyInfo()"></td>
				 
				 <!-- 커뮤니티 게시판이 모두 완성되면 만들기 -->
			<td><input type="button" value="내가 작성한 글"
				class="mybtn"
				 onclick="goToMyBoard()"></td>
		</tr>
		<tr>
			<!-- 상품 게시판이 완성되면 만들기 -->
			<td><input type="button" value="가입한 상품 조회"
				 class="mybtn" onclick="goToMyProduct()"></td>
			<td><input type="button" value="관심상품"
				  class="mybtn" onclick="goToMyWant()"></td>
		</tr>
	</table>
	</div>
	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>