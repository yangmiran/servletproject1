<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
		function testConfirm(){
			//confirm창에서 선택하는 것을  result에 저장
			result = window.confirm("정말 삭제할꺼냐?");
			//alert(result);
			if(result){
				alert("실제 db에서 삭제하는 작업을 수행 - state값 변경");
			}else{
				alert("삭제취소");
			}
		}
		function searchAddress(){
			//창을 오픈하기 위한 함수
			popup = window.open("js_popup.html", "mywin",
			"width=300,height=300,top=100,toolbar=yes,status=yes");
		}
		function idcheck(){
			popup2 = window.open("js_popup.html", "mywin2",
			"width=300,height=300,top=100,toolbar=yes,status=yes");
		 /* 	popup2.document.mypopup.addr.value = 
					                  document.kitri.id.value;  */
		}
		function test_number(){
			mynum1 = parseInt(kitri.num1.value);
			mynum2 = parseInt(kitri.num2.value);
			kitri.result.value = mynum1+mynum2;
		}
		function test_number2(){
			alert("isNaN결과==>"+isNaN(kitri.num1.value));
			alert("eval결과==>"+eval(kitri.num1.value));
			data = kitri.num1.value;
			alert("문자열의 길이=>"+data.length);
			data2 = data.trim();
			alert("공백을 제거한 문자열의 길이=>"+data2.length);
		}
	</script>
</head>
<body>
	<button onClick="window.alert('경고창')">경고창</button>
	<button onClick="testConfirm()">확인창</button>
	<button onClick="test_number()">숫자처리메서드</button>
	<button onClick="test_number2()">숫자처리메서드2</button>
	<button onClick="window.close()">닫기</button>
	
	<form action= "" name="kitri">
		아이디 : <input type="text" name="id">
		<input type="button" value="아이디체크"
						 onClick="idcheck()">
		<br>
		우편번호 : <input type="text" name="zipcode">
		<input type="button" value="SEARCH ADDRESS" onClick="searchAddress()">
		<br>
		
		상세주소 : <input type="text" name="address"><br>
		
		숫자1 : <input type="text" name="num1"><br>
		숫자2 : <input type="text" name="num2" onblur="test_number()"><br>
		결과 : <input type="text" name="result" ><br>
		<input type="submit" value="SEND">
	</form>
</body>
</html>






