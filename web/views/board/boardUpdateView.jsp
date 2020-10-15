<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Board" %>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
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
<h1 align="center"><%= board.getBoardNum() %> 번 게시글 수정 페이지</h1>
<!-- 원글 수정 폼 -->
<% if(board.getBoardLevel() == 0){ //원글이면  %>
<form action="/test1/boriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<input type="hidden" name="ofile" value="<%= board.getBoardOriginalFileName() %>">
<input type="hidden" name="rfile" value="<%= board.getBoardRenameFileName() %>">
	<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="120">제 목</th>
			<td><input type="text" name="title" size="50" value="<%= board.getBoardTitle() %>"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" readonly value="<%= board.getBoardWriter() %>"></td>
		</tr>
		<tr>
			<th>파일 선택 : </th>
			<td>
				<% if(board.getBoardOriginalFileName() != null){ %>
					<%= board.getBoardOriginalFileName() %> &nbsp;
					<input type="checkbox" name="delflag" value="yes"> 파일삭제
					<br>
				<% } %>
				<input type="file" name="upfile">
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="50" name="content"><%= board.getBoardContent() %></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정하기"> &nbsp;
				<input type="reset" value="작성취소"> &nbsp;
				<input type="button" value="이전 페이지로 이동" onclick="javascript:history.go(-1); return false;">
			</th>
		</tr>
	</table>
</form>
<% }else{ //댓글 또는 대댓글일때의 폼 %>
<form action="/test1/breplyup" method="post">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
	<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="title" size="50" value="<%= board.getBoardTitle() %>"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" readonly value="<%= board.getBoardWriter() %>"></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="50" name="content"><%= board.getBoardContent() %></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정하기"> &nbsp;
				<input type="reset" value="작성취소"> &nbsp;
				<input type="button" value="이전페이지로 이동" onclick="javascript:history.go(-1); return false;">
			</th>
		</tr>
	</table>
</form>
<% } %>
</body>
</html>