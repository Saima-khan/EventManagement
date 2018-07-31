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

public class ViewPlaceId extends JFrame implements WindowListener{

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement psdata,pscount;
	private ResultSet rsdata,rscount;
	String[]colNames={"placeid","placename"};
	Object[][]data;
	JTable jt;
	JScrollPane jsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPlaceId frame = new ViewPlaceId();
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
	public ViewPlaceId() {
		setTitle("Places");
		addWindowListener(this);
		con=CrudOperation.createConnection();
		createGUI();
	}
public void createGUI()
{
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	fillrow();
	jt=new JTable(new DefaultTableModel(data, colNames));
	jsp=new JScrollPane(jt);
	jsp.setBounds(30,90,300,300);
	contentPane.add(jsp);
}
public void fillrow()
{
try{
	
	String strcount="select count(*) from place";
	
	pscount=con.prepareStatement(strcount);
	rscount=pscount.executeQuery();
		rscount.next();
		int cnt=rscount.getInt(1);
		data=new Object[cnt][2];
String strdata="select * from place";
psdata=con.prepareStatement(strdata);
rsdata=psdata.executeQuery();
int row=0;
	while(rsdata.next())
	{
		data[row][0]=rsdata.getString("placeid");
		data[row][1]=rsdata.getString("placename");
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
