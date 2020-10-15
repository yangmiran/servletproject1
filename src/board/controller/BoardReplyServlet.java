package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardReplyServlet
 */
@WebServlet("/breply")
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 댓글 등록 처리용 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		
		int boardNum = Integer.parseInt(request.getParameter("bnum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String boardTitle = request.getParameter("title");
		String boardWriter = request.getParameter("writer");
		String boardContent = request.getParameter("content");
		
		BoardService bservice = new BoardService();
		
		//등록될 댓글의 원글을 조회함
		Board board = bservice.selectBoard(boardNum);
		
		//댓글 객체 생성함
		Board reply = new Board();
		reply.setBoardContent(boardContent);
		reply.setBoardTitle(boardTitle);
		reply.setBoardWriter(boardWriter);
		reply.setBoardLevel(board.getBoardLevel() + 1);
		reply.setBoardRef(board.getBoardRef()); //참조하는 원글번호
		
		//board_reply_ref : 원글의 댓글일 때 자기번호
		//대댓글일 때는 참조하는 댓글 번호를 기록함
		if(reply.getBoardLevel() == 2) { //댓글의 댓글일 때
			reply.setBoardReplyRef(board.getBoardReplyRef());
		}
		
		//댓글의 순번 처리
		reply.setBoardReplySeq(1); //최근 댓글이 무조건 1
		//이전 댓글의 순번은 모두 1증가 처리함
		bservice.updateReplySeq(reply); //update 실행함
		
		//댓글 등록 처리함
		int result = bservice.insertReply(reply);
		
		if(result > 0) {
			response.sendRedirect("/test1/blist?page=" + currentPage);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", boardNum + "번 게시글 댓글 등록 실패!");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
