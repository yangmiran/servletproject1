<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>양식태그</h1>
	<form>
		<input type="date"/>
		<h2>1. 텍스트 관련 태그</h2>
		아이디:<input type="text" size="50" maxlength="10" 
					name="id" placeholder="아이디" required="required" value="test"><br/>
		패스워드:<input type="password" name="pass"><br/>
		정보:<br/>
		<textarea rows="10" cols="30" name="info">자기소개:</textarea>
		<h2>3. 선택 관련 태그</h2>
		좋아하는 과목1<br/>
		<input type="checkbox" name="subject1" value="java">자바
		<input type="checkbox" name="subject1" value="servlet">서블릿
		<input type="checkbox" name="subject1" value="html5">HTML5
		<input type="checkbox" name="subject1"  value="jdbc"
				 checked="checked">jdbc
		<br/><br/>
		
		좋아하는 과목2<br/>
		<input type="radio" name="subject2" value="java">자바
		<input type="radio" name="subject2" value="servlet">서블릿
		<input type="radio" name="subject2" value="html5">HTML5
		<input type="radio" name="subject2"   value="jdbc"
					checked>jdbc
		<br/><br/>
		
		좋아하는 과목3<br/>
		<select name="subject3">
			<option value="자바">자바
			<option value="하둡">하둡
			<option value="스프링">스프링
			<option value="MEAN">MEAN
			<option value="spark">spark
		</select>
		
		좋아하는 과목4<br/>
		<select name="subject4" size="4" multiple="multiple">
			<option value="자바">자바
			<option value="하둡">하둡
			<option value="스프링">스프링
			<option value="MEAN">MEAN
			<option value="spark">spark
		</select>
		
		<h2>2. 버튼 관련 태그</h2>
		<input type="button" value="자바스크립트 명령문을 실행할 용도"
					onclick="alert('안녕')">
		<input type="reset" value="데이터지우기(취소)">
		<input type="submit" value="서버로 데이터 전송하기">	
		
		
	</form>
</body>
</html>









