<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
 <!-- <script src="https://kit.fontawesome.com/9bbe6ae1b4.js" crossorigin="anonymous"></script>  -->
<meta charset="EUC-KR">
<title>아워뱅크::HOME</title>
<script type="text/javascript">

</script>
</head>
<body>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="header.jsp"></jsp:include>
	

<!-- *********************** 내용 ****************************  -->

	<div id="home">
	<div class="row1">
		<div class="area1"><h3>베스트 게시물<a href="best_listSpecificPageWork.do?current_page=1">
		<i class="fa fa-plus" aria-hidden="true" style="float:right;"></i></a></h3>
		<div>
		<c:forEach var="bestBean" items="${bestBean}">
		<p>
			<a href="bestView.do?best_idx=${bestBean.getBest_idx()}&current_page=1&category_num=${bestBean.getCategory_num()}">
			-${bestBean.getSubject()} &nbsp;${bestBean.getId()}&nbsp;${bestBean.getCreated_date()}
			</a>
		</p>
		</c:forEach>
		</div>
		</div>
		<div class="area2"><h3>최근 공지<a href="newnoticeList.do?current_page=1">
		<i class="fa fa-plus" aria-hidden="true" style="float:right;"></i></a></h3>
		<c:forEach var="newNoticeBean" items="${newNoticeBean}">
		<p>
			<a href="newnoticeView.do?idx=${newNoticeBean.getIdx()}&current_page=1&searchStr=None">
			-${newNoticeBean.getSubject()} &nbsp;${newNoticeBean.getCreated_date()}
			</a>
		</p>
		</c:forEach>
		</div>
	</div>	
	<div class="row2">
		<div class="area3"><h3>동영상 
		<i class="fa fa-plus" aria-hidden="true" style="float:right;"></i></h3>
		
		<iframe width="300" height="200" src="https://www.youtube.com/embed/T2Unam50vEc" 
		frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
		</iframe>
		</div>
		
		<a href="depositContent.do?current_page=1&fin_prdt_cd=${bestDeposit.getFin_prdt_cd()}">
		<div class="area5"><h3>추천상품<i class="fa fa-plus" aria-hidden="true" style="float:right;"></i></h3>
			<div class="best_product">
			<span>
			<i class="fa fa-credit-card fa-5x" aria-hidden="true"></i>
			</span>
			<div class="info">
			<ul>
				<li>${bestDeposit.getKor_co_nm()}</li>
				<li><h2>${bestDeposit.getFin_prdt_nm()}</h2></li>
			</ul>
			<h1>최대 ${bestDeposit.getIntr_rate2()}%</h1>
			</div>
			</div>
		</div>
		</a>
		<div class="area4"><h3>금융단어<i class="fa fa-plus" aria-hidden="true" style="float:right;"></i></h3>
		<a href="http://fine.fss.or.kr/main/fin_tip/dic/financedic.jsp" target="_blank">
		<div class="book">
		<span>
		<i class="fa fa-book fa-5x" aria-hidden="true"  align="center"></i>
		</span>
		</div></a>
		</div>
	</div>	
	</div>


	<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>