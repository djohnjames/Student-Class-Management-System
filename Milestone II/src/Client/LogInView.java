package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


import javax.swing.*;

public class LogInView extends JFrame{

	private String username, password;
	
	private JTextField usernameField;
	private JTextField passwordField;
	
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	
	private JButton logInButton = new JButton("Log In");
	private JButton cancelButton = new JButton("Cancel");
	
	public LogInView() {
		super("Student Log In");
		
		setSize(600, 150);
		setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.add(new JLabel("Please Enter Your Username and Password"));
		add("North", north);
		
		//Center
		JPanel center = new JPanel();
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		usernameLabel.setLabelFor(usernameField);
		passwordLabel.setLabelFor(passwordField);
		center.add(usernameLabel);
		center.add(usernameField);
		center.add(passwordLabel);
		center.add(passwordField);
		
		add("Center", center);
		//add labels for user and password
		
		
		//South
		JPanel south = new JPanel();
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


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
}
