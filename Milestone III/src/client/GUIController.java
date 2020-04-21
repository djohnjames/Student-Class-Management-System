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
 * @author Dylan Rae and Tyler Sawatzky
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
	 * The constructor for the GUIController.
	 * @param client the client to be used
	 */
	public GUIController(ClientController client) {
		newLogInView();
		this.client = client;
		System.out.println(this.client.receiveCommand());
	}
	
	/**
	 * Creates a new log in view
	 */
	public void newLogInView() {
		logInView = new LogInView();
		
		this.logInView.addCancelButtonListener(new addCancelButtonListener());
		this.logInView.addLogInButtonListener(new addLogInButtonListener());
		
		logInView.setVisible(true);
	}
	
	/**
	 * Creates and assigns a new main view
	 * @param b True for admin, false for student
	 */
	public void newMainView(Boolean b) {
		mainView = new MainView(b);
		
		this.mainView.addSearchButtonListener(new addSearchButtonListener());
		this.mainView.addAddCourseButtonListener(new addAddCourseButtonListener());
		this.mainView.addRemoveCourseButtonListener(new addRemoveCourseButtonListener());
		this.mainView.addViewAllCoursesButtonListener(new addViewAllCoursesButtonListener());
		this.mainView.addViewAllStudentCoursesButtonListener(new addViewAllStudentCoursesButtonListener());
		this.mainView.addCreateCourseButtonListener(new addCreateCourseButtonListener());
		this.mainView.addLogOutButtonListener(new addLogOutButtonListener());
		this.mainView.addQuitButtonListener(new addQuitButtonListener());
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
				//System.out.println("You have the string: "+ "1" + out + " ready to send");
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
		 * Sends and receives strings to view all courses
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
	
	/**
	 * Inner class for quit button in main view
	 */
	public class addQuitButtonListener implements ActionListener {
		/**
		 * Quits the program.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Quit");
			quit();
		}
	}
	
	/**
	 * Inner class for log in button in in LogInView
	 */
	public class addLogInButtonListener implements ActionListener {
		/**
		 * Sends and recieves Strings for log in validation. Then
		 * launches main view if ok.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("login");
			String username = logInView.getUsernameField().getText().toString();
			String password = logInView.getPasswordField().getText().toString();
			String out = username + ";" + password;
			client.sendCommand("9" + out);
			String in = client.receiveCommand();
			//System.out.println(in);
			if(in.contentEquals("1")) {
				logInView.displayLogInSuccess();
				newMainView(false);
				mainView.setVisible(true);
				logInView.dispose();
			}
			//this for admin mode testing
			else if(in.contentEquals("2")) {
				logInView.displayLogInSuccess();
				newMainView(true);
				mainView.setVisible(true);
				logInView.dispose();
			}
			//if we are at else means login was not succesful
			else
				logInView.displayLogInError();
		}
	}
	
	/**
	 * Inner class for create course button in main view
	 */
	public class addCreateCourseButtonListener implements ActionListener {
		/**
		 * Adds a course.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Create Course Button");
			String out = mainView.createCourseDisplay();
			if(!out.contentEquals("")) {
				client.sendCommand("6" + out);
				String in = client.receiveCommand();
				mainView.addCourseSuccess(in);
			}
		}
	}
	
	/**
	 * Inner class for log out button in main view
	 */
	public class addLogOutButtonListener implements ActionListener {
		/**
		 * Returns to log in screen.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			logInView.displayLogOutSuccess();
			mainView.dispose();
			newLogInView();
		}
	}
	
	/**
	 * Inner class for cancel button function in log in view
	 */
	public class addCancelButtonListener implements ActionListener {
		/**
		 * Quits the program.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("cancel");
			quit();
		}
	}
	
	public void quit() {
		client.sendCommand("0");
		client.close();
		System.exit(1);
	}

	/**
	 * A setter for the log in view
	 * @param logInView The log in view to set
	 */
	public void setLogInView(LogInView logInView) {
		this.logInView = logInView;
	}

	/**
	 * A setter for the main view
	 * @param mainview The main view to set
	 */
	public void setMainView(MainView mainview) {
		this.mainView = mainview;
	}
}
