package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
public class EventBooking extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JTextField txtbookid;
	private JComboBox cmbvenue,cmbevent,cmbclient;
	private JDateChooser jd;
	private JRadioButton rdbook,rdnotbook;
	private JButton btnsubmit;
	private ButtonGroup booking;
Calendar c;
java.sql.Date todaydate;

private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventBooking frame = new EventBooking();
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
	public EventBooking() {
		setTitle("Event Booking");
		 c=Calendar.getInstance();
		 c.add(Calendar.DATE, -1);
		 todaydate=new java.sql.Date(c.getTime().getTime());
		con=CrudOperation.createConnection();
		updateBooking();
		createGUI();
	}
public void createGUI()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	booking=new ButtonGroup();
	setBounds(400, 200, 594, 424);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblBookId = new JLabel("BOOK ID");
	lblBookId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblBookId.setBounds(28, 76, 68, 25);
	contentPane.add(lblBookId);
	
	txtbookid = new JTextField();
	txtbookid.setBounds(125, 76, 165, 28);
	contentPane.add(txtbookid);
	txtbookid.setColumns(10);
	
	jd = new JDateChooser();
	jd.setBounds(125, 112, 165, 25);
	jd.getJCalendar().setMinSelectableDate(todaydate);
	contentPane.add(jd);


	
	JLabel lblDate = new JLabel("DATE");
	lblDate.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblDate.setBounds(28, 112, 64, 25);
	contentPane.add(lblDate);
	
	JLabel lblVenueId = new JLabel("VENUE ID");
	lblVenueId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblVenueId.setBounds(28, 148, 77, 25);
	contentPane.add(lblVenueId);
	
	cmbvenue = new JComboBox();
	cmbvenue.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
	cmbvenue.setModel(new DefaultComboBoxModel(new String[] {"Select Venue ID"}));
	cmbvenue.setBounds(125, 151, 165, 25);
	fillCombov();
	contentPane.add(cmbvenue);
	
	JLabel lblPlaceId = new JLabel("EVENT ID");
	lblPlaceId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblPlaceId.setBounds(28, 184, 77, 25);
	contentPane.add(lblPlaceId);
	
	cmbevent = new JComboBox();
	cmbevent.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
	cmbevent.setModel(new DefaultComboBoxModel(new String[] {"Select Event ID"}));
	cmbevent.setBounds(125, 185, 165, 25);
	fillComboe();
	contentPane.add(cmbevent);
	
	JLabel lblClientId = new JLabel("CLIENT ID");
	lblClientId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblClientId.setBounds(24, 220, 91, 25);
	contentPane.add(lblClientId);
	
	 cmbclient = new JComboBox();
	cmbclient.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
	cmbclient.setModel(new DefaultComboBoxModel(new String[] {"Select Client ID"}));
	cmbclient.setBounds(125, 221, 165, 25);
	fillComboc();
	contentPane.add(cmbclient);
	
	JLabel lblStatus = new JLabel("STATUS");
	lblStatus.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblStatus.setBounds(28, 256, 68, 28);
	contentPane.add(lblStatus);
	
	 rdbook = new JRadioButton("Booked");
	rdbook.setBackground(Color.WHITE);
	rdbook.setBounds(125, 253, 68, 23);
	contentPane.add(rdbook);
	
	 rdnotbook = new JRadioButton("Not Booked");
	rdnotbook.setBackground(Color.WHITE);
	rdnotbook.setBounds(125, 278, 105, 23);
	contentPane.add(rdnotbook);
	
	booking.add(rdbook);
	booking.add(rdnotbook);
	
	btnsubmit = new JButton("SUBMIT");
	btnsubmit.addActionListener(this);
	btnsubmit.setBounds(105, 320, 89, 23);
	contentPane.add(btnsubmit);
	
	label_1 = new JLabel("");
	label_1.setIcon(new ImageIcon(EventBooking.class.getResource("/images/booking_icon.png")));
	label_1.setBounds(273, 11, 320, 374);
	contentPane.add(label_1);
}
public void updateBooking()
{
	try{
	String strcount="select date,bookid,venueid from eventbooking";
	ps=con.prepareStatement(strcount);
	rs=ps.executeQuery();
		while(rs.next())
		{
			
			
			Date sqldate=rs.getDate("date");
			String bid=rs.getString("bookid");
			String vid=rs.getString("venueid");
			if(sqldate.before(todaydate))
			{

				String strupdate1="update venue set status=? where venueid=? ";
				try
				{
					
					ps=con.prepareStatement(strupdate1);
					ps.setString(1,"Not Booked");
					ps.setString(2, vid);
					int rw1=ps.executeUpdate();
						}
				catch(SQLException se)
				{
					System.out.println(se);
				}
				String strdelete="delete from eventbooking where bookid=?";
				try
				{
					
					ps=con.prepareStatement(strdelete);
					ps.setString(1,bid);
					int rw1=ps.executeUpdate();
						}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
		}
		
	
		
}
	catch(SQLException se)
	{
		System.out.println(se);
	}
}
public void fillCombov()
{
	
	try
	{
		
		String strsql="select venueid from venue where status='Not Booked'";
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			
			String venue=rs.getString("venueid");
					cmbvenue.addItem(venue);//to add item to the combo box.
			
			}
	}
	catch(SQLException se)
	{
		System.out.println(se);
	}
}

public void fillComboe()
{
	try
	{
		String strsql="select eventid from event";
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			String acid=rs.getString("eventid");
			cmbevent.addItem(acid);
		}
	}
	catch(SQLException se)
	{
		System.out.println(se);
	}
}

public void fillComboc()
{
	try
	{
		String strsql="select clientid from client";
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			String acid=rs.getString("clientid");
			cmbclient.addItem(acid);
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
	String status=null;
	// TODO Auto-generated method stub
	String id=txtbookid.getText();
	java.util.Date d=jd.getDate();
	java.sql.Date sd= new java.sql.Date(d.getTime());

	
	String venue=(String)cmbvenue.getSelectedItem();
	String event=(String)cmbevent.getSelectedItem();
	String client=(String)cmbclient.getSelectedItem();
	if(rdbook.isSelected())
		status=rdbook.getText();
	if(rdnotbook.isSelected())
		status=rdnotbook.getText();
	if(id.isEmpty() || d==null ||venue.equals("Select Venue ID")||event.equals("Select Event ID")||client.equals("Select Client ID"))
		JOptionPane.showMessageDialog(this, "Fields to be filled");
	else
	{
		try
		{
			String strinsert="insert into eventbooking values(?,?,?,?,?,?)";
			ps=con.prepareStatement(strinsert);
			ps.setString(1, id);
			ps.setDate(2, sd);
			ps.setString(3, venue);
			ps.setString(4, event);
			ps.setString(5, client);
			ps.setString(6, status);
			int rw=ps.executeUpdate();
			if(rw>0)
			{
				JOptionPane.showMessageDialog(this, "Event Booked");
				if(status=="Booked")
				{
					String strupdate="update venue set status=? where venueid=?";
					try
					{
						
						ps=con.prepareStatement(strupdate);
						ps.setString(1,"Booked" );
						ps.setString(2, venue);
						int rw1=ps.executeUpdate();
							}
					catch(SQLException se)
					{
						System.out.println(se);
					}
				}
			}
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	cmbvenue.removeAllItems();
	cmbvenue.addItem("Select Venue ID");
	fillCombov();
	
}
	
}

