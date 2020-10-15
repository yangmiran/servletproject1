package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemp {

	//톰캣(WAS)이 제공하는 DBCP(DataBase Connection Pool)를 이용해서 연결 처리
	//content directory(web)/META-INF/context.xml 파일에 설정됨
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			//context.xml 에 <Resource> 앨리먼트의 설정값을 읽어와서 DBCP에서 Connection 을 받음
			Context initContext = new InitialContext(); //import javax.naming.InitialContext; 로 선택
			DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/oraDB"); //context.xml파일에 있는 Resource의 name="jdbc/oraDB"을 붙혀넣음
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
