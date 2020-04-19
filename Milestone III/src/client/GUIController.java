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
import javax.swing.UIManager;

public class GUIController {
	
	private EntryView entryView;
	private LogInView logInView;
	private MainView mainView;
	
	private ClientController client;

	
	public GUIController(EntryView entryView, LogInView logInView, MainView mainview, ClientController client)
	{
		setEntryView(entryView);
		this.entryView.addStudentButtonListener(new addStudentButtonListener());
		this.entryView.addAdminButtonListener(new addAdminButtonListener());
		
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
		
		entryView.setVisible(true);
		this.client = client;
		System.out.println(this.client.receiveCommand());
	}

//	private void sendString(String toSend) {
//		socketOut.println(toSend);
//		socketOut.flush();
//	}
	
	public class addSearchButtonListener implements ActionListener {
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
	
	public class addAddCourseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("add course!!");
			String out = mainView.addCourseDisplay();
			System.out.println(out);
			if(!out.contentEquals("")) {
				client.sendCommand("2" + out);
				String in = client.receiveCommand();
				mainView.addCourseSuccess(in);
				
//				//if the return from server is 1(success) display success message
//				if(in.contentEquals("1"))
//					mainView.addCourseSuccess();
//				else
//					mainView.addCourseFailure();
			}
		}
	}
	
	public class addRemoveCourseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("remove course!!");
			String out = mainView.removeCourseDisplay();
			if(!out.contentEquals("")) {
				client.sendCommand("3" + out);
				String in = client.receiveCommand();
				mainView.removeCourseSuccess(in);
				
				//if the return from server is 1(success) display success message
//				if(in.contentEquals("1"))
//					mainView.removeCourseSuccess();
//				else
//					mainView.removeCourseFailure();
			}
		}
	}
	
	
	public class addViewAllCoursesButtonListener implements ActionListener {
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
	
	public class addViewAllStudentCoursesButtonListener implements ActionListener {
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
			if(logInView.getUsernameField().getText().toString().contentEquals("11")) {
				logInView.displayLogInSuccess();
				mainView.setVisible(true);
				logInView.setVisible(false);
			}
			else
				logInView.displayLogInError();
		}
	}
	
	public class addCancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			logInView.setVisible(false);
			entryView.setVisible(true);
			System.out.println("cancel");
		}
	}
	
	
	public class addStudentButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//mainView.setUserType('s');	
			System.out.println("STUDENT");
			entryView.setVisible(false);
			logInView.setVisible(true);
				
				 //false means student mode turned on
		}
	}
	
	public class addAdminButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//mainView.setUserType('a');
			System.out.println("ADMIN");
			entryView.setVisible(false);
			logInView.setVisible(true);
		}
	}
	
	public void setEntryView(EntryView entryView) {
		this.entryView = entryView;
	}


	public void setLogInView(LogInView logInView) {
		this.logInView = logInView;
	}


	public void setMainView(MainView mainview) {
		this.mainView = mainview;
	}
	
	
}
