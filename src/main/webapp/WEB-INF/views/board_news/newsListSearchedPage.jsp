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
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<meta charset="EUC-KR">
<title>뉴스와 정보</title>
</head>
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
	HttpSession session=request.getSession();
	String id=(String) session.getAttribute("id");
%>
<c:set var="id" value="<%=id%>"/>
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
//리스트 _ 제목링크 누를 시 
function boardView_idCheck() {
	var loginUser = "${id}";
	if(!loginUser) {
		alert('로그인 후 이용 가능합니다.');
		return location.href = "loginForm.do";		
	}
}
</script>


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
<div id="line_div">
 <div id="sub_logo">
<h2>뉴스와 정보</h2>	
 </div> 
 <div id="site_div">

<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing=1 width=700>
	<tr>
		<td>총 게시물:<c:out value="${totalCnt}"/></td>
		<td><p align=right>페이지:<c:out value="${t_pages}"/></p></td>
	</tr>
</table>
</div>


<table cellspacing="1" width="700">
	<thead>
	<tr>
		<td width="50" class="tlb_board_top">번호</td>
		<td width="320" class="tlb_board_top">제목</td>
		<td width="100" class="tlb_board_top">아이디</td>
		<td width="100" class="tlb_board_top">등록일</td>
		<td width="100" class="tlb_board_top">조회수</td>
	</tr></thead>
	<tbody>
	<c:forEach var="board" items="${searchedList}">
		<tr class="tlb_board_bottom">
		
			<td width="50">${board.getIdx()}</td>
			<td width="320">
			
			<!-- 로그인 o -->
				<c:if test="${id == null }"> <!--   (사용시 ${id != null }로바꾸기!!-->
				<a href="newsView.do?idx=${board.getIdx()}&current_page=${pageForView}&searchStr=${searchStr}" 
					title="${board.getContent()}">${board.getSubject()}</a>
				</c:if>
				
				<!-- 로그인 x -->
				<c:if test="${id !=null}">
				<a onclick="boardView_idCheck(this.href);return false;" onkeypress="this.onclick;"
					href="newsView.do" > <c:out value="${board.getSubject()}" /></a>
				</c:if>
			</td>
			<td width="100">${board.getId()}</td>
			<td width="100">${board.getCreated_date()}</td>
			<td width="100">${board.getHits()}</td>
		</tr>
	</c:forEach>
</tbody>
	</table>
			
<div style="margin-top: 50px; font-weight: bold;">			
<table cellspacing="1" width="700" border="1">
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
</div>

<div>
	<input type="button" value="전체 목록으로 돌아가기" class="btn_comu"
		onclick="window.location='newsList.do?current_page=1'">
</div>
</div>
</div>
</div>

</html>