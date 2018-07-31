package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class ViewEventId extends JFrame implements WindowListener {

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement psdata,pscount;
	private ResultSet rsdata,rscount;
	String[]colNames={"eventid"};
	Object[][]data;
	JTable jt;
	JScrollPane jsp;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEventId frame = new ViewEventId();
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
	public ViewEventId() {
		setTitle("Event Id's");
		
		addWindowListener(this);
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(620, 250, 317, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		fillrow();
		contentPane.setLayout(null);
		jt=new JTable(new DefaultTableModel(data, colNames));
		jsp=new JScrollPane(jt);
		jsp.setBounds(86,55,121,122);
		contentPane.add(jsp);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ViewEventId.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label.setBounds(0, 0, 301, 246);
		contentPane.add(label);
	}
	public void fillrow()
	{
	try{
		
		String strcount="select count(*) from event";
		
		pscount=con.prepareStatement(strcount);
		rscount=pscount.executeQuery();
			rscount.next();
			int cnt=rscount.getInt(1);
			data=new Object[cnt][1];
	String strdata="select * from event";
	psdata=con.prepareStatement(strdata);
	rsdata=psdata.executeQuery();
	int row=0;
		while(rsdata.next())
		{
			data[row][0]=rsdata.getString("eventid");
			row++;
			
		}
		
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
