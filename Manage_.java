package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manage_ extends JFrame {
	RoundedButton1 delete_mem = new RoundedButton1("결제 취소");
	RoundedButton1 total_sale = new RoundedButton1("매출 관리");
	RoundedButton1 info_manage = new RoundedButton1("정보 수정");
	JPanel p1 = new JPanel();
	JPanel panel = new JPanel(new GridLayout(4, 4));
	public String next_id;

	Manage_() {

		setTitle("매장 관리");

		setLayout(new BorderLayout(10, 10));
		show_third(login_mem.ID);
		setSize(300, 150);
		setVisible(true);
	}

	public void show_third(String id) {
		next_id = id;

		p1.add(info_manage);
		p1.add(delete_mem);
		p1.add(total_sale);

		panel.add(p1);

		add(panel, BorderLayout.CENTER);

		total_sale.addActionListener(new manage_ButtonListener());
		info_manage.addActionListener(new manage_ButtonListener());
		delete_mem.addActionListener(new manage_ButtonListener());
	}

	// 버튼 누르면 반응하는 클래스
	class manage_ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("정보 수정")) {
				// 가게의 정보 및 사장의 정보 수정할 수 있게
				// 재로그인 후 수정 가능하게
				modi_info info = new modi_info();
				info.modi_info();

			} else if (log_comm.equals("매출 관리")) {
				// 일매출 확인 및 월 매출 확인 가능하게.
				total_price tp = new total_price();
				tp.total_price();
			} else if (log_comm.equals("결제 취소")) {
				pay_list_print plp = new pay_list_print();
				plp.pay_can();
			}

		}

	}

}
