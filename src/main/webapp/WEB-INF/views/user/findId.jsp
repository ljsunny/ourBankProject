<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fonts.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���̵� ã��</title>
<script language="javaScript" src="resources/js/script.js"
	charset="EUC-KR"></script>
</head>
<body>

	<!-- *********************** �Խ��� �۾��� �� ****************************  -->
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- *********************** ���̵� �޴� ****************************  -->

	<div id="side_menu">
		<h4>
			<a href="#">���̵� ã��</a>
		</h4>


	</div>

	<!-- *********************** ���� ****************************  -->
	<c:set var="ck" value="${ck}"></c:set>
 <div id="site_div">
	<div id="find">
		<c:if test="${user_id==null}">
			<table class="top">
				<tr>
					<td>
					<div class="left">
							<h1>���̵� ã��</h1>
						</div>
						<div class="right">
							<a href="findPasswd.do">��й�ȣã�� </a>| <a href="signUp.do">ȸ������</a>
						</div>
					</td>
				</tr>
			</table>
			<hr/>

			<table class="id_form">
				<form method="post" action="findIdCheck.do">
				<tr>
					<td>�̸�</td>
					<td><input type="text"name="user_name"
						placeholder="�̸�" /></td>
					<td rowspan="3">
					<input type="submit" value="���̵� ã��" class="btn1" ></td>
				</tr>
				<tr>
					<td>�������</td>
					<td><input type="date" name="user_birth" /></td>
				</tr>
				<tr>
					<td>��ȭ��ȣ</td>
					<td><input type="text" name="user_phone"
						placeholder="000-0000-0000" /></td>
				</tr>
				</form>
			</table>


		</c:if>
		<hr/>
		<c:if test="${ck}">
			<div class="info">
				<c:if test="${user_id!=null}">
					<p>���̵�� ${user_id} �Դϴ�.</p>
					<input type="button" value="�α�������" onclick="goLoginForm()">
				</c:if>
				<c:if test="${user_id==null}">
				<p>��ġ�ϴ� ������ �����ϴ�.</p>
				</c:if>
				<hr width="500px" />
			</div>
		</c:if>
	</div>
</div>
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>