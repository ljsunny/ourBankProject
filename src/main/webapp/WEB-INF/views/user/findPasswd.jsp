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
<title>��й�ȣ ����</title>
<script language="javaScript" src="resources/js/script.js" charset="ECU-KR"></script>
</head>
<body>
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">��й�ȣ ã��</a></h4>
	</div>
	
<!-- *********************** ���� ****************************  -->

<c:set var="ck" value="${ck}"></c:set>

 <div id="site_div">
<div id="find">
<c:if test="${ck==1||ck==0}">
		<table class="top">
		<tr>
		<td>
			<div class="left">
				<h1>��й�ȣ ã��</h1>
			</div>
			<div class="right">
				<a href="findId.do">���̵� ã��  </a>| <a href="signUp.do">ȸ������</a>
			</div>
			</td>
			</tr>
		</table>
		<hr />
	<form method="post" action="findPasswdCheck.do">
			<table class="id_form">
				<tr>
					<td>���̵�</td>
					<td><input type="text" name="id"
						placeholder="���̵�" /></td>
					<td rowspan="4">
					<input class="btn2" type="submit" value="��й�ȣ ã��" >
					</td>	
				</tr>
				<tr>
					<td>�̸�</td>
					<td><input type="text"  name="user_name"
						placeholder="�̸�" /></td>	
				</tr>
				<tr>
					<td>�������</td>
					<td><input type="date"   name="user_birth"/></td>
				</tr>
				<tr>
					<td>��ȭ��ȣ</td>
					<td><input type="text"   name="user_phone" 
					placeholder="��ȭ��ȣ" placeholder="000-0000-0000"  /></td>
				</tr>
			</table>
		</form>


<hr />
<c:if test="${ck==1}">
<div class="info">
	��ġ�ϴ� ������ �����ϴ�.
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
				<h1>��й�ȣ �缳��</h1>
			</div>
			<div class="right">
				<a href="findId.do">���̵� ã��  </a>| <a href="signUp.do">ȸ������</a>
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
					<td>���ο� ��й�ȣ</td>
					<td><input type="password"  size="17" name="passwd"
						placeholder="���ο� ��й�ȣ Ȯ��" /></td>
					<td rowspan="2">
					<input type="submit" value="��й�ȣ ����" class="btn3" >
					</td>	
				</tr>
				<tr>
					<td>���ο� ��й�ȣ Ȯ��</td>
					<td><input type="password"  size="17" name="ck_passwd"
						placeholder="���ο� ��й�ȣ Ȯ��" /></td>	
				</tr>
			</table>
		</form>
</div>
</c:if>
</div>
</div>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>