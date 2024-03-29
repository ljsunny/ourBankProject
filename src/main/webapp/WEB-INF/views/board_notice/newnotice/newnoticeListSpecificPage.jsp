<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page session="false"%>
<%@page import="com.ourbank.app.bean.NewnoticeBoard_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="com.ourbank.app.PageNumberingManager"%>

<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>새소식 글목록</title>
<script>
function totallist(){
	location.href='newnoticeList.do?current_page=1';
}
function banknoticelist(){
	location.href='newnoticeBankList.do?current_page=1';
}
function productnoticelist(){
	location.href='newnoticeProductList.do?current_page=1';
}
function etclist(){
	location.href='newnoticeEtcList.do?current_page=1';
}
//리스트 _ 제목링크 누를 시 
function boardView_idCheck() {
	var loginUser = "${id}";
	if(!loginUser) {
		alert('로그인 후 이용 가능합니다.');
		return location.href = "loginForm.do";		
	}else{
	
	}
//검색어
function send(theform) {
		if(theform.search.value=="") {
			alert("검색어를 입력하세요");
			theform.search.focus();
			return false;
		}
		theform.submit();
	}
}

</script>
</head>
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
<div>
<c:set var="current_page" value="${current_page}" />
<c:set var="total_cnt" value="${totalCnt}" />
<%
	int c_page = Integer.parseInt((String) (pageContext.getAttribute("current_page")));
	pageContext.setAttribute("c_page", c_page);
	
	HttpSession session=request.getSession();
	String id=(String) session.getAttribute("id");
%>
<c:set var="id" value="<%=id%>"/>
<div id="line_div">
	<div id="sub_logo">
		<h2>공지사항</h2>
    </div> 
 <div id="site_div">
			<div class="comunity_top_menu" >
				<div>
					<button type="button" onclick="javascript:totallist()" class="bnt_comu">전체</button>
					<button type="button" onclick="javascript:banknoticelist()" class="bnt_comu">은행별공지</button>
					<button type="button" onclick="javascript:productnoticelist()" class="bnt_comu">상품별공지</button>
					<button type="button" onclick="javascript:etclist()" class="bnt_comu">기타공지</button>
				</div>
				<div style="float: right; width:50%; vertical-align: center">
					<form name=searchf method=post action="newnoticeSearch.do">
						<input type="text" name="searchStr" size="30" maxlenght="50">
						<input type="submit" value="글찾기" onclick="send(this.form)" class="bnt_search">
					</form>
				</div>
			</div>
<div style="margin-top: 50px; font-weight: bold;">	
<table cellspacing=1 width=700>
	<tr>
		<td>총 게시물수: <c:out value="${totalCnt}" /></td>
		<td><p align="right">
				페이지:
				<c:out value="${current_page}" />
			</p></td>
	</tr>

</table>
</div>
<table cellspacing=1  >
	<thead>
	<tr>
		<td width="50" class="tlb_board_top">글번호</td>
		<td width="320" class="tlb_board_top">제목</td>
		<td width="100" class="tlb_board_top">아이디</td>
		<td width="100" class="tlb_board_top">등록일</td>
		<td width="100" class="tlb_board_top">조회수</td>
	</tr></thead>
	<tbody>
	<c:forEach var="board" items="${boardList}">
		<tr class="tlb_board_bottom">
			<td  width="50"><p align="center">${board.getIdx()}</p></td>
			<td width="320">
			
			<!-- 로그인 o -->
					<c:if test="${id == null }"> <!--   (사용시 ${id != null }로바꾸기!!-->
					<a href="newnoticeView.do?idx=${board.getIdx()}
							&current_page=<c:out value="${current_page}"/>&searchStr=None"
							title="${board.getContent()}"> <c:out value="${board.getSubject()}" /></a>
							</c:if>
						
					<!-- 로그인 x -->
					<c:if test="${id !=null}"><!--  Id없음 -->
					<a onclick="boardView_idCheck(this.href);return false;" onkeypress="this.onclick;"
						href="newnoticeView.do" > <c:out value="${board.getSubject()}" /></a>
					</c:if>
					
			</td>
			<td width="100">
				<c:out value="${board.getId() }"/>
				</td>
			<td width="100">
					<c:out value="${board.getCreated_date()}" />
				</td>
			<td width="100">
					<c:out value="${board.getHits()}" />
				</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<%
		int rowsPerPage = 10;
		int total_cnt = ((Integer) (pageContext.getAttribute("total_cnt"))).intValue();

		int total_pages = PageNumberingManager.getInstance().getTotalPage(total_cnt, rowsPerPage);
		pageContext.setAttribute("t_pages", total_pages);
	%>
<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing="1" width="700" class="page" align="center">
	<tr>
		<td><c:forEach var="i" begin="1" end="${t_pages}">
				<a href="newnoticeList.do?current_page=${i}"> [ <c:if
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
<!-- 로그인 안 할 시 '글쓰기' 안 됨  (사용시 ${id != null }로바꾸기!! -->
<c:if test="${id==null}">
<div>
<input type="button" value="글쓰기" class="bnt_comu"
			onclick="window.location='newnotice_show_write_form.do'"></td>
</div>
</c:if>
</div>
</div>
</div>	
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
</html>
