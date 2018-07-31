package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import java.sql.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
public class Venue extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtvenueid;
	private JTextField txtname;
	private JButton btnsubmit;
	private JComboBox cmbid;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public JRadioButton rdbook,rdnotbook;
	private ButtonGroup booking;
	private JLabel label;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Venue frame = new Venue();
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
	public Venue() {
		setTitle("Venue");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		booking=new ButtonGroup();
		setBounds(70, 250, 406, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVenueId = new JLabel("VENUE ID");
		lblVenueId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblVenueId.setBounds(50, 104, 77, 29);
		contentPane.add(lblVenueId);
		
		txtvenueid = new JTextField();
		txtvenueid.setBounds(174, 103, 159, 34);
		contentPane.add(txtvenueid);
		txtvenueid.setColumns(10);
		
		JLabel lblVenueName = new JLabel("VENUE NAME");
		lblVenueName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblVenueName.setBounds(50, 149, 113, 29);
		contentPane.add(lblVenueName);
		
		txtname = new JTextField();
		txtname.setBounds(174, 148, 159, 34);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblPlaceId = new JLabel("PLACE ID");
		lblPlaceId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPlaceId.setBounds(50, 196, 93, 29);
		contentPane.add(lblPlaceId);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblStatus.setBounds(50, 236, 78, 22);
		contentPane.add(lblStatus);
		
		 btnsubmit = new JButton("SUBMIT");
		 btnsubmit.addActionListener(this);
		btnsubmit.setBounds(149, 280, 89, 23);
		contentPane.add(btnsubmit);
		
		cmbid = new JComboBox();
		cmbid.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select Place ID"}));
		fillCombo();
		cmbid.setBounds(174, 194, 162, 34);
		
		contentPane.add(cmbid);
		
		 rdbook = new JRadioButton("Booked");
		rdbook.setBackground(new Color(255, 255, 255));
		rdbook.setBounds(174, 238, 77, 23);
		contentPane.add(rdbook);
		
		 rdnotbook = new JRadioButton("Not Booked");
		rdnotbook.setBackground(new Color(255, 255, 255));
		rdnotbook.setBounds(253, 238, 93, 23);
		contentPane.add(rdnotbook);
		booking.add(rdbook);
		booking.add(rdnotbook);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Venue.class.getResource("/images/img-huonhill-icon-bottom-venue.png")));
		label.setBounds(51, -85, 267, 150);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Venue.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label_1.setBounds(0, 0, 551, 343);
		contentPane.add(label_1);
	}
	public void fillCombo()
	{
		try
		{
			String strsql="select placeid from place";
			ps=con.prepareStatement(strsql);//prepare the query,compiles it
			rs=ps.executeQuery();//to execute the Select Query. 'rs' refers to the first item in the column referred.
			while(rs.next())//to enter in the column and check whether any entry is present in column or not.(returns Boolean value)
			{
				String acid=rs.getString("placeid");//the value is returned as String and saved in acid.
				cmbid.addItem(acid);//to add item to the combo box.
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
		String caption=e.getActionCommand();
		String status=null;
		// TODO Auto-generated method stub
		if(caption.equals("SUBMIT"))
		{
		String vid=txtvenueid.getText();
		String name=txtname.getText();
		String data=(String)cmbid.getSelectedItem();
		if(rdbook.isSelected())
			status=rdbook.getText();
		if(rdnotbook.isSelected())
			status=rdnotbook.getText();
		if(vid.isEmpty()||name.isEmpty()||data.equals("Select Place ID")||status.isEmpty())
			JOptionPane.showMessageDialog(this, "Fields Required!");
		else
		{
			try
			{
				String strinsert="insert into venue values(?,?,?,?)";//same as syntax of mysql and the number of ?=number of columns in table 
				ps=con.prepareStatement(strinsert);//pass the query to database
				ps.setString(1, vid);
				ps.setString(2, name);
				ps.setString(3, status);
				ps.setString(4, data);
				int rw=ps.executeUpdate();//to insert the values in table executeUpdate function used.
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Data Added");
			}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		}
		
	}
		
}
}
