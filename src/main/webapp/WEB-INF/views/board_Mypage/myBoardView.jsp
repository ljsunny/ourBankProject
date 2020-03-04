<%@page import="java.nio.file.Path"%>
<%@page import="org.springframework.core.io.ClassPathResource"%>
<%@page import="com.ourbank.app.bean.FreeBoard_Bean"%>
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
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<title>글보기</title>
</head>


<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
<div id="body_div">
	<div id="side_menu">
		<h4>My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> 개인정보관리 </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> 내가 작성한 글 </a></li>
				<li>- <a href="myProductList.do"> 가입한 상품 조회 </a></li>
				<li>- <a href="myWantList.do?current_page=1"> 관심상품 </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->	


<!-- model에 저장해 둔거 불러옴 -->
<c:set var="board_idx" value="${board_idx}" />
<c:set var="current_page" value="${current_page}" />
<c:set var="filename" value="${filename}"/>


<%
	String filename=(String) (pageContext.getAttribute("filename"));
%>
<script  language="javascript">
function boardlist(){
		location.href='myBoardList.do?current_page=1';
}


function download(){
	var param="<%=filename%>";
	en_filename=encodeURI(param)
	location.href="free_download.do?filename="+en_filename;

}
</script>

	<div id="line_div">
 	 <div id="sub_logo">
		<h2>내가 쓴 글</h2>	
	</div> 
	<div id="site_div">	
<table  class="tlb_board">
	<tr>
			<td style="background-color: #f2f2f2"><b>제목</b></td>
			<td><c:out value="${boardData.getSubject()}" /></td>
			<td style="background-color: #f2f2f2"><b>조회수</b></td>
			<td><c:out value="${boardData.getHits()}" /></td>
	</tr>
	<tr>
			<td style="background-color: #f2f2f2"><b>아이디</b></td>
			<td><c:out value="${boardData.getId()}" /></td>
			<td style="background-color: #f2f2f2"><b>작성일</b></td>
			<td><c:out value="${boardData.getCreated_date()}"></c:out></td>
	</tr>
	<tr>
		<td style="background-color: #f2f2f2"><b>내용</b></td>
		<td  colspan="3" height="300" style="text-align: left; padding-left: 15px;">
		<c:out value="${boardData.getContent()}"/></td>
		</td>
	</tr>
	<tr>
			<td style="background-color: #f2f2f2"><b>파일첨부</b></td>
			<td colspan="3" style="text-align: left; padding-left: 15px; ">
			<input type="button" onclick="download()"
			value="${boardData.getFilename()}">
			</td>
		</tr>
</table>

<div class="div_board_bnt">
		<input type="button" value="목 록" onclick="javascript:boardlist()" class="bnt_view">
	</div>
</div>
</div>
 </div> 
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</html>