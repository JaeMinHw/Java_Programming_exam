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
		String returnNum = null;

		String str = "Select count(*) from user where ID = '" + id + "' and PW = '" + pw + "';";

		save_infoma(id, pw);
		ResultSet query = spendQuery(str);

		if (query.next()) {
			returnNum = query.getString(1);
		}

		return query.getString(1);
	}

	// 로그인 한 정보 저장
	public void save_infoma(String id, String pw) throws SQLException {
		String[] save_info = {};
		int i = 0;

		String sql = "Select * from user where ID = '" + id + "' and PW = '" + pw + "';";
		ResultSet query1 = spendQuery(sql);
		while (query1.next()) {
			login_mem.store_name = query1.getString(1);
			login_mem.store_tel = query1.getString(2);
			login_mem.master_name = query1.getString(3);
			login_mem.master_tel = query1.getString(4);
			login_mem.pw = query1.getString(6);
		}

		login_mem.ID = id;

		System.out.println(login_mem.store_name);

	}

//	 회원 정보 수정을 위한 메서드
	public int modi_infomat(String[] infor) throws SQLException {
		String sql = "update user set store_name = '" + infor[0] + "', store_tel = '" + infor[1] + "', master_name = '"
				+ infor[2] + "', master_tel = '" + infor[3] + "', pw = '" + infor[5] + "' where ID = '" + infor[4]
				+ "';";
		PreparedStatement stmt = null;
		int count = 0;

		try {
			stmt = conn.prepareStatement(sql);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
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
		String returnNum = null;
		String str = "Select store_name from user where ID = '" + id + "';";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			returnNum = query.getString(1);
		}
		return returnNum;
	}

	// 버튼이 눌리 메뉴의 가격 출력
	public int menu_price(String menu) throws SQLException {
		int price = 0;
		String sql = "select price from menu_manage where menu = '" + menu + "';";
		ResultSet query = spendQuery(sql);
		if (query.next()) {
			price = query.getInt(1);
		}

		return price;
	}

	public String find_pay_code() throws SQLException {
		String max_pay = null;
		String str = "select max(pay_code) from pay_list";
		ResultSet query = spendQuery(str);
		if (query.next()) {
			max_pay = query.getString(1);
		}

		return max_pay;
	}

	// 주문한 메뉴 집어넣기
	public int insert_pay1(String menu_name, String menu_co, String menu_pr) throws SQLException {
		String str = "insert into pay_list_arry (pay_code,menu,count,menu_price) values(?,?,?,?)";

		String max_pa = find_pay_code();
		String[] info = { max_pa, menu_name, menu_co, menu_pr };
		int query = 0;
		query = spendUpdate(str, info);

		return query;

	}

	// 총 가격 집어넣기
	public int insert_total(String store, String total_pri) throws SQLException {
		String str = "insert into pay_list (store_name,total_price) values(?,?)";
		String conver_store = find_store(store);
		String[] info = { conver_store, total_pri };

		int query = spendUpdate(str, info);
		return query;

	}

	// 일 매출 게산하기
	// select * from pay_list where date_format(pay_time,'%d') = '04'; 이런 sql 사용
	// select * from pay_list where date_format(pay_time,'%Y') = '2022'
	// and date_format(pay_time,'%m') = '06' and date_format(pay_time,'%d') = '04';
	public int day_total_pri(String year, String month, String day) throws SQLException {
		int day_total = 0;
		String sql = "select * from pay_list where store_name = '" + login_mem.store_name
				+ "' and date_format(pay_time,'%Y') = '" + year + "' " + "and date_format(pay_time,'%m') = '" + month
				+ "' and date_format(pay_time,'%d') = '" + day + "';";

		ResultSet query = spendQuery(sql);
		while (query.next()) {
			day_total += query.getInt(4);
		}

		return day_total;
	}

	// 월 매출 계산하기
	// select * from pay_list where date_format(pay_time,'%m') = '06';
	public int month_total_pri(String year, String month) throws SQLException {
		int month_total = 0;
		String sql = "select * from pay_list where store_name = '" + login_mem.store_name
				+ "' and date_format(pay_time,'%Y') = '" + year + "' " + "and date_format(pay_time,'%m') = '" + month
				+ "';";

		ResultSet query = spendQuery(sql);
		while (query.next()) {
			month_total += query.getInt(4);
		}

		return month_total;
	}

	// 회원 탈퇴
	// delete from user where ID = 'id' and pw = 'pw';
	public int delete_member(String ID, String PW) throws SQLException {
		String sql = "delete from user where ID = '" + ID + "' and pw = '" + PW + "';";
		String[] info = { ID, PW };
		int query = spendUpdate(sql, info);
		return query;
	}

}
