package java_programming_cla;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class modi_menu extends JFrame {
	private Container c = getContentPane();

	String menu_name = null;
	int menu_price = 0;

	JButton[] coffee = new JButton[15];
	JButton[] drink = new JButton[15];
	JButton[] tea = new JButton[15];
	JButton[] dessert = new JButton[15];
	JButton[] els = new JButton[15];

	JPanel category = new JPanel();

	JPanel menuBorder1 = new JPanel();
	JPanel menuBorder2 = new JPanel();
	JPanel menuBorder3 = new JPanel();
	JPanel menuBorder4 = new JPanel();
	JPanel menuBorder5 = new JPanel();

	JButton category1 = new JButton("커피");
	JButton category2 = new JButton("음료");
	JButton category3 = new JButton("티");
	JButton category4 = new JButton("디저트");
	JButton category5 = new JButton("기타");

	public void modi_frame() {

		setLayout(null);
		setTitle(login_mem.store_name);
		setSize(1000, 1000);
		setLocationRelativeTo(null);

		category.setLayout(new GridLayout(1, 5, 3, 3));
		category.add(category1);
		category.add(category2);
		category.add(category3);
		category.add(category4);
		category.add(category5);
		category.setSize(450, 70);
		category.setLocation(530, 23);

		menuBorder1.setLayout(new GridLayout(5, 3, 3, 3));
		menuBorder1.setSize(450, 300);
		menuBorder1.setLocation(530, 100);

		menuBorder2.setLayout(new GridLayout(5, 3, 3, 3));
		menuBorder2.setSize(450, 300);
		menuBorder2.setLocation(530, 100);

		menuBorder3.setLayout(new GridLayout(5, 3, 3, 3));
		menuBorder3.setSize(450, 300);
		menuBorder3.setLocation(530, 100);

		menuBorder4.setLayout(new GridLayout(5, 3, 3, 3));
		menuBorder4.setSize(450, 300);
		menuBorder4.setLocation(530, 100);

		menuBorder5.setLayout(new GridLayout(5, 3, 3, 3));
		menuBorder5.setSize(450, 300);
		menuBorder5.setLocation(530, 100);

		for (int i = 0; i < coffee.length; i++) {
			menuBorder1.add(coffee[i] = new JButton());
			menuBorder2.add(drink[i] = new JButton());
			menuBorder3.add(tea[i] = new JButton());
			menuBorder4.add(dessert[i] = new JButton());
			menuBorder5.add(els[i] = new JButton());
		}

		c.add(category);
		c.add(menuBorder1);
		c.add(menuBorder2);
		c.add(menuBorder3);
		c.add(menuBorder4);
		c.add(menuBorder5);

		category1.addActionListener(new cateButtonListener());
		category2.addActionListener(new cateButtonListener());
		category3.addActionListener(new cateButtonListener());
		category4.addActionListener(new cateButtonListener());
		category5.addActionListener(new cateButtonListener());

		setVisible(true);

		for (int i = 0; i < 15; i++) {
			coffee[i].addActionListener(new modi_menuButtonListener());
			drink[i].addActionListener(new modi_menuButtonListener());
			tea[i].addActionListener(new modi_menuButtonListener());
			dessert[i].addActionListener(new modi_menuButtonListener());
			els[i].addActionListener(new modi_menuButtonListener());
		}
	}

	class modi_menuButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String log_comm = e.getActionCommand();

			JButton coffee = (JButton) e.getSource();
			database db;
			try {
				db = new database();
				menu_name = coffee.getText();
				if (menu_name == null) {
					// 메뉴가 비어있으면 비어 있는 값
				} else {
					menu_price = db.menu_pri(menu_name);
					modi_menu_pop mmp = new modi_menu_pop();
					mmp.modi_menu_pop(menu_name, menu_price);

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	// 메뉴 버튼에 대한 액션 리스너

	// 버튼 누르면 반응하는 클래스
	class cateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			database db;

			if (log_comm.equals("커피")) {
				// 커피 카테고리를 누르면
				try {
					db = new database();
					String[] take_na = db.click_cate("커피");
					for (int i = 0; i < take_na.length - 1; i++) {
						coffee[i].setText(take_na[i]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reMove();
				c.add(menuBorder1);
				menuBorder1.revalidate();
				menuBorder1.repaint();

			} else if (log_comm.equals("음료")) {
				// 음료 카테고리를 누르면
				try {
					db = new database();
					String[] take_na = db.click_cate("음료");
					for (int i = 0; i < take_na.length - 1; i++) {
						drink[i].setText(take_na[i]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reMove();
				c.add(menuBorder2);
				menuBorder2.revalidate();
				menuBorder2.repaint();
			} else if (log_comm.equals("티")) {
				// 티 카테고리를 누르면
				try {
					db = new database();
					String[] take_na = db.click_cate("티");
					for (int i = 0; i < take_na.length - 1; i++) {
						tea[i].setText(take_na[i]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reMove();
				c.add(menuBorder3);
				menuBorder3.revalidate();
				menuBorder3.repaint();
			} else if (log_comm.equals("디저트")) {
				// 디저트 카테고리를 누르면
				try {
					db = new database();
					String[] take_na = db.click_cate("디저트");
					for (int i = 0; i < take_na.length - 1; i++) {
						dessert[i].setText(take_na[i]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reMove();
				c.add(menuBorder4);
				menuBorder4.revalidate();
				menuBorder4.repaint();
			} else if (log_comm.equals("기타")) {
				// 기타 카테고리를 누르면
				try {
					db = new database();
					String[] take_na = db.click_cate("기타");
					for (int i = 0; i < take_na.length - 1; i++) {
						els[i].setText(take_na[i]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reMove();
				c.add(menuBorder5);
				menuBorder5.revalidate();
				menuBorder5.repaint();
			}

		}

	}

	public void reMove() {
		c.remove(menuBorder1);
		c.remove(menuBorder2);
		c.remove(menuBorder3);
		c.remove(menuBorder4);
		c.remove(menuBorder5);
	}
}
