<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>사이트소개</title>


</head>


<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="introduction_show.do?current_page=1">공지사항</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="introduction_show.do?current_page=1"> 사이트소개 </a></li>
				<li>- <a href="newnoticeList.do?current_page=1"> 새소식 </a></li>
				
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<div id="line_div">
 <div id="sub_logo">
 <h2>OurBank 소개</h2>
 </div> 
 <div id="site_div">
 
 <div id="list_div">	
	<img alt="logo" src="${pageContext.request.contextPath}/resources/images/logo.JPG" width="400" height="100" >
   <h4 id="subtitle" class="icon icon-subtitle">  사이트 소개</h4>
    <div class="sitelist_area">
     <h5 class="sub_subtitle">예금/적금 상품을 은행별, 상품별로 조회하고 가입할 수 있는 사이트 입니다</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  설립자</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">권희지, 김효민, 이지선</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  설립일</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">2020.02.01</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  주소</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">서울시 종로구 관철동</h5>
    </div>
    
    <h4 id="subtitle" class="icon icon-subtitle">  수상이력</h4>
    <div class="sitelist_area">
    <ul>
     	 <li>-1<li>
      	 <li>-2<li>
      	 <li>-3<li>
    </ul>
    </div>
    
    
  </div>
  </div>
 </div>


</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</html>