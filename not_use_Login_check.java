package java_programming_cla;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class not_use_Login_check {
	public int log_chec(String id, String pw) {
		database db;
		String id1 = id;
		String pw1 = pw;
		try {
			db = new database();
			String check_id = db.checkLogin(id1, pw1);

			if (check_id.equals("1")) {
				System.out.println("로그인되었습니다.");
				// login_mem에 데이터 저장 후 불러서 사용

				JOptionPane.showMessageDialog(null, "성공", "", JOptionPane.INFORMATION_MESSAGE);

				return 1;
			} else {
				System.out.println("로그인실패.");
				JOptionPane.showMessageDialog(null, "회원정보가 일치하지 않습니다.", "", JOptionPane.WARNING_MESSAGE);
				return 0;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}

	}

}
