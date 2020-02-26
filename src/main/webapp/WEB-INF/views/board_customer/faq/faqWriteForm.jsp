<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>자주하는 질문</title>
<script type="text/javascript">
// 작성 취소
function boardlist(){
	location.href='faqList.do?current_page=1';
}

</script>


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
 				<li>- <a href="email.do">Contact-email</a></li>
	            
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<c:url var="insertUrl" value="/faq_write_form.do" />	
<sf:form modelAttribute="boardBean" commandName="boardBean" enctype="multipart/form-data" method="POST" action="${insertUrl}" >
	<sf:hidden path="idx" value="${id_x}"/>

<div id="line_div">
	<div id="sub_logo">
		<h2>FAQ</h2>
    </div> 
 <div id="site_div">
	<table class="tlb_board">
		<tr>
			<td style="background-color: #f2f2f2"><b>제목</b></td>
			<td style="float: left; margin-left: 10px;">
			<sf:input path="subject" size="50" maxlength="50"  value="[질문]"/><br /> 
			<sf:errors path="subject" cssClass="error" /></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>카테고리</b></td>
			<td style="float: left; margin-left: 10px;">
			<sf:select path="category">
				<sf:option value="signup" label="회원가입"/>
				<sf:option value="savings" label="예적금"/>
				<sf:option value="etc" label="기타"/>
			</sf:select></td>
		</tr>
		
		<tr>
			<td style="background-color: #f2f2f2"><b>내용</b></td>
			<td style="float: left; margin-left: 10px;">
			<sf:textarea path="content" size="200"
					cssStyle="width:350px;height:100px;" maxlength="200" /><br /> 
				<sf:errors path="content" cssClass="error" /></td>
		</tr>
		<!-- 엔코딩 깨짐 -->
		<tr>
			<td style="background-color: #f2f2f2"><b>파일</b></td>
			<td style="float: left; margin-left: 10px;">
			<input type="file" name="file"></td>
		</tr>
		</table>
		<div class="div_board_bnt">
			<input type="submit" value="등록"  class="bnt_view"/>
			<input type="button" value="취소" onclick="javascript:boardlist()"  class="bnt_view">
		</div>
</sf:form>
</div>
</div>
</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</html>