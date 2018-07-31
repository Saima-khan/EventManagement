package embs.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;
import embs.gui.Manager;
import embs.gui.Clerk;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Action;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
public class Account extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpass;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JButton btnsubmit; 
	private JLabel label_1;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Account() {
		setTitle("Log In");
		con=CrudOperation.createConnection();
		createGUI();
	}
public void createGUI()
{
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 547, 371);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 255, 255));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblEnterId = new JLabel("User  ID");
	lblEnterId.setFont(new Font("Century Gothic", Font.BOLD, 15));
	lblEnterId.setBounds(233, 93, 80, 17);
	contentPane.add(lblEnterId);
	
	txtid = new JTextField();
	txtid.setBounds(351, 93, 153, 33);
	contentPane.add(txtid);
	txtid.setColumns(10);
	
	JLabel lblEnterPassword = new JLabel("Password");
	lblEnterPassword.setFont(new Font("Century Gothic", Font.BOLD, 15));
	lblEnterPassword.setBounds(233, 153, 80, 14);
	contentPane.add(lblEnterPassword);
	
	txtpass = new JPasswordField();
	txtpass.setBounds(351, 153, 153, 33);
	contentPane.add(txtpass);
	
	btnsubmit = new JButton("Log In");
	btnsubmit.setBorder(null);
	btnsubmit.setBackground(SystemColor.textHighlightText);
	btnsubmit.setIcon(new ImageIcon(Account.class.getResource("/images/download.jpg")));
	btnsubmit.addActionListener(this);
	btnsubmit.setForeground(new Color(153, 0, 51));
	btnsubmit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	btnsubmit.setBounds(376, 197, 104, 57);
	contentPane.add(btnsubmit);
	
	label_1 = new JLabel("");
	label_1.setIcon(new ImageIcon(Account.class.getResource("/images/login.jpg")));
	label_1.setBounds(-12, 31, 240, 235);
	contentPane.add(label_1);
	
	label = new JLabel("");
	label.setIcon(new ImageIcon(Account.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
	label.setBounds(204, 0, 327, 332);
	contentPane.add(label);
}

@Override
public void actionPerformed(ActionEvent e) 
{
	String userid =txtid.getText();//getText used to get the text from the field.
	char[] pwd=txtpass.getPassword();
	String userpass=new String(pwd).trim();//trim() to truncate the leading and trailing spaces.
	if(userid.isEmpty() || userpass.length()==0)
	{
		JOptionPane.showMessageDialog(this, "Mandatory Fields!");
	}
	else
	{
	try
	{
		String strsql="select * from account where userid=? and userpass=?";
		ps=con.prepareStatement(strsql);
		ps.setString(1, userid);
		ps.setString(2, userpass);
		rs=ps.executeQuery();
		if(rs.next())
		{
			String actype=rs.getString("userrole");
			if(actype.equals("manager"))
			{
				Manager m=new Manager();//to navigate object is created which invokes AdminFrame constructor.
				m.setVisible(true);
				this.dispose();
			}
			if(actype.equals("clerk"))
			{
				Clerk c=new Clerk();
				c.setVisible(true);
				this.dispose();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Invalid UserId or Password");
		}
		
	}
	catch(SQLException se)
	{
		System.out.println(se);
	}
	
	
}
}
}