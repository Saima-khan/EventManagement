package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class AddAccount extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtpass;
	private JButton btnsubmit;
	private Connection con;
	private PreparedStatement ps;
	private JLabel label_1;
	private JLabel label;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAccount frame = new AddAccount();
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
	public AddAccount() {
		setTitle("Add Account");
		con=CrudOperation.createConnection();
		createGUI();
		
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 543, 385);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateAccount = new JLabel("Create Account ");
		lblCreateAccount.setForeground(new Color(153, 153, 204));
		lblCreateAccount.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
		lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setBounds(125, 25, 195, 35);
		contentPane.add(lblCreateAccount);
		
		JLabel lblClerkId = new JLabel("Clerk ID");
		lblClerkId.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblClerkId.setBounds(45, 90, 71, 26);
		contentPane.add(lblClerkId);
		
		txtid = new JTextField();
		txtid.setBounds(169, 87, 167, 35);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblClerkPassword = new JLabel("Clerk Password");
		lblClerkPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblClerkPassword.setBounds(42, 144, 123, 24);
		contentPane.add(lblClerkPassword);
		
		txtpass = new JTextField();
		txtpass.setBounds(169, 133, 167, 35);
		contentPane.add(txtpass);
		txtpass.setColumns(10);
		
		btnsubmit = new JButton("ADD");
		btnsubmit.addActionListener(this);
		btnsubmit.setForeground(new Color(153, 0, 0));
		btnsubmit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnsubmit.setBounds(125, 199, 89, 23);
		contentPane.add(btnsubmit);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddAccount.class.getResource("/images/account-256.png")));
		label_1.setBounds(271, 165, 256, 181);
		contentPane.add(label_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AddAccount.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label.setBounds(0, 0, 310, 346);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		String clerkid=txtid.getText();
		String clerkpass=txtpass.getText();
		if(clerkid.isEmpty() || clerkpass.isEmpty())
			JOptionPane.showMessageDialog(this, "Fields to be filled");
		else
		{
			try
			{
				String strinsert="insert into account values(?,?,?)";
				ps=con.prepareStatement(strinsert);
				ps.setString(1, clerkid);
				ps.setString(2, clerkpass);
				ps.setString(3, "clerk");
				
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Clerk Details Added");
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
	}
		
	}


