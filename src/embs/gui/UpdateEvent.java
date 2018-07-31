package embs.gui;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class UpdateEvent extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtcharge;
	private JButton btnsubmit,btnshow,btnid;
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
					UpdateEvent frame = new UpdateEvent();
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
	public UpdateEvent() {
		setTitle("Update Event");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 541, 395);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEventId = new JLabel("EVENT ID");
		lblEventId.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblEventId.setBounds(147, 128, 72, 35);
		contentPane.add(lblEventId);
		
		txtid = new JTextField();
		txtid.setBounds(263, 114, 139, 35);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		btnid = new JButton("View ID's");
		btnid.addActionListener(this);
		btnid.setBounds(426, 120, 89, 23);
		contentPane.add(btnid);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblName.setBounds(147, 164, 58, 23);
		contentPane.add(lblName);
		
		txtname = new JTextField();
		txtname.setBounds(263, 160, 139, 35);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtcharge = new JTextField();
		txtcharge.setBounds(263, 205, 139, 35);
		contentPane.add(txtcharge);
		txtcharge.setColumns(10);
		
		JLabel lblCharge = new JLabel("CHARGE");
		lblCharge.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblCharge.setBounds(147, 193, 72, 25);
		contentPane.add(lblCharge);
		
		btnshow = new JButton("Show Details");
		btnshow.addActionListener(this);
		btnshow.setBounds(257, 275, 108, 23);
		contentPane.add(btnshow);
		
		btnsubmit = new JButton("UPDATE");
		btnsubmit.addActionListener(this);
		btnsubmit.setBounds(375, 275, 89, 23);
		contentPane.add(btnsubmit);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(UpdateEvent.class.getResource("/images/update.png")));
		label.setBounds(42, 28, 293, 270);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String caption=e.getActionCommand();
		if(caption.equals("Show Details"))
		{
			String id=txtid.getText();
			String strsql="select eventname,charge from event where eventid=?";//select query syntax.
			try
			{
				ps=con.prepareStatement(strsql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next())
				{
					String name=rs.getString("eventname");
					txtname.setText(name);
					int ch=rs.getInt("charge");
					txtcharge.setText(Integer.toString(ch));
				}
				else
					JOptionPane.showMessageDialog(this, "Event ID not found");
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
			String strupdate="update event set eventname=?,charge=? where eventid=?";
			try
			{
				String eid=txtid.getText();
				String ename=txtname.getText();
				int echarge=Integer.parseInt(txtcharge.getText());
				ps=con.prepareStatement(strupdate);
				ps.setString(1,ename );
				ps.setInt(2,echarge);
				ps.setString(3, eid);
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
			ViewEventId ve=new ViewEventId();
			ve.setVisible(true);
		
	}
		
	}
		
	}


