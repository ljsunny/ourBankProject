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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/h_script.js"></script>
<meta http-equiv="Content-Type" content="text/html" ; charset="EUC-KR">
<title>�����Խ��� �۸��</title>
</head>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->   
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** ���̵� �޴� ****************************  -->   
	<div id="side_menu">
		<h4><a href="#">My Page</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="myInfo.do"> ������������ </a></li>
				<li>- <a href="myBoardList.do?current_page=1"> ���� �ۼ��� �� </a></li>
				<li>- <a href="myProductList.do"> ������ ��ǰ ��ȸ </a></li>
				<li>- <a href="myWantList.do?current_page=1"> ���ɻ�ǰ </a></li>
				<li>- <a href="manageDeposit.do"> ���ݻ�ǰ ���� </a></li>
				<li>- <a href="manageSaving.do"> ���ݻ�ǰ ���� </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** ���� ****************************  -->

   <div id="line_div">
 <div id="sub_logo">
<h2>���� �ۼ��� ��</h2>   
  </div> 
 <div id="site_div">      

<div style="margin-top: 50px; font-weight: bold;">
<table cellspacing=1 width=700>
   <tr>
      <td>�� �Խù���: <c:out value="${totalCnt}" /></td>
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
      <td width="50" class="tlb_board_top">�۹�ȣ</td>
      <td width="320" class="tlb_board_top">����</td>
      <td width="100" class="tlb_board_top">���̵�</td>
      <td width="100" class="tlb_board_top">�����</td>
      <td width="100" class="tlb_board_top">��ȸ��</td>
   </tr> </thead>
   <tbody>
   <c:set var="count" value="0" />
   <c:forEach var="board" items="${boardList}">
   <c:set var="count" value="${count+1}"/>
      <tr class="tlb_board_bottom">
         <td width="50">${count}</td>
         <td width="320">
            
               <a href="myBoardView.do?board_idx=${board.getBoard_idx()}&current_page=<c:out value="${current_page}"/>"> 
               <c:out value="${board.getSubject()}" /></a>
               
         </td>
         <td width="100">
            <c:out value="${board.getId()}"/>
            </td>
         <td width="100">
               <c:out value="${board.getCreated_date()}" />
            </td>
         <td width="100">
               <c:out value="${board.getHits()}" />
            </td>
      </tr>
      </c:forEach>
   </tbody>
   </table>
   <c:set var="total_cnt" value="${totalCnt}"/>
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
            <a href="freeList.do?current_page=${i}"> [ <c:if
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
<!-- *********************** �Խ��� �۾��� �� ****************************  -->   

   <jsp:include page="../footer.jsp"></jsp:include>
</html>