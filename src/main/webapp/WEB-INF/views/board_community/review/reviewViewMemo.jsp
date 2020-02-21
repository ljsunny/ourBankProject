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
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<title>review</title>
<%
	String searchString=(String) (pageContext.getAttribute("searchString"));
	String filename=(String) (pageContext.getAttribute("filename"));
%>
<script  language="javascript">
function boardlist(){
	var s="<%=searchString%>";
	if(s=="None")
		location.href='reviewList.do?current_page=${current_page}';
	else
		location.href='listSearchedSpecificPageWork.do?pageForView=${current_page}&searchStr=${searchStr}';	
}

function boardmodify(){
	location.href='review_show_update_form.do?idx=${idx}&current_page=${current_page}';
}

function boarddelete(){
	location.href='reviewDeleteSpecificRow.do?idx=${idx}&current_page=${current_page}';	
}

function boardrewrite(){
	location.href='review_show_rewrite_form.do?idx=${idx}&current_page=${current_page}';
}

function download(){
	var param="<%=filename%>";
	en_filename=encodeURI(param);
	location.href='review_download.do?filename='+en_filename;
}

</script>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	   <div id="body_div">
	<div id="side_menu">
		<h4><a href="/app/reviewList.do?current_page=1">커뮤니티</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="/app/reviewList.do?current_page=1"> 리 뷰</a></li>
				<li>- <a href="/app/freeList.do?current_page=1"> 자유게시판</a></li>
				<li>- <a href="/app/meetingList.do?current_page=1"> 모임방</a></li>
				<li>- <a href="/app/debateList.do?current_page=1"> 토론방</a></li>
				<li>- <a href="/app/investList.do?current_page=1"> 제태크노하우</a></li>
				<li>- <a href="/app/bestList.do?current_page=1"> BEST게시물</a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->	
<div id="line_div">
	<div id="sub_logo">
		<h2>리뷰 게시판</h2>
    </div> 
 <div id="site_div">	
<table cellspacing="0" cellpadding="5" border="1" width="600"  >
		<tr>
			<td><b>제목</b></td>
			<td><c:out value="${boardData.getSubject()}" /></td>
			<td><b>조회수</b></td>
			<td><c:out value="${boardData.getHits()}" /></td>
		</tr>
		<tr>
			<td><b>아이디</b></td>
			<td><c:out value="${boardData.getId()}" /></td>
			<td><b>작성일</b></td>
			<td><c:out value="${boardData.getCreated_date()}"></c:out></td>
		</tr>
		<tr>
			<td><b>은행명</b></td>
			<td><c:out value="${boardData.getBanks()}" /></td>
			<td><b>상품명</b></td>
			<td><c:out value="${boardData.getRe_productname()}"></c:out></td>
			
		</tr>
		<c:if test="${boardData.getDepth()==0}"> <!-- 원글이면 -->
		<tr>
			<td><b>만족도</b></td>
			<td><c:out value="${boardData.getSatisfaction() }" /></td>
		</tr>
		</c:if>
		
		<tr>
			<td><b>내용</b></td>
			<td  colspan="3" height="400" >
			<c:if test="${boardData.getDepth()==0}">
			<c:out value="[${boardData.getReview_case()}]"/><br><br>
			</c:if>
			<c:if test="${boardData.getDepth()!=0}">
			<c:out value="[${boardData.getReview_case()}_답글]"/><br><br>
			<c:out value="${boardData.getContent()}" /></td>
			</c:if>
		</tr>
		<tr>
			<td><b>파일첨부</b></td>
			<td colspan="3">
			<input type="button" onclick="download()"
			value="${boardData.getFilename()}">
			</td>
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
</div>	


<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>