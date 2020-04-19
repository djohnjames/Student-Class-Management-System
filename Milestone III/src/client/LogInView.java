package client;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


import javax.swing.*;

public class LogInView extends JFrame{
	
	private JTextField usernameField = new JTextField(15);;
	private JPasswordField passwordField = new JPasswordField(15);
	
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	
	private JButton logInButton = new JButton("Log In");
	private JButton cancelButton = new JButton("Cancel");
	
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	
	public LogInView() {
		super("Student Log In");
		
		setSize(600, 150);
		setLayout(new BorderLayout());
		
		//North
		north.add(new JLabel("Please Enter Your Username and Password"));
		add("North", north);
		
		//Center
		usernameLabel.setLabelFor(usernameField);
		passwordLabel.setLabelFor(passwordField);
		center.add(usernameLabel);
		center.add(usernameField);
		center.add(passwordLabel);
		center.add(passwordField);
		
		add("Center", center);
		//add labels for user and password
		
		
		//South
		south.add(logInButton);
		south.add(cancelButton);
		add("South", south);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	
	public void displayLogInError() {
		JOptionPane.showMessageDialog(null, "Credentials Invalid. Please Try Again.");
	}
	
	public void displayLogInSuccess() {
		JOptionPane.showMessageDialog(null, "Log In Successful!");
	}
	

	public void addLogInButtonListener(ActionListener listenForStudentButton) {
		logInButton.addActionListener(listenForStudentButton);
	}
	
	public void addCancelButtonListener(ActionListener listenForStudentButton) {
		cancelButton.addActionListener(listenForStudentButton);
	}


	public JTextField getUsernameField() {
		return usernameField;
	}


	public JTextField getPasswordField() {
		return passwordField;
	}
	
}
