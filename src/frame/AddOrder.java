package frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import database.ConnectDataBase;

public class AddOrder extends JFrame implements ActionListener {
	JLabel[] label1 = { new JLabel("order_number"), new JLabel("product"), new JLabel("unit_price ($USD)"),
			new JLabel("quantity"), new JLabel("product_type") };

	JLabel[] label2 = { new JLabel("customer"), new JLabel("factory"), new JLabel("oder_received_date"),
			new JLabel("order_finished"), new JLabel("order_shipped") };

	JTextField field1[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	JTextField field2[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField() };

	JLabel[] hintsJLabel = { new JLabel("yyyy-mm-dd"), new JLabel(" \"YES\" or \"NO\""),
			new JLabel("\"YES\" or \"NO\"") };

	JButton submit = new JButton("Submit");


	String pattern = "^(?:YES|NO)$";
	String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
	String quantityPattern = "\\d+";
	String pricePattern = "\\d{0,3}(\\.\\d{1,2})?";

	String sql = "";
	ConnectDataBase addOrder = new ConnectDataBase();// 连接数据库类
	Statement stmt = addOrder.stmt;

	public AddOrder( ) {

		init();
		Dimension deSize;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		deSize = toolkit.getScreenSize();// Get screen size
		setLocation((int) (deSize.width / 2.5), deSize.height / 4);

		setVisible(true);
		setSize(950, 250);
		setTitle("Add orders");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
	}

	public void init() {
		setLayout(null);
		// int i;
		for (int i = 0; i < label1.length; i++) {
			label1[i].setBounds(30 + 160 * i, 10, 130, 30);
			add(label1[i]);
		}
		for (int i = 0; i < field1.length; i++) {
			field1[i].setBounds(30 + 160 * i, 40, 90, 30);
			add(field1[i]);
			field1[i].addActionListener(this);
		}

		for (int i = 0; i < label2.length; i++) {
			label2[i].setBounds(30 + 160 * i, 100, 130, 30);
			add(label2[i]);
		}

		for (int i = 0; i < field1.length; i++) {
			field2[i].setBounds(30 + 160 * i, 130, 90, 30);
			add(field2[i]);
			field2[i].addActionListener(this);
		}

		for (int i = 0; i < hintsJLabel.length; i++) {
			hintsJLabel[i].setBounds(350 + 160 * i, 150, 90, 30);
			add(hintsJLabel[i]);
		}

		submit.setBounds(800, 60, 80, 80);
		add(submit);
		submit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == submit) {
				String[] query1 = new String[field1.length];
				String[] query2 = new String[field1.length];

				for (int j = 0; j < field1.length; j++) {
					query1[j] = field1[j].getText();
					if (query1[j].equals("")) {// 注意:不要写成strT[j]==""

						JOptionPane.showMessageDialog(null, "Input can not be empty.", "Alert",
								JOptionPane.ERROR_MESSAGE);

						return;
					}

					if (j == 2 && !query1[j].matches(pricePattern)) {
						JOptionPane.showMessageDialog(null, "Invalid price.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (j == 3 && !query1[j].matches(quantityPattern)) {
						JOptionPane.showMessageDialog(null, "Invalid quantity.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

				}

				for (int j = 0; j < field2.length; j++) {
					query2[j] = field2[j].getText();
					if (query2[j].equals("")) {

						JOptionPane.showMessageDialog(null, "Input can not be empty.", "Alert",
								JOptionPane.ERROR_MESSAGE);

						return;
					}

					if (j == 2 && !query2[j].matches(datePattern)) {
						JOptionPane.showMessageDialog(null, "Invalid date format.", "Alert", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (j == 3 && !query2[j].matches(pattern)) {
						JOptionPane.showMessageDialog(null, "Please input \"YES\" or \"NO\"", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (j == 4 && !query2[j].matches(pattern)) {
						JOptionPane.showMessageDialog(null, "Please input \"YES\" or \"NO\"", "Alert",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				}

				sql = "insert into orders (order_number, product, unit_price, quantity, product_type, customer,factory, oder_received_date, order_finished, order_shipped) values('"
						+ query1[0] + "','" + query1[1] + "','" + query1[2] + "','" + query1[3] + "','" + query1[4]
						+ "','" + query2[0] + "','" + query2[1] + "','" + query2[2] + "','" + query2[3] + "','"
						+ query2[4] + "');";
				
				stmt.executeUpdate(sql);
				
				
				JOptionPane.showMessageDialog(null, "Add Order successfully!", "Congratualations!",
						JOptionPane.INFORMATION_MESSAGE);
				
				
				int i = JOptionPane.showConfirmDialog(null, "Do you want to add another order?", "Hint", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.OK_OPTION) {
					sql = "";
					field1[0].setText("");
					field1[1].setText("");
					field1[2].setText("");
					field1[3].setText("");
					field1[4].setText("");
					field2[0].setText("");
					field2[1].setText("");
					field2[2].setText("");
					field2[3].setText("");
					field2[4].setText("");
					return;
				} else {
					
					stmt.close();
	
					dispose();
					
					
				}
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	









}