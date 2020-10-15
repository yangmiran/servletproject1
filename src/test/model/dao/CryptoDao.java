package test.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.model.vo.CryptoMember;

public class CryptoDao {
	public CryptoDao() {}
	
	public CryptoMember loginCheck(Connection conn, String userid, String userpwd) {
		CryptoMember member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from crypto_member where userid = ? and userpwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new CryptoMember();
				
				member.setUserid(userid);
				member.setUserpwd(userpwd);
				member.setUsername(rset.getString("username"));
				member.setGender(rset.getString("gender"));
				member.setAge(rset.getInt("age"));
				member.setPhone(rset.getString("phone"));
				member.setEmail(rset.getString("email"));
				member.setHobby(rset.getString("hobby"));
				member.setEtc(rset.getString("etc"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public int insertMember(Connection conn, CryptoMember member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into crypto_member values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpwd());
			pstmt.setString(3, member.getUsername());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getEtc());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
