package java_programming_cla;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Order_ extends JFrame {
	private Container c = getContentPane();
	int for_table_count;

	String ColName[] = { "메뉴", "수량", "가격" };
	String Data[][];
	JButton SBtn[] = new JButton[3];
	String Str[] = { "선택취소", "전체취소", "결제" };
	DefaultTableModel model = new DefaultTableModel(Data, ColName);
	StrBtn sbtn = new StrBtn();

	int count = 0;
	int sum_pri = 0;
	String take_id;

	RoundJTextField tf = new RoundJTextField(30); // 총 가격 출력하는 텍스트 필드
	JTable table = new JTable(model);

	JButton[] coffee = new JButton[15];
	JButton[] drink = new JButton[15];
	JButton[] tea = new JButton[15];
	JButton[] dessert = new JButton[15];
	JButton[] els = new JButton[15];

	JPanel category = new JPanel();
	JPanel tableBorder = new JPanel();
	JPanel sele = new JPanel();

	JPanel menuBorder1 = new JPanel();
	JPanel menuBorder2 = new JPanel();
	JPanel menuBorder3 = new JPanel();
	JPanel menuBorder4 = new JPanel();
	JPanel menuBorder5 = new JPanel();

	JPanel seleBorder = new JPanel();

	JButton category1 = new JButton("커피");
	JButton category2 = new JButton("음료");
	JButton category3 = new JButton("티");
	JButton category4 = new JButton("디저트");
	JButton category5 = new JButton("기타");

	public void TestFrame(String id) {

		take_id = id;

		setLayout(null);
		setTitle(login_mem.store_name);
		setSize(1000, 1000);
		setLocationRelativeTo(null);

		Screen sc = new Screen();
		tableBorder.add(sc);
		tableBorder.setSize(500, 500);
		tableBorder.setLocation(25, 20);
		// 금액란
		tf.setSize(450, 70);
		tf.setLocation(50, 480);
		add(tf);

		sbtn.setSize(400, 70);
		sbtn.setLocation(530, 480);
		add(sbtn);

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

		c.add(tableBorder);
		c.add(category);
		c.add(menuBorder1);
		c.add(menuBorder2);
		c.add(menuBorder3);
		c.add(menuBorder4);
		c.add(menuBorder5);
		c.add(seleBorder);
		reMove();
		category1.addActionListener(new cateButtonListener());
		category2.addActionListener(new cateButtonListener());
		category3.addActionListener(new cateButtonListener());
		category4.addActionListener(new cateButtonListener());
		category5.addActionListener(new cateButtonListener());

		for (int i = 0; i < Str.length; i++) {
			SBtn[i].addActionListener(new SBtnButtonListener());
		}

		setVisible(true);

		for (int i = 0; i < 15; i++) {
			coffee[i].addActionListener(new menuButtonListener());
			drink[i].addActionListener(new menuButtonListener());
			tea[i].addActionListener(new menuButtonListener());
			dessert[i].addActionListener(new menuButtonListener());
			els[i].addActionListener(new menuButtonListener());
		}

	}

	// 메뉴 버튼 눌렀을 때 가격 출력하게 ->
	// 버튼이 눌리면 그 버튼의 텍스트 값을 db에 넘겨서 select 비교 후 가격 가져오기

	// 텍스트 값이 null이면 추가하지 말기
	class menuButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();
			int price = 0;

			int rowCont = table.getRowCount();
			int flag = 0;
			JButton coffee = (JButton) e.getSource();
			DefaultTableModel m = (DefaultTableModel) table.getModel();

			database db;

			try {
				db = new database();
				price = db.menu_price(log_comm);
				sum_pri += price;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < rowCont; i++) {
				if (table.getValueAt(i, 0).equals(coffee.getText())) {
					flag = 1;
					table.setValueAt((int) table.getValueAt(i, 1) + 1, i, 1);
					table.setValueAt((int) table.getValueAt(i, 2) + price, i, 2);
					break;
				}
			}
			if (flag == 0) {
				if (log_comm != "") {
					m.addRow(new Object[] { log_comm, count + 1, price });
				}
			}

			tf.setText("총  :	" + Integer.toString(sum_pri) + "원");
			tf.setFont(new Font("GOTHIC", Font.BOLD, 25));

		}

	}

	class Screen extends JPanel {
		Screen() {
			/* setBackground(Color.WHITE); */
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			table.setRowHeight(30);
			table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	}

	// 선택 취소, 결제, 전체 취소 버튼 생성
	class StrBtn extends JPanel {
		StrBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 4, 3, 3));

			for (int i = 0; i < Str.length; i++) {
				SBtn[i] = new JButton(Str[i]);
				add(SBtn[i]);
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

	// 선택 취소, 전체 취소, 결제 눌렀을 때 액션
	// 버튼 누르면 반응하는 클래스
	class SBtnButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("선택취소")) {
				JButton MBtn = (JButton) e.getSource();
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				tf.setText(String.valueOf(""));
				int choose_pri = (int) table.getValueAt(table.getSelectedRow(), 2);
				sum_pri -= choose_pri;
				m.removeRow(table.getSelectedRow());
				tf.setText("총  :	" + Integer.toString(sum_pri) + "원");

			} else if (log_comm.equals("전체취소")) {
				JButton MBtn = (JButton) e.getSource();
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				sum_pri = 0;
				m.setRowCount(0);
				tf.setText(String.valueOf(""));
			} else if (log_comm.equals("결제")) {
				int cou = table.getRowCount();
				int j;
				int print = 0;
				String choose_me;
				int choose_co = 0;
				int choose_pr = 0;
				String ch_sum_pri;
				ch_sum_pri = String.valueOf(sum_pri);

				database db;

				DefaultTableModel m = (DefaultTableModel) table.getModel();
				System.out.println(cou);
				pay_fun p_f = new pay_fun();
				p_f.pay_fun(sum_pri);

				if (cou != 0) {
					try {
						db = new database();
						db.insert_total(take_id, ch_sum_pri); // 가게 이름
						for (j = 0; j < cou; j++) {
							choose_me = (String) table.getValueAt(j, 0);
							choose_co = (int) table.getValueAt(j, 1);
							choose_pr = (int) table.getValueAt(j, 2);
							print = db.insert_pay1(choose_me, String.valueOf(choose_co), String.valueOf(choose_pr));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				m.setRowCount(0);
				tf.setText(String.valueOf(""));
			}
		}

	}

	// 계산기 버튼 만들어서 총 금액에서 +,- 가능하게

	public void reMove() {
		c.remove(menuBorder1);
		c.remove(menuBorder2);
		c.remove(menuBorder3);
		c.remove(menuBorder4);
		c.remove(menuBorder5);
	}

	public static void main(String[] args) {
		new Order_();
	}
}
