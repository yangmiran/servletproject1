<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		alert("시작")
		$("#myid").blur(function() {
			$("#result").html("아이디가 focus를 잃어버렸습니다. - onblur속성과 동일");
		});
		$("#myid").focus(function() {
			$("#result").html("아이디가 focus를 받았습니다. - onfocus속성과 동일");
		});
	})
	function checkAll(){
		 if (!checkUserId(myform.id.value)) {
			 alert("checkUserId")
	            return false; //완료되지 않으면 submit하지 않기
	           
	   		 
	     }
		 return true;//확인이 완료되었을 때 submit */
	}
	function checkUserId(id) {
		//Id가 입력되었는지 확인하기
		
		alert(id)
		 var idRegExp = /^[a-zA-z0-9]{3,}$/; 
		/*  → / : 자바스크립트의 정규표현식의 처음과 끝을 의미한다.
		 → [ ] : 문자셋이다. 예를 들면 [a-z]라고 적을경우 정규표현식에 만족해야하는 값들은 반드시 a~z사이의 값만 넣을 수 있다.
		 → ^ : 문장의 처음을 뜻한다.
		 → $ : 문장의 마지막을 뜻한다.
		 → { } : 문자열 길이를 뜻한다. 예를 들어 {4,12}일 경우 최소 길이 4, 최대 길이 12이다.
 */

        if (!idRegExp.test(id)) {
		     alert("아이디는 영문 대소문자와 숫자가 입력되어도 좋으며 4~12자리로 입력해야합니다!");
		     myform.id.value = "";
		     myform.id.focus();
		     return false;
        }
        return true;
		
	}
</script>
</head>
<body>
	<form method="get" action="result.jsp" name="myform" onsubmit="return checkAll()">
		아이디:<input type="text" size="50" maxlength="10" name="id" id="myid"><br />
		패스워드:<input type="password" name="pass"><br /> 아이디:<input
			type="text" size="50" maxlength="10" name="id2" id="myid2"
			onblur="alert('focus를 잃었습니다.')"><br />
		<!-- onfocus="alert('focus를 얻었습니다.')" -->
		패스워드:<input type="password" name="pass2"><br /> <input
			type="submit" value="로그인">
	</form>
	<div id="result"></div>
</body>
</html>