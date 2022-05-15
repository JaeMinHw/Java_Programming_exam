package java_programming_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBdemo {
	private Connection conn;
	private static final String USERNAME = "root";
	private static final String PASSWOED = "1234";
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";

	public DBdemo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("데이터베이스 연결중..");
			conn = DriverManager.getConnection(URL, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다");
		} catch (SQLException e) {
			System.out.println("데이터 베이스 연결 실패");
		}
	}

	// 데이터 타입에 맞게 수정하기
	public void insertBoard(String a, String b, String c, String d, String f, String g) {
		String sql = "insert into javadb values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			pstmt.setString(3, c);
			pstmt.setString(4, d);
			pstmt.setString(5, f);
			pstmt.setString(6, g);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("Board 데이터 삽입 성공");

			}
		} catch (Exception e) {
			System.out.println("Board 데이터 삽입 실패!");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
