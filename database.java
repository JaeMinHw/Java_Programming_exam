package java_programming_cla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	Statement st;
	Connection conn;

	// 처음 데이터 베이스 연결을 위한 메서드
	public database() throws SQLException {
		String user = "root";
		String password = "1234";
		String url = "jdbc:mysql://localhost:3306/mydb";
		conn = DriverManager.getConnection(url, user, password);
		st = conn.createStatement();
		System.out.println(conn.toString());
	}

	public String checkId(String id) throws SQLException {
		String returnNum;
		String str = "Select count(*) from user where ID='" + id + "';";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			returnNum = query.getString(1);

		}
		return query.getString(1);
	}

	// 로그인 하기 위한 메서드
	public String checkLogin(String id, String pw) throws SQLException {
		String returnNum;
		String str = "Select count(*) from user where ID = '" + id + "' and PW = '" + pw + "';";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			returnNum = query.getString(1);
		}
		return query.getString(1);
	}

	public String SelectOne(String from, String where, String Condition, String get) {
		String returnNum = null;
		String str = "select " + get + " from " + from + " where " + where + " = ?;";
		ResultSet query = spendQuerys(str, new String[] { Condition });
		try {
			if (query.next()) {
				returnNum = query.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnNum;
	}

	// 카테고리를 클릭했을 때
	public String[] click_cate(String cate_name) throws SQLException {
		int a = 0;
		String sql = "";
		int i = 0;
		if (cate_name.equals("커피")) {
			sql = "select menu from menu_manage where category = 1";
			a = count_menu(1);
		} else if (cate_name.equals("음료")) {
			sql = "select menu from menu_manage where category = 2";
			a = count_menu(2);
		} else if (cate_name.equals("티")) {
			sql = "select menu from menu_manage where category = 3";
			a = count_menu(3);
		} else if (cate_name.equals("디저트")) {
			sql = "select menu from menu_manage where category = 4";
			a = count_menu(4);
		} else if (cate_name.equals("기타")) {
			sql = "select menu from menu_manage where category = 5";
			a = count_menu(5);
		}

		ResultSet query = spendQuery(sql);

		String[] menu_na = new String[a];

		if (query.next()) {
			for (i = 0; query.next(); i++) {
				menu_na[i] = query.getString("menu");
			}
		}
		return menu_na;
	}

	// 카테고리에 해당하는 메뉴의 개수가 몇개인지
	public int count_menu(int a) throws SQLException {
		int returnNum = 0;
		String str = "Select count(*) from menu_manage where category ='" + a + "';";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			returnNum = query.getInt(1);
		}
		return returnNum;
	}

	// 회원가입을 위한 메서드
	public int insertSign(String[] infor) throws SQLException {
		String str = "insert into user values(?,?,?,?,?,?)";

		int query = spendUpdate(str, infor);

		return query;
	}

	private ResultSet spendQuerys(String str, String[] infor) {
		ResultSet query = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			for (int i = 1; i < infor.length + 1; i++) {
				pstmt.setString(i, infor[i - 1]);
			}
			query = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}

	private int spendUpdate(String str, String[] infor) {
		int query = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			for (int i = 0; i < infor.length; i++) {
				pstmt.setString(i + 1, infor[i]);
			}
			query = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}

	private ResultSet spendQuery(String str) {
		ResultSet query = null;
		try {
			query = st.executeQuery(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(query);
		return query;
	}

	// 식당 이름 찾는 메서드
	public String find_store(String id) throws SQLException {
		String returnNum;
		String str = "Select store_name from user where ID='" + id + "';";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			returnNum = query.getString(1);

		}
		return query.getString(1);
	}

}
