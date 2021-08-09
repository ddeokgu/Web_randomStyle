<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<link rel = "stylesheet" href = "${path}/include/style.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>
$(function(){
	$("#e_address").change(function(){
		   $("#e_address option:selected").each(function () {
				
				if($(this).val() == "direct"){ 
					 $("#e_addressDirect").val("");                        
					 $("#e_addressDirect").attr("disabled",false); 
				}else{
					 $("#e_addressDirect").val($(this).text());      
					 $("#e_addressDirect").attr("disabled",true); 
				}
		   });
		});
	
	$("#btnSignUp").click(function() {
		
		if ($("#name").val() == "") {
			alert("이름을 입력하세요.");
			$("#name").focus();
		
		}else if ($("#userid").val() == "") {
			alert("아이디를 입력하세요.");
			$("#userid").focus();
			
			
		} else if ($("#passwd").val() == "") {
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			
			
		} else if ($("#email").val() == "") {
			alert("이메일을 입력하세요.");
			$("#email").focus();
			
		
		} else if ($("#tel").val() == "") {
			alert("전화번호를 입력하세요.");
			$("#tel").focus();
			
			
		} else if ($("#zipcode").val() == "") {
			alert("우편번호 입력하세요.");
			$("#zipcode").focus();
			
			
		} else if ($("#address1").val() == ""){
			alert("주소를 입력하세요.");
			$("#address1").focus();
			
			
		} else if ($("#address2").val() == "") {
			alert("상세주소를 입력하세요.");
			$("#address2").focus();
			
		} else if ($("#passwd").val() != $("#passwdCheck").val()) {
			alert("비밀번호가 일치하지 않습니다.")
			$("#passwd").focus();
		}
		
	var userid =  $("#userid").val();
		
	var param = "userid=" + $("#userid").val() + "&name=" + $("#name").val() + "&passwd=" + $("#passwd").val()
							+ "&email=" + ($("#email").val()+"@"+$("#e_addressDirect").val()) 
							+ "&phone="+ $("#phone option:selected").val() + "&tel=" + $("#tel").val() 
							+ "&zipcode=" + $("#zipcode").val() + "&address1=" + $("#address1").val()
							+ "&address2=" + $("#address2").val();

	var param2 = "userid=" + $("#userid").val() + "&passwd=" + $("#passwd").val();
	console.log(param);
	$.ajax({
		type : "post",
		data : param,
		url : "${path}/member/sign_up.do",
		success : function(result) {
			alert(result);
		location.href = "${path}/member/login_check.do?"+param2;
			
			}
		
		});
	});



$("#checkId").click(function(){

	$.ajax({
		url:"${path}/member/idCheck.do",
		type:"post",
		data: "userid=" + $("#userid").val(),
		success: function(result) {
			$("#div1").html(result);
	
		
			}
		});
	});
	
$("#btnPwdCheck").click(function(){
	var passwd1 = $("#passwd").val();
	var passwd2 = $("#passwdCheck").val();
	if(passwd1 == passwd2){
		$("#div2").html("비밀번호 일치");
		$("#div2").css("color","blue");
	}else {
		$("#div2").html("비밀번호 불일치");
		$("#div2").css("color","red");
		}
	});	
});


function showPostcode(){
	new daum.Postcode({
		oncomplete : function(data){
			var fullAddr = '';
			var extraAddr = '';
			if(data.userSelectedType === 'R'){
				fullAddr = data.roadAddress;
			} else {
				fullAddr = data.jibunAddress;
			}
			if(data.userSelectedType === 'R'){
				if(data.bname !==''){
					extraAddr += data.bname;
				}
				if(data.buildingName !==''){
					extraAddr += (extraAddr !== '' ?','+data.buildingName : data.buildingName);
				}
				fullAddr += (extraAddr !== ''?'(' + extraAddr + ')':'');
			}
			document.getElementById('zipcode').value = data.zonecode;
			document.getElementById('address1').value = fullAddr;
			document.getElementById('address2').focus();
		}
	}).open();
}
</script>
<style>
.c {
	margin-top: 1px;
	margin-bottom: 10px;
	height: 15px;
}

.d {
	font-size: 20px;
}


h3 {
	text-align: center;
    color: black;
    text-decoration : underline;
    text-underline-position: under;
	
} 
</style>

</head>
<body>
	<h1 class ="a">오늘의 랜덤 스타일</h1>
	<h3 >회원가입</h3>
	<div align = "center" style="margin: auto; padding-right: 500px; padding-left: 500px;">
		<fieldset align = "left" style="height: 600px; width:200px; padding-top:20px;">
			<span style="margin-top:10px;" class="d">이름</span><br> 
			<input type="text" id="name" class="c" style="margin-top: 5px;" ><br> 
				<span class="d">아이디</span><br> 
				<input type="text" id="userid" class="c" placeholder="영문,숫자 조합 30자까지">
			<button style="margin-left: 5px;" type="button" id="checkId">중복확인</button><br> 
			<div id = "div1"></div><br>
			<span class="d">비밀번호</span><br> 
			<input type="password" id="passwd" class="c"><br> 
				<span class="d">비밀번호 확인</span><br>
				<input type="password" class="c" id = "passwdCheck">
				<input style="margin-left: 5px;" type = "button" id = "btnPwdCheck" value = "비밀번호 확인">
				<div id = div2></div><br> 
				<span class="d">전화번호</span><br>  
				<select name="phone" id = "phone">
					<option value = "skt">SKT</option>
					<option value = "kt">KT</option>
					<option value = "lgu+">LGU+</option>
					<option value = "알뜰">알뜰폰</option>
				</select>
				
				<input type="text" id="tel" class="c"><br> 
				<span class="d">이메일</span><br> 
				<input type="text" id="email" class="c" style="width:120px;">@
				<input style="width:120px;" type = "text" 
							id = "e_addressDirect" name = "e_addressDirect">
							
				<select name="e_address" id = "e_address">
					<option value = "direct" >직접입력</option>
					<option value = "naver.com">naver.com</option>
					<option value = "hanmail.net">hanmail.net</option>
					<option value = "gmail.com">gmail.com</option>
					<option value= "nate.com">nate.com</option> 
					<option value= "yahoo.co.kr">yahoo.co.kr</option>
				</select>
	
			<span class="d">주소</span><br> 
			<input type="text" id="zipcode" class="c" placeholder="우편번호" style="width:60px;"> 
			<input type="button" onclick="showPostcode()" value="우편번호찾기"><br> 
			<input type="text" id="address1" class="c" style="width: 300px;"><br>
			<span class="d">상세주소</span><br>
			<input type="text" id="address2" class="c" style="width: 300px;"><br>
		</fieldset>
		<button type="button" id="btnSignUp"
			style="margin-top: 30px; width: 70px; height: 60px;">회원가입</button>
	</div>

</body>
</html>