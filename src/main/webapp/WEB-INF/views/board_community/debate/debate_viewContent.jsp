<%@page import="java.nio.file.Path"%>
<%@page import="org.springframework.core.io.ClassPathResource"%>
<%@page import="com.ourbank.app.bean.DebateBoard_Bean"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<title>토론방게시판 글보기</title>
</head>

<!-- model에 저장해 둔거 불러옴 -->
<c:set var="idx_num" value="${idx_num}" />
<c:set var="current_page" value="${current_page}" />
<c:set var="searchString" value="${searchStr}" />
<c:set var="filename" value="${filename}"/>


<%
	String searchString=(String) (pageContext.getAttribute("searchString"));
	String filename=(String) (pageContext.getAttribute("filename"));
%>
<script  language="javascript">
function boardlist(){
	var s="<%=searchString%>";
	if(s=="None")
		location.href='debate_listSpecificPageWork.do?current_page=${current_page}';
	else
		location.href='debate_listSearchedSpecificPageWork.do?pageForView=${current_page}&searchStr=${searchStr}';	
}

function boardmodify(){
	location.href='debate_listSpecificPageWork_to_update.do?idx_num=${idx_num}&current_page=${current_page}';
}

function boarddelete(){
	location.href='debate_DeleteSpecificRow.do?idx_num=${idx_num}&current_page=${current_page}';	
}

function boardrewrite(){
	location.href='debate_show_rewrite_form.do?idx_num=${idx_num}&current_page=${current_page}';
}
function download(){
	var param="<%=filename%>";
	en_filename=encodeURI(param)
	location.href="debate_download.do?filename="+en_filename;
}
</script>

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

<div id="body_div">
<h2>토론방게시판</h2>	
<div id="board_div">

<input type="hidden" value="${boardData.getFilename()}">
<table cellspacing="0" cellpadding="5" border="1" width="500">
	
	<tr>
		<td><b>조회수</b></td><td><c:out value="${boardData.getHits()}"/></td>
	</tr>
	<tr>
		<td><b>작성일</b></td><td><c:out value="${boardData.getCreated_date()}"/></td>
	</tr>
	<tr>
		<td><b>아이디</b></td><td><c:out value="${boardData.getId()}"/></td>
	</tr>
	<tr>
		<td><b>제목</b></td><td><c:out value="${boardData.getSubject()}"/></td>
	</tr>
	<tr>
		<td><b>내용</b></td><td><c:out value="${boardData.getContent()}"/></td>
	</tr>
	<tr>
		<td>파일첨부</td>
		<td><input type="button"
		onclick="download()" 
		value="${boardData.getFilename()}"/></td>
	</tr>
</table>

<table cellspacing="0" cellpadding="0" border="0" width="500">
	<tr>
	<td>
		<input type="button" value="수정" onclick="javascript:boardmodify()">
		<input type="button" value="목록" onclick="javascript:boardlist()">
		<input type="button" value="답글" onclick="javascript:boardrewrite()">
		<input type="button" value="삭제" onclick="javascript:boarddelete()">
	</td>
	</tr>

</table>
</div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
</html>