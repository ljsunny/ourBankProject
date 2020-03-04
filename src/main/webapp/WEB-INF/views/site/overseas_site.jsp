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
<title>�ؿܻ���Ʈ</title>
</head>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	
<jsp:include page="../header.jsp"></jsp:include>
<!-- *********************** ���̵� �޴� ****************************  -->	
	
	<div id="side_menu">
		<h4><a href="#">Ŀ�´�Ƽ</a></h4>
		<div id="side_div">
			<ul id="side_submenu">
				<li>- <a href="/app/domestic_site_show.do"> ��������Ʈ</a></li>
				<li>- <a href="/app/overseas_site_show.do"> �ؿܻ���Ʈ</a></li>
			</ul>
		</div>
	</div>
<!-- *********************** ���� ****************************  -->	
<div id="line_div">
 <div id="sub_logo">
 <h2>�ؿܻ���Ʈ</h2>
 </div> 
 <div id="site_div">
  <h4 id="subtitle" class="icon icon-subtitle">  �ֿ䱹������������</h4>	
  <div class="sitelist_area">
  <h5 class="sub_subtitle">* �̱�(U.S.A)</h5>
	  <table class="tlb_ovsite" >
	   <colgroup>
	    <col width="30%">
	    <col width="40%">
	    <col width="30%"> 
	   </colgroup>
	   <tbody >
	    <tr class="tr_site">
	     <th>�̱� �����غ�����ȸ</th>
	     <td>Board of Governors of the Federal Reserve System (FRB)</td>
	     <td><a href="http://www.federalreserve.gov/">http://www.federalreserve.gov</a></td>
	    </tr>
	    <tr>
	     <th>�̱� ���ǰŷ�����ȸ</th>
	     <td>United States Securities and Exchange Commission (SEC)</td>
	     <td ><a href="http://www.sec.gov">http://www.sec.gov</a></td>
	    </tr>
	    <tr>
	     <th>�̱� ���忬���غ�����</th>
	     <td>Federal Reserve Bank of New York (FRBNY)</td>
	     <td ><a href="http://www.ny.frb.org">http://www.ny.frb.org</a></td>
	    </tr>
	    <tr>
	     <th>�̱� ���������౹</th>
	     <td>New York State Department of Financial Services</td>
	     <td ><a href="http://www.dfs.ny.gov">http://www.dfs.ny.gov</a></td>
	    </tr>
	    <tr>
	     <th>�̱��繫��</th>
	     <td>U.S. Department of the Treasury</td>
	     <td><a href="http://www.treas.gov">http://www.treas.gov</a></td>
	    </tr>
	   </tbody> 
	  </table>
	  
	   <h5 class="sub_subtitle">* ����</h5>
	  <table class="tlb_ovsite">
	   <colgroup>
	    <col width="30%">
	    <col width="40%">
	    <col width="30%"> 
	   </colgroup>
	   <tbody >
	    <tr class="tr_site">
	     <th>���� ����������û</th>
	     <td>Prudential Regulation Authoriy (PRA)</td>
	     <td ><a href="http://www.bankofengland.co.uk">http://www.bankofengland.co.uk</a></td>
	    </tr>
	    <tr>
	     <th>���� ������������û</th>
	     <td>Financial Conduct Authority (FCA)</td>
	     <td ><a href="http://www.fca.org.uk/">http://www.fca.org.uk/</a></td>
	    </tr>
	    <tr>
	     <th>���� ������å����ȸ</th>
	     <td>Financial Policy Committee (FPC)</td>
	     <td ><a href="https://www.bankofengland.co.uk">https://www.bankofengland.co.uk</a></td>
	    </tr>
	   </tbody> 
	  </table>
  
     <h5 class="sub_subtitle">* �߱�(China)</h5>
  <table class="tlb_ovsite">
   <colgroup>
    <col width="30%">
    <col width="40%">
    <col width="30%"> 
   </colgroup>
   <tbody >
    <tr class="tr_site">
     <th>�߱� ���ǰ�����������ȸ</th>
     <td>China Securities Regulatory Commission</td>
     <td ><a href="http://www.csrc.gov.cn">http://www.csrc.gov.cn</a></td>
    </tr>
    <tr>
     <th>�߱� �ι�����</th>
     <td>People's Bank of China</td>
     <td ><a href="http://www.pbc.gov.cn"></a>http://www.pbc.gov.cn</td>
    </tr>
    <tr>
     <th>�߱� ���ຸ�������������ȸ</th>
     <td>China Banking and Insurance Regulatory Commission(CBIRC)</td>
     <td ><a href="http://www.cbirc.gov.cn">	http://www.cbirc.gov.cn</a></td>
    </tr>
   </tbody> 
  </table>
  
  <h5 class="sub_subtitle">* ȣ��(Australia)</h5>
  <table class="tlb_ovsite">
   <colgroup>
    <col width="30%">
    <col width="40%">
    <col width="30%"> 
   </colgroup>
   <tbody >
    <tr class="tr_site">
     <th>ȣ�ְ���������û</th>
     <td>Australian Prudential Regulation Authority</td>
     <td ><a href="http://www.apra.gov.au">http://www.apra.gov.au</a></td>
    </tr>
    <tr>
     <th>ȣ�� ������������ȸ</th>
     <td>Australian Securities and Investments Commission</td>
     <td ><a href="http://www.asic.gov.au">http://www.asic.gov.au</a></td>
    </tr>
    <tr>
     <th>ȣ�� �߾�����</th>
     <td>	Reserve Bank of Australia</td>
     <td ><a href="http://www.rba.gov.au">http://www.rba.gov.au</a></td>
    </tr>
   </tbody> 
  </table>
  </div>
  
  <h4 id="subtitle" class="icon icon-subtitle">  �ֿ� ���� �������� �ⱸ</h4>	
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
				  <th rowspan="2" class="first" scope="col">�ǿ�</th>
				  <th colspan="2" scope="col">�����ⱸ</th>
				  <th scope="col" rowspan="2">��������</th>
				  </tr>
				<tr>
				  <th scope="col">���� ��Ī</th>
				  <th scope="col">���� ��Ī</th>
				  </tr>
				</thead>
				<tbody>
				<tr>
				  <th rowspan="2" class="first" scope="row">����</th>
					<td class="left bt">������������ȸ</td>
					<td class="left">FSB(Financial Stability Board)</td>
					<td class="left"><a href="http://www.financialstabilityboard.org" target="_blank" title="��â����">http://www.financialstabilityboard.org</a></td>
				</tr>
				<tr>
				  <td class="left bt">������ȭ���</td>
				  <td class="left">IMF(International Monetary Fund)</td>
				  <td class="left"><a href="http://www.imf.org" target="_blank" title="��â����">http://www.imf.org </a></td>
				  </tr>
				<tr>
				  <th rowspan="2" class="first bt" scope="row">����</th>
					<td class="left bt"><p>�������న������ȸ</p></td>
					<td class="left">BCBS(Basel Committee on Banking Supervision)</td>
					<td class="left"><a href="http://www.bis.org/bcbs" target="_blank" title="��â����">http://www.bis.org/bcbs</a></td>
				</tr>
				<tr>
				  <td class="left bt"><p>������������</p></td>
					<td class="left"> BIS(Bank for International Settlements)</td>
					<td class="left"><a href="http://www.bis.org" target="_blank" title="��â����">http://www.bis.org/</a></td>
				</tr>
				<tr>
				  <th scope="row" class="first bt">����</th>
					<td class="left bt">�������ǰ����ⱸ</td>
					<td class="left">IOSCO(International Organization of Securities Commissions)</td>
					<td class="left"><a href="http://www.iosco.org" target="_blank" title="��â����">http://www.iosco.org </a></td>
				</tr>
				<tr>
				  <th scope="row" class="first bt">ȸ��</th>
					<td class="left bt"><p>����ȸ�谨���ⱸ</p></td>
					<td class="left">IFIAR(International Forum of Independent Audit Regulators)</td>
					<td class="left"><a href="http://www.ifiar.org" target="_blank" title="��â����">http://www.ifiar.org</a></td>
				</tr>
				
				</tbody>
				</table>
   </div>
 </div>
 </div>
</div>

<!-- *********************** �Խ��� �۾��� �� ****************************  -->	

	<jsp:include page="../footer.jsp"></jsp:include>
</html>