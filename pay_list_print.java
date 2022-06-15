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

public class pay_list_print extends JFrame {
	private Container c = getContentPane();

	String ColName[] = { "코드", "가게 이름", "날짜", "결제 가격" };
	String Data[][];
	String Str[] = { "결제 취소" };
	JButton SBtn[] = new JButton[1];
	DefaultTableModel model = new DefaultTableModel(Data, ColName);
	StrBtn sbtn = new StrBtn();
	JPanel seleBorder = new JPanel();
	int count = 0;
	int sum_pri = 0;
	String take_id;

	JPanel tableBorder = new JPanel();

	JTable table = new JTable(model);

	public void pay_can() {
		setLayout(null);
		setTitle(login_mem.store_name);
		setSize(1000, 1000);
		setLocationRelativeTo(null);

		Screen sc = new Screen();
		tableBorder.add(sc);
		tableBorder.setSize(500, 500);
		c.add(tableBorder);
		tableBorder.setLocation(25, 20);
		sbtn.setSize(400, 70);
		sbtn.setLocation(530, 700);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		c.add(seleBorder);
		add(sbtn);
		show_pay_1();
		setVisible(true);
	}

	public void show_pay_1() {
		int rowCont = table.getRowCount();
		String[] print_p1 = {};
		String[] print_p2 = {};
		String[] print_p3 = {};
		String[] print_p4 = {};
		int i = 0;
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		SBtn[0].addActionListener(new SBtnButtonListener());
		database db;

		try {
			db = new database();
			print_p1 = db.show_pay_code1(1);
			print_p2 = db.show_pay_code1(2);
			print_p3 = db.show_pay_code1(3);
			print_p4 = db.show_pay_code1(4);
			for (i = 0; i < print_p1.length; i++) {
				m.addRow(new Object[] { print_p1[i], print_p2[i], print_p3[i], print_p4[i] });
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	class SBtnButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			int resu = 0;
			if (log_comm.equals("결제 취소")) {
				JButton MBtn = (JButton) e.getSource();
				String choose_pay_cod = (String) table.getValueAt(table.getSelectedRow(), 0); // 클릭한 테이블 pay_code
				pay_check_dele pcd = new pay_check_dele();
				pcd.pay_check_delete(choose_pay_cod);
				m.removeRow(table.getSelectedRow());
//				tf.setText("총  :	" + Integer.toString(sum_pri) + "원");
				dispose();
			}
		}
	}

}
