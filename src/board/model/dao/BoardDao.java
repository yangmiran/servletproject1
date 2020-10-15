package board.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;

public class BoardDao {

	public ArrayList<Board> selectTop3(Connection conn) {
		ArrayList<Board> list = new ArrayList<Board>();
		Statement stmt = null;
		ResultSet rset = null;
		
		//query문 SQL 에서 작성해서 가져옴
		String query = "SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, BOARD_NUM, BOARD_TITLE, BOARD_READCOUNT " + 
				"      FROM (SELECT * FROM BOARD " + 
				"            WHERE BOARD_LEVEL = 0 " + 
				"            ORDER BY BOARD_READCOUNT DESC)) " + 
				"WHERE RNUM >= 1 AND RNUM <= 3";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBoardNum(rset.getInt("board_num"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from board";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public ArrayList<Board> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, BOARD_NUM, BOARD_TITLE, BOARD_WRITER, BOARD_CONTENT, BOARD_ORIGINAL_FILENAME, BOARD_RENAME_FILENAME, " + 
				"        BOARD_DATE, BOARD_LEVEL, BOARD_REF, BOARD_REPLY_REF, BOARD_REPLY_SEQ, BOARD_READCOUNT " + 
				"      FROM (SELECT * FROM BOARD " + 
				"            ORDER BY BOARD_REF DESC, BOARD_REPLY_REF DESC, BOARD_LEVEL ASC, BOARD_REPLY_SEQ ASC)) " + 
				"WHERE RNUM >= ? AND RNUM <= ?";
		
		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				
				board.setBoardNum(rset.getInt("board_num"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardLevel(rset.getInt("board_level"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertOriginBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into board values ((select max(board_num) + 1 from board), ?, ?, ?, ?, ?, "
				+ "sysdate, 0, (select max(board_num) + 1 from board), default, default, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardWriter());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getBoardOriginalFileName());
			pstmt.setString(5, board.getBoardRenameFileName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int addReadCount(Connection conn, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set board_readcount = board_readcount + 1 where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Board selectBoard(Connection conn, int boardNum) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from board where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				
				board.setBoardNum(boardNum);
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardLevel(rset.getInt("board_level"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}

	public int updateReplySeq(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		if(reply.getBoardLevel() == 1) {
			query = "update board set board_reply_seq = board_reply_seq + 1 where board_ref = ? and board_level = ?";
		}
		if(reply.getBoardLevel() == 2) {
			query = "update board set board_reply_seq = board_reply_seq + 1 where board_ref = ? and board_level = ? and board_reply_ref = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getBoardRef());
			pstmt.setInt(2, reply.getBoardLevel());
			if(reply.getBoardLevel() == 2) {
				pstmt.setInt(3, reply.getBoardReplyRef());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertReply(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		if(reply.getBoardLevel() == 1) {
			query = "insert into board values ((select max(board_num) + 1 from board), ?, ?, ?, null, null, "
					+ "sysdate, 1, ?, (select max(board_num) + 1 from board), ?, default)";
		}
		if(reply.getBoardLevel() == 2) {
			query = "insert into board values ((select max(board_num) + 1 from board), ?, ?, ?, null, null, "
					+ "sysdate, 2, ?, ?, ?, default)";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getBoardTitle());
			pstmt.setString(2, reply.getBoardWriter());
			pstmt.setString(3, reply.getBoardContent());
			pstmt.setInt(4, reply.getBoardRef());
			if(reply.getBoardLevel() == 1) {
				pstmt.setInt(5, reply.getBoardReplySeq());
			}
			if(reply.getBoardLevel() == 2) {
				pstmt.setInt(5, reply.getBoardReplyRef());
				pstmt.setInt(6, reply.getBoardReplySeq());
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int boardNum, int boardLevel) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from board ";
		
		if(boardLevel == 0)
				query += "where board_ref = ?";
		if(boardLevel == 1)
			query += "where board_reply_ref = ?";
		if(boardLevel == 2)
			query += "where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}

	public int updateReply(Connection conn, Board reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set board_title = ?, board_content = ? where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getBoardTitle());
			pstmt.setString(2, reply.getBoardContent());
			pstmt.setInt(3, reply.getBoardNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}

	public int updateOrigin(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board set board_title = ?, board_content = ?, board_original_filename = ?, board_rename_filename = ? where board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardOriginalFileName());
			pstmt.setString(4, board.getBoardRenameFileName());
			pstmt.setInt(5, board.getBoardNum());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}

}
