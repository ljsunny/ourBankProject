<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- model에 저장해 둔거 불러옴 -->

<c:set var="str_aid" value="${idx }" />
<c:set var="str_c_page" value="${current_page}" />
<c:set var="searchString" value="${searchStr}" />
<c:set var="filename" value="${filename}"/>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<title>새소식</title>
<%
	String searchString=(String) (pageContext.getAttribute("searchString"));
	String filename=(String) (pageContext.getAttribute("filename"));
%>
<script  language="javascript">
function boardlist(){
	var s="<%=searchString%>";
	if(s=="None")
		location.href='newnoticeList.do?current_page=${current_page}';
	else
		location.href='listSearchedSpecificPageWork.do?pageForView=${current_page}&searchStr=${searchStr}';	
}

function boardmodify(){
	location.href='newnotice_show_update_form.do?idx=${idx}&current_page=${current_page}';
}

function boarddelete(){
	if(confirm('정말 삭제하시겠습니까?')==true){
		location.href='newnotice_DeleteSpecificRow.do?idx=${idx}&current_page=${current_page}';
	}else {
		return;
	}
}

function boardrewrite(){
	location.href='newnotice_show_rewrite_form.do?idx=${idx}&current_page=${current_page}';
}

function download(){
	var param="<%=filename%>";
	en_filename=encodeURI(param);
	location.href='newnotice_download.do?filename='+en_filename;
}

</script>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="introduction_show.do">공지사항</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="introduction_show.do"> 사이트소개 </a></li>
				<li>- <a href="newnoticeList.do?current_page=1"> 새소식 </a></li>
				
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->	
	
<!-- model에 저장해 둔거 불러옴 -->
<c:set var="idx" value="${idx}" />
<c:set var="current_page" value="${current_page}" />
<c:set var="searchString" value="${searchStr}" />
<c:set var="filename" value="${filename}"/>


<div id="line_div">
 	 <div id="sub_logo">
		<h2>새소식</h2>	
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
			<td style="background-color: #f2f2f2"><b>이름</b></td>
			<td><c:out value="${boardData.getId()}" /></td>
			<td style="background-color: #f2f2f2"><b>작성일</b></td>
			<td><c:out value="${boardData.getCreated_date()}"></c:out></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>내용</b></td>
			<td  colspan="3" height="300" style="text-align: left; padding-left: 15px;">
			<c:out value="${boardData.getContent()}" /></td>
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
		<input type="button" value="수정" onclick="javascript:boardmodify()"class="bnt_view">
		<input type="button" value="목록" onclick="javascript:boardlist()"class="bnt_view">
		<input type="button" value="답글" onclick="javascript:boardanswer()"class="bnt_view">
		<input type="button" value="삭제" onclick="javascript:boarddelete()"class="bnt_view">
</div>
</div>
</div>
</div>
</html>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>