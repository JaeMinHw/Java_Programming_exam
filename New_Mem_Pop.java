package java_programming_project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class New_Mem_Pop extends JDialog {
	private String[] tel = { "SKT", "KT", "LGU+", "기타 통신사" };
	JLabel new_l0 = new JLabel("가게 이름");
	JLabel new_l1 = new JLabel("가게 전화번호");
	JLabel new_l2 = new JLabel("대표 이름");
	JLabel new_l3 = new JLabel("대표 전화번호");
	JLabel new_l4 = new JLabel(" 아이디 ");
	JLabel new_l5 = new JLabel("비밀 번호");

	JButton new_join = new JButton("가입하기");

	JTextField new_t0 = new JTextField(10);
	JComboBox<String> strtel = new JComboBox<String>(tel);
	JTextField new_t1 = new JTextField(3);
	JTextField new_t1_1 = new JTextField(4);
	JTextField new_t1_2 = new JTextField(4);
	JTextField new_t2 = new JTextField(5);
	JTextField new_t3 = new JTextField(3);
	JTextField new_t3_1 = new JTextField(4);
	JTextField new_t3_2 = new JTextField(4);
	JComboBox<String> mastertel = new JComboBox<String>(tel);
	JTextField new_t4 = new JTextField(10);
	JPasswordField new_t5 = new JPasswordField(10);

	New_Mem_Pop() {
		setTitle("회원가입");
		setLayout(new BorderLayout(10, 10));

		JPanel n_p1 = new JPanel();
		JPanel n_p2 = new JPanel();
		JPanel n_p3 = new JPanel();
		JPanel n_p4 = new JPanel();
		JPanel n_p5 = new JPanel();
		JPanel n_p6 = new JPanel();
		JPanel join_panel = new JPanel(new GridLayout(6, 0));

		n_p1.add(new_l0);
		n_p1.add(new_t0); // 가게 이름

		n_p2.add(new_l1);
		n_p2.add(strtel);
		n_p2.add(new_t1);
		n_p2.add(new_t1_1);
		n_p2.add(new_t1_2);// 가게 전화번호

		n_p3.add(new_l2);
		n_p3.add(new_t2); // 대표 이름

		n_p4.add(new_l3);
		n_p4.add(mastertel);
		n_p4.add(new_t3);
		n_p4.add(new_t3_1);
		n_p4.add(new_t3_2);// 대표 전화번호

		n_p5.add(new_l4);
		n_p5.add(new_t4); // 아이디

		n_p6.add(new_l5);
		n_p6.add(new_t5); // 비밀번호

		n_p6.add(new_join); // 가입하기 버튼

		join_panel.add(n_p1);
		join_panel.add(n_p2);
		join_panel.add(n_p3);
		join_panel.add(n_p4);
		join_panel.add(n_p5);
		join_panel.add(n_p6);
		add(join_panel, BorderLayout.CENTER);

		new_join.addActionListener(new signButtonListener());

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
	}

	// 회원가입 버튼을 누르면 반응하는 클래스
	class signButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

	public void New_Member() {

	}
}
