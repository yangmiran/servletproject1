<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Board" %>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center"><%= board.getBoardNum() %> 번 게시글 상세보기</h2>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr>
		<th>제 목</th>
		<td><%= board.getBoardTitle() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%= board.getBoardWriter() %></td>
	</tr>
	<tr>
		<th>등록날짜</th>
		<td><%= board.getBoardDate() %></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<% if(board.getBoardOriginalFileName() != null){ %>
			<a href="/test1/bfdown?ofile=<%= board.getBoardOriginalFileName() %>&rfile=<%= board.getBoardRenameFileName() %>"><%= board.getBoardOriginalFileName() %></a>
			<% }else{ %>
			&nbsp;
			<% } %>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><%= board.getBoardContent() %></td>
	</tr>
	<tr>
		<th colspan="2">
			<% if(loginMember != null){ //댓글달기, 수정페이지로 이동 둘 다 로그인 상태여야 함
				if(loginMember.getUserid().equals(board.getBoardWriter())){ //로그인한 회원과 글 작성자가 같으면 수정페이지로 이동 보이게 함%>
				<a href="/test1/bupview?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>">[수정페이지로 이동]</a>
				&nbsp; &nbsp;
				<a href="/test1/bdelete?bnum=<%= board.getBoardNum() %>&level=<%= board.getBoardLevel() %>&rfile=<%= board.getBoardRenameFileName() %>">[글삭제]</a>
				&nbsp; &nbsp;
				<%}else{ //로그인한 회원과 글 작성자가 다르면 댓글달기  보이게함 %>
					<a href="/test1/views/board/boardReplyForm.jsp?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>">[댓글달기]</a>
			<% }} %>
			&nbsp; &nbsp;
			<button onclick="javascript:location.href='/test1/blist?page=<%= currentPage %>'">목록</button>
		</th>
	</tr>
</table>
</body>
</html>