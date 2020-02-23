<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<script src="https://kit.fontawesome.com/9bbe6ae1b4.js" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>�ƿ���ũ::HOME</title>
<script type="text/javascript">

</script>
</head>
<body>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="header.jsp"></jsp:include>
	

<!-- *********************** ���� ****************************  -->

	<div id="home">
	<div class="row1">
		<div class="area1"><h3>����Ʈ �Խù�<a href="best_listSpecificPageWork.do?current_page=1">
		<i class="fas fa-plus-circle"></i></a></h3>
		<div>
		<c:forEach var="bestBean" items="${bestBean}">
		<p>
			<a href="best_viewWork.do?best_idx=${bestBean.getBest_idx()}&current_page=1&category_num=${bestBean.getCategory_num()}">
			-${bestBean.getSubject()} &nbsp;${bestBean.getId()}&nbsp;${bestBean.getCreated_date()}
			</a>
		</p>
		</c:forEach>
		</div>
		</div>
		<div class="area2"><h3>�ֱ� ����<a href="newnoticeList.do?current_page=1">
		<i class="fas fa-plus-circle"></i></a></h3>
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
		<div class="area3"><h3>������ <i class="fas fa-plus-circle"></i></h3>
		
		<iframe width="300" height="200" src="https://www.youtube.com/embed/T2Unam50vEc" 
		frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
		</iframe>
		</div>
		<div class="area4"><h3>�����ܾ�<i class="fas fa-plus-circle"></i></h3>
		<a href="http://fine.fss.or.kr/main/fin_tip/dic/financedic.jsp" target="_blank">
		<div class="book">
		<span>
		<i class="fas fa-book-open fa-9x"  align="center"></i>
		</span>
		</div></a>
		</div>
		<div class="area5"><h3>����<i class="fas fa-plus-circle"></i></h3></div>
	</div>	
	</div>


	<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>