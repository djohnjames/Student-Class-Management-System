package client;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;


public class EntryView extends JFrame{

	/**
	 * To make serizable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Buttons for first screen
	 */
	private JButton studentButton = new JButton("Student");
	private JButton adminButton = new JButton("Admin");

	/**
	 * Creates view when user first connects
	 */
	public EntryView() {
		super("Student Record Application");
		setSize(400, 150);
		setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.add(new JLabel("Please Select Your User Type"));
		add("North", north);
		
		//Center
		JPanel center = new JPanel();
		center.add(studentButton);
		center.add(adminButton);
		add("Center", center);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	public void addStudentButtonListener(ActionListener listenForStudentButton)
	{
		studentButton.addActionListener(listenForStudentButton);
	}
	
	public void addAdminButtonListener(ActionListener listenForAdminButton)
	{
		adminButton.addActionListener(listenForAdminButton);
	}
	
}
