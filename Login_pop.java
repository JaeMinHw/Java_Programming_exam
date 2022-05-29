package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Login_pop extends JFrame {
	RoundedButton store_manage = new RoundedButton("매장 관리");
	RoundedButton order = new RoundedButton("주문");

	public void log_pop(String id) {
		database db;
		try {
			db = new database();
			String store_name = db.find_store(id);
			setTitle(store_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout(10, 10));
		show_second();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150);
		setVisible(true);

	}

	public void show_second() {
		JPanel p1 = new JPanel();
		JPanel panel = new JPanel(new GridLayout(3, 0));
		p1.add(store_manage);
		p1.add(order);

		panel.add(p1);
		add(panel, BorderLayout.CENTER);
		store_manage.addActionListener(new manageButtonListener());
		order.addActionListener(new manageButtonListener());
	}

	// 매장관리 버튼 누르면 반응하는 클래스
	class manageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("매장 관리")) {
				// 매장 관리를 누르면 메뉴 수정, 매출 등 관리 위주
				//
				System.out.println("매장 관리");
				Manage_ mana = new Manage_();
				mana.show_third();

			} else if (log_comm.equals("주문")) {
				// 주문을 누르면 pos UI 나오게
				System.out.println("주문");
			}

		}

	}

}
