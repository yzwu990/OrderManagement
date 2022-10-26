package database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class UpdateRecords {
	String pattern = "^(?:YES|NO)$";
	String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
	String quantityPattern = "\\d+";
	String pricePattern = "\\d{0,3}(\\.\\d{1,2})?";
	String updateSQL = "";

	// Name pattern
	String namePattern = "^[A-Za-z]+$";
	// Password pattern. 6-12 digits, number + letter + 1 special character
	String passwordPattern = "^(?![A-Za-z]+$)(?!\\d+$)(?!\\W+$)\\S{6,12}$";
	// Username pattern. 6-12 digits, number + letter
	String userNmaePattern = "^(?![A-Za-z]+$)(?!\\d+$)\\S{6,12}$";
	// 6-digit employee ID
	String employeeIDPattern = "[0-9]{6}";

	public void updateRecords(JTable jTable, JButton updatemButton) {

		ConnectDataBase updateConnect = new ConnectDataBase();
		Statement stmt = updateConnect.stmt;

		int choice = JOptionPane.showConfirmDialog(null, "Do you want to update selected rows?", "Update",
				JOptionPane.YES_NO_OPTION);
		if (choice == 0) {

			Boolean[] key = new Boolean[jTable.getRowCount()];

			ArrayList<String> rowdata = new ArrayList<String>();

			ArrayList<ArrayList> rows = new ArrayList<ArrayList>();

			ArrayList<String> header = new ArrayList<String>();

			for (int i = 0; i < jTable.getRowCount(); i++) {

				key[i] = (Boolean) jTable.getValueAt(i, jTable.getColumnCount() - 1);

				if (key[i] == true) {

					for (int j = 0; j < jTable.getColumnCount(); j++) {

						rowdata.add(jTable.getValueAt(i, j).toString());
						header.add(jTable.getColumnName(j));
					}

					rows.add(rowdata);
					rowdata = new ArrayList<String>();

				}

			}

			if (header.get(0).equals("id")) {
				for (int i = 0; i < rows.size(); i++) { //

					String orderNumber = rows.get(i).get(1).toString();
					String product = rows.get(i).get(2).toString();
					String price = rows.get(i).get(3).toString();
					String quantity = rows.get(i).get(4).toString();
					String productType = rows.get(i).get(5).toString();
					String customer = rows.get(i).get(6).toString();
					String factory = rows.get(i).get(7).toString();
					String receiveDate = rows.get(i).get(8).toString();
					String finished = rows.get(i).get(9).toString();
					String shipped = rows.get(i).get(10).toString();

					String primaryKey = rows.get(i).get(0).toString();

					if (!receiveDate.matches(datePattern)) {
						JOptionPane.showMessageDialog(null, "Please enter yyyy-mm-dd as date format.", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!finished.matches(pattern)) {
						JOptionPane.showMessageDialog(null, "Please input \"YES\" or \"NO\" for order_finished status",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!shipped.matches(pattern)) {
						JOptionPane.showMessageDialog(null, "Please input \"YES\" or \"NO\" for order_shipped status",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!price.matches(pricePattern)) {
						JOptionPane.showMessageDialog(null, "Invalid price.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!quantity.matches(quantityPattern)) {
						JOptionPane.showMessageDialog(null, "Invalid quantity.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					updateSQL = "UPDATE orders SET order_number = '" + orderNumber + "',product = '" + product
							+ "',unit_price='" + price + "',quantity = '" + quantity + "',product_type='" + productType
							+ "',customer='" + customer + "',factory=  '" + factory + "',order_received_date=  '"
							+ receiveDate + "', order_finished= '" + finished + "',order_shipped='" + shipped
							+ "' WHERE id= '" + primaryKey + "';";

					try {
						stmt.executeUpdate(updateSQL);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else if (header.get(0).equals("userID")) {
				for (int i = 0; i < rows.size(); i++) {

					String first_name = rows.get(i).get(1).toString();
					String last_name = rows.get(i).get(2).toString();
					String username = rows.get(i).get(3).toString();
					String password = rows.get(i).get(4).toString();
					String register_date = rows.get(i).get(5).toString();

					String primaryKey = rows.get(i).get(0).toString();

					if (!first_name.matches(namePattern) && !last_name.matches(namePattern)) {
						JOptionPane.showMessageDialog(null, "Invalid name", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!username.matches(userNmaePattern)) {
						JOptionPane.showMessageDialog(null,
								"Invalid username.\n6-12 digits.Must include letters and numbers.  ", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!password.matches(passwordPattern)) {
						JOptionPane.showMessageDialog(null,
								"Invalid password.\n6-12 digits. Must include letters, numbers and one special character.",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!register_date.matches(datePattern)) {
						JOptionPane.showMessageDialog(null, "Please enter yyyy-mm-dd as date format.", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					updateSQL = "UPDATE users SET first_name = '" + first_name + "',last_name = '" + last_name
							+ "',username='" + username + "',password = '" + password + "',register_date='"
							+ register_date + "' WHERE userID= '" + primaryKey + "';";

					try {
						stmt.executeUpdate(updateSQL);
					} catch (SQLException e) {

						e.printStackTrace();
					}

				}
			} else {

				for (int i = 0; i < rows.size(); i++) {

					String username = rows.get(i).get(1).toString();
					String password = rows.get(i).get(2).toString();

					String primaryKey = rows.get(i).get(0).toString();

					updateSQL = "UPDATE admin SET username = '" + username + "',password = '" + password
							+ "' WHERE adminID= '" + primaryKey + "';";

					try {
						stmt.executeUpdate(updateSQL);
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
			JOptionPane.showMessageDialog(null, "Update successfully", "Update orders",
					JOptionPane.INFORMATION_MESSAGE);
			updatemButton.setEnabled(false);

		} else {
			return;
		}
	}

}
