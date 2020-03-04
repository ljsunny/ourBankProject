<%@ page language="java" contentType="text/html; charset=EUC-KR" import="java.util.*,java.sql.*,javax.servlet.http.*"
    pageEncoding="EUC-KR"%>
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- css ����
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css">
  -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >

<meta charset="EUC-KR">
<title>header</title>
</head>
<body>

<!--�˻� ��ȿ���˻� -->
<script language=javascript>
	function send(theform) {
		if(theform.search.value=="") {
			alert("�˻�� �Է��ϼ���");
			theform.search.focus();
			return false;
		}
		theform.submit();
	}
</script>
<%
String uid=(String)session.getAttribute("uid");

%>
<div id="logo">
	<h1><a href="index.do" class="icon icon-group"><span>OurBank</span></a></h1>
	
	<div id="search">
	 <ul class="member">
				<c:if test="${uid==null}">
					<li><a href="loginForm.do" class="icon icon-login"><span>�α���</span></a></li>
					<li><a href="signUp.do" class="icon icon-join"><span>ȸ������</span></a></li>
				</c:if>

				<c:if test="${uid !=null}">
					<i class="icon icon-join "></i>
				<span>	<c:out value="${uid}"></c:out>���� �湮�� ȯ���մϴ�.</span>
					<a href="logOut.do">�α׾ƿ�</a>
				 </c:if>
			</ul>
	 <ul class="contact">
			 <li><form action="indexSearch.do" method=post name="sform">
		 	   <input type=text name=searchStr size=25>
			   <input type=submit value="�� ��" class="bnt_search" onclick="send(this.form);">
			 </form></li>
			 <li><a href="https://www.facebook.com/" class="icon icon-facebook"><span>Facebook</span></a></li>
			 <li><a href="https://www.instagram.com/?hl=ko" class="icon icon-Instagram"><span>Instagram</span></a></li>
			 <li><a href="https://twitter.com/?lang=ko" class="icon icon-twitter"><span>Twitter</span></a></li>
	 </ul>
	</div>
</div>

<!------------------------------ �޴��� --------------------------->
<div id="header">
	<div id="menu" class="container">
		<ul>
			<li><a href="introduction_show.do?current_page=1" accesskey="1" title="">��������</a>
			  <div class="sub_menu"> 
			    <ul>
	              <li><a href="introduction_show.do?current_page=1">����Ʈ�Ұ�</a></li>
	              <li><a href="newnoticeList.do?current_page=1">�� �ҽ�</a></li>
	            </ul>
	          </div> 
	        </li>
			<li><a href="depositList.do?current_page=1" accesskey="2" title="">��ǰ�Ұ�</a>
			  <div class="sub_menu"> 
			    <ul>
	              <li><a href="depositList.do?current_page=1">����</a></li>
	              <li><a href="savingList.do?current_page=1">����</a></li>
	            </ul>
	          </div>
	        </li>
			<li><a href="reviewList.do?current_page=1" accesskey="3" title="">Ŀ�´�Ƽ</a>
			  <div class="sub_menu"> 
				<ul id="side_submenu">
				 <li>- <a href="reviewList.do?current_page=1"> �� ��</a></li>
				 <li>- <a href="/app/freeList.do?current_page=1"> �����Խ���</a></li>
				 <li>- <a href="/app/meetingList.do?current_page=1"> ���ӹ�</a></li>
				 <li>- <a href="/app/debateList.do?current_page=1"> ��й�</a></li>
				 <li>- <a href="investList.do?current_page=1"> ����ũ���Ͽ�</a></li>
				 <li>- <a href="bestList.do?current_page=1"> BEST�Խù�</a></li>
				</ul>
	          </div>
	        </li>
			<li><a href="faqList.do?current_page=1" accesskey="4" title="">������</a>
			  <div class="sub_menu"> 
			    <ul>
	              <li><a href="faqList.do?current_page=1">�����ϴ� ����</a></li>
	              <li><a href="qnaList.do?current_page=1">QnA</a></li>
	              <li><a href="email.do">Contact-email</a></li>
	            </ul>
	          </div>
	        </li>
			<li><a href="/app/domestic_site_show.do" accesskey="5" title="">���û���Ʈ</a>
			  <div class="sub_menu"> 
			    <ul>
	              <li><a href="/app/domestic_site_show.do">�������û���Ʈ</a></li>
	              <li><a href="/app/overseas_site_show.do">�ؿܰ��û���Ʈ</a></li>
	            </ul>
	          </div>
			</li>
			<li><a href="myPage.do" accesskey="5" title="">MyPage</a>
		  	  <div class="sub_menu"> 
			    <ul>
	              <li><a href="myInfo.do">������������</a></li>
	              <li><a href="myBoardList.do?current_page=1">���� �ۼ��� ��</a></li>
	              <li><a href="myProductList.do">���Ի�ǰ</a></li>
	              <li><a href="myWantList.do?current_page=1">���ɻ�ǰ</a></li>
	            </ul>
	          </div>
			</li>
			<li><a href="newsList.do?current_page=1" accesskey="5" title="">����������</a>
			  <div class="sub_menu"> 
			    <ul>
	              <li><a href="financeCompanyList.do?current_page=1">����ȸ������</a></li>
	              <li><a href="relatedNewsList.do?current_page=1">���ô���</a></li>
	            </ul>
	          </div>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
