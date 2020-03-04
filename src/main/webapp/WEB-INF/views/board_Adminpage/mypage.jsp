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
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> ������������ </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> ���� �ۼ��� �� </a></li>
				<li>- <a href="myProductList.do"> ������ ��ǰ ��ȸ </a></li>
				<li>- <a href="myWantList.do?current_page=1"> ���ɻ�ǰ </a></li>
				<li>- <a href="manageDeposit.do"> ���ݻ�ǰ ���� </a></li>
				<li>- <a href="manageSaving.do"> ���ݻ�ǰ ���� </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** ���� ****************************  -->
	<div id="my_wrap">
	<table >
		<tr>
			<td><input type="button" value="������������"
				class="mybtn"
				 onclick="goToMyInfo()"></td>
				 
				 <!-- Ŀ�´�Ƽ �Խ����� ��� �ϼ��Ǹ� ����� -->
			<td><input type="button" value="���� �ۼ��� ��"
				class="mybtn"
				 onclick="goToMyBoard()"></td>
		</tr>
		<tr>
			<!-- ��ǰ �Խ����� �ϼ��Ǹ� ����� -->
			<td><input type="button" value="������ ��ǰ ��ȸ"
				 class="mybtn" onclick="goToMyProduct()"></td>
			<td><input type="button" value="���ɻ�ǰ"
				  class="mybtn" onclick="goToMyWant()"></td>
		</tr>
	</table>
	</div>
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>