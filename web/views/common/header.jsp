<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
<style>
header {margin:0; padding:0;}
header h1#logo {
	font-size: 36pt;
	font-style: italic;
	color: navy;
	text-shadow: 2px 2px 2px gray;
}

header ul#menubar {
	list-style:none;
	text-align: center;
	padding: 0;
}
hr {clear:both;}

header ul#menubar li {
	/* float: left; */
	display:inline-block;
	width: 120px;
	height: 30px;
	margin-right: 5px;
	padding: 0;
}
header ul#menubar li a {
	text-decoration: none;
	width:120px;
	height: 30px;
	display:block;
	background-color: orange;
	text-align: center;
	color: navy;
	font-weight: bold;
	margin: 0;
	text-shadow: 1px 1px 2px white;
	line-height : 30px;
}
header ul#menubar li a:hover {
	text-decoration: none;
	width:120px;
	height: 30px;
	display:block;
	background-color: navy;
	text-align: center;
	color: white;
	font-weight: bold;
	margin: 0;
	text-shadow: 1px 1px 2px navy;
	line-height : 30px;
}
</style>
</head>
<body>
<header>
<h1 id="logo">test1</h1>
<% if(loginMember != null && loginMember.getUserid().equals("admin")){ //관리자가 로그인 했을 때 %>
<ul id="menubar">
	<li><a href="/test1/mlist">회원관리</a></li>
	<li><a href="/test1/nlist.ad">공지글관리</a></li>
	<li><a href="/test1/blist">게시글관리</a></li>
	<li><a href="#">QnA관리</a></li>
	<li><a href="#">사진게시판관리</a></li>
	<li><a href="/test1/views/test/testPage.jsp">필터테스트</a></li>
	<li><a href="/test1/index.jsp">Home</a></li>
</ul>
<% }else if(loginMember != null){ // 일반 회원이 로그인했을 때 %>
<ul id="menubar">
	<li><a href="/test1/mlist">암호화회원가입</a></li>
	<li><a href="/test1/nlist">공지사항</a></li>
	<li><a href="/test1/blist?page=1">게시글</a></li>
	<li><a href="#">QnA</a></li>
	<li><a href="#">사진게시판</a></li>
	<li><a href="/test1/views/test/testPage.jsp">필터테스트</a></li>
	<li><a href="/test1/index.jsp">Home</a></li>
</ul>
<% }else{ //로그인하지 않았을 때 %>
<ul id="menubar">
	<li><a href="/test1/views/member/enrollPage.html">회원가입</a></li>
	<li><a href="/test1/nlist">공지사항</a></li>
	<li><a href="/test1/blist?page=1">게시글</a></li>
	<li><a href="#">QnA</a></li>
	<li><a href="/test1/views/test/cryptoEnrollPage.html">암호화회원가입</a></li>
	<li><a href="/test1/views/test/testPage.jsp">필터테스트</a></li>
	<li><a href="/test1/index.jsp">Home</a></li>
</ul>
<% } %>
</header>
</body>
</html>