package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import embs.dbutil.CrudOperation;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ViewDetails extends JFrame {

	private Connection con;
	private PreparedStatement psdata,pscount;
	private ResultSet rsdata,rscount;

	private JPanel contentPane;
	String[]colNames={"ClientID","Client","Event","Date"};
	Object[][]data;
	JTable jt;
	JScrollPane jsp;
	private JLabel label;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetails frame = new ViewDetails();
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
	public ViewDetails() {
		setTitle("Details");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 558, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fillData();
		jt=new JTable(new DefaultTableModel(data, colNames));
		jsp=new JScrollPane(jt);
		jsp.setBounds(89,175,337,160);
		contentPane.add(jsp);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ViewDetails.class.getResource("/images/details-title.png")));
		label.setBounds(64, -11, 414, 142);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ViewDetails.class.getResource("/images/copy-2.png")));
		label_1.setBounds(0, 0, 547, 460);
		contentPane.add(label_1);
	}
	public void fillData()
	{
		
		try{
			
		String strcount="select count(*) from eventbooking where status='Booked'";
		
		pscount=con.prepareStatement(strcount);
		rscount=pscount.executeQuery();
			rscount.next();
			int cnt=rscount.getInt(1);
			data=new Object[cnt][4];
	String strdata="select c.clientid,name,eventname,date from event e INNER JOIN eventbooking b ON e.eventid=b.eventid INNER JOIN client c ON b.clientid=c.clientid WHERE b.status='Booked'";
	psdata=con.prepareStatement(strdata);
	rsdata=psdata.executeQuery();
	int row=0;
		while(rsdata.next())
		{
			data[row][0]=rsdata.getString("clientid");
			data[row][1]=rsdata.getString("name");
			data[row][2]=rsdata.getString("eventname");
			data[row][3]=rsdata.getDate("date");
			
			row++;
			
		}
		
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		
		
		
		
		
		
		
		
		
		
	}

}
