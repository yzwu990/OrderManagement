package frame;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.SelectTable;




public class RefreshTable {
	

	
	public JPanel refresh(JTable jTable, JPanel midJPanel) {
		midJPanel.removeAll();

		JScrollPane JSP = new JScrollPane(jTable);
		JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JSP.setBounds(0, 0, 1250, 400);

		midJPanel.setBounds(20, 120, 1250, 400);
		midJPanel.add(JSP);



		midJPanel.revalidate();
		return midJPanel;
	}

}
