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
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>

<script language="javascript">

function boardlist(){
	location.href='newsList.do?current_page=${current_page}';
}
</script>
<title>뉴스와 정보 수정</title>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="newsList.do?current_page=1">뉴스와 정보</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="financeCompanyList.do?current_page=1"> 금융회사정보 </a></li>
				<li>- <a href="relatedNewsList.do?current_page=1"> 관련뉴스 </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<div id="line_div">
	<div id="sub_logo">
		<h2>뉴스와 정보</h2>
    </div> 
 <div id="site_div">

<c:url var="updateUrl" value="/news_update.do"/>
<sf:form modelAttribute="boardData" method="POST" action="${updateUrl}" enctype="multipart/form-data">
	<table class="tlb_board">
		<input type="hidden" name="idx" value="${idx}">
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
			<td style="background-color: #f2f2f2"><b>카테고리</b></td>
			<td style="float: left; margin-left: 10px;">
				<c:if test="${boardData.getDepth()==0}"> <!-- 답글이 아닐 때 수정 가능-->
					<sf:select path="news_case">
					<sf:option value="금융회사정보" label="금융회사정보"/>
					<sf:option value="관련뉴스" label="관련뉴스"/>
					</sf:select>
				</c:if>
			<c:if test="${boardData.getDepth()==1}"> <!-- 답글일 때 -->
					[<c:out value="${boardData.getReview_case()}" />]
				
				</c:if>
			
			</td>
		</tr>
		
		<tr>
			<td style="background-color: #f2f2f2"><b>내용</b></td>
			<td><sf:textarea path="content" size="200"
					Style="width:550px;height:250px; margin-left: 10px;" maxlength="200" /><br /> 
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
				<input type="submit" value="재등록" id="save" class="bnt_view"/>
				<input type="button" value="목록" onclick="javascript:boardlist()" class="bnt_view"/>
		</div>
</sf:form>
</div>
</div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>
