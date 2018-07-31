package embs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setTitle("Welcome");
		createGUI();
	}
	public void createGUI()
	{
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 646, 428);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(Welcome.class.getResource("/images/image1.jpg")));
	label.setBounds(0, 0, 630, 390);
	contentPane.add(label);
	new Thread(new Runnable()
	{
public void run()
{
	try
	{
	
		Thread.sleep(2000);
		 Account ac=new Account();
		ac.setVisible(true);
		Welcome.this.dispose();
	 }catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.out.println(e);;
	}
}
	}).start();
	
}
}