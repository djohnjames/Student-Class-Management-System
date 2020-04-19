package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 * The GUIController for the clients Registration App.
 * Dictates functionality of model and GUI depending on user inputs.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 19, 2020
 */
public class GUIController {
	
	/**
	 * The logInView controlled by the GUIController
	 */
	private LogInView logInView;
	
	/**
	 * The MainView controlled by the GUIController
	 */
	private MainView mainView;
	
	/**
	 * The clientController used by the GUIController
	 */
	private ClientController client;

	/**
	 * The constructor for the GUICOntroller. Adds listeners to buttons
	 * and sets the logInView to visible.
	 * @param logInView the logInView to be controlled
	 * @param mainview the mainView to be controlled
	 * @param client the client to be used
	 */
	public GUIController(LogInView logInView, MainView mainview, ClientController client) {
		setLogInView(logInView);
		this.logInView.addCancelButtonListener(new addCancelButtonListener());
		this.logInView.addLogInButtonListener(new addLogInButtonListener());
		
		setMainView(mainview);
		this.mainView.addSearchButtonListener(new addSearchButtonListener());
		this.mainView.addAddCourseButtonListener(new addAddCourseButtonListener());
		this.mainView.addRemoveCourseButtonListener(new addRemoveCourseButtonListener());
		this.mainView.addViewAllCoursesButtonListener(new addViewAllCoursesButtonListener());
		this.mainView.addViewAllStudentCoursesButtonListener(new addViewAllStudentCoursesButtonListener());
		this.mainView.addQuitButtonListener(new addQuitButtonListener());
		
		logInView.setVisible(true);
		this.client = client;
		System.out.println(this.client.receiveCommand());
	}
	
	/**
	 * Inner class for search button in main view.
	 */
	public class addSearchButtonListener implements ActionListener {
		/**
		 * If the search button fields are no null, sends a string to server.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Search!");
			String out = mainView.searchInputDisplay();
			
			//If not null search for course and display result
			if(!out.contentEquals("")) {
				System.out.println("You have the string: "+ "1" + out + " ready to send");
				client.sendCommand("1" + out);
				mainView.searchOutputDisplay(client.receiveCommand());
			}
		}
	}
	
	/**
	 * Inner class for add course button in main view.
	 */
	public class addAddCourseButtonListener implements ActionListener {
		/**
		 * When add course is clicked, sends string to server to add course.
		 * Displays success message if successful.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("add course!!");
			String out = mainView.addCourseDisplay();
			System.out.println(out);
			if(!out.contentEquals("")) {
				client.sendCommand("2" + out);
				String in = client.receiveCommand();
				mainView.addCourseSuccess(in);
			}
		}
	}
	
	/**
	 * Inner class for remove button in main view
	 */
	public class addRemoveCourseButtonListener implements ActionListener {
		/**
		 * When remove course is clicked, sends string to server to remove course.
		 * Displays success message if successful.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("remove course!!");
			String out = mainView.removeCourseDisplay();
			if(!out.contentEquals("")) {
				client.sendCommand("3" + out);
				String in = client.receiveCommand();
				mainView.removeCourseSuccess(in);
			}
		}
	}
	
	/**
	 * Inner class for view all courses button in main view.
	 */
	public class addViewAllCoursesButtonListener implements ActionListener {
		/**
		 * When view all courses is selected, sends string to server asking
		 * for all courses
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("view all course!!");
			String out = "4"; //ask for all courses
			client.sendCommand(out);
			//recieve courses and send to display
			String in = client.receiveCommand();
			mainView.displayAllCourses(in);
		}
	}
	
	/**
	 * Inner class for view all student courses button in main view.
	 */
	public class addViewAllStudentCoursesButtonListener implements ActionListener {
		/**
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("view all student course!!");
			String out = mainView.viewStudentCoursesDisplay();
			//recieve courses and send to option pane
			if(!out.contentEquals("")) {
				client.sendCommand("5" + out);
				String in = client.receiveCommand();
				//if the return from server is 1(success) display success message
				mainView.viewStudentCoursesResult(in);
			}
		}
	}
	
	public class addQuitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Quit");
			System.exit(1);
		}
	}
	
	public class addLogInButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("login");
			//System.out.println(logInView.getUsernameField().getText().toString());
			//get: logInView.getUsernameField().getText().toString()
			//check to make sure it matches in database
			String username = logInView.getUsernameField().getText().toString();
			String password = logInView.getPasswordField().getText().toString();
			String out = username + "\0" + password;
			client.sendCommand("9" + out);
			String in = client.receiveCommand();
			
			if(in.contentEquals("1")) {
				logInView.displayLogInSuccess();
				mainView.setVisible(true);
				logInView.setVisible(false);
			}
			//this for admin mode testing
			else if(logInView.getUsernameField().getText().toString().contentEquals("22")) {
				logInView.displayLogInSuccess();
				mainView.setAdmin(true);
				mainView.setVisible(true);
				logInView.setVisible(false);
			}
			//if we are at else means login was not succesful
			else
				logInView.displayLogInError();
		}
	}
	
	public class addCancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
			System.out.println("cancel");
		}
	}

	public void setLogInView(LogInView logInView) {
		this.logInView = logInView;
	}

	public void setMainView(MainView mainview) {
		this.mainView = mainview;
	}
}
