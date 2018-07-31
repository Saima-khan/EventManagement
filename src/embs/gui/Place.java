package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import embs.dbutil.CrudOperation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.ImageIcon;

public class Place extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
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
					Place frame = new Place();
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
	public Place() {
		setTitle("Place");
		con=CrudOperation.createConnection();
		createGUI();
	}
public void createGUI()
{
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(70, 250, 512, 345);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 255, 255));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblPlaceid = new JLabel("PLACE ID");
	lblPlaceid.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblPlaceid.setBounds(94, 68, 92, 31);
	contentPane.add(lblPlaceid);
	
	txtid = new JTextField();
	txtid.setBounds(224, 66, 143, 38);
	contentPane.add(txtid);
	txtid.setColumns(10);
	
	JLabel lblPlaceName = new JLabel("PLACE NAME");
	lblPlaceName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblPlaceName.setBounds(94, 133, 107, 31);
	contentPane.add(lblPlaceName);
	
	txtname = new JTextField();
	txtname.setBounds(224, 133, 143, 38);
	contentPane.add(txtname);
	txtname.setColumns(10);
	
	 btnsubmit = new JButton("SUBMIT");
	 btnsubmit.addActionListener(this);
	btnsubmit.setBounds(200, 200, 89, 23);
	contentPane.add(btnsubmit);
	
	label = new JLabel("");
	label.setIcon(new ImageIcon(Place.class.getResource("/images/location_add.png")));
	label.setBounds(368, 156, 128, 150);
	contentPane.add(label);
	
	label_1 = new JLabel("");
	label_1.setIcon(new ImageIcon(Place.class.getResource("/images/triangle-geometrical-background-vector-1714260.jpg")));
	label_1.setBounds(0, 0, 496, 306);
	contentPane.add(label_1);
}

@Override
public void actionPerformed(ActionEvent e) 
{
	// TODO Auto-generated method stub
	String id=txtid.getText();
	String name=txtname.getText();
	if(id.isEmpty()||name.isEmpty())
		JOptionPane.showMessageDialog(this, "Fields Required!");
	
		else
		{
			try
			{
				String strinsert="insert into place values(?,?)";
				ps=con.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, name);
				
				
				int rw=ps.executeUpdate();
				if(rw>0)
					JOptionPane.showMessageDialog(this, "Place Added");
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
	}
}

