<%@ page language="java"
	import="java.util.*,java.sql.*,javax.servlet.http.*"
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<html>
<head>
<!-- JQouery -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900"
	rel="stylesheet" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product.css">


<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Page</title>
<script type="text/javascript">
	function chageLangSelect() {
		var bankSelect = document.getElementById("pro_bank");
		// select element에서 선택된 option의 value가 저장된다.
		var selectValue = bankSelect.options[bankSelect.selectedIndex].value;
		//인코딩함
		var en_bank = encodeURIComponent(selectValue);

		location.href = "depositByBank.do?current_page=" + $
		{
			current_page
		}
		+"&bank=" + en_bank;

	}
	function show_depo() {

		document.getElementById("depo").style.display = "block";
		document.getElementById("sav").style.display = "none";
	}
	function show_sav() {
		document.getElementById("sav").style.display = "block";
		document.getElementById("depo").style.display = "none";
	}
	$(function() {
		$( "#accordion" ).accordion({

			  active: true,

			  collapsible: true

			  });

	});
	jQuery( document ).ready( function() {
        $( 'h3' ).width( '750px' );
        $( '.div_dp' ).width( '700px' );
      } );
	

</script>
</head>
<body>
	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- *********************** 사이드 메뉴 ****************************  -->

	<div id="side_menu">
		<h4><a href="#">My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> 개인정보관리 </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> 내가 작성한 글 </a></li>
				<li>- <a href="myProductList.do"> 가입한 상품 조회 </a></li>
				<li>- <a href="myWantList.do?current_page=1"> 관심상품 </a></li>
				<li>- <a href="manageDeposit.do"> 예금상품 관리 </a></li>
				<li>- <a href="manageSaving.do"> 적금상품 관리 </a></li>
			</ul>
		</div>
	</div>
	

	<!-- *********************** 내용 ****************************  -->
	<div id="my_product">
	<!-- 가입된 예금 상품 선택 -->

		<div id="depo">
			<h2>가입된 상품 정보 등록</h2>
			
			
			<div>
			<button id="btn1" class="btn_btn" 
			onclick="location.href='myProductDetail.do?ck=0'">예금</button>
			<button id="btn2" class="btn_btn" 
			onclick="location.href='myProductDetail.do?ck=1'">적금</button>
			<hr>
			<p></p>
			</div>
		<form method="post" action="myProduct.do">
		<input type="hidden" name="ck" value="${ck}">
			<div class="wrap">
				<div id="accordion">
				<c:forEach var="bank_bean" items="${bank_bean}">
						<h3>${bank_bean.getKor_co_nm()}&nbsp;&nbsp;</h3>
						<div class="div_dp">
							<c:forEach var="product_bean" items="${product_bean}">
								<!-- 은행으로 정렬 -->
								<c:set var="bk" value="${bank_bean.getKor_co_nm()}" />
								<c:set var="pd_bk" value="${product_bean.getKor_co_nm()}" />
								<c:if test="${bk==pd_bk}">
								
								<input id="pdt_nm" type="checkbox" name="fin_prdt_cd"
										value="${product_bean.getFin_prdt_cd()}"
										onselect="inject_info()"></input>
										${product_bean.getFin_prdt_nm()}&nbsp;&nbsp;	
								</c:if>
							</c:forEach>
						</div>
				</c:forEach>
				</div>
			</div>
			<input class="btn" type="submit">
			</form>
		</div>
	</div>
	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>