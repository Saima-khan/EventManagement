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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.sql.*;
import java.awt.event.*;
public class DeleteEvent extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JComboBox cmbid;
	private JButton btndelete;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEvent frame = new DeleteEvent();
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
	public DeleteEvent() {
		setTitle("Delete Event");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 396, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteEvent = new JLabel("Delete Event");
		lblDeleteEvent.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
		lblDeleteEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteEvent.setBounds(113, 35, 171, 20);
		contentPane.add(lblDeleteEvent);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblId.setBounds(97, 109, 54, 20);
		contentPane.add(lblId);
		
		 cmbid = new JComboBox();
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select Event ID"}));
		cmbid.setBounds(177, 111, 141, 20);
		fillCombo();
		contentPane.add(cmbid);
		
		 btndelete = new JButton("DELETE");
		 btndelete.addActionListener(this);
		btndelete.setBounds(177, 170, 89, 23);
		contentPane.add(btndelete);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DeleteEvent.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label.setBounds(0, 0, 469, 292);
		contentPane.add(label);
	}
	public void fillCombo()
	{
		try
		{
			String strsql="select eventid from event";
			ps=con.prepareStatement(strsql);//prepare the query,compiles it
			rs=ps.executeQuery();//to execute the Select Query. 'rs' refers to the first item in the column referred.
			while(rs.next())//to enter in the column and check whether any entry is present in column or not.(returns Boolean value)
			{
				String acid=rs.getString("eventid");//the value is returned as String and saved in acid.
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
		String data=(String)cmbid.getSelectedItem();
		if(data.equals("Select Event ID"))
			JOptionPane.showMessageDialog(this, "Select the Event Id");
				else
		{
		int option=JOptionPane.showConfirmDialog(this, "Are You Sure To Delete?");
		if(option==0)
		{
			String strdelete="delete from event where eventid=?";
			try
			{
				ps=con.prepareStatement(strdelete);
				ps.setString(1, data);
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Event Deleted Succesfully");
			}
			catch(SQLException se)
			{
			System.out.println(se);
		}
		cmbid.removeAllItems();
		cmbid.addItem("Select Event ID");
		fillCombo();
		
	}

}
}
}