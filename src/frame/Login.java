package frame;

import javax.swing.*;

import database.ConnectDataBase;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Login extends JFrame implements ActionListener {

	JLabel userName = new JLabel("Username");
	JLabel passWord = new JLabel("Password");
	JLabel click = new JLabel("Click  ----->");
	JButton submit = new JButton("Submit");
	JTextField JUusername = new JTextField();
	JPasswordField JPassword = new JPasswordField();

	// Connect to database
	ConnectDataBase login = new ConnectDataBase();
	Connection conn = login.conn;
	Statement stmt = login.stmt;

	String tableName; // Table in database
	String username; // Username in tables
	String password; // Password in tables
	String title; // Title in the login window

	// String st1=null;
	JPanel jpl;
	JLabel lblBackground = new JLabel(new ImageIcon("image/Login.jpg"));

	public Login(String tableName, String username, String password, String title) {

		this.tableName = tableName;
		this.username = username;
		this.password = password;
		this.title = title;

		Dimension deSize;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		deSize = toolkit.getScreenSize();// Get screen size

		setVisible(true);
		setLocation((int) (deSize.width / 2.5), deSize.height / 4);
		setSize(300, 300);
		setTitle(title);
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
				userName.setBounds((int) (0.18 * fraWidth), (int) (0.1 * fraHeight), 100, 30);

				JUusername.setBounds((int) (0.42 * fraWidth), (int) (0.1 * fraHeight), 100, 30);

				// Password fields
				passWord.setBounds((int) (0.18 * fraWidth), (int) (0.4 * fraHeight), 100, 30);

				JPassword.setBounds((int) (0.42 * fraWidth), (int) (0.4 * fraHeight), 100, 30);

				// click ->
				click.setBounds((int) (0.18 * fraWidth), (int) (0.67 * fraHeight), 100, 30);

				// Submit button
				submit.setBounds((int) (0.42 * fraWidth), (int) (0.67 * fraHeight), 100, 30);

				// Background fields
				lblBackground.setBounds(0, 0, 300, 300);

			}

		});
		// Add fields and buttons
		add(userName);
		add(JUusername);
		add(passWord);
		add(JPassword);
		add(click);
		add(submit);
		add(lblBackground);

		// Add action listener
		JUusername.addActionListener(this);
		JPassword.addActionListener(this);
		submit.addActionListener(this);

		// Login window pops up, main window closes
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Close login window, main window pops up
				super.windowClosing(e);
				new MainFrame();
			}
		});
	}

	// Submit
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String getUsername = JUusername.getText();
			String getPassword = new String(JPassword.getPassword());
			if (getUsername.length() == 0) {// Empty username error message
				JOptionPane.showMessageDialog(null, "You need a username", "Alert", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (getPassword.length() == 0) {// Empty password error message
				JOptionPane.showMessageDialog(null, "you need a password", "Alert", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String verifySQL;
			String welcomeName;
			verifySQL = "select * from " + tableName + " where " + username + "='" + getUsername + "' and " + password
					+ " ='" + getPassword + "'";
			ResultSet sqlResult = stmt.executeQuery(verifySQL);

			if (!sqlResult.next()) {
				JOptionPane.showMessageDialog(null, "Username or password is not correct", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				welcomeName = sqlResult.getString(1);

			}
			dispose();
			if (title == "Administrator Login") {
				new AdminFrame();
			} else {
				new OrderFrame();
			}

			sqlResult.close();
			stmt.close();
			conn.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
