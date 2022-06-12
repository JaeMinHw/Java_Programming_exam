package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class pay_check_dele extends JFrame {
	private Container c = getContentPane();

	String ColName[] = { "코드", "메뉴", "개수", "메뉴 가격" };
	String Data[][];

	DefaultTableModel model = new DefaultTableModel(Data, ColName);

	JLabel p_1 = new JLabel("취소하시겠습니까?");
	JLabel p_2 = new JLabel();

	RoundedButton pay_can = new RoundedButton("결제 취소");

	int pay_succ;
	String pay_codd = null;
	JPanel tableBorder = new JPanel();
	RoundJTextField tf = new RoundJTextField(10); // 총 가격 출력하는 텍스트 필드
	JPanel seleBorder = new JPanel();
	JPanel pay_can_p = new JPanel();
	JTable table = new JTable(model);
	int cop_cou = 0;
	DefaultTableModel m = (DefaultTableModel) table.getModel();

	public void pay_check_delete(String pay_code) {
		setTitle("취소 확인");
		setLayout(new BorderLayout(3, 3));
		setSize(800, 700);
		setLocationRelativeTo(null);
		Screen sc = new Screen();
		tableBorder.add(sc);
		tableBorder.add(sc);
		tableBorder.setSize(500, 500);
		c.add(tableBorder);
		tableBorder.setLocation(25, 20);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		c.add(seleBorder);
		pay_can_p.add(pay_can);
		add(pay_can_p);
		pay_list_arry1(pay_code);

		setVisible(true);

		pay_can.addActionListener(new pay_funButtonListener());

	}
	// pay_code에 맞는 메뉴들 테이블에 보여주기

	public void pay_list_arry1(String pay_code) {
		database db;
		int i = 0;
		String[] print_p1 = {};
		String[] print_p2 = {};
		String[] print_p3 = {};
		String[] print_p4 = {};
		pay_codd = pay_code;
		try {
			db = new database();
			print_p1 = db.select_pay_list_arry(1, pay_code);
			print_p2 = db.select_pay_list_arry(2, pay_code);
			print_p3 = db.select_pay_list_arry(3, pay_code);
			print_p4 = db.select_pay_list_arry(4, pay_code);
			for (i = 0; i < print_p1.length; i++) {
				m.addRow(new Object[] { print_p1[i], print_p2[i], print_p3[i], print_p4[i] });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Screen extends JPanel {
		Screen() {
			/* setBackground(Color.WHITE); */
			table.setRowHeight(30);
			table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	}

	// 버튼 누르면 반응하는 클래스
	class pay_funButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			int resu = 0;
			if (log_comm.equals("결제 취소")) {

				database db;
				try {
					db = new database();
					resu = db.delete_pay(pay_codd);
					if (resu == 1) {
						JOptionPane.showMessageDialog(null, "결제 취소 성공", "결제 취소", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "결제 취소 실패", "결제 취소 실패", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 성공하면 return 1 - 창 닫기
//				pay_succ = 1;
//
//				// Jtable 초기화
//				JOptionPane.showMessageDialog(null, "결제가 성공되었습니다.", "결제 성공", JOptionPane.PLAIN_MESSAGE);
//				dispose();
//				

			}

		}
	}
}
