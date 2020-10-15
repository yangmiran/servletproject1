<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Board, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
<script>
	function showWriteForm(){
		location.href = "/test1/views/board/boardWriteForm.jsp";
	}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center">게시글 목록  : 총 <%= listCount %> 개</h2>
<!-- 게시글 쓰기(등록)은 로그인한 회원만 가능함 -->
<% if(loginMember != null){ //로그인 상태라면 %>
<div style="align:center; text-align:center">
	<button onclick="showWriteForm();">글쓰기</button>
</div>
<% } %>
<br>
<table align="center" border="1" cellspacing="0" width="700">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>첨부파일</th>
	</tr>
	<% for(Board b : list){ %>
	<tr>
		<td align="center"><%= b.getBoardNum() %></td>
		<td>
			<!-- 댓글일때는 제목을 들여쓰기함 -->
			<% if(b.getBoardLevel() == 1){ //원글의 댓글일 때 %>
			&nbsp; &nbsp; ▷
			<% }else if(b.getBoardLevel() == 2){ //댓글의 댓글일 때 %>
			&nbsp; &nbsp; &nbsp; &nbsp; ▷▷
			<% } %>
			<!-- 로그인한 사용자만(회원만) 상세보기할 수 있게 함 -->
			<% if(loginMember != null){ %>
				<a href="/test1/bdetail?bnum=<%= b.getBoardNum() %>&page=<%= currentPage %>"><%= b.getBoardTitle() %></a>
			<% }else{ %>
				<%= b.getBoardTitle() %>
			<% } %>
		</td>
		<td align="center"><%= b.getBoardWriter() %></td>
		<td align="center"><%= b.getBoardDate() %></td>
		<td align="center"><%= b.getBoardReadCount() %></td>
		<td align="center">
			<% if(b.getBoardOriginalFileName() != null){ %>
			◎
			<% }else{ %>
			&nbsp;
			<% } //for each %>
		</td>
	</tr>
	<% } %>
</table>
<br>
<!-- 페이징 처리 -->
<div style="text-align:center;">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/test1/blist?page=1">[맨처음]</a>
<% } %>
<!-- 이전 그룹으로 이동 처리 -->
<!-- - 10은 밑에 보여질 페이지가 10 (5로 할거면 5로 변경하면됨) -->
<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){ %>
<a href="/test1/blist?page=<%= startPage - 10 %>">[이전그룹]</a>
<% }else{ %>
	[이전그룹]&nbsp;
<% } %>
<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
<% for(int p = startPage; p <= endPage; p++){ 
	if(p == currentPage){ %>
	<font color="red" size="4"><b>[<%= p %>]</b></font>
	<% }else{ %>
	<a href="/test1/blist?page=<%= p %>"><%= p %></a>
<% }} %>
<!-- 다음 그룹으로 이동 처리 -->
<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){ %>
<a href="/test1/blist?page=<%= endPage + 10 %>">[다음그룹]</a>
<% }else{ %>
	[다음그룹]&nbsp;
<% } %>
<!-- 맨끝 페이지로 이동 처리 -->
<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/test1/blist?page=<%= maxPage %>">[맨끝]</a>
<% } %>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>