<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/body.css" >
<meta charset="EUC-KR">
<title>해외사이트</title>
</head>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** 사이드 메뉴 ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">커뮤니티</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="/app/domestic_site_show.do"> 국내사이트</a></li>
				<li>- <a href="/app/overseas_site_show.do"> 해외사이트</a></li>
			</ul>
		</div>
	</div>
<!-- *********************** 내용 ****************************  -->	
<div id="line_div">
 <div id="sub_logo">
 <h2>해외사이트</h2>
 </div> 
 <div id="site_div">
  <h4 id="subtitle" class="icon icon-subtitle">  주요국가금융감독원</h4>	
  <div class="sitelist_area">
  <h5 class="sub_subtitle">* 미국(U.S.A)</h5>
	  <table class="tlb_ovsite" >
	   <colgroup>
	    <col width="30%">
	    <col width="40%">
	    <col width="30%"> 
	   </colgroup>
	   <tbody >
	    <tr class="tr_site">
	     <th>미국 연방준비위원회</th>
	     <td>Board of Governors of the Federal Reserve System (FRB)</td>
	     <td><a href="http://www.federalreserve.gov/">http://www.federalreserve.gov</a></td>
	    </tr>
	    <tr>
	     <th>미국 증권거래위원회</th>
	     <td>United States Securities and Exchange Commission (SEC)</td>
	     <td ><a href="http://www.sec.gov">http://www.sec.gov</a></td>
	    </tr>
	    <tr>
	     <th>미국 뉴욕연방준비은행</th>
	     <td>Federal Reserve Bank of New York (FRBNY)</td>
	     <td ><a href="http://www.ny.frb.org">http://www.ny.frb.org</a></td>
	    </tr>
	    <tr>
	     <th>미국 뉴욕주은행국</th>
	     <td>New York State Department of Financial Services</td>
	     <td ><a href="http://www.dfs.ny.gov">http://www.dfs.ny.gov</a></td>
	    </tr>
	    <tr>
	     <th>미국재무부</th>
	     <td>U.S. Department of the Treasury</td>
	     <td><a href="http://www.treas.gov">http://www.treas.gov</a></td>
	    </tr>
	   </tbody> 
	  </table>
	  
	   <h5 class="sub_subtitle">* 영국</h5>
	  <table class="tlb_ovsite">
	   <colgroup>
	    <col width="30%">
	    <col width="40%">
	    <col width="30%"> 
	   </colgroup>
	   <tbody >
	    <tr class="tr_site">
	     <th>영국 건전성감독청</th>
	     <td>Prudential Regulation Authoriy (PRA)</td>
	     <td ><a href="http://www.bankofengland.co.uk">http://www.bankofengland.co.uk</a></td>
	    </tr>
	    <tr>
	     <th>영국 영업행위감독청</th>
	     <td>Financial Conduct Authority (FCA)</td>
	     <td ><a href="http://www.fca.org.uk/">http://www.fca.org.uk/</a></td>
	    </tr>
	    <tr>
	     <th>영국 금융정책위원회</th>
	     <td>Financial Policy Committee (FPC)</td>
	     <td ><a href="https://www.bankofengland.co.uk">https://www.bankofengland.co.uk</a></td>
	    </tr>
	   </tbody> 
	  </table>
  
     <h5 class="sub_subtitle">* 중국(China)</h5>
  <table class="tlb_ovsite">
   <colgroup>
    <col width="30%">
    <col width="40%">
    <col width="30%"> 
   </colgroup>
   <tbody >
    <tr class="tr_site">
     <th>중국 증권감독관리위원회</th>
     <td>China Securities Regulatory Commission</td>
     <td ><a href="http://www.csrc.gov.cn">http://www.csrc.gov.cn</a></td>
    </tr>
    <tr>
     <th>중국 인민은행</th>
     <td>People's Bank of China</td>
     <td ><a href="http://www.pbc.gov.cn"></a>http://www.pbc.gov.cn</td>
    </tr>
    <tr>
     <th>중국 은행보험관리감독위원회</th>
     <td>China Banking and Insurance Regulatory Commission(CBIRC)</td>
     <td ><a href="http://www.cbirc.gov.cn">	http://www.cbirc.gov.cn</a></td>
    </tr>
   </tbody> 
  </table>
  
  <h5 class="sub_subtitle">* 호주(Australia)</h5>
  <table class="tlb_ovsite">
   <colgroup>
    <col width="30%">
    <col width="40%">
    <col width="30%"> 
   </colgroup>
   <tbody >
    <tr class="tr_site">
     <th>호주건전성감독청</th>
     <td>Australian Prudential Regulation Authority</td>
     <td ><a href="http://www.apra.gov.au">http://www.apra.gov.au</a></td>
    </tr>
    <tr>
     <th>호주 증권투자위원회</th>
     <td>Australian Securities and Investments Commission</td>
     <td ><a href="http://www.asic.gov.au">http://www.asic.gov.au</a></td>
    </tr>
    <tr>
     <th>호주 중앙은행</th>
     <td>	Reserve Bank of Australia</td>
     <td ><a href="http://www.rba.gov.au">http://www.rba.gov.au</a></td>
    </tr>
   </tbody> 
  </table>
  </div>
  
  <h4 id="subtitle" class="icon icon-subtitle">  주요 국제 금융감독 기구</h4>	
   <div class="sitelist_area">
	   <table class="tlb_ovsite" >
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="35%">
					<col width="30%">
				</colgroup>
				<thead>
				<tr class="tbLinet">
				  <th rowspan="2" class="first" scope="col">권역</th>
				  <th colspan="2" scope="col">국제기구</th>
				  <th scope="col" rowspan="2">웹페이지</th>
				  </tr>
				<tr>
				  <th scope="col">국문 명칭</th>
				  <th scope="col">영문 명칭</th>
				  </tr>
				</thead>
				<tbody>
				<tr>
				  <th rowspan="2" class="first" scope="row">공통</th>
					<td class="left bt">금융안정위원회</td>
					<td class="left">FSB(Financial Stability Board)</td>
					<td class="left"><a href="http://www.financialstabilityboard.org" target="_blank" title="새창열림">http://www.financialstabilityboard.org</a></td>
				</tr>
				<tr>
				  <td class="left bt">국제통화기금</td>
				  <td class="left">IMF(International Monetary Fund)</td>
				  <td class="left"><a href="http://www.imf.org" target="_blank" title="새창열림">http://www.imf.org </a></td>
				  </tr>
				<tr>
				  <th rowspan="2" class="first bt" scope="row">은행</th>
					<td class="left bt"><p>바젤은행감독위원회</p></td>
					<td class="left">BCBS(Basel Committee on Banking Supervision)</td>
					<td class="left"><a href="http://www.bis.org/bcbs" target="_blank" title="새창열림">http://www.bis.org/bcbs</a></td>
				</tr>
				<tr>
				  <td class="left bt"><p>국제결제은행</p></td>
					<td class="left"> BIS(Bank for International Settlements)</td>
					<td class="left"><a href="http://www.bis.org" target="_blank" title="새창열림">http://www.bis.org/</a></td>
				</tr>
				<tr>
				  <th scope="row" class="first bt">증권</th>
					<td class="left bt">국제증권감독기구</td>
					<td class="left">IOSCO(International Organization of Securities Commissions)</td>
					<td class="left"><a href="http://www.iosco.org" target="_blank" title="새창열림">http://www.iosco.org </a></td>
				</tr>
				<tr>
				  <th scope="row" class="first bt">회계</th>
					<td class="left bt"><p>국제회계감독기구</p></td>
					<td class="left">IFIAR(International Forum of Independent Audit Regulators)</td>
					<td class="left"><a href="http://www.ifiar.org" target="_blank" title="새창열림">http://www.ifiar.org</a></td>
				</tr>
				
				</tbody>
				</table>
   </div>
 </div>
 </div>
</div>

<!-- *********************** 게시판 글쓰기 폼 ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</html>