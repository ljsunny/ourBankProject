<%@page import="com.ourbank.app.PageNumberingManager"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  

<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<!-- <script src="https://kit.fontawesome.com/9bbe6ae1b4.js" crossorigin="anonymous"></script> -->
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<meta charset="EUC-KR">
<title>예금상품 리스트</title>
<script type="text/javascript">
function go_to_site(){
	alert(site);
	location.href=site;
}
function paging(page){
	location.href="depositList.do?current_page="+page;
}
function pre_paging(page){
	var pre_page=page-1;
	location.href="depositList.do?current_page="+pre_page;
}
function after_paging(page){
	var after_page=page+1;
	location.href="depositList.do?current_page="+after_page;
}
function chageLangSelect(){
	var bankSelect = document.getElementById("pro_bank");
    // select element에서 선택된 option의 value가 저장된다.
    var selectValue = bankSelect.options[bankSelect.selectedIndex].value;
    //인코딩함
   	var en_bank= encodeURIComponent(selectValue);
   	
    location.href="depositByBank.do?current_page="+${current_page}+"&bank="+en_bank;
    
}

function searchProduct(){
	var searchStr=document.getElementById("searchStr");
	var selectValue = searchStr.value;
	var en_search= encodeURIComponent(selectValue);
	location.href="depositSearch.do?current_page="+${current_page}+"&searchStr="+en_search;
}

function myWant(count){

	var pc="prdt_cd"+count;
	var prdt_cd=document.getElementById(pc).value;
	
	location.href='myWant.do?current_page=1&dep_or_sav=0&fin_prdt_cd='+prdt_cd;
}
</script>
</head>
<body>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">상품소개</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="depositList.do?current_page=1">예 금</a></li>
				<li>- <a href="savingList.do?current_page=1"> 적 금</a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<div id="site_div">
<div id="product_list">
<table id="product_search" >

	<tr>
		<!-- 은행 select  -->
		<td><select id="pro_bank" name="bank" onchange="chageLangSelect()">
			
			<option label="${bank_text}" value="${bank_text}" 
					selected="selected" style="color: #fcfcfc">
			<c:forEach var="all_bank" items="${all_bank}">	
				<option label="${all_bank.getKor_co_nm()}"
						value="${all_bank.getKor_co_nm()}"></option>
			</c:forEach>
			</select></td>
	<!-- 검색폼 -->
	
	<td>
		<input type="text" id="searchStr" name="searchStr"  placeholder="검색어를 입력하세요">
	</td>
	<td>
		<input  type="button" value="찾기" width="100px" onclick="searchProduct();">
	</td>
	</tr>
	
</table>
<hr width="65%">
<!-- 상품 리스트 출력 -->
	<table align="center" id="product_table" width="60%" >
		<c:forEach var="boardList" items="${boardList}" varStatus="status">
	

			<tr >
				<td><a id="product_name" 
				href="depositContent.do?current_page=${current_page}&fin_prdt_cd=${boardList.getFin_prdt_cd()}">
				${boardList.getFin_prdt_nm()}
				</a></td>
				<td>${boardList.getKor_co_nm()}</td>
				<td>금리 ${boardList.getIntr_rate()}%</td>
				<td>우대금리 ${boardList.getIntr_rate2()}%</td>
				<td>${boardList.getIntr_rate_type_nm()}</td>
				<td>( ${boardList.getSave_trm()} 개월)</td>
				<c:set var="prdt_cd" value="prdt_cd"/>
				<input id="${prdt_cd}${status.count}" type="hidden" value="${boardList.getFin_prdt_cd()}">
				<td><span onclick="myWant(${status.count})">
					<img src="${pageContext.request.contextPath}/resources/images/cart.png" 
					width="30" height="30" ></span>&nbsp;&nbsp;&nbsp;</td>
				<td><button>
					<a href="${boardList.getHomp_url()}" target="blank">가입하기</a>
					
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr width="65%">
	<!-- 페이징 -->
	<c:set var="total_cnt" value="${totalCnt}"></c:set>
	<c:set var="total_page" value="${total_page}"></c:set>
		<%
			int rowsPerPage = 10;
			int total_cnt = ((Integer) (pageContext.getAttribute("total_cnt"))).intValue();

			int total_pages = ((Integer) (pageContext.getAttribute("total_page"))).intValue();;
			pageContext.setAttribute("t_pages", total_pages);
		%>

<div style="margin-top: 10px; font-weight: bold;">
<table align="center" cellspacing="1" width="700" class="page">
	<tr>

		<td align="center"><c:forEach var="i" begin="1" end="${t_pages}">
				<a href="depositList.do?current_page=${i}"> <button>
					<c:if test="${i==c_page}">
						<b>
					</c:if> 
					${i} 
					<c:if test="${i==c_page}">
						<b>
					</c:if> </button>
				</a>
			</c:forEach></td>
	</tr>
</table>

</div>

	<hr width="65%">
	</div></div>
	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>