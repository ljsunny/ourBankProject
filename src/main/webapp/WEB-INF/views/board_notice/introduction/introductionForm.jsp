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

<title>����Ʈ�Ұ�</title>


</head>


<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="introduction_show.do?current_page=1">��������</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="introduction_show.do?current_page=1"> ����Ʈ�Ұ� </a></li>
				<li>- <a href="newnoticeList.do?current_page=1"> ���ҽ� </a></li>
				
			</ul>
		</div>
	</div>
	
<!-- *********************** ���� ****************************  -->
<div id="line_div">
 <div id="sub_logo">
 <h2>OurBank �Ұ�</h2>
 </div> 
 <div id="site_div">
 
 <div id="list_div">	
	<img alt="logo" src="${pageContext.request.contextPath}/resources/images/logo.JPG" width="400" height="100" >
   <h4 id="subtitle" class="icon icon-subtitle">  ����Ʈ �Ұ�</h4>
    <div class="sitelist_area">
     <h5 class="sub_subtitle">����/���� ��ǰ�� ���ະ, ��ǰ���� ��ȸ�ϰ� ������ �� �ִ� ����Ʈ �Դϴ�</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  ������</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">������, ��ȿ��, ������</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  ������</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">2020.02.01</h5>
     </div>
     <h4 id="subtitle" class="icon icon-subtitle">  �ּ�</h4>
     <div class="sitelist_area">
     <h5 class="sub_subtitle">����� ���α� ��ö��</h5>
    </div>
    
    <h4 id="subtitle" class="icon icon-subtitle">  �����̷�</h4>
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
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</html>