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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>

<script language="javascript">
	function boardlist(){
		location.href='/app/myBoardList.do?current_page=${current_page}';
	}
</script>
<title>글수정</title>
</head>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
<div id="body_div">
	<div id="side_menu">
		<h4><a>My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> 개인정보관리 </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> 내가 작성한 글 </a></li>
				<li>- <a href="myProductList.do"> 가입한 상품 조회 </a></li>
				<li>- <a href="myWantList.do?current_page=1"> 관심상품 </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->

<div id="line_div">
 		<div id="sub_logo">
<h2>내가 쓴 글</h2>	
  </div> 
 <div id="site_div">	

<c:url var="updateUrl" value="/myboard_update.do"/>
<sf:form modelAttribute="boardData" method="POST" action="${updateUrl}" enctype="multipart/form-data" id="form" >
		<table class="tlb_board">
		<input type="hidden" name="board_idx" value="${board_idx}">
		<input type="hidden" name="current_page" value="${current_page}">
		
		<tr>
			<td style="background-color: #f2f2f2"><b>제목</b></td>
			<td style="float: left; margin-left: 10px;">
			<sf:input path="subject" size="50" maxlength="50" /><br /> 
			<sf:errors path="subject" cssClass="error" /></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>아이디</b></td>
			<td style="float: left; margin-left: 10px;">
			<sf:input readonly="true" path="id" 
						  size="50" maxlength="50" />
			<sf:errors path="id" cssClass="error" />			
			</td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>내용</b></td>
			<td><sf:textarea path="content" size="200"
					Style="width:550px;height:250px; margin-left: 10px;" maxlength="200" /><br /> 
				<sf:errors path="content" cssClass="error" /></td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>파일</b></td>
			<td style="float: left; margin-left: 10px;">
			<input type="file" name="file"></td>
		</tr>
	</table>
		<div class="div_board_bnt">
				<input type="submit" value="재등록" id="save" class="bnt_view"/>
				<input type="button" value="목록" onclick="javascript:boardlist()" class="bnt_view"/>
		</div>
</sf:form>
</div>
</div>
</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>

</html>