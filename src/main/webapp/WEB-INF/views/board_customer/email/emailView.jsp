<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
messageType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html> 
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="message-Type" message="text/html; charset=EUC-KR">
<title>자유게시판 글쓰기</title>

<script type="text/javascript">
 
	 // 작성 취소
	function boardlist(){
		location.href='freeList.do?current_page=1';
	 }
	
</script>


</style>

</head>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
<jsp:include page="../../header.jsp"></jsp:include>
<!-- *********************** 사이드 메뉴 ****************************  -->		 
<div id="body_div">
	<div id="side_menu">
		<h4><a href="#">고객센터</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="faqList.do?current_page=1"> 자주하는 질문 </a></li>
				<li>- <a href="qnaList.do?current_page=1"> QnA </a></li>
				<li>- <a href="email.do"> Contact </a></li>
			</ul>
		</div>
	</div>
<!-- *********************** 내용 ****************************  -->	

<div id="line_div">
<div id="sub_logo">
<h2>자유게시판</h2>
  </div> 
 <div id="site_div">	
<c:url var="insertUrl" value="/email_send.do" />
<sf:form modelAttribute="emailBean" commandName="emailBean" id="form" 
	method="POST" action="${insertUrl}" enctype="multipart/form-data"  >
	

	<table class="tlb_board">
		<tr>
			<td style="background-color: #f2f2f2"><b>제목</b></td>
			<td style="float: left; margin-left: 10px;">
				<sf:input path="subject" size="50" maxlength="50" id="subject"  />
				<sf:errors path="subject" cssClass="error" /></td>
		</tr>
		<tr>
			<th style="background-color: #f2f2f2"><b>아아디</b></th>
			 <td style="float: left; margin-left: 10px;">
			 <sf:input readonly="true" path="id" value="${uid}" size="50" cssStyle="width:450px;"/></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>내용</b></td>
			<td><sf:textarea path="message" size="200" id="message"
					Style="width:550px;height:250px; margin-left: 10px;" maxlength="200" /><br /> 
				<sf:errors path="message" cssClass="error" /></td>
		</tr>
		<!--  <tr>
			<td style="background-color: #f2f2f2"><b>메일 형태</b></td>
			<td style="float: left; margin-left: 10px;">
				<sf:radiobutton path="mailtype" value="text" size="50" maxlength="50" id="mailtype" />text형태 &nbsp;
				<sf:radiobutton path="mailtype" value="html" size="50" maxlength="50" id="mailtype" checked />HTML형태
				<sf:errors path="mailtypet" cssClass="error" /></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>파일</b></td>
			<td style="float: left; margin-left: 10px;">
			<input type="file" name="file"></td>
		</tr>-->
	</table>
	<div class="div_board_bnt">
				<input type="submit" value="메일전송" id="save" class="bnt_view"/>
				<input type="reset" value="다시작성" class="bnt_view"/>
		</div>
</sf:form>
</div>
</div>
</div>	
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>

</html>