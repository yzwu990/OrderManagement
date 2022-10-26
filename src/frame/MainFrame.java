package frame;

import javax.swing.*;

import database.ConnectDataBase;
import database.UserRegister;

import java.awt.event.*;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.sql.*;
import java.awt.*;

public class MainFrame extends JFrame implements ActionListener {

	JMenuBar menubar = new JMenuBar();
	//  menus in menubar
	JMenu menuUser = new JMenu("User");
	JMenu menuAdmin = new JMenu("Admin");

	// sub-menus in each menu
	JMenuItem buttonUserLogin = new JMenuItem("User Login");
	JMenuItem buttonRegister = new JMenuItem("User Register");
	JMenuItem buttonAdminLogin = new JMenuItem("Admin Login");


	JMenuItem buttonHelp = new JMenuItem("Help");

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == buttonUserLogin) {// user login
				new Login("users", "username", "password", "User Login");
				dispose();

			} else if (e.getSource() == buttonAdminLogin) {// admin login
				new Login("admin", "username", "password", "Administrator Login");
				dispose();

			} else if (e.getSource() == buttonRegister) {// user register
				new UserRegister();

			} 

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	private Container contentPane;
	static Dimension deSize;
	static Toolkit toolkit = Toolkit.getDefaultToolkit();

	ConnectDataBase jdbc = new ConnectDataBase();
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;

	public MainFrame() {

		deSize = toolkit.getScreenSize();
		setLocation((int) (deSize.width / 2.5), deSize.height / 4);

		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

		setResizable(true);

		init();
	}

	public void init() {
//	    
		setTitle("Order Management System");

		menuUser.add(buttonUserLogin);
		menuAdmin.add(buttonAdminLogin);
		menuUser.add(buttonRegister);
		buttonUserLogin.addActionListener(this);
		buttonAdminLogin.addActionListener(this);
		buttonRegister.addActionListener(this);

		// Assemble menubar
		menubar.add(menuUser);
		menubar.add(menuAdmin);

		setJMenuBar(menubar);
		JLabel mainBackground = new JLabel(new ImageIcon("image\\batman.png"));
		getContentPane().add(mainBackground);

	}

}
