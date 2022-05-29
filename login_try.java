package java_programming_cla;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class login_try {
	// database 파일 열어서 checkLogin 실행하기
	public void check_log(String id, String pw) {
		database db;
		String id1 = id;
		String pw1 = pw;
		try {
			db = new database();
			String check_id = db.checkLogin(id1, pw1);
			if (check_id.equals("1")) {
				System.out.println("로그인되었습니다.");
				JOptionPane.showMessageDialog(null, "로그인 성공", "", JOptionPane.WARNING_MESSAGE);
				Login_pop success = new Login_pop();
				success.log_pop(id1);
			} else {
				System.out.println("로그인실패.");
				JOptionPane.showMessageDialog(null, "회원정보가 일치하지 않습니다.", "", JOptionPane.WARNING_MESSAGE);

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
