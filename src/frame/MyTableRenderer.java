package frame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// set checkbox

		JCheckBox box = new JCheckBox(); // ¸´Ñ¡¿ò
		box.setOpaque(true); 
		box.setHorizontalAlignment(JCheckBox.CENTER); 
		if (isSelected) {// change background color when selected
			box.setBackground(new Color(135, 206, 250));
		} else {
			if (row % 2 == 0) { // background color for even rows
				box.setBackground(new Color(240, 250, 250));
				box.setForeground(table.getForeground());
			} else { // background color for odd rows
				box.setBackground(table.getBackground());
			}
		}
		boolean valu = (Boolean) value;
		box.setSelected(valu);
		return box;
	}

}
