package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Manage_ extends JFrame {
	RoundedButton1 menu_modi = new RoundedButton1("메뉴 수정");
	RoundedButton1 total_sale = new RoundedButton1("매출 관리");
	RoundedButton1 info_manage = new RoundedButton1("정보 수정");

	Manage_() {
		setTitle("매장 관리");

		setLayout(new BorderLayout(10, 10));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);
	}

	public void show_third() {
		JPanel p1 = new JPanel();
		JPanel panel = new JPanel(new GridLayout(4, 4));

		p1.add(info_manage);
		p1.add(menu_modi);
		p1.add(total_sale);

		panel.add(p1);

		add(panel, BorderLayout.CENTER);
	}

}
