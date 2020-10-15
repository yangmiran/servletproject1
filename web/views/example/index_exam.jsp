<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
<style type="text/css">
p { 
	background-color : orange;
	border : 2px solid navy;
}

/* body 안에 있는 모든 div 태그를 의미함 */
div { 
	border : 1px solid red;
	width : 500px;
	height : 200px;
	margin : 5px;
}

div#box1 { background : #99ffff; }

div.bb { background-image : url('resources/images/button.jpg'); }
</style>
</head>
<BODY>
<!-- 
<center>
처음 만드는 웹프로젝트<br>
welcome file test 확인<br>
2020-&nbsp;&nbsp;&nbsp;07-&nbsp;&nbsp;&nbsp;16<br>
</center>
<hr size="5" width="50%" noshade color="green" align="center">
<p align="left">
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
</p>
<p align="center">
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
</p>
<p align="right">
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
문단으로 묶어서 내용 구분할 때 사용하는 paragraph 태그임.
</p>
 -->
<div id="box1" class="aa">
	<ol> <!-- order list : 순번있는 목록 만들기용 태그 -->
		<li>html5</li>
		<li>css3</li>
		<li>javascript</li>
		<li>jquery</li>
		<li>html5 api</li>
	</ol>
</div>
<div id="box2" class="bb">
	
</div>
<div id="box3" class="aa">
	<ul> <!-- unorder list : 순번없는 목록 만들기용 태그임 -->
		<li>html5</li>
		<li>css3</li>
		<li>javascript</li>
		<li>jquery</li>
		<li>html5 api</li>
	</ul>
</div>
<div id="box4" class="bb">
	
</div>
</BODY>
</html>