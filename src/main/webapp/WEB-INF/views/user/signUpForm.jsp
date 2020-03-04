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
<title>ȸ������</title>
</head>
<%
String uid=(String)session.getAttribute("uid");

%>
<body>
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">ȸ������</a></h4>
	</div>
	
<!-- *********************** ���� ****************************  -->
<div id="signup_form">
<h1>ȸ������</h1>
<div class="signup_in">
<sf:form  commandName="boardBean"  name="regForm" method="POST" 
action="doSignUp.do" onsubmit="return inputCheck()">
	<table>
		<tr>
			<td>
				<ul>
					<li><p>���̵�</p></li>
					<li><p>��й�ȣ</p></li>
					<li><p>��й�ȣ Ȯ��</p></li>
				</ul>
			</td>
			
			<td>
				<ul>
					<li>
					<!-- �ߺ�Ȯ�� ó�� �ʿ���!!!!!!!!!!!!!!!!!!!!!! -->
						<sf:input id="id" path="id"  maxlength="50" placeholder="���̵� �Է����ּ���"></sf:input>
						<input class="button" type="button" value="�ߺ�Ȯ��" onclick="idCheck(this.form.id.value)" >
						<input type="hidden" id="id_check" value="0">
					</li>
					<!-- ��й�ȣ ��ȿ�� �˻� �ؾ���!!!!!!!!!!!!!!! -->
					<li><sf:input type="password" path="passwd"  maxlength="50" placeholder="��й�ȣ�� �Է����ּ���"></sf:input></li>
					<li><input type="password" name="ck_passwd"  placeholder="��й�ȣ Ȯ���� �Է����ּ���"></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li><p>�̸�</p></li>
					<li><p>�������</p></li>
					<li><p>�޴��� ��ȣ</p></li>
					<li><p>�ּ�(����)</p></li>
					<li><p>�̸���</p></li>
				</ul>
			</td>
				<td>
				<ul>
					<li><sf:input path="user_name"  maxlength="50" placeholder="�̸��� �Է����ּ���"></sf:input></li>
					<li><sf:input type="date" path="user_birth" maxlength="50" placeholder="������ �Է����ּ���"></sf:input></li>
					<li><sf:input path="user_phone"  maxlength="50" placeholder="000-0000-0000"></sf:input></li>
					<li><sf:input class="big" path="user_address" 
					id="user_address"  maxlength="50" readonly="true" placeholder="�ּҸ� �Է����ּ���"></sf:input>
						<input class="button" type="button" value="�ּ�ã��" onclick="findAdress()"></li>
					
					<li><sf:input class="big" path="user_email" size="50" maxlength="50" placeholder="�̸����� �Է����ּ���"></sf:input></li>
				</ul>
			</td>
		</tr>
		<c:if test="${uid=='admin'}">
		<!--�������� ��쿡��--> 
		
		<tr><td colspan="2" align="right">�������� ��� üũ<input type="checkbox"></td></tr>
		</c:if>
		<tr> 
		<td colspan="2" align="center">
		<input class="button" type="submit" value="ȸ������" >
		<input class="button" type="button" onclick="history.back()" value="���">
		</td>
		</tr>
	</table>
</sf:form>
</div>
</div>

	<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>