package java_programming_cla;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class create_table extends JFrame {
	private Container c = getContentPane();
	String ColName[] = { "메뉴", "수량", "가격" };
	String Data[][] = null;
	DefaultTableModel model = new DefaultTableModel(Data, ColName);

	JTable table = new JTable(model);

	create_table() {
		JPanel tableBorder = new JPanel();

		Screen sc = new Screen();
		tableBorder.add(sc);
		tableBorder.setSize(500, 500);
		tableBorder.setLocation(25, 20);
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

}
