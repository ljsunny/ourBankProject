<%@ page language="java" import="java.util.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html> 
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
<title>Email</title>

<script type="text/javascript">
function main() {
	location.href='main.do';
}	

function email() {
	location.href='email.do'
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
				<li>- <a href="email.do"> Contact-Email </a></li>
			</ul>
		</div>
	</div>
<!-- *********************** 내용 ****************************  -->	

<div id="line_div">
<div id="sub_logo">
<h2>Contact-email</h2>
  </div> 
 <div id="site_div">	
<c:url var="insertUrl" value="/email_send.do" />
	<div class="div_complete">
	<p>전송이 완료되었습니다.	</p>
	<div class="div_board_bnt">
				<input type="button" value="메인 화면"  onclick="javascript:main()" class="bnt_view"/>
				<input type="button" value="메일보내기" onclick="javascript:email()" class="bnt_view"/>
		</div>
	</div>	
</div>
</div>
</div>	
<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../../footer.jsp"></jsp:include>

</html>