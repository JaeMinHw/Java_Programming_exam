package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class total_price extends JFrame {
	JPanel p1 = new JPanel();
	JPanel panel = new JPanel(new GridLayout(4, 4));
	RoundedButton1 total_day = new RoundedButton1("일 매출");
	RoundedButton1 total_month = new RoundedButton1("월 매출");

	public void total_price() {
		setTitle("매출 관리");
		setLayout(new BorderLayout(10, 10));
		show_1();
		setSize(300, 150);
		setVisible(true);
	}

	public void show_1() {
		p1.add(total_month);
		p1.add(total_day);

		panel.add(p1);

		add(panel, BorderLayout.CENTER);

		total_month.addActionListener(new price_ButtonListener());
		total_day.addActionListener(new price_ButtonListener());
	}

	// 버튼 누르면 반응하는 클래스
	class price_ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("일 매출")) {
				total_price_day tpd = new total_price_day();
				tpd.total_price_day();

			} else if (log_comm.equals("월 매출")) {
				total_price_month tpm = new total_price_month();
				tpm.total_price_month();
			}

		}

	}
}
