package frame;

import javax.swing.*;

import database.AdminRegister;
import database.ConnectDataBase;
import database.DeleteRecords;
import database.SelectTable;
import database.UpdateRecords;
import database.UserRegister;

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

public class AdminFrame extends JFrame implements ActionListener {

	JButton searchButton = new JButton("Search");
	JButton addButton = new JButton("Add");
	JButton editButton = new JButton("Edit");
	JButton deleteButton = new JButton("Delete");
	JButton updatemButton = new JButton("Update");
	JButton manageUserButton = new JButton("Manage User");
	JButton manageAdminButton = new JButton("Manage Adminnistrator");
	JButton resetButton = new JButton("Clear Input");


	JLabel employeeIDJLabel = new JLabel("EmployeeID");

	JTextField jTextFields = new JTextField();

	JPanel panelUp = new JPanel();
	JPanel panelUp1 = new JPanel();
	JPanel panelUp2 = new JPanel();
	JPanel panelUp3 = new JPanel();

	SelectTable tableOrders;
	JScrollPane JSP;

	JPanel midJPanel = new JPanel();

	JTable defaltTable;
	JTable editableTable;

	String updateSQL = "";
	String primaryKey = "";

	
	String choice = null;
	String sql = "select * from "+choice;
	String searchSQL = null;
	


	public AdminFrame() {
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

		setLayout(null);

		setTitle("Administrator Center");

		panelUp.setBounds(0, 0, 1300, 110);
	
		panelUp.setLayout(null);
		add(panelUp);

		panelUp1.setBounds(0, 10, 1300, 40);

		panelUp.add(panelUp1);

		panelUp1.add(manageUserButton);
		panelUp1.add(manageAdminButton);

		panelUp1.add(addButton);

		panelUp1.add(editButton);
		panelUp1.add(updatemButton);
		updatemButton.setEnabled(false);

		panelUp1.add(deleteButton);
		deleteButton.setEnabled(false);

	
		panelUp2.setLayout(null);
		panelUp2.setBounds(0, 55, 1300, 50);

		panelUp.add(panelUp2);

		employeeIDJLabel.setBounds(450, 5, 90, 50);
		jTextFields.setBounds(530, 15, 90, 30);
		searchButton.setBounds(650, 15, 90, 30);
		resetButton.setBounds(750, 15, 130, 30);

		panelUp2.add(employeeIDJLabel);
		panelUp2.add(jTextFields);
		panelUp2.add(resetButton);
		panelUp2.add(searchButton);



		editButton.addActionListener(this);
		addButton.addActionListener(this);
		manageUserButton.addActionListener(this);
		manageAdminButton.addActionListener(this);
		
		updatemButton.addActionListener(this);
		deleteButton.addActionListener(this);
		searchButton.addActionListener(this);
		resetButton.addActionListener(this);

		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == editButton) {// Edit user or admin
				midJPanel.removeAll();

				tableOrders = new SelectTable();

				if (choice == null) {
					JOptionPane.showMessageDialog(null, "Please choose to manage user or administrator first", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					editableTable = tableOrders.getTable(searchSQL, true);
				}

				RefreshTable ediTable = new RefreshTable();
				JPanel refreshedTable = ediTable.refresh(editableTable, midJPanel);

				add(refreshedTable);
				updatemButton.setEnabled(true);
				deleteButton.setEnabled(true);

	
				midJPanel.revalidate();

				
			} else if (e.getSource() == addButton) {// Add user or admin
				
				if (choice==null) {
					JOptionPane.showMessageDialog(null, "Please choose to manage user or administrator first", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if (choice.equals("users")) {
					new UserRegister();
				}else {
					new AdminRegister();
				}

			} else if (e.getSource() == manageUserButton) {// Refresh main page
				choice = "users";
				sql = "select * from "+choice;
				searchSQL= "select * from "+choice;
				midJPanel.removeAll();
				refresnMain(sql);

				// Update order;
			} else if (e.getSource() == manageAdminButton) {
				choice = "admin";
				sql = "select * from "+choice;
				searchSQL= "select * from "+choice;
				midJPanel.removeAll();
				refresnMain(sql);
			}
			else if (e.getSource() == updatemButton) {// Update

				UpdateRecords updateOrder = new UpdateRecords();
				updateOrder.updateRecords(editableTable, updatemButton);

				refresnMain(sql);

			} else if (e.getSource() == deleteButton) {// delete
				DeleteRecords deleteRecords = new DeleteRecords();
				deleteRecords.deleteRecords(editableTable, deleteButton,choice);
				if (searchSQL == null) {
					refresnMain(sql);
				} else {
					refresnMain(searchSQL);
				}

			} else if (e.getSource() == searchButton) {// Search
				Search();
				refresnMain(searchSQL);

			} else if (e.getSource() == resetButton) {// Reset

				jTextFields.setText(null);
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
		
		
		JSP = new JScrollPane(defaltTable);
		JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JSP.setBounds(0, 0, 1250, 400); midJPanel.setLayout(null);

		RefreshTable refreshTable = new RefreshTable();
		JPanel refreshedTable = refreshTable.refresh(defaltTable, midJPanel);

		add(refreshedTable, BorderLayout.CENTER);
		updatemButton.setEnabled(false);
		deleteButton.setEnabled(false);

		midJPanel.revalidate();
	}

	public void Search() {

		String content = jTextFields.getText().trim();

		String sql1 = "employeeID LIKE " + "'%" + content + "%'";
		
	
		if (content.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Employee ID", "Alert", JOptionPane.ERROR_MESSAGE);
			return;
		}
		searchSQL = "SELECT * FROM "+ choice+ " WHERE " + sql1 + ";";
		

	}

}
