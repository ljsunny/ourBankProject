<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ourbank.app.PageNumberingManager"%>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<script src="https://kit.fontawesome.com/9bbe6ae1b4.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>아워뱅크::장바구니</title>
<script type="text/javascript">
function wantdelete(count){
	
	var id=document.getElementById("uid").value;
	
	var pc="prdt_cd"+count;
	var fin_co_cd=document.getElementById(pc).value;
	if(confirm('정말 삭제하시겠습니까?')==true){
		location.href='deleteMyWant.do?id='+id+'&fin_prdt_cd='+fin_co_cd;
	}else {
		return;
	}
}
</script>
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

 <div id="site_div">	
<div id="my_list_wrap">
<div style="margin-top: 50px; font-weight: bold;">
<h1>${uid}님의 장바구니
<button class="mybtn2">장바구니 비우기</button>
</h1>

<table cellspacing=1 width=700>
	<tr>
		<td>관심있는 상품 수: <c:out value="${totalCnt}" /></td>
		<td><p align="right">
				페이지:
				<c:out value="${current_page}" />
		</td>
	</tr>

</table>
</div>

<table cellspacing=1 width=700 >
	<thead>
	<tr>
		<td width="100" class="tlb_board_top">예금/적금</td>
		<td width="320" class="tlb_board_top">상품명</td>
		<td width="200" class="tlb_board_top">은행</td>
		<td width="100" class="tlb_board_top">삭제</td>
		
	</tr> </thead>
	<tbody>
	<c:set var="count" value="0" />
	<c:set var="total_cnt" value="${totalCnt}"/>
	<c:forEach var="board" items="${boardList}" varStatus="status">
	<c:set var="count" value="${count+1}"/>
		<tr class="tlb_board_bottom">
			<td width="100">
				${board.getDep_or_sav()} 
			</td>
			<td width="320">
			<c:if test="${board.getDep_or_sav() eq '예금'}">
			 <a href="depositContent.do?current_page=1&fin_prdt_cd=${board.getFin_prdt_cd()}">
			</c:if>
			<c:if test="${board.getDep_or_sav() eq '적금'}">
			 <a href="savingContent.do?current_page=1&fin_prdt_cd=${board.getFin_prdt_cd()}">
			</c:if>
			<b>
				${board.getFin_prdt_nm()}
			</b>
			</a>
			</td>
			<td width="100">
				${board.getKor_co_nm()}
			</td>
			<td width="100">
			<input id="uid" type="hidden" value="${board.getId()}">
			<c:set var="prdt_cd" value="prdt_cd" />
			<input id="${prdt_cd}${status.count}" type="hidden" value="${board.getFin_prdt_cd()}">
			
			
			
			<span class="mi_back" onclick="wantdelete(${status.count})">
			<i class="fas fa-minus-square"></i>
			</span>'
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
<table cellspacing="1" width="700" class="page">
	<tr>

		<td><c:forEach var="i" begin="1" end="${t_pages}">
				<a href="myProductList.do?current_page=${i}"> [ <c:if
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
</div>
</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</html>