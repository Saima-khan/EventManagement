package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.*;
public class AddEvent extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtcharge;
	private JButton btnsubmit;
	private Connection con;
	private PreparedStatement ps;
	private JLabel label;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEvent frame = new AddEvent();
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
	public AddEvent() {
		setTitle("Add Event");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(70, 250, 555, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtid = new JTextField();
		contentPane.add(txtid);
		txtid.setBounds(145, 67, 156, 40);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		contentPane.add(txtname);
		txtname.setBounds(145, 118, 156, 40);
		txtname.setColumns(10);
		
		txtcharge = new JTextField();
		contentPane.add(txtcharge);
		txtcharge.setBounds(145, 169, 156, 40);
		txtcharge.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblId.setBounds(64, 72, 54, 27);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblName.setBounds(64, 123, 62, 27);
		contentPane.add(lblName);
		
		JLabel lblCharge = new JLabel("CHARGE");
		lblCharge.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblCharge.setBounds(64, 174, 62, 27);
		contentPane.add(lblCharge);
		
		 btnsubmit = new JButton("ADD");
		 btnsubmit.addActionListener(this);
		btnsubmit.setBounds(145, 235, 89, 23);
		contentPane.add(btnsubmit);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AddEvent.class.getResource("/images/add-event.png")));
		label.setBounds(329, 95, 252, 266);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddEvent.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
		label_1.setBounds(0, 0, 539, 354);
		contentPane.add(label_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id=txtid.getText();
		String name=txtname.getText();
		int charge=Integer.parseInt(txtcharge.getText());
		if(id.isEmpty() || name.isEmpty() || charge==0)
			JOptionPane.showMessageDialog(this, "Fields Requuired!");
		else
		{
			try
			{
				String strinsert="insert into event values(?,?,?)";
				ps=con.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setInt(3, charge);
				
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Event Details Added");
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		
	}

}
