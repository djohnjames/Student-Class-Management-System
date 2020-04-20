package client;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


import javax.swing.*;

/**
 * A Log in View JFrame
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 19th, 2020
 */
public class LogInView extends JFrame{
	/**
	 * A text field for the username.
	 */
	private JTextField usernameField = new JTextField(15);;
	
	/**
	 * A password field for the password.
	 */
	private JPasswordField passwordField = new JPasswordField(15);
	
	/**
	 * A Label for the username field.
	 */
	private JLabel usernameLabel = new JLabel("Username");
	
	/**
	 * A label for the password field.
	 */
	private JLabel passwordLabel = new JLabel("Password");
	
	/**
	 * A button to log in.
	 */
	private JButton logInButton = new JButton("Log In");
	
	/**
	 * A cancel button.
	 */
	private JButton cancelButton = new JButton("Cancel");
	
	/**
	 * The north panel of the frame.
	 */
	JPanel north = new JPanel();
	
	/**
	 * The center panel of the frame.
	 */
	JPanel center = new JPanel();
	
	/**
	 * The south panel of the frame.
	 */
	JPanel south = new JPanel();
	
	/**
	 * The constructor for the view
	 */
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
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Displays a log in error jOptionPane.
	 */
	public void displayLogInError() {
		UIManager.put("OptionPane.okButtonText", "Ok"); //add ok button
		JOptionPane.showMessageDialog(null, "Credentials Invalid. Please Try Again.");
	}
	
	/**
	 * Displays a log in success JOptionPane.
	 */
	public void displayLogInSuccess() {
		UIManager.put("OptionPane.okButtonText", "Ok"); //add ok button
		JOptionPane.showMessageDialog(null, "Log In Successful!");
		
	}
	
	/**
	 * Displays a log out success panel.
	 */
	public void displayLogOutSuccess() {
		UIManager.put("OptionPane.okButtonText", "Ok"); //add ok button
		JOptionPane.showMessageDialog(null, "Log Out Successful!");
	}

	/**
	 * Adds a listener for the login button.
	 * @param listenForStudentButton
	 */
	public void addLogInButtonListener(ActionListener listenForStudentButton) {
		logInButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Adds a listener for the cancel button.
	 * @param listenForStudentButton
	 */
	public void addCancelButtonListener(ActionListener listenForStudentButton) {
		cancelButton.addActionListener(listenForStudentButton);
	}

	/**
	 * Returns the username field object
	 * @return A JTextfield for user.
	 */
	public JTextField getUsernameField() {
		return usernameField;
	}

	/**
	 * A password field for student.
	 * @return A JPasswordField for user.
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	
	
}
