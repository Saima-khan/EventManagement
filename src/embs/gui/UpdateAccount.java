package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import embs.dbutil.CrudOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
public class UpdateAccount extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtpass;
	private JButton btnpass,btnupdate,btnid;
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
					UpdateAccount frame = new UpdateAccount();
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
	public UpdateAccount() {
		setTitle("Update Account");
		con=CrudOperation.createConnection();
		createGUI();
		
	}
public void createGUI()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(70, 250, 545, 388);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 255, 255));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	
	
	JLabel lblClerlId = new JLabel("Clerk ID");
	lblClerlId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblClerlId.setBounds(102, 147, 68, 25);
	contentPane.add(lblClerlId);
	
	txtid = new JTextField();
	txtid.setBounds(231, 134, 159, 32);
	contentPane.add(txtid);
	txtid.setColumns(10);
	
	btnid = new JButton("View ID's");
	btnid.addActionListener(this);
	btnid.setBounds(413, 139, 89, 23);
	contentPane.add(btnid);
	
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblPassword.setBounds(102, 202, 68, 25);
	contentPane.add(lblPassword);
	
	txtpass = new JTextField();
	txtpass.setBounds(231, 200, 159, 32);
	contentPane.add(txtpass);
	txtpass.setColumns(10);
	
	btnupdate = new JButton("UPDATE");
	btnupdate.addActionListener(this);
	btnupdate.setBounds(241, 264, 89, 23);
	contentPane.add(btnupdate);
	
	btnpass = new JButton("Show");
	btnpass.addActionListener(this);
	btnpass.setBounds(413, 205, 89, 23);
	contentPane.add(btnpass);
	
	label = new JLabel("");
	label.setIcon(new ImageIcon(UpdateAccount.class.getResource("/images/update.png")));
	label.setBounds(0, 46, 256, 262);
	contentPane.add(label);
}

@Override
public void actionPerformed(ActionEvent e) 
{
	
	String caption=e.getActionCommand();
	if(caption.equals("Show"))
	{
		String id=txtid.getText();
		String strsql="select userpass from account where userid=?";//select query syntax.
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String pass=rs.getString("userpass");
				txtpass.setText(pass);
			}
			else
				JOptionPane.showMessageDialog(this, "Account ID not found");
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		finally {
			
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
	if(caption.equals("UPDATE"))
	{
		
		/* To  update multiple columns in table: update tablename set column1=?,column2=?,column3=? where id=?
		 * To update single column in table: update tablename set column=? where id=?*/
		String strupdate="update account set userpass=? where userid=?";
		try
		{
			String acid=txtid.getText();
			String acpass=txtpass.getText();
			ps=con.prepareStatement(strupdate);
			ps.setString(1,acpass );
			ps.setString(2,acid );
			int rw=ps.executeUpdate();
			if(rw>0)
				JOptionPane.showMessageDialog(this, "Data Updated");
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	if(caption.equals("View ID's"))
	{
		ViewClerkId vc=new ViewClerkId();
		vc.setVisible(true);
	
}
	
}
}


