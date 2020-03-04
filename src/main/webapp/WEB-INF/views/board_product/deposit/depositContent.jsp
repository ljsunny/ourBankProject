<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">  
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fonts.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product.css">
<meta charset="EUC-KR">
<title>���� ��ǰ ��</title>
<script>
	function show_area1() {
		document.getElementById("a1").style.display = "block";
		document.getElementById("a2").style.display = "none";
		document.getElementById("m1").style.display = "block";
		document.getElementById("m2").style.display = "none";
	}

	function show_area2() {
		document.getElementById("a1").style.display = "none";
		document.getElementById("a2").style.display = "block";
		document.getElementById("m1").style.display = "none";
		document.getElementById("m2").style.display = "block";
	}
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	function check_deposit(){
		
		var deposit_amount=document.getElementById("d_amount").value;
		var deposit_period=document.getElementById("d_period").value;
		var deposit_intr=document.getElementById("d_intr").value;
		
		
		deposit_intr=Number(deposit_intr);
		deposit_intr=deposit_intr/100;
		deposit_period=Number(deposit_period);
		deposit_amount=Number(deposit_amount);
		alert(deposit_intr);
		if(deposit_amount==""){
			alert("��ġ�ݾ��� �Է����ּ���");
			return false;
		}
		if(deposit_period==""){
			alert("��ġ�Ⱓ�� �Է����ּ���");
			return false;
		}
		if(deposit_period%6!=0||deposit_period>36){
			alert("��ġ�Ⱓ�� 6���������� �Է����ּ���");
			return false;
		}
		alert(deposit_period/12);
		var result=deposit_amount=deposit_amount+(deposit_amount*deposit_intr*(deposit_period/12));
		result=numberWithCommas(result)+" ��";

		document.getElementById("m3").style.display = "block";
		document.getElementById("d_result").value=result;
		
		return true;
	}
	function myWant(prdt_cd){

		location.href='myWant.do?current_page=1&dep_or_sav=0&fin_prdt_cd='+prdt_cd;
	}
	function go_to_site(){
		var url=document.getElementById("bank_url").value;
		location.href=url;
	}
</script>
</head>
<body>

	<!-- *********************** �Խ��� �۾��� �� ****************************  -->
	<jsp:include page="../../header.jsp"></jsp:include>

	<!-- *********************** ���̵� �޴� ****************************  -->

	<div id="body_div">
		<div id="side_menu">
			<h4>
				<a href="#">��ǰ�Ұ�</a>
			</h4>
			<div id="side_div">
				<ul id="side_submenu">
					<li>- <a href="depositList.do?current_page=1">�� ��</a></li>
					<li>- <a href="savingList.do?current_page=1"> �� ��</a></li>
				</ul>
			</div>
		</div>
		<!-- *********************** ���� ****************************  -->

		<div id="product_warp">
			<div id="productContent">
				<sapn>����ȸ�� ����������:${submitDay}</span> <b><h2>${depositBean.getFin_prdt_nm()}</h2></b>
				<dl class="product-info1">
					<dd>${joinWay}</dd>
				</dl>
				<hr>
				<div class="product-info2">
					<dl class="img1">
						<img
							src="${pageContext.request.contextPath}/resources/images/calender.png" />
						<dt>�Ⱓ</dt>
						<dd>${depositBean.getSave_trm()}����</dd>
					</dl>
					<dl class="img2">
						<img
							src="${pageContext.request.contextPath}/resources/images/coin.png" />
						<dt>����ȸ��</dt>
						<dd>${depositBean.getKor_co_nm()}</dd>
					</dl>
					<dl class="img3">
						<img
							src="${pageContext.request.contextPath}/resources/images/graph.png" />
						<dt>�ְ�</dt>
						<dd>
							��&nbsp;<span>${depositBean.getIntr_rate2()}%</span>
						</dd>
					</dl>
				</div>
				<div class="product-info3">
					<input type="button" value="��ٱ���" onclick="myWant(${depositBean.getFin_prdt_cd()})"> 
					<input type="button" value="�����ϱ�" onclick="go_to_site()">
				</div>
			</div>
			<!-- �󼼼���/���ݰ��� ���� -->

			<div id="productContet3">
				<div id="a1" class="product_button">
					<input type="button" value="�󼼼���" onclick="show_area1()" /> <input
						id="clicked" type="button" value="���ݰ���" onclick="show_area2();" />
				</div>
				<div id="a2" class="product_button" style="display: none;">
					<input id="clicked" type="button" value="�󼼼���"
						onclick="show_area1();"> <input type="button"
						value="���ݰ���" onclick="show_area2();">
				</div>
			</div>
			<div id="productContent2">
				<div id="m1">
					<div class="product-info-group">
						<div class="product-info4">
							<dl>
								<dt>��������</dt>
								<dd>${join_deny}</dd>
							</dl>
							<dl>
								<dt>���Դ��</dt>
								<dd>${depositBean.getJoin_member()}</dd>
							</dl>
							<dl>
								<dt>�ݸ� ����</dt>
								<dd>${depositBean.getIntr_rate_type_nm()}</dd>
							</dl>
							<dl>
								<dt>���� �� ������</dt>
								<dd>${depositBean.getMtrt_int()}</dd>
							</dl>

						</div>
						<div class="product-info5">
							<dl>
								<dt>�������</dt>
								<dd>${depositBean.getSpcl_cnd()}</dd>
							</dl>

							<dl>
								<dt>��Ÿ����</dt>
								<dd>${depositBean.getEtc_note()}</dd>
							</dl>
						</div>
					</div>

				</div>

				<div class="product-info6">
					<div id="m2" style="display: none;">
						<dl>
							<dd>
								<input id="d_amount" class="input1" type="text" placeholder="����">���� &nbsp; 
								<input id="d_period" type="text" placeholder="�Ⱓ">���� �� �ݸ�&nbsp; 
								<input id="d_intr" readonly="readonly" value="${depositBean.getIntr_rate2()}" 
								type="text" placeholder="�ݸ�">%�� ����ϸ�?  &nbsp;
								<input class="button1" type="submit" 
								value="���" onclick="check_deposit()">
								<input id="bank_url" type="hidden" value="${url}">
							</dd>
						</dl>
						<div id="m3" style="display: none;">
							<h2><input readonly="readonly" class="input2" id="d_result" ></input></h2>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- *********************** �Խ��� �۾��� �� ****************************  -->

	<jsp:include page="../../footer.jsp"></jsp:include>

</body>
</html>