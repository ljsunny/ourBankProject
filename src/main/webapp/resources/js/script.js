

function idCheck(id){
	if(id==""){
		alert("아이디를 입력해 주세요");
	}else{
		url="idCheck.do?id="+id;
		window.open(url,"get","width=300,height=150");
	}
}
function returnID(){
		document.regForm.id.value=null;
		
		document.regForm.id.focus();
}

function findAdress(){
	var pop = 
		window.open("zipCode.do","pop",
				"width=570,height=420, scrollbars=yes, resizable=yes"); 
}
function jusoCallBack(roadFullAddr){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.	
	document.regForm.user_address.value = roadFullAddr;		
}

function inputCheck() {
	if(document.regForm.id.value==""){
		alert("아이디를 입력해 주세요");
		regForm.id.focus();
		
		return false;
	}
	if(document.regForm.passwd.value==""){
		alert("비밀번호를 입력해 주세요");
		document.regForm.passwd.focus();
		return false;
	}
	if(document.regForm.ck_passwd.value==""){
		alert("비밀번호 확인을 입력해 주세요");
		document.regForm.ck_passwd.focus();
		return false;
	}
	if(document.regForm.passwd.value!=document.regForm.ck_passwd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.ck_passwd.focus();
		return false;
	}
	if(document.regForm.user_name.value==""){
		alert("이름을 입력해주세요");
		document.regForm.user_name.focus();
		return false;
	}
	if(document.regForm.user_birth.value==""){
		alert("생일을 입력해주세요");
		document.regForm.user_birth.focus();
		return false;
	}
	if(document.regForm.user_phone.value==""){
		alert("휴대폰 번호를 입력해주세요");
		document.regForm.user_phone.focus();
		return false;
	}
	if(document.regForm.user_email.value==""){
		alert("이메일을 입력해주세요");
		document.regForm.user_email.focus();
		return false;
	}
	var str=document.regForm.user_email.value;
	var atPos=str.indexOf('@');
	var atLastPos=str.lastIndexOf('@');
	var dotPos=str.indexOf('.');
	var spacePos=str.indexOf(' ');
	var commaPos=str.indexOf(',');
	var eMailSize=str.length;
	
	if(atPos > 1 && atPos == atLastPos && dotPos > 3 && spacePos==-1 && 
		commaPos==-1 && atPos+1 < dotPos && dotPos+1 < eMailSize){
		
	}else{
		alert('E-mail 주소 형식이 잘못되었습니다.\r\n다시 입력해주세요!');
		document.regForm.user_email.focus();
		return false;
	}
	
	return true;
}
function blankCheck(){
	if(document.form.id.value==""){
		alert("아이디를 입력해주세요");
		return false;
	}
	
	if(document.form.passwd.value==""){
		alert("비밀번호를 입력해주세요");
		return false;
	}
	return true;
}
function equalPasswd(){
	if(document.regForm.passwd.value==""){
		alert("비밀번호를 입력해 주세요");
		document.regForm.passwd.focus();
		return false;
	}
	if(document.regForm.ck_passwd.value==""){
		alert("비밀번호 확인을 입력해 주세요");
		document.regForm.ck_passwd.focus();
		return false;
	}
	if(document.regForm.passwd.value!=document.regForm.ck_passwd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.ck_passwd.focus();
		return false;
	}
	return true;
}
function goLoginForm(){
	location.href='loginForm.do';
}