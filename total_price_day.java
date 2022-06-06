package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 일매출 구하는 클래스

public class total_price_day extends JFrame {

	JLabel l1 = new JLabel(" 년도 ");
	JLabel l2 = new JLabel(" 월 ");
	JLabel l3 = new JLabel(" 일 ");
	String[] year_co = { "2018", "2019", "2020", "2021", "2022" };
	String[] mon_co = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] day_co = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

	JComboBox com_year = new JComboBox(year_co);
	JComboBox com_mon = new JComboBox(mon_co);
	JComboBox com_day = new JComboBox(day_co);

	JPanel p3 = new JPanel();
	JTextField t5 = new JTextField(20);

	RoundedButton cal = new RoundedButton("계산");

	public void total_price_day() {
		setTitle("일 매출 정보");
		setLayout(new BorderLayout(5, 5));
		show_total_pr();
		setSize(500, 200);
		setVisible(true);

	}

	public void show_total_pr() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		JPanel panel = new JPanel(new GridLayout(3, 0));

		p1.add(l1);
		p1.add(com_year);
		p1.add(l2);
		p1.add(com_mon);
		p1.add(l3);
		p1.add(com_day);

		p2.add(cal);
		p3.add(t5);
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		t5.setEditable(false);
		add(panel, BorderLayout.NORTH);
		cal.addActionListener(new cal_ButtonListener());

	}

	// 버튼 누르면 반응하는 클래스
	class cal_ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();

			if (log_comm.equals("계산")) {
				String year = com_year.getSelectedItem().toString();
				String month = com_mon.getSelectedItem().toString();
				String day = com_day.getSelectedItem().toString();

				database db;
				try {
					db = new database();
					int print_sum = 0;
					print_sum = db.day_total_pri(year, month, day);
					t5.setText("");
					t5.repaint();
					t5.revalidate();
					t5.setText("매출 : " + Integer.toString(print_sum) + "원");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

}
