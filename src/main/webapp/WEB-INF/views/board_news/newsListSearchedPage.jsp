<%@page import="com.ourbank.app.PageNumberingManager"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
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
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>뉴스와 정보</title>
<script>
function totallist(){
	location.href='newsList.do?current_page=1';
}
function financeCompanylist(){
	location.href='financeCompanyList.do?current_page=1';
}
function relatedNewslist(){
	location.href='relatedNewsList.do?current_page=1';

}
</script>
</head>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="newsList.do?current_page=1">뉴스와 정보</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="financeCompanyList.do?current_page=1"> 금융회사정보 </a></li>
				<li>- <a href="relatedNewsList.do?current_page=1"> 관련뉴스 </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<c:set var="total_cnt" value="${totalCnt}"/>
<c:set var="searchString" value="${searchStr}"/>
<c:set var="pageForView" value="${pageForView}"/>
<%
	int total_cnt=((Integer)(pageContext.getAttribute("total_cnt"))).intValue();
	String searchStr=(String) pageContext.getAttribute("searchString");
	int rowsPerPage=10;
	int total_pages=PageNumberingManager.getInstance().getTotalPage(total_cnt, rowsPerPage);
	//el로 꺼내쓸 수 있는 방법
	pageContext.setAttribute("t_pages", total_pages);
%>
<div id="line_div">
 <div id="sub_logo">
<h2>뉴스와 정보</h2>	
 </div> 
 <div id="site_div">
 
<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing=1 width=700>
	<tr>
		<td>총 게시물수: <c:out value="${totalCnt}" /></td>
		<td><p align=right>페이지:<c:out value="${t_pages}"/></td>
	</tr>
</table>
</div>

<table cellspacing=1 width=700 >
	<thead>
	<tr>
		<td width="50" class="tlb_board_top">글번호</td>
		<td width="320" class="tlb_board_top">제목</td>
		<td width="100" class="tlb_board_top">아이디</td>
		<td width="100" class="tlb_board_top">등록일</td>
		<td width="100" class="tlb_board_top">조회수</td>
	</tr> </thead>
	<tbody>
	<c:forEach var="board" items="${searchedList}">
		<tr class="tlb_board_bottom">
			<td width="50">${board.getIdx()}</td>
			<td width="320">
				
					<a
						href="newsView.do?idx=${board.getIdx()}
							&current_page=<c:out value="${current_page}"/>
							&searchStr=None"
						title="${board.getContent()}"> <c:out
							value="${board.getSubject()}" /></a>
				
			</td>
			<td width="100">${board.getId()}</td>
			<td width="100">
					<c:out value="${board.getCreated_date()}" />
				</td>
			<td width="100">
					<c:out value="${board.getHits()}" />
				</td>
		</tr>
	</c:forEach>
</table>

<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing="1" width="700" class="page">
	<tr>
		<td>
		<c:forEach var="i" begin="1" end="${t_pages}">
			<a href="newsSearchedList.do?
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
<div>
		<input type="button" value="전체 목록으로 돌아가기" class="bnt_comu"
		onclick="window.location='newsList.do?current_page=1'">

	</div>
</div>
</div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>

</html>