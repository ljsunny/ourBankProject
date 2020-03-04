<%@ page language="java" import="java.util.*,java.sql.*,javax.servlet.http.*" 
contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="java.io.*,java.text.*"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ourbank.app.PageNumberingManager"%>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css" >
<script src="https://kit.fontawesome.com/9bbe6ae1b4.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>�ƿ���ũ::��ٱ���</title>
<script type="text/javascript">
function wantdelete(count){
	
	var id=document.getElementById("uid").value;
	
	var pc="prdt_cd"+count;
	var fin_co_cd=document.getElementById(pc).value;
	if(confirm('���� �����Ͻðڽ��ϱ�?')==true){
		location.href='deleteMyWant.do?id='+id+'&fin_prdt_cd='+fin_co_cd;
	}else {
		return;
	}
}
</script>
</head>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** ���̵� �޴� ****************************  -->	
	
<div id="body_div">
	<div id="side_menu">
		<h4>My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> ������������ </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> ���� �ۼ��� �� </a></li>
				<li>- <a href="myProductList.do"> ������ ��ǰ ��ȸ </a></li>
				<li>- <a href="myWantList.do?current_page=1"> ���ɻ�ǰ </a></li>
			</ul>
		</div>
	</div>
<!-- *********************** ���� ****************************  -->

 <div id="site_div">	
<div id="my_list_wrap">
<div style="margin-top: 50px; font-weight: bold;">
<h1>${uid}���� ��ٱ���
<button class="mybtn2">��ٱ��� ����</button>
</h1>

<table cellspacing=1 width=700>
	<tr>
		<td>�����ִ� ��ǰ ��: <c:out value="${totalCnt}" /></td>
		<td><p align="right">
				������:
				<c:out value="${current_page}" />
		</td>
	</tr>

</table>
</div>

<table cellspacing=1 width=700 >
	<thead>
	<tr>
		<td width="100" class="tlb_board_top">����/����</td>
		<td width="320" class="tlb_board_top">��ǰ��</td>
		<td width="200" class="tlb_board_top">����</td>
		<td width="100" class="tlb_board_top">����</td>
		
	</tr> </thead>
	<tbody>
	<c:set var="count" value="0" />
	<c:set var="total_cnt" value="${totalCnt}"/>
	<c:forEach var="board" items="${boardList}" varStatus="status">
	<c:set var="count" value="${count+1}"/>
		<tr class="tlb_board_bottom">
			<td width="100">
				${board.getDep_or_sav()} 
			</td>
			<td width="320">
			<c:if test="${board.getDep_or_sav() eq '����'}">
			 <a href="depositContent.do?current_page=1&fin_prdt_cd=${board.getFin_prdt_cd()}">
			</c:if>
			<c:if test="${board.getDep_or_sav() eq '����'}">
			 <a href="savingContent.do?current_page=1&fin_prdt_cd=${board.getFin_prdt_cd()}">
			</c:if>
			<b>
				${board.getFin_prdt_nm()}
			</b>
			</a>
			</td>
			<td width="100">
				${board.getKor_co_nm()}
			</td>
			<td width="100">
			<input id="uid" type="hidden" value="${board.getId()}">
			<c:set var="prdt_cd" value="prdt_cd" />
			<input id="${prdt_cd}${status.count}" type="hidden" value="${board.getFin_prdt_cd()}">
			
			
			
			<span class="mi_back" onclick="wantdelete(${status.count})">
			<i class="fas fa-minus-square"></i>
			</span>'
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	<%
		int rowsPerPage = 10;
		int total_cnt = ((Integer) (pageContext.getAttribute("total_cnt"))).intValue();

		int total_pages = PageNumberingManager.getInstance().getTotalPage(total_cnt, rowsPerPage);
		pageContext.setAttribute("t_pages", total_pages);
	%>

<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing="1" width="700" class="page">
	<tr>

		<td><c:forEach var="i" begin="1" end="${t_pages}">
				<a href="myProductList.do?current_page=${i}"> [ <c:if
						test="${i==c_page}">
						<b>
					</c:if> ${i} <c:if test="${i==c_page}">
						<b>
					</c:if> ]
				</a>
			</c:forEach></td>
	</tr>
</table>

</div>

</div>
</div>
</div>
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</html>