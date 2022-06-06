package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class modify extends JDialog {
	private String[] tel = { "SKT", "KT", "LGU+", "기타 통신사" };
	JLabel new_l0 = new JLabel("가게 이름");
	JLabel new_l1 = new JLabel("가게 전화번호");
	JLabel new_l2 = new JLabel("대표 이름");
	JLabel new_l3 = new JLabel("대표 전화번호");
	JLabel new_l4 = new JLabel(" 아이디 ");
	JLabel new_l5 = new JLabel("비밀 번호");

	RoundedButton new_join = new RoundedButton("수정하기");
	RoundedButton delete_mem = new RoundedButton("회원탈퇴");

	JTextField new_t0 = new JTextField(10);
	JComboBox<String> strtel = new JComboBox<String>(tel);
	JTextField new_t1 = new JTextField(11);
	JTextField new_t2 = new JTextField(5);
	JTextField new_t3 = new JTextField(11);
	JComboBox<String> mastertel = new JComboBox<String>(tel);
	JTextField new_t4 = new JTextField(10);

	JPasswordField new_t5 = new JPasswordField(10);
	JPanel join_panel = new JPanel(new GridLayout(7, 0));

	modify() {
		setTitle("회원정보 수정");
		setLayout(new BorderLayout(10, 10));
		new_t0.setText(login_mem.store_name);
		new_t1.setText(login_mem.store_tel);
		new_t2.setText(login_mem.master_name);
		new_t3.setText(login_mem.master_tel);
		new_t4.setText(login_mem.ID);
		new_t4.setEditable(false);
		JPanel n_p1 = new JPanel();
		JPanel n_p2 = new JPanel();
		JPanel n_p3 = new JPanel();
		JPanel n_p4 = new JPanel();
		JPanel n_p5 = new JPanel();
		JPanel n_p6 = new JPanel();
		JPanel n_p7 = new JPanel();

		n_p1.add(new_l0);
		n_p1.add(new_t0); // 가게 이름

		n_p2.add(new_l1);
		n_p2.add(new_t1);// 가게 전화번호

		n_p3.add(new_l2);
		n_p3.add(new_t2); // 대표 이름

		n_p4.add(new_l3);
		n_p4.add(new_t3);// 대표 전화번호

		n_p5.add(new_l4);
		n_p5.add(new_t4); // 아이디

		n_p6.add(new_l5);
		n_p6.add(new_t5); // 비밀번호

		n_p7.add(new_join); // 가입하기 버튼
		n_p7.add(delete_mem);

		join_panel.add(n_p1);
		join_panel.add(n_p2);
		join_panel.add(n_p3);
		join_panel.add(n_p4);
		join_panel.add(n_p5);
		join_panel.add(n_p6);
		join_panel.add(n_p7);
		add(join_panel, null);

		new_join.addActionListener(new modiButtonListener());
		delete_mem.addActionListener(new modiButtonListener());
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
	}

	// 수정 버튼을 누르면 반응하는 클래스
	class modiButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String log_comm = e.getActionCommand();

			String a = new_t0.getText();
			String b = new_t1.getText();
			String c = new_t2.getText();
			String d = new_t3.getText();
			String f = new_t4.getText();
			String g = new_t5.getText();
			System.out.println(f);
			database db;
			if (log_comm.equals("수정하기")) {
				try {
					db = new database();
					int check_id = db.modi_infomat(new String[] { a, b, c, d, f, g });
					if (check_id == 1) {
						JOptionPane.showMessageDialog(null, "회원 정보 수정이 성공적으로 완료되었습니다.", "정보 수정 성공",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "회원 정보를 다시 확인하여주세요", "정보 수정 실패",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (log_comm.equals("회원탈퇴")) {
				try {
					db = new database();
					int check_id = db.delete_member(f, g);
					if (check_id == 1) {
						JOptionPane.showMessageDialog(null, "회원 정보 수정이 성공적으로 완료되었습니다.", "정보 수정 성공",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "회원 정보를 다시 확인하여주세요", "정보 수정 실패",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
