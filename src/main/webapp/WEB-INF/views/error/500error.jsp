<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css">
<meta charset="EUC-KR">
<title>글 작성하기</title>
</head>
<body>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="index.do">홈으로 돌아가시오</a></h4>
		
	</div>
	
<!-- *********************** 내용 ****************************  -->	
	
	<div id="error">
		<p>500 ERROR</p>
	</div>
	
	
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>