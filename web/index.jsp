<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" errorPage="views/common/error.jsp" %>
<%-- include 한 header.jsp 와 충돌나므로 제거함 <%
	Member loginMember = (Member)session.getAttribute("loginMember");
	
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
<style type="text/css">

div.lineA { 
	height : 100px;
	border : 1px solid gray;
	float : left;
	margin : 5px;
	padding : 5px;
}

div#banner {
	width : 750px;
}

div#loginBox {
	width : 274px;
	font-size : 9px;
	text-align : left;
	padding-left : 20px;
}

div#loginBox button {
	width : 250px;
	height : 35px;
	background-color : navy;
	color : white;
	margin-top : 10px;
	margin-bottom : 15px;
	font-size : 14pt;
	font-weight : bold;
	border : 1px solid #dae1e6
}
div#banner {
	width : 750px;
	padding : 0;
}
div#banner img {
	margin : 0;
	padding : 0;
	width : 750px;
	height : 110px;
}
</style>
<script src="/test1/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	/*
		주기적으로 반복 요청하려면
		setInterval(function(){ $.ajax(); }, 시간);
		시간은 밀리세컨드임 : 1000 이 1초임
	*/
	/* setInterval(function(){
		console.log("setInterval() 에 의해 자동 실행 확인");
	}, 100); */
	
	//최근 등록한 공지글 3개 출력되게 함
	$.ajax({
		url: "/test1/ntop3",
		type: "get",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			//object ==> string 으로 변환
			var jsonStr = JSON.stringify(data);
			//string ==> json 객체로 바꿈
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].no + "</td><td><a href='/test1/ndetail?noticeno=" + json.list[i].no + "'>" 
						+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ")
						+ "</a></td><td>" + json.list[i].date + "</td></tr>";
			} //for in
			
			$("#newnotice").html($("#newnotice").html() + values);
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	}); //ajax
	
	//조회수 많은 인기 게시 원글 상위 3개 조회 출력 처리
	$.ajax({
		url: "/test1/btop3",
		type: "get",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			//object ==> string 으로 변환
			var jsonStr = JSON.stringify(data);
			//string ==> json 객체로 바꿈
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].bnum + "</td><td><a href='/test1/bdetail?bnum=" + json.list[i].bnum + "'>" 
						+ decodeURIComponent(json.list[i].btitle).replace(/\+/gi, " ")
						+ "</a></td><td>" + json.list[i].rcount + "</td></tr>";
			} //for in
			
			$("#toplist").html($("#toplist").html() + values);
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});
	
}); //document.ready

function movePage(){
	location.href = "views/member/loginPage.html";
}
</script>
</head>
<body>
	<%@ include file="views/common/header.jsp" %>
	<hr style="clear:both;">
	<div style ="width: 1140px;margin: 0 auto;">
		<div id="banner" class="lineA">
			<img src="resources/images/photo2.jpg">
		</div>
		<% if(loginMember == null){ //로그인 안 한 상태이면  %>
		<div id="loginBox" class="lineA">
			test1 사이트 방문을 환영합니다.<br>
			<!-- <button onclick="javascript:location.href='views/member/loginPage.html';">로그인하세요.</button>-->
			<button onclick="movePage();">로그인 하세요.</button> <br>
			<a>아이디/비밀번호 조회</a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<a href="views/member/enrollPage.html">회원가입</a>
		</div>
		<% }else if(loginMember.getUserid().equals("admin")){  //로그인한 사용자가 admin 이면 %>
		<div id="loginBox" class="lineA">
			<%= loginMember.getUsername() %> 님<br>
			<button onclick="javascript:location.href='logout';">로그아웃</button> <br>
			<!-- <a href="/test1/mlist">회원관리</a> &nbsp; &nbsp;
			<a>공지사항관리</a> &nbsp; &nbsp;
			<a>게시글관리</a> &nbsp; &nbsp;  -->
			<a href="/test1/myinfo?userid=<%= loginMember.getUserid() %>">My page</a>
			<!-- 쿼리스트링(Query String) : ?이름=전송값&이름=전송값 -->
		</div>
		<% }else {  //일반 회원이 로그인한 상태이면 %>
		<div id="loginBox" class="lineA">
			<%= loginMember.getUsername() %> 님<br>
			<button onclick="javascript:location.href='logout';">로그아웃</button> <br>
			<a>쪽지</a> &nbsp; &nbsp;
			<a>메일</a>
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<a href="/test1/myinfo?userid=<%= loginMember.getUserid() %>">My page</a>
			<!-- 쿼리스트링(Query String) : ?이름=전송값&이름=전송값 -->
		</div>
		<% } %>
	</div>
	<hr style="clear:left;">
	
	<section>
		<!-- 최근 등록 공지글 3개 조회 출력 -->
		<div style="float:left;border:1px solid navy;padding:5px;margin:5px;">
			<h4>최근 공지글</h4>
			<table id="newnotice" border="1" cellspacing="0">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>날짜</th>
				</tr>
			</table>
		</div>
		<!-- 조회수 많은 게시글 3개 조회 출력 -->
		<div style="float:left;border:1px solid navy;padding:5px;margin:5px;">
			<h4>인기 게시글</h4>
			<table id="toplist" border="1" cellspacing="0">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
				</tr>
			</table>
		</div>
	</section>
	<hr style="clear:both;">
	<!-- 상대경로만 사용 가능함 -->
	<%@ include file="views/common/footer.jsp" %>
</body>
</html>