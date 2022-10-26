package database;

import java.awt.Color;
import java.awt.Component;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.*;

import frame.MyTableCellEditor;
import frame.MyTableRenderer;
import frame.TableCellTextAreaRenderer;

public class SelectTable {
	String tableName;
	ConnectDataBase connectTable = new ConnectDataBase();
	Connection conn = connectTable.conn;
	Statement stmt = connectTable.stmt;
	JTable jtable;
	JScrollPane JSP = null;
	Vector<String> columnData = new Vector<String>();
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	Boolean flag;

	public SelectTable() {

	}


	public JTable getTable(String sql, Boolean flag) {
		try {

			ResultSet result = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = result.getMetaData();
			int column = result.getMetaData().getColumnCount();// get number of columns

			Vector<String> columnNames = new Vector<String>();
			while (result.next()) {
				for (int i = 0; i < column; i++) {// get rows except for header
					columnData.addElement(result.getString(i + 1));

				}
				rowData.addElement(columnData);
				columnData = new Vector<String>(0);
			}

			for (int i = 0; i < column; i++) {// get header
				columnNames.add(rsmd.getColumnName(i + 1));
				
			}

			DefaultTableModel tableModel;
			tableModel = new DefaultTableModel(rowData, columnNames);

			if (columnNames.get(0).equals("id") ) {
				jtable = new JTable(tableModel) {
					public boolean isCellEditable(int row, int column) {
						return flag;
					}
				};
			} else {
				jtable = new JTable(tableModel) {
					public boolean isCellEditable(int row, int column) {
						if(0 <= row && row < getRowCount() && column == getColumnCount()-2){
							return false;
						}else{
							return flag;
						}
					}
				};
			};
			// Set center text
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			jtable.setDefaultRenderer(Object.class, r);

			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			// Can not drag table header
			jtable.getTableHeader().setReorderingAllowed(false);

			/////////////////////////////////////////////////////////////////////

			DefaultTableModel model = (DefaultTableModel) jtable.getModel();
			model.addColumn("Select");

			for (int i = 0; i < jtable.getRowCount(); i++) {
				model.setValueAt(false, i, jtable.getColumnCount() - 1);
			}

			JCheckBox box = new JCheckBox();

			jtable.getColumnModel().getColumn(jtable.getColumnCount() - 1).setCellRenderer(new MyTableRenderer());
			jtable.getColumnModel().getColumn(jtable.getColumnCount() - 1)
					.setCellEditor(new MyTableCellEditor(new JCheckBox()));

			// Hide fist column (primary key)
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) jtable.getColumnModel();// Get column model

			// Set min width and max width to be 0
			dcm.getColumn(0).setMinWidth(0);
			dcm.getColumn(0).setMaxWidth(0);

			stmt.close();
			return jtable;
		} catch (Exception ee) {
			ee.printStackTrace();
			return jtable;
		}
	}

}
