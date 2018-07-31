package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Clerk extends JFrame implements ActionListener,WindowListener {

	private JPanel contentPane;
	private JButton btnadd,btnbook;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clerk frame = new Clerk();
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
	public Clerk() {
		setResizable(false);
		addWindowListener(this);
	createGUI();
	
	}
public void createGUI()
{
	setTitle("Clerk");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setBounds(100, 100, 900, 564);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	btnbook = new JButton("Book Event");
	btnbook.setForeground(new Color(255, 255, 255));
	btnbook.setBackground(new Color(153, 0, 51));
	btnbook.setFont(new Font("Goudy Old Style", Font.PLAIN, 20));
	btnbook.setBounds(36, 161, 158, 56);
	btnbook.addActionListener(this);
	contentPane.add(btnbook);
	
	 btnadd = new JButton("Add Client");
	 btnadd.setForeground(new Color(255, 255, 255));
	 btnadd.setBackground(new Color(153, 0, 51));
	btnadd.setFont(new Font("Goudy Old Style", Font.PLAIN, 20));
	btnadd.setBounds(36, 242, 158, 56);
	btnadd.addActionListener(this);
	contentPane.add(btnadd);
	
	JLabel lblWelcome = new JLabel("WELCOME !!!!");
	lblWelcome.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
	lblWelcome.setBounds(393, 50, 158, 61);
	contentPane.add(lblWelcome);
	
	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(Clerk.class.getResource("/images/img41.jpg")));
	label.setBounds(0, 0, 894, 535);
	contentPane.add(label);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String Caption=e.getActionCommand();
	if(Caption.equals("Book Event"))
	{
		EventBooking ev=new EventBooking();
		ev.setVisible(true);
	}
	if(Caption.equals("Add Client"))
	{
		AddClient c=new AddClient();
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
