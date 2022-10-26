package database;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UserRegister extends JFrame implements ActionListener {

	JLabel leftColumn1[] = { new JLabel("First Name"), new JLabel("Last Name"), new JLabel("Username"),
			new JLabel("Password"), new JLabel("Employee ID") };
	JLabel leftColumn2 = new JLabel("Register Date");
	JTextField textArr[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	// set date format
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String datestr = dateFormat.format(new Date());
	JTextField date = new JTextField(datestr);

	JButton register = new JButton("Register");

	// Name pattern
	String namePattern = "^[A-Za-z]+$";
	// Password pattern. 6-12 digits, number + letter + 1 special character
	String passwordPattern = "^(?![A-Za-z]+$)(?!\\d+$)(?!\\W+$)\\S{6,12}$";
	// Username pattern. 6-12 digits, number + letter
	String userNmaePattern = "^(?![A-Za-z]+$)(?!\\d+$)\\S{6,12}$";

	// 6-digit employee ID
	String employeeIDPattern = "[0-9]{6}";

	String[] pattern = { namePattern, namePattern, userNmaePattern, passwordPattern, employeeIDPattern };

	// Hints for input
	JTextArea helpArea1 = new JTextArea("Please enter your first name.");
	JTextArea helpArea2 = new JTextArea("Please enter your last name.");
	JTextArea helpArea3 = new JTextArea("6-12 digits. Must include letters and numbers.");
	JTextArea helpArea4 = new JTextArea("6-12 digits.\nMust include letters, numbers and one special character.");
	JTextArea helpArea5 = new JTextArea("Please enter your 6-digit Employee ID.");

	JTextArea helptArea[] = { helpArea1, helpArea2, helpArea3, helpArea4, helpArea5 };

	// Connect to Database
	ConnectDataBase jdbc = new ConnectDataBase();
	Statement newuser = jdbc.stmt;

	public UserRegister() {

		Dimension deSize;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		deSize = toolkit.getScreenSize();// Get screen size
		setLocation((int) (deSize.width / 2.5), deSize.height / 4);
		setVisible(true);
		setSize(550, 390);
		setTitle("User Register");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);

		init();
	}

	public void init() {
		setLayout(null);

		// Dynamic adjustment
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fraWidth = getWidth();// get window width
				int fraHeight = getHeight();// get window height
				
				// The first column, on the left side
				for (int i = 0; i < leftColumn1.length; i++) {
					leftColumn1[i].setBounds((int) (0.04 * fraWidth), (int) (0.01 * fraHeight + i * 0.15 * fraHeight),
							350, 60);
					textArr[i].setBounds((int) (0.2 * fraWidth), (int) (0.05 * fraHeight + i * 0.15 * fraHeight), 90,
							30);
					add(leftColumn1[i]);
					add(textArr[i]);
				}
				// First column, date fields
				leftColumn2.setBounds((int) (0.04 * fraWidth), (int) (0.75 * fraHeight), 350, 50);
				add(leftColumn2);
				date.setBounds((int) (0.2 * fraWidth), (int) (0.77 * fraHeight), 80, 30);
				date.setEditable(false);
				add(date);

				// The second column, on the right side
				for (int i = 0; i < helptArea.length; i++) {
					helptArea[i].setBounds((int) (0.4 * fraWidth), (int) (0.07 * fraHeight + i * 0.15 * fraHeight), 400,
							40);
					helptArea[i].setOpaque(false);
					helptArea[i].setEditable(false);
					// adjust position for the 2-line text
					if (i == 3) {
						helptArea[i].setBounds((int) (0.4 * fraWidth), (int) (0.04 * fraHeight + i * 0.15 * fraHeight),
								400, 40);
					}

					add(helptArea[i]);
				}
				// Register button
				register.setBounds((int) (0.5 * fraWidth), (int) (0.77 * fraHeight), 100, 30);
				add(register);

			}

		});

		// Click register
		addListener();

	}

	// Click submit
	public void addListener() {
		for (int i = 0; i < textArr.length; i++) {//
			textArr[i].addActionListener(this);
		}
		register.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == register) {
				String[] userInfo = new String[textArr.length];

				for (int i = 0; i < userInfo.length; i++) {
					userInfo[i] = textArr[i].getText();
					if (userInfo[i].length() == 0) {
						JOptionPane.showMessageDialog(null, "Input can not be empty.", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Error message for invalid password
					if (i == 2 && !userInfo[i].matches(pattern[i])) {
						JOptionPane.showMessageDialog(null, "Invalid username.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Error message for invalid password
					if (i == 3 && !userInfo[i].matches(pattern[i])) {
						JOptionPane.showMessageDialog(null, "Invalid password.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Error message for invalid Employee ID
					if (i == 4 && !userInfo[i].matches(pattern[i])) {
						JOptionPane.showMessageDialog(null, "Invalid Employee ID.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				// SQL query
				String insertSQL = "insert into users (first_name,last_name,username,password,register_date,employeeID) values('"
						 + userInfo[0] + "','" + userInfo[1] + "','" + userInfo[2] + "','"
						+ userInfo[3] + "',NOW(),"+ "'"+userInfo[4]+"');";
			
				
				newuser.executeUpdate(insertSQL);

				// Register successfully
				JOptionPane.showMessageDialog(null, "Register successfully!", "Congratualations!",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
				// Disconnect database
				newuser.close();
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
