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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ����������</title>
<script language="javaScript" src="resources/js/script.js" charset="ECU-KR"></script>
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
<div id="my_product">
<sf:form  commandName="userBean"  name="regForm" method="POST" 
action="myInfoUpdate.do" onsubmit="return inputCheck()">
	<table cellspacing="0" cellpadding="5" border="1" width="600">
		<tr>
			<td>
				<ul>
					<li>���̵�</li>
					
				</ul>
			</td>
			
			<td>
				<ul>
					<li>
					<c:out value="${id}"></c:out>
					<sf:hidden path="id" value="${id}"/>
					</li>
					
				</ul>
			</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li>�̸�</li>
					<li>�������</li>
					<li>�޴��� ��ȣ</li>
					<li>�ּ�(����)</li>
					<li>�̸���</li>
				</ul>
			</td>
				<td>
				<ul>
					<li><sf:input path="user_name" size="50" maxlength="50"></sf:input></li>
					<li><sf:input type="date" path="user_birth" size="50" maxlength="50"></sf:input></li>
					<li><sf:input path="user_phone" size="50" maxlength="50"></sf:input></li>
					<li><sf:input path="user_address" id="user_address" size="50" maxlength="50" readonly="true"></sf:input>
						<input type="button" value="�ּ�ã��" onclick="findAdress()"></li>
					
					<li><sf:input path="user_email" size="50" maxlength="50"></sf:input></li>
				</ul>
			</td>
		</tr>
		<c:if test="${uid=='admin'}">
		<!--�������� ��쿡��--> 
		
		<tr><td colspan="2" align="right">�������� ��� üũ<input type="checkbox"></td></tr>
		</c:if>
		<tr> 
		<td colspan="2" align="center">
		<input type="submit" value="�����ϱ�" >
		<input type="button" onclick="history.back()" value="���">
		</td>
		</tr>
	</table>
</sf:form>
</div>
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>