package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import static common.JDBCTemp.*;

public class BoardService {
	//DI
	private BoardDao bdao = new BoardDao();
	
	public BoardService() {}

	public ArrayList<Board> selectTop3() {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectTop3(conn);
		close(conn);
		return list;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Board> list = bdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int insertOriginBoard(Board board) {
		Connection conn = getConnection();
		int result = bdao.insertOriginBoard(conn, board);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public void addReadCount(int boardNum) {
		Connection conn = getConnection();
		int result = bdao.addReadCount(conn, boardNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
	}

	public Board selectBoard(int boardNum) {
		Connection conn = getConnection();
		Board board = bdao.selectBoard(conn, boardNum);
		close(conn);
		return board;
	}

	public int insertReply(Board reply) {
		Connection conn = getConnection();
		int result = bdao.insertReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public void updateReplySeq(Board reply) {
		//댓글 순번 1증가
		Connection conn = getConnection();
		int result = bdao.updateReplySeq(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public int deleteBoard(int boardNum, int boardLevel) {
		Connection conn = getConnection();
		int result = bdao.deleteBoard(conn, boardNum, boardLevel);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateReply(Board reply) {
		Connection conn = getConnection();
		int result = bdao.updateReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateOrigin(Board board) {
		Connection conn = getConnection();
		int result = bdao.updateOrigin(conn, board);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
}
