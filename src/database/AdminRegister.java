package database;

import javax.swing.*;

import database.ConnectDataBase;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AdminRegister extends JFrame implements ActionListener {

	JLabel adminName = new JLabel("Username");
	JLabel passWord = new JLabel("Password");
	JLabel EmployeeID = new JLabel("EmployeeID");
	JLabel click = new JLabel("Click  ----->");
	JButton submit = new JButton("Submit");
	JTextField JAdminname = new JTextField();
	JPasswordField JPassword = new JPasswordField();
	JTextField JemployeeID = new JTextField();

	// Connect to Database
	ConnectDataBase jdbc = new ConnectDataBase();
	Statement newAdmin = jdbc.stmt;

	// String st1=null;
	JPanel jpl;
	JLabel lblBackground = new JLabel(new ImageIcon("image/µÇÂ½½çÃæ.jpg"));

	public AdminRegister() {

		Dimension deSize;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		deSize = toolkit.getScreenSize();// Get screen size

		setVisible(true);
		setLocation((int) (deSize.width / 2.5), deSize.height / 4);
		setSize(350, 350);
		setTitle("Administrator Register");
		setResizable(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		init();

	}

	public void init() {
		setLayout(null);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				int fraWidth = getWidth();// get window width
				int fraHeight = getHeight();// get window height

				// Username fields
				adminName.setBounds((int) (0.18 * fraWidth), (int) (0.1 * fraHeight), 100, 30);

				JAdminname.setBounds((int) (0.42 * fraWidth), (int) (0.1 * fraHeight), 100, 30);

				// Password fields
				passWord.setBounds((int) (0.18 * fraWidth), (int) (0.3 * fraHeight), 100, 30);

				JPassword.setBounds((int) (0.42 * fraWidth), (int) (0.3 * fraHeight), 100, 30);
				
				// EmployeeID fields
				EmployeeID.setBounds((int) (0.18 * fraWidth), (int) (0.5 * fraHeight), 100, 30);

				JemployeeID.setBounds((int) (0.42 * fraWidth), (int) (0.5 * fraHeight), 100, 30);

				// click ->
				click.setBounds((int) (0.18 * fraWidth), (int) (0.7 * fraHeight), 100, 30);

				// Submit button
				submit.setBounds((int) (0.42 * fraWidth), (int) (0.7 * fraHeight), 100, 30);

				// Background fields
				lblBackground.setBounds(0, 0, 300, 300);

			}

		});
		// Add fields and buttons
		add(adminName);
		add(JAdminname);
		add(passWord);
		add(JPassword);
		add(EmployeeID);
		add(JemployeeID);
		add(click);
		add(submit);
		add(lblBackground);

		// Add action listener
		JAdminname.addActionListener(this);
		JPassword.addActionListener(this);
		submit.addActionListener(this);

	}

	// Submit
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String getUsername = JAdminname.getText();
			String getPassword = new String(JPassword.getPassword());
			String getID = JemployeeID.getText();
			
			
			if (getUsername.length() == 0) {// Empty username error message
				JOptionPane.showMessageDialog(null, "You need a username", "Alert", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (getPassword.length() == 0) {// Empty password error message
				JOptionPane.showMessageDialog(null, "you need a password", "Alert", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// SQL query
			String insertSQL = "insert into admin (username,password,employeeID) values('"
					 + getUsername + "','" + getPassword+ "','" + getID+"' );";



			newAdmin.executeUpdate(insertSQL);

			// Register successfully
			JOptionPane.showMessageDialog(null, "Register successfully!", "Congratualations!",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			// Disconnect database
			newAdmin.close();

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
