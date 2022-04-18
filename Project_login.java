package java_programming_project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Project_login extends JFrame {
	JLabel l1 = new JLabel(" 아이디 ");
	JLabel l2 = new JLabel("비밀 번호");
	RoundedButton log_in = new RoundedButton("로그인");
	RoundedButton new_mem = new RoundedButton("회원가입");
	JTextField t1 = new JTextField(10);
	JPasswordField t2 = new JPasswordField(10);
	Project_login owner = this;

	Project_login() {
		setTitle(" POS 로그인 ");

		setLayout(new BorderLayout(10, 10));
		show_check();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);

	}

	public void show_check() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel panel = new JPanel(new GridLayout(3, 0));

		p1.add(l1);
		p1.add(t1);
		p2.add(l2);
		p2.add(t2);
		p3.add(log_in);
		p3.add(new_mem);

		panel.add(p1);
		panel.add(p2);
		panel.add(p3);

		add(panel, BorderLayout.NORTH);
		t2.setEchoChar('*');

		log_in.addActionListener(new loginButtonListener());
		new_mem.addActionListener(new newmemButtonListener());

	}

	// 로그인 버튼 누르면 반응하는 클래스
	class loginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("로그인")) {
				// 버튼 클릭 이벤트는 완료
				// 아무것도 없을 때
				if (t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "아이디 입력", JOptionPane.WARNING_MESSAGE);
					t1.grabFocus();
				} else if (t2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "비밀번호 입력", JOptionPane.WARNING_MESSAGE);
				} else {
					// Login_pop.java 파일 열기
					Login_pop check_log = new Login_pop();
					// check_log.Check_login();
				}

			}

		}

	}

	// 회원가입 버튼 누르면 반응하는 클래스
	class newmemButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String new_comm = e.getActionCommand();
			if (new_comm.equals("회원가입")) {
				New_Mem_Pop New_M = new New_Mem_Pop();
				New_M.New_Member();
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Project_login();
	}

}
