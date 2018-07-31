package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.ImageIcon;
public class DeleteAccount extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JButton btndelete;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JComboBox cmbacid;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccount frame = new DeleteAccount();
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
	public DeleteAccount() {
		setTitle("Delete Account");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 388, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteAccount = new JLabel("Delete Account");
		lblDeleteAccount.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		lblDeleteAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteAccount.setBounds(105, 21, 183, 36);
		contentPane.add(lblDeleteAccount);
		
		JLabel lblClerkId = new JLabel("Clerk Id");
		lblClerkId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblClerkId.setBounds(57, 84, 73, 14);
		contentPane.add(lblClerkId);
		
		cmbacid = new JComboBox();
		cmbacid.setModel(new DefaultComboBoxModel(new String[] {"Select Account ID"}));
		cmbacid.setBounds(170, 83, 131, 20);
		fillCombo();
		contentPane.add(cmbacid);
		
		btndelete = new JButton("DELETE");
		btndelete.addActionListener(this);
		btndelete.setBounds(134, 130, 89, 23);
		contentPane.add(btndelete);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DeleteAccount.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label.setBounds(0, 0, 434, 261);
		contentPane.add(label);
	}
	public void fillCombo()
	{
		try
		{
			String strsql="select userid from account";
			ps=con.prepareStatement(strsql);//prepare the query,compiles it
			rs=ps.executeQuery();//to execute the Select Query. 'rs' refers to the first item in the column referred.
			while(rs.next())//to enter in the column and check whether any entry is present in column or not.(returns Boolean value)
			{
				String acid=rs.getString("userid");//the value is returned as String and saved in acid.
				cmbacid.addItem(acid);//to add item to the combo box.
			}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String data=(String)cmbacid.getSelectedItem();
		if(data.equals("Select Account ID"))
			JOptionPane.showMessageDialog(this, "Select the Account Id");
		if(data.equals("saima"))
			JOptionPane.showMessageDialog(this, "You Can't Delete Manager's Account");
		else
		{
		int option=JOptionPane.showConfirmDialog(this, "Are You Sure To Delete?");
		if(option==0)
		{
			String strdelete="delete from account where userid=?";
			try
			{
				ps=con.prepareStatement(strdelete);
				ps.setString(1, data);
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Account Deleted Succesfully");
			}
			catch(SQLException se)
			{
			System.out.println(se);
		}
		cmbacid.removeAllItems();
		cmbacid.addItem("Select Account ID");
		fillCombo();
		
	}
}
}
}
