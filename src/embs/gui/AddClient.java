package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Color;
public class AddClient extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtemail;
	private JButton btnsubmit;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClient frame = new AddClient();
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
	public AddClient() {
		setResizable(false);
		setTitle("Add Client");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 399, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClientDetails = new JLabel("Client Details");
		lblClientDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientDetails.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		lblClientDetails.setBounds(49, 23, 216, 34);
		contentPane.add(lblClientDetails);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblId.setBounds(74, 84, 55, 22);
		contentPane.add(lblId);
		
		txtid = new JTextField();
		txtid.setBounds(158, 87, 158, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblName.setBounds(62, 115, 55, 22);
		contentPane.add(lblName);
		
		txtname = new JTextField();
		txtname.setBounds(158, 118, 158, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblAddress.setBounds(57, 148, 72, 20);
		contentPane.add(lblAddress);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(158, 149, 158, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("PHONE No.");
		lblPhoneNo.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPhoneNo.setBounds(62, 186, 94, 14);
		contentPane.add(lblPhoneNo);
		
		txtphone = new JTextField();
		txtphone.setBounds(158, 180, 158, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(62, 211, 72, 14);
		contentPane.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setBounds(158, 211, 158, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		 btnsubmit = new JButton("ADD");
		 btnsubmit.addActionListener(this);
		btnsubmit.setBounds(140, 252, 89, 23);
		contentPane.add(btnsubmit);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AddClient.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label.setBounds(0, 0, 539, 331);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id=txtid.getText();
		String name=txtname.getText();
		String adrs=txtaddress.getText();
		int phone=Integer.parseInt(txtphone.getText());
		String email=txtemail.getText();
		if(id.isEmpty() || name.isEmpty() || email.isEmpty() || phone==0 || adrs.isEmpty())
			JOptionPane.showMessageDialog(this, "Fields to be filled");
		else
		{
			try
			{
				String strinsert="insert into client values(?,?,?,?,?)";
				ps=con.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, adrs);
				ps.setInt(4, phone);
				ps.setString(5, email);
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Client Details Added");
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
	}

}
