<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
<style>
	
</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h1 align="center">공지사항</h1>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>첨부파일</th>
		<th>날짜</th>
	</tr>
	<% for(Notice n : list){ %>
	<tr>
		<td><%= n.getNoticeNo() %></td>
		<td><a href="/test1/ndetail?noticeno=<%= n.getNoticeNo() %>"><%= n.getNoticeTitle() %></a></td>
		<td><%= n.getNoticeWriter() %></td>
		<td>
			<% if(n.getOriginalFilepath() != null){ %>
			◎
			<% }else{ //첨부파일이 없을 때 %>
			&nbsp;
			<% } %>
		</td>
		<td><%= n.getNoticeDate() %></td>
	</tr>
	<% } %>
</table>
</body>
</html>