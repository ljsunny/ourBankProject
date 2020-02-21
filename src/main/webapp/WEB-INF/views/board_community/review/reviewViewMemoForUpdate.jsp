<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<script language="javascript">
	function writeCheck(){
		var form=document.modifyform;
		if(!form.dbsubject.value){
			alert("제목을 적어주세요.")
			form.dbsubject.focus();
			return;
		}
		if(!form.dbmemo.value){
			alert("내용을 적어주세요.")
			form.dbmemo.focus();
			return;
		}
		form.submit();
	}
	function boardlist(){
		location.href='reviewList.do?current_page=${current_page}';
		
	}
</script>
<title>review</title>
</head>
<body>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
	<jsp:include page="../../header.jsp"></jsp:include>
	
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	   <div id="body_div">
	<div id="side_menu">
		<h4><a href="/app/reviewList.do?current_page=1">커뮤니티</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="/app/reviewList.do?current_page=1"> 리 뷰</a></li>
				<li>- <a href="/app/freeList.do?current_page=1"> 자유게시판</a></li>
				<li>- <a href="/app/meetingList.do?current_page=1"> 모임방</a></li>
				<li>- <a href="/app/debateList.do?current_page=1"> 토론방</a></li>
				<li>- <a href="/app/investList.do?current_page=1"> 제태크노하우</a></li>
				<li>- <a href="/app/bestList.do?current_page=1"> BEST게시물</a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** 내용 ****************************  -->
<div id="line_div">
	<div id="sub_logo">
		<h2>리뷰 게시판</h2>
    </div> 
 <div id="site_div">
<c:url var="updateUrl" value="/review_update.do"/>
<sf:form modelAttribute="boardData" method="POST" action="${updateUrl}" enctype="multipart/form-data">
	<table width="400" border="1" cellspacing="0" cellpadding="5">
		<input type="hidden" name="idx" value="${idx}">
		<input type="hidden" name="current_page" value="${current_page}">
		<tr>
			<td><b>제목</b></td>
			<td><sf:input path="subject" size="50" maxlength="50" /><br /> 
			<sf:errors path="subject" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><b>카테고리</b></td>
			<td><c:if test="${boardData.getDepth()==0}"> <!-- 답글이 아닐 때 수정 가능-->
				<sf:select path="review_case">
				<sf:option value="예금리뷰" label="예금리뷰"/>
				<sf:option value="적금리뷰" label="적금리뷰"/>
				<sf:option value="기타리뷰" label="기타리뷰"/>
				</sf:select>
				</c:if>
				<c:if test="${boardData.getDepth()==1}"> <!-- 답글일 때 -->
					[<c:out value="${boardData.getReview_case()}" />]
					<sf:hidden path="reviewcase"/>
				</c:if>
			</td>
		</tr>
		<c:if test="${boardData.getDepth()==0}"> <!-- 답글이 아닐 때 수정 가능-->
		<tr>
			<td><b>은행명</b></td>
			<td><sf:input path="banks" size="30" maxlength="30" value="${banks}"/>
				<sf:errors path="banks" cssClass="error"/></td>
		</tr>
		<tr>
			<td><b>상품명</b></td>
			<td><sf:input path="re_productname" size="30" maxlength="30" value="${re_productname}"/>
				<sf:errors path="re_productname" cssClass="error"/></td>
		</tr>
		<tr>
			<td><b>만족도</b></td>
				<td><sf:select path="satisfaction">
				<sf:option value="★★★★★  매우만족" label="★★★★★   매우만족"/>
				<sf:option value="★★★★  만족" label="★★★★      만족"/>
				<sf:option value="★★★  보통" label="★★★         보통"/>
				<sf:option value="★★  불만족" label="★★		  불만족"/>
				<sf:option value="★  매우불만족" label="★		 매우불만족"/>
			</sf:select></td>
		</tr>
		</c:if>
		
		<tr>
			<td><b>내용</b></td>
			<td><sf:textarea path="content" size="200"
					cssStyle="width:350px;height:100px;" maxlength="200" /><br /> 
				<sf:errors path="content" cssClass="error" /></td>
		</tr>
		<!-- 엔코딩 깨짐 -->
		<tr>
			<td><b>파일</b></td>
			<td><input type="file" name="file"></td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" border="0" width="500">
		<tr>
			<td><input type="submit" value="재등록" />
				<input type="button" value="목록" onclick="javascript:boardlist()"/>
			</td>
		</tr>
	</table>
</sf:form>
</div>
</div>
</div>
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>
	
</body>
</html>
