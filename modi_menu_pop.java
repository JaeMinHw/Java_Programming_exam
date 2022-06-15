package java_programming_cla;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class modi_menu_pop extends JFrame {
	JTextField menu_name_te = new JTextField(11);
	JTextField menu_pric_te = new JTextField(6);
	JTextField menu_cate_te = new JTextField(6);
	JLabel menu_name_l = new JLabel("메뉴 이름");
	JLabel menu_pric = new JLabel("가격");
	JLabel menu_cate = new JLabel("카테고리");
	JPanel n_p1 = new JPanel();
	JPanel n_p2 = new JPanel();
	String menu_name = null;
	int menu_price = 0;
	JPanel join_panel = new JPanel(new GridLayout(5, 3));

	JButton modi = new JButton("가격 수정");
	JButton dele = new JButton("메뉴 삭제");
	String menu_name_sear = null;

	public void modi_menu_pop(String menu_name, int menu_price) {
		setTitle("메뉴 수정");
		this.menu_price = menu_price;
		this.menu_name = menu_name;
		this.menu_name_sear = menu_name;
		setLayout(new BorderLayout(3, 3));
		show_titl();
		setSize(300, 150);
		setVisible(true);
	}

	public void show_titl() {
		JPanel n_p1 = new JPanel();
		JPanel n_p2 = new JPanel();
		JLabel menu_name_l = new JLabel("메뉴 이름");
		JLabel menu_pric = new JLabel("가격");
		menu_name_te.setText(menu_name);
		menu_pric_te.setText(Integer.toString(menu_price));
		n_p1.add(menu_name_l);
		n_p1.add(menu_name_te);
		n_p1.add(menu_cate);
		n_p1.add(menu_cate_te);
		n_p1.add(menu_pric);
		n_p1.add(menu_pric_te);
		n_p2.add(modi);
		n_p2.add(dele);
		join_panel.add(n_p1);
		join_panel.add(n_p2);
		add(join_panel, BorderLayout.NORTH);

		modi.addActionListener(new manage_ButtonListener());
		dele.addActionListener(new manage_ButtonListener());
	}

	// 버튼 누르면 반응하는 클래스
	class manage_ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 여기에는 창 띄우기
			String log_comm = e.getActionCommand();
			String get_name = menu_name_te.getText();
			int check = 0;
			int check1 = 0;
			int c = 0;
			int get_price = Integer.parseInt(menu_pric_te.getText());
			int get_cate = Integer.parseInt(menu_cate_te.getText());
			database db;

			if (log_comm.equals("가격 수정")) {
				try {
					db = new database();
					check = db.modify_menu(get_name, menu_name_sear, get_price);
					if (check == 1) {
						JOptionPane.showMessageDialog(null, "수정 완료", "수정 완료", JOptionPane.INFORMATION_MESSAGE);
					} else { // 빈칸은 insert로 메뉴 삽입
						c = db.insert_menu(get_cate, get_name, get_price);
						if (c == 1) {
							JOptionPane.showMessageDialog(null, "추가 완료", "추가 완료", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "추가 실패", "추가 실패", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (log_comm.equals("메뉴 삭제")) {
				try {
					db = new database();
					check1 = db.delete_menu(get_name);
					if (check1 == 1) {
						JOptionPane.showMessageDialog(null, "삭제 완료", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "삭제 오류 다시 수정해주세요", "삭제 오류", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

}
