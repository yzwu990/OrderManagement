package database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DeleteRecords {

	public String deleteSQL = null;

	public void deleteRecords(JTable jTable, JButton deleteButton, String choice) {

		ConnectDataBase deleteConnect = new ConnectDataBase();
		Statement stmt = deleteConnect.stmt;

		int message = JOptionPane.showConfirmDialog(null, "Do you want to delete selected rows?", "Delete orders",
				JOptionPane.YES_NO_OPTION);
		if (message == 0) {
			Boolean[] key = new Boolean[jTable.getRowCount()];

			for (int i = 0; i < jTable.getRowCount(); i++) {

				key[i] = (Boolean) jTable.getValueAt(i, jTable.getColumnCount() - 1);

				if (key[i] == true) {

					String primaryKey = jTable.getValueAt(i, 0).toString();

					if (jTable.getColumnName(0).equals("id")) {
						deleteSQL = "DELETE FROM " + choice + " WHERE id= '" + primaryKey + "';";
					} else if (jTable.getColumnName(0).equals("userID")) {
						deleteSQL = "DELETE FROM " + choice + " WHERE userID= '" + primaryKey + "';";
					} else {
						deleteSQL = "DELETE FROM " + choice + " WHERE adminID= '" + primaryKey + "';";
					}

					try {
						stmt.executeUpdate(deleteSQL);
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			}

			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Delete successfully", "Delete orders",
					JOptionPane.INFORMATION_MESSAGE);

			deleteButton.setEnabled(false);

		} else {
			return;
		}

	}
}
