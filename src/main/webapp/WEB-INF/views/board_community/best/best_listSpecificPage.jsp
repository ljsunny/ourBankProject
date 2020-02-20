<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page session="false"%>
<%@page import="com.ourbank.app.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.ourbank.app.PageNumberingManager"%>
<%@page import="com.ourbank.app.bean.BestBoard_Bean" %>

<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>Best게시물 글목록</title>
</head>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
<jsp:include page="../../header.jsp"></jsp:include>
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">커뮤니티</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="/app/review_listSpecificPageWork.do?current_page=1"> 리 뷰</a></li>
				<li>- <a href="/app/free_listSpecificPageWork.do?current_page=1"> 자유게시판</a></li>
				<li>- <a href="/app/meeting_listSpecificPageWork.do?current_page=1"> 모임방</a></li>
				<li>- <a href="/app/debate_listSpecificPageWork.do?current_page=1"> 토론방</a></li>
				<li>- <a href="/app/_listSpecificPageWork.do?current_page=1"> 제태크노하우</a></li>
				<li>- <a href="/app/best_listSpecificPageWork.do?current_page=1"> BEST게시물</a></li>
			</ul>
		</div>
	</div>
<!-- *********************** 내용 ****************************  -->
<c:set var="current_page" value="${current_page}" />

<%
	int c_page = Integer.parseInt((String) (pageContext.getAttribute("current_page")));
	pageContext.setAttribute("c_page", c_page);
%>

<div id="body_div">
<h2>BEST게시물</h2>	
<div id="board_div">	

<table cellspacing=1 width=700 border=0>
	<tr>
		<td><p align="right">
				페이지:
				<c:out value="${current_page}" />
			</p></td>
	</tr>
</table>

<table cellspacing=1 width=700 border=1>
	<tr>
		<td width="50"><p align="center">글번호</td>
		<td width="320"><p align="center">제목</td>
		<td width="100"><p align="center">아이디</td>
		<td width="100"><p align="center">등록일</td>
		<td width="100"><p align="center">조회수</td>
	</tr>
	

	<c:set var="count" value="0" />
	 <c:forEach var="board" items="${boardList}">
	   <c:set var="count" value="${count+1}"/>

		<tr>
			<td width="50"><p align="center">${count}</p></td>
			<td width="320">
				<p align="center">
					<a
						href="best_viewWork.do?best_idx=${board.getBest_idx()}
			&current_page=<c:out value="${current_page}"/>
			&category_num=<c:out value="${board.getCategory_num() }"/>"
						title="${board.getContent()}"> <c:out
							value="[${board.getCategory() }게시판]  ${board.getSubject()}" /></a>
				</p>
			</td>
			<td width="100"><p align="center">
				<c:out value="${board.getId()}"/>
				</p></td>
			<td width="100"><p align="center">
					<c:out value="${board.getCreated_date()}" />
				</p></td>
			<td width="100"><p align="center">
					<c:out value="${board.getHits()}" />
				</p></td>
		</tr>
	</c:forEach>


	<%
		int rowsPerPage = 10;
		//best게시물 최대20개
		int total_pages = PageNumberingManager.getInstance().getTotalPage(20, rowsPerPage);
		pageContext.setAttribute("t_pages", total_pages); 
	%>
</table>
<table cellspacing="1" width="700" border="1">
	<tr>
		<td><c:forEach var="i" begin="1" end="${t_pages}">
				<a href="best_listSpecificPageWork.do?current_page=${i}"> [ <c:if
						test="${i==c_page}">
						<b>
					</c:if> ${i} <c:if test="${i==c_page}">
						<b>
					</c:if> ]
				</a>
			</c:forEach></td>
	</tr>
</table>

</div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
</html>