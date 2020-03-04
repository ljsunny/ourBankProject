<%@page import="com.ourbank.app.PageNumberingManager"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import="java.io.*,java.text.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page session="false"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>

<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<meta charset="EUC-KR">
<title>���ҽ�</title>
</head>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="#">��������</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="#"> ����Ʈ�Ұ� </a></li>
				<li>- <a href="newnoticeList.do?current_page=1"> ���ҽ� </a></li>
				
			</ul>
		</div>
	</div>
	
<!-- *********************** ���� ****************************  -->
<c:set var="total_cnt" value="${totalCnt}"/>
<c:set var="searchString" value="${searchStr}"/>
<c:set var="pageForView" value="${pageForView}"/>
<%
	int total_cnt=((Integer)(pageContext.getAttribute("total_cnt"))).intValue();
	String searchStr=(String) pageContext.getAttribute("searchString");
	int rowsPerPage=10;
	int total_pages=PageNumberingManager.getInstance().getTotalPage(total_cnt, rowsPerPage);
	//el�� ������ �� �ִ� ���
	pageContext.setAttribute("t_pages", total_pages);
	HttpSession session=request.getSession();
	String id=(String) session.getAttribute("id");
%>
<c:set var="id" value="<%=id%>"/>
<script type="text/javascript">
	//����Ʈ _ ����ũ ���� �� 
	function boardView_idCheck() {
		var loginUser = "${id}";
		if(!loginUser) {
			alert('�α��� �� �̿� �����մϴ�.');
			return location.href = "loginForm.do";		
		}
	}
</script>
<div id="line_div">
	<div id="sub_logo">
		<h2>��������</h2>
    </div> 
 <div id="site_div">
 
 <div style="margin-top: 50px; font-weight: bold;">
<table cellspacing="1" width="700" border="0">
	<tr>
		<td>�� �Խù�:<c:out value="${totalCnt}"/></td>
		<td><p align=right>������:<c:out value="${t_pages}"/></p></td>
	</tr>
</table>
</div>

<table cellspacing=1 width=700 >
	<thead>
	<tr>
		<td width="50"><p align="center">�۹�ȣ</td>
		<td width="320"><p align="center">����</td>
		<td width="1000"><p align="center">���̵�</td>
		<td width="100"><p align="center">�����</td>
		<td width="100"><p align="center">��ȸ��</td>
	</tr> </thead>
	<tbody>
	<c:forEach var="board" items="${searchedList}">
		<tr class="tlb_board_bottom">
			<td width="40">${board.getidx()}</td>
			<td width="100">${board.getId()}</td>
			<td width="320">
			<!-- �α��� o -->
				<c:if test="${id == null }"> <!--   (���� ${id != null }�ιٲٱ�!!-->
					<a href="reviewView.do?idx=${board.getidx()}
							&current_page=<c:out value="${current_page}"/>&searchStr=None"
						title="${board.getContent()}"> <c:out value="${board.getSubject()}" /></a>
					</c:if>
				
				<!-- �α��� x -->
				<c:if test="${id !=null}">
				<a onclick="boardView_idCheck(this.href);return false;" onkeypress="this.onclick;"
					href="reviewView.do" > <c:out value="${board.getSubject()}" /></a>
				</c:if>
				</td>
				<td width="100">
					<c:out value="${board.getCreated_date()}" />
				</td>
				<td width="100">
					<c:out value="${board.getHits()}" />
				</td>
				</tr>
				</c:forEach></tbody>
				</table>
				
			<div style="margin-top: 50px; font-weight: bold;">
			<table cellspacing="1" width="700" class="page">
			<tr>
		<td>
		<c:forEach var="i" begin="1" end="${t_pages}">
			<a href="newnoticeSearchedList.do?
			pageForView=${i}&searchStr=<c:out value="${searchStr}"/>">
			[
			<c:if test="${i==pageForView}"><b></c:if>
			${i}
			<c:if test="${i==pageForView}"><b></c:if>
			]
		</c:forEach>
		</td>
	</tr>
</table>
</div>

<div>
	<input type="button" value="��ü ������� ���ư���" class="bnt_comu"
		onclick="window.location='newnoticeList.do?current_page=1'">
</div>
</div>
</div>
</div>
</html>