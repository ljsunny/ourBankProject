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
		// select element���� ���õ� option�� value�� ����ȴ�.
		var selectValue = bankSelect.options[bankSelect.selectedIndex].value;
		//���ڵ���
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
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- *********************** ���̵� �޴� ****************************  -->

	<div id="side_menu">
		<h4><a href="#">My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> ������������ </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> ���� �ۼ��� �� </a></li>
				<li>- <a href="myProductList.do"> ������ ��ǰ ��ȸ </a></li>
				<li>- <a href="myWantList.do?current_page=1"> ���ɻ�ǰ </a></li>
				<li>- <a href="manageDeposit.do"> ���ݻ�ǰ ���� </a></li>
				<li>- <a href="manageSaving.do"> ���ݻ�ǰ ���� </a></li>
			</ul>
		</div>
	</div>
	

	<!-- *********************** ���� ****************************  -->
	<div id="my_product">
	<!-- ���Ե� ���� ��ǰ ���� -->

		<div id="depo">
			<h2>���Ե� ��ǰ ���� ���</h2>
			
			
			<div>
			<button id="btn1" class="btn_btn" 
			onclick="location.href='myProductDetail.do?ck=0'">����</button>
			<button id="btn2" class="btn_btn" 
			onclick="location.href='myProductDetail.do?ck=1'">����</button>
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
								<!-- �������� ���� -->
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
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>