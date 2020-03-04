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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>������ ���� �۾���</title>
<script type="text/javascript">
// �ۼ� ���
function boardlist(){
	location.href='newsList.do?current_page=1';
}
</script>

</head>


<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
	<jsp:include page="../header.jsp"></jsp:include>
	
<!-- *********************** ���̵� �޴� ****************************  -->	
	<div id="body_div">
	<div id="side_menu">
		<h4><a href="newsList.do?current_page=1">������ ����</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="financeCompanyList.do?current_page=1"> ����ȸ������ </a></li>
				<li>- <a href="relatedNewsList.do?current_page=1"> ���ô��� </a></li>
			</ul>
		</div>
	</div>
	
<!-- *********************** ���� ****************************  -->
<div id="line_div">
	<div id="sub_logo">
		<h2>������ ����</h2>
    </div> 
 <div id="site_div">
<c:url var="insertUrl" value="/news_write_form.do" />	
<sf:form modelAttribute="boardBean" commandName="boardBean" enctype="multipart/form-data" 
id="form" method="POST" action="${insertUrl}" >

	<sf:hidden path="ref" value="${ref}"/>
	<sf:hidden path="step" value="${step}"/>
	<sf:hidden path="depth" value="${depth}"/>
	<sf:hidden path="re_idx" value="${re_idx}"/>
	<sf:hidden path="idx" value="${idx_num}"/>

	<table class="tlb_board">
		<tr>
			<td style="background-color: #f2f2f2"><b>����</b></td>
			<td style="float: left; margin-left: 10px;">
			<c:if test="${re_idx==0}">
			<sf:input path="subject" size="50" maxlength="50" id="subject" value="[������ ����]"/><br />
			</c:if> 
			<c:if test="${re_idx==1}">
			<sf:input path="subject" size="50" maxlength="50" id="subject" value="${subject }" />
			</c:if><br />
			<sf:errors path="subject" cssClass="error" /></td>
		</tr>
		<tr>
		<th style="background-color: #f2f2f2"><b>�ƾƵ�</b></th>
		 <td style="float: left; margin-left: 10px;">
		 <sf:input path="id" value="${id}" size="50" cssStyle="width:450px;"/></td>
		</tr>
			<tr>
			<td style="background-color: #f2f2f2"><b>ī�װ�</b></td>
			<td style="float: left; margin-left: 10px;">
				<c:if test="${re_idx==0}"> <!-- ������ �� -->
					<sf:select path="news_case">
					<sf:option value="����ȸ������" label="����ȸ������"/>
					<sf:option value="���ô���" label="���ô���"/>
					</sf:select>
				</c:if>
			<c:if test="${re_idx==1}"> <!-- ����� �� -->
					<c:out value="[${news_case}]"></c:out>
				</c:if>
			
			</td>
		</tr>
		<tr>
			<td style="background-color: #f2f2f2"><b>����</b></td>
			<td><sf:textarea path="content" size="200" id="content"
					Style="width:550px;height:250px; margin-left: 10px;" maxlength="200" /><br /> 
				<sf:errors path="content" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td style="background-color: #f2f2f2"><b>����</b></td>
			<td style="float: left; margin-left: 10px;">
			<input type="file" name="file"></td>
		</tr>
		</table>
		<div class="div_board_bnt">
			<input type="submit" value="���" id="save" class="bnt_view"/>
			<input type="button" value="���" onclick="javascript:boardlist()" class="bnt_view"/>
		</div>
</sf:form>
</div>
</div>
</div>
	
	
<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
	
</html>