package java_programming_cla;

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

public class modi_info extends JFrame {
	JLabel l1 = new JLabel(" 아이디 ");
	JLabel l2 = new JLabel("비밀 번호");
	JTextField t1 = new JTextField(10);
	JPasswordField t2 = new JPasswordField(10);

	RoundedButton log_in = new RoundedButton("본인 확인");

	public void modi_info() {
		// 로그인 확인하기
		setTitle("본인 확인");

		setLayout(new BorderLayout(3, 1));
		check_you();
		setSize(300, 150);
		setVisible(true);
	}

	public void check_you() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel panel = new JPanel(new GridLayout(3, 0));
		p1.add(l1);
		p1.add(t1);
		p2.add(l2);
		p2.add(t2);

		p3.add(log_in);

		panel.add(p1);
		panel.add(p2);
		panel.add(p3);

		add(panel, BorderLayout.CENTER);

		log_in.addActionListener(new loginButtonListener());
	}

	// 버튼 누르면 반응하는 클래스
	class loginButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			String a = t1.getText();
			String b = t2.getText();

			if (log_comm.equals("본인 확인")) {
				// 버튼 클릭 이벤트는 완료
				// 아무것도 없을 때
				if (t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "아이디 입력", JOptionPane.WARNING_MESSAGE);
					t1.grabFocus();
				} else if (t2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "비밀번호 입력", JOptionPane.WARNING_MESSAGE);
				} else {
					// database 파일 열어서 checkLogin 실행하기

					not_use_Login_check log_c = new not_use_Login_check();
					int i = log_c.log_chec(a, b);
					if (i == 1) { // 본인확인 성공
						// 로그인 한 아이디에 대한 정보를 수정할 수 있는 함수 만들기 입력한 id값 가지고 modify 함수로 넘기기
						modify modi = new modify();
						dispose();
					} else { // 본인확인 실패
						// 다시 입력하게
					}
				}

			}

		}

	}

}
