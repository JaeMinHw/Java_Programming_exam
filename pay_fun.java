package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class pay_fun extends JFrame {
	private Container c = getContentPane();

	JLabel p_1 = new JLabel("결제하시겠습니까?");
	JLabel p_2 = new JLabel();

	RoundedButton pay_btn = new RoundedButton("결제");
	RoundedButton pay_can = new RoundedButton("취소");

	int pay_succ;

	RoundJTextField tf = new RoundJTextField(10); // 총 가격 출력하는 텍스트 필드

	JTable table = new JTable();
	int cop_cou = 0;

	DefaultTableModel m = (DefaultTableModel) table.getModel();

	public void pay_fun(int sum_pr) {
		setTitle("결제 확인");

		setLayout(new BorderLayout(3, 3));

		setSize(500, 700);

		JPanel p1 = new JPanel(); // 메뉴 리스트라는 텍스트 출력
		JPanel p2 = new JPanel(); // 총 가격 출력
		JPanel p3 = new JPanel(); // 버튼 출력
		JPanel panel = new JPanel(new GridLayout(3, 0));

		p1.add(p_1);

		tf.setText("총   :    " + Integer.toString(sum_pr) + "원");
		tf.setFont(new Font("GOTHIC", Font.BOLD, 25));
		tf.setEditable(false);
		p2.setSize(10, 20);
		p2.add(tf);

		p3.add(pay_btn);
		p3.add(pay_can);
		panel.add(p1);
		panel.add(p2);

		panel.add(p3);

		add(panel, BorderLayout.NORTH);
		pay_btn.addActionListener(new pay_funButtonListener());
		pay_can.addActionListener(new pay_funButtonListener());

		setVisible(true);
	}

	// 버튼 누르면 반응하는 클래스
	class pay_funButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();
			DefaultTableModel m = (DefaultTableModel) table.getModel();

			if (log_comm.equals("결제")) {
				// 성공하면 return 1 - 창 닫기
				pay_succ = 1;

				// Jtable 초기화
				JOptionPane.showMessageDialog(null, "결제가 성공되었습니다.", "결제 성공", JOptionPane.PLAIN_MESSAGE);
				dispose();

			}

		}
	}

	public void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
