package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

public class Manager extends JFrame implements ActionListener,WindowListener {

	private JPanel contentPane;
	private JButton btnacadd,btnacupdate,btnacdel,btnplace,btnaddeve,btnupdateeve,btndeleve,btnView,btnaddvenue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	public Manager() {
		createGUI();
		addWindowListener(this);
}
	public void createGUI()
	{setExtendedState(Frame.MAXIMIZED_BOTH);
	setTitle("Manager");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setBounds(625, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblWelcomeToThe = new JLabel("Welcome To The Manager Frame");
	lblWelcomeToThe.setForeground(new Color(102, 0, 51));
	lblWelcomeToThe.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
	lblWelcomeToThe.setBounds(40, 22, 406, 90);
	contentPane.add(lblWelcomeToThe);
	
	
	
	btnplace = new JButton("Add Place");
	btnplace.setForeground(new Color(255, 255, 255));
	btnplace.setBackground(new Color(204, 51, 102));
	btnplace.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnplace.setBounds(712, 142, 89, 44);
	btnplace.addActionListener(this);
	contentPane.add(btnplace);
	
	btnacupdate = new JButton("Update Account");
	btnacupdate.setForeground(new Color(255, 255, 255));
	btnacupdate.setBackground(new Color(204, 51, 102));
	btnacupdate.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnacupdate.setBounds(125, 142, 121, 44);
	btnacupdate.addActionListener(this);
	contentPane.add(btnacupdate);
	
	btnacdel = new JButton("Delete Account");
	btnacdel.setForeground(new Color(255, 255, 255));
	btnacdel.setBackground(new Color(204, 51, 102));
	btnacdel.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnacdel.setBounds(256, 142, 121, 44);
	btnacdel.addActionListener(this);
	contentPane.add(btnacdel);
	
	btnaddeve = new JButton("Add Event");
	btnaddeve.setForeground(new Color(255, 255, 255));
	btnaddeve.setBackground(new Color(204, 51, 102));
	btnaddeve.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnaddeve.setBounds(387, 142, 89, 44);
	btnaddeve.addActionListener(this);
	contentPane.add(btnaddeve);
	
	 btnupdateeve = new JButton("Update Event");
	 btnupdateeve.setForeground(new Color(255, 255, 255));
	btnupdateeve.setBackground(new Color(204, 51, 102));
	btnupdateeve.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnupdateeve.setBounds(486, 142, 107, 44);
	btnupdateeve.addActionListener(this);
	contentPane.add(btnupdateeve);
	
	btndeleve = new JButton("Delete Event");
	btndeleve.setForeground(new Color(255, 255, 255));
	btndeleve.setBackground(new Color(204, 51, 102));
	btndeleve.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btndeleve.setBounds(603, 142, 99, 44);
	btndeleve.addActionListener(this);
	contentPane.add(btndeleve);
	
	btnaddvenue = new JButton("Add Venue");
	btnaddvenue.setForeground(new Color(255, 255, 255));
	btnaddvenue.setBackground(new Color(204, 51, 102));
	btnaddvenue.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnaddvenue.addActionListener(this);
	btnaddvenue.setBounds(811, 142, 99, 44);
	contentPane.add(btnaddvenue);
	
	btnView = new JButton("View");
	btnView.setForeground(new Color(255, 255, 255));
	btnView.setBackground(new Color(204, 51, 102));
	btnView.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnView.setBounds(920, 143, 66, 43);
	btnView.addActionListener(this);
	contentPane.add(btnView);
	
	btnacadd = new JButton("Add Account");
	btnacadd.setForeground(new Color(255, 255, 255));
	btnacadd.setFont(new Font("Goudy Old Style", Font.PLAIN, 15));
	btnacadd.setBackground(new Color(204, 51, 102));
	btnacadd.setBounds(10, 143, 105, 43);
	btnacadd.addActionListener(this);
	contentPane.add(btnacadd);
	
	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(Manager.class.getResource("/images/img412.jpg")));
	label.setBounds(0, 0, 1362, 705);
	contentPane.add(label);
	
	
}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String caption=e.getActionCommand();//stores the value of label clicked
		if(caption.equals("Add Account"))
		{
			AddAccount c=new AddAccount();
			c.setVisible(true);
		}
		if(caption.equals("Update Account"))
		{
			UpdateAccount c=new UpdateAccount();
			c.setVisible(true);
		}
		if(caption.equals("Delete Account"))
		{
			DeleteAccount c=new DeleteAccount();
			c.setVisible(true);
		}
	
		if(caption.equals("Add Event"))
		{
			AddEvent c=new AddEvent();
			c.setVisible(true);
		}
		if(caption.equals("Update Event"))
		{
			UpdateEvent c=new UpdateEvent();
			c.setVisible(true);
		}
		if(caption.equals("Delete Event"))
		{
			DeleteEvent c=new DeleteEvent();
			c.setVisible(true);
		}
		
		if(caption.equals("Add Place"))
		{
			Place c=new Place();
			c.setVisible(true);
		}
		if(caption.equals("View"))
		{
			ViewDetails c=new ViewDetails();
			c.setVisible(true);
		}
		if(caption.equals("Add Venue"))
		{
			Venue c=new Venue();
			c.setVisible(true);
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
		Account si=new Account();
		si.setVisible(true);
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
