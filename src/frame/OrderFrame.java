package frame;

import javax.swing.*;

import database.ConnectDataBase;
import database.DeleteRecords;
import database.SelectTable;
import database.UpdateRecords;
import database.UpdateRecords;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderFrame extends JFrame implements ActionListener {

	JButton searchButton = new JButton("Search Orders");
	JButton addButton = new JButton("Add Orders");
	JButton editButton = new JButton("Edit Orders");
	JButton deleteButton = new JButton("Delete Orders");
	JButton updatemButton = new JButton("Update");
	JButton refreshButton = new JButton("Refresh Main Page");
	JButton resetButton = new JButton("Clear Input");

	JLabel jLabels[] = { new JLabel("order_number"), new JLabel("product_type"), new JLabel("customer"),
			new JLabel("factory"), new JLabel("order_finished"), new JLabel("order_shipped") };

	JTextField jTextFields[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };

	JPanel panelUp = new JPanel();
	JPanel panelUp1 = new JPanel();
	JPanel panelUp2 = new JPanel();
	JPanel panelUp3 = new JPanel();

	SelectTable tableOrders;
	JScrollPane JSP;
	JPanel midJPanel = new JPanel();

	JComboBox<String> finishedBox = new JComboBox<String>();
	JComboBox<String> shippedBox = new JComboBox<String>();

	JTable defaltTable;
	JTable editableTable;

	String updateSQL = "";
	String primaryKey = "";

	String sql = "select * from orders";
	String searchSQL = null;
	
	String sql1 = "";

	public OrderFrame() {
		init();

	}

	public void init() {
		Dimension deSize;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		deSize = toolkit.getScreenSize();// Get screen size

		setLocation((int) (deSize.width / 2.5), deSize.height / 4);
		setVisible(true);
		setSize(1300, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		setLayout(null);// Set layout to be border

		setTitle("Order Management Center");

		panelUp.setBounds(0, 10, 1300, 110);

		panelUp.setLayout(null);
		add(panelUp);

		panelUp1.setBounds(0, 0, 1300, 40);

		panelUp.add(panelUp1);

		panelUp1.add(refreshButton);

		panelUp1.add(addButton);

		panelUp1.add(editButton);
		panelUp1.add(updatemButton);
		updatemButton.setEnabled(false);

		panelUp1.add(deleteButton);
		deleteButton.setEnabled(false);

		panelUp2.setLayout(null);
		panelUp2.setBounds(0, 25, 1300, 50);

		panelUp.add(panelUp2);

		for (int i = 0; i < jLabels.length; i++) {
			jLabels[i].setBounds(210 + 150 * i, 5, 90, 50);
			if (i == 4) {
				jLabels[i].setBounds(190 + 150 * i, 5, 90, 50);
			}
			if (i == 5) {
				jLabels[i].setBounds(190 + 150 * i, 5, 90, 50);
			}
			panelUp2.add(jLabels[i]);
		}

		resetButton.setBounds(1050, 15, 130, 30);
		panelUp2.add(resetButton);

		panelUp3.setLayout(null);
		panelUp3.setBounds(0, 70, 1300, 45);

		panelUp.add(panelUp3);

		for (int i = 0; i < jTextFields.length; i++) {
			jTextFields[i].setBounds(205 + 150 * i, 5, 90, 30);

			if (i == 2) {
				jTextFields[i].setBounds(195 + 150 * i, 5, 90, 30);
			}
			if (i == 3) {
				jTextFields[i].setBounds(190 + 150 * i, 5, 90, 30);
			}

			panelUp3.add(jTextFields[i]);
		}

		finishedBox.addItem("YES");
		finishedBox.addItem("NO");
		finishedBox.setSelectedIndex(-1);

		shippedBox.addItem("YES");
		shippedBox.addItem("NO");
		shippedBox.setSelectedIndex(-1);

		finishedBox.setBackground(Color.WHITE);
		finishedBox.setBounds(788, 5, 90, 30);

		panelUp3.add(finishedBox);

		shippedBox.setBackground(Color.WHITE);
		shippedBox.setBounds(940, 5, 90, 30);

		panelUp3.add(shippedBox);

		searchButton.setBounds(1050, 5, 130, 30);
		panelUp3.add(searchButton);

		tableOrders = new SelectTable();

		defaltTable = tableOrders.getTable(sql, false);

		JSP = new JScrollPane(defaltTable);
		JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JSP.setBounds(0, 0, 1250, 400);
		midJPanel.setLayout(null);

		midJPanel.add(JSP);
		midJPanel.setBounds(20, 120, 1250, 400);

		add(midJPanel);

		editButton.addActionListener(this);
		addButton.addActionListener(this);
		refreshButton.addActionListener(this);
		updatemButton.addActionListener(this);
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		resetButton.addActionListener(this);

		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == editButton) {// Edit Orders
				midJPanel.removeAll();

				tableOrders = new SelectTable();

				if (searchSQL == null) {
					editableTable = tableOrders.getTable(sql, true);
				} else {
					editableTable = tableOrders.getTable(searchSQL, true);
				}

				RefreshTable ediTable = new RefreshTable();
				JPanel refreshedTable = ediTable.refresh(editableTable, midJPanel);

				add(refreshedTable);
				updatemButton.setEnabled(true);
				deleteButton.setEnabled(true);

				midJPanel.revalidate();

			} else if (e.getSource() == addButton) {// Add orders
				new AddOrder();

				refresnMain(sql);

			} else if (e.getSource() == refreshButton) {// Refresh main page

				refresnMain(sql);

			} else if (e.getSource() == updatemButton) {// Update orders

				UpdateRecords updateOrder = new UpdateRecords();
				updateOrder.updateRecords(editableTable, updatemButton);

				refresnMain(sql);

			} else if (e.getSource() == deleteButton) {// Delete orders
				DeleteRecords deleteRecords = new DeleteRecords();
				deleteRecords.deleteRecords(editableTable, deleteButton, "orders");
				if (searchSQL == null) {
					refresnMain(sql);
				} else {
					refresnMain(searchSQL);
				}

			} else if (e.getSource() == searchButton) {// Search
				Search();
				
				if (sql1 == "") {
					refresnMain(sql);
				} else {
					refresnMain(searchSQL);
				}
				
				

			} else if (e.getSource() == resetButton) {// Reset
				finishedBox.setSelectedIndex(-1);
				shippedBox.setSelectedIndex(-1);
				for (int i = 0; i < jTextFields.length; i++) {
					jTextFields[i].setText(null);
				}
				sql1 = "";
				searchSQL = null;
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	// Refresh main view
	public void refresnMain(String sql) {
		midJPanel.removeAll();

		tableOrders = new SelectTable();

		defaltTable = tableOrders.getTable(sql, false);

		RefreshTable refreshTable = new RefreshTable();
		JPanel refreshedTable = refreshTable.refresh(defaltTable, midJPanel);

		add(refreshedTable, BorderLayout.CENTER);
		updatemButton.setEnabled(false);
		deleteButton.setEnabled(false);

		midJPanel.revalidate();
	}

	// Search
	public void Search() {

		String finishedValue = String.valueOf(finishedBox.getSelectedItem());
		String shippedValue = String.valueOf(shippedBox.getSelectedItem());

		String content[] = new String[5];
		String[] title = { "order_number", "product_type", "customer", "factory", };
		
		for (int i = 0; i < 4; i++) {

			content[i] = jTextFields[i].getText().trim();

			if (!content[i].equals("")) {
				sql1 += title[i] + " LIKE " + "'%" + content[i] + "%'" + " AND ";

			}

		}
	
		if (!finishedValue.equals("null")) {
			sql1 += "order_finished = " + "'" + finishedValue + "'" + " AND ";
		}

		if (!shippedValue.equals("null")) {
			sql1 += "order_shipped = " + "'" + shippedValue + "'" + " AND ";
		}

		if (content[0].equals("") && content[1].equals("") && content[2].equals("") && content[3].equals("")
				&& finishedValue.equals("null") && shippedValue.equals("null")) {
			JOptionPane.showMessageDialog(null, "Need at least one field!", "Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		searchSQL = "SELECT * FROM orders WHERE " + sql1.substring(0, sql1.length() - 5) + ";";
		
	}

}
