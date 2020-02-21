<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<title>ID 중복체크</title>

<script type="text/javascript">
function reject(){
	opener.document.getElementById("id").value="";
}
function id_reCheck(){
	var id=document.getElementById("id").value;
	if(id==""){
		alert("아이디를 입력해 주세요");
	}else{
		url="idCheck.do?id="+id;
		location.href=url;
	}
}
function useId(){
	opener.document.getElementById("id").value=document.getElementById("changed_id").value;
	opener.document.getElementById("id_check").value=1;
	self.close();
}
</script>
</head>
<body onload="reject()">
<c:set var="check" value="${check}" />
<c:set var="id" value="${id}"/>

	<div id="idcheck_form" >
	<c:choose>
		<c:when test="${check}">
		<div>
		<div class="border">
			<input id="changed_id" type="hidden" value="${id}">
			<b>${id}</b> 는 사용 가능 합니다.<p>
			<input class="button" type="button" value="ID사용하기" onclick="useId()"><p>
		</div>
			<a href="#" onclick="self.close();">닫기</a>
		</div>
		</c:when>
		<c:otherwise >
		<div >
		<div class="border">
			<b>${id}</b> 는 이미 존재하는 ID 입니다.<p>
			<sf:input id="id" path="id"  maxlength="50" placeholder="아이디를 입력해주세요"></sf:input>
			<input class="button" type="button" value="중복확인" onclick="id_reCheck()" >
		</div>	
		</div>
		</c:otherwise>
	</c:choose>
	<p>
	
</div>
</body>
</html>