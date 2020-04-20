package client;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The main view for the reg app.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 20th, 2020
 */
public class MainView extends JFrame {

	/**
	 * The Panel for the north of the frame.
	 */
	private JPanel north = new JPanel();
	
	/**
	 * The panel for the west side of the frame.
	 */
	private JPanel west = new JPanel();
	
	/**
	 * The text area for teh GUI.
	 */
	private JTextArea myText = new JTextArea();
	
	/**
	 * The Button to search catalogue courses
	 */
	private JButton searchButton = new JButton ("Search Catalogue Courses");
	
	/**
	 * The Button to add a course to students courses.
	 */
	private JButton addCourseButton = new JButton ("Add Course to Student Courses");
	
	/**
	 * The button to remove courses from a student
	 */
	private JButton removeCourseButton = new JButton ("Remove Course from Student Course");
	
	/**
	 * The button to view all courses
	 */
	private JButton viewAllCoursesButton  = new JButton ("View all Courses");
	
	/**
	 * The button to view all student courses
	 */
	private JButton viewAllStudentCoursesButton = new JButton ("View all Student Courses");
	
	/**
	 * The button for admins to create a new course
	 */
	private JButton createCourseButton = new JButton("Create a New Course");
	
	/**
	 * The button to quit.
	 */
	private JButton quitButton = new JButton ("Quit");
	
	/**
	 * The button to log out.
	 */
	private JButton logOutButton = new JButton("Log Out");
	
	/**
	 * A boolean used to determine if it is admin or student view.
	 */
	private boolean admin = false;

	/**
	 * The default constructor for the view.
	 */
	public MainView() {
		super("Main Menu");
		setSize(600, 500);
		setLayout(new BorderLayout());
		
		//North
		north.add(new JLabel("Welcome to the Main Menu. Please select from the options on the left."));
		add("North", north);
		
		//West
		west.setLayout(new GridLayout(15,1));
		west.add(searchButton);
		west.add(addCourseButton);
		west.add(removeCourseButton);
		west.add(viewAllCoursesButton);
		west.add(viewAllStudentCoursesButton);
		west.add(logOutButton);
		west.add(quitButton);

		add("West", west);

		//Center
		myText.setEditable(false);
		add("Center", new JScrollPane(myText));
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Displays option pane to gather course name and course number.
	 * @return The user input as a string for course name and number
	 */
	public String searchInputDisplay() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "Search"); //add ok button
		JTextField courseName = new JTextField(10); //Width of text field
		JTextField courseNum = new JTextField(10);
		Object[] inputFile = {"Enter the Course Name", courseName, "Enter the Course Number", courseNum,};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Course Search", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		//if the fields are not null then proceed
		if (option == JOptionPane.OK_OPTION && !courseName.getText().toString().contentEquals("") && !courseNum.getText().toString().contentEquals("")) {
			String cName = courseName.getText().toString();
			String cNum = courseNum.getText().toString();
			String stringOut = cName + cNum;
			return stringOut;
		}
		else if (option ==JOptionPane.CANCEL_OPTION) {
			return "";
		}
		else {
			UIManager.put("OptionPane.okButtonText", "Ok");
			JOptionPane.showMessageDialog(null, "Search Invalid. Please Try Again.");
			return "";
		}
	}
	
	/**
	 * Displays the String in(course search info) in an option pane
	 * @param in the course info to be printed. New lines should be represented by \0
	 */
	public void searchOutputDisplay(String in) {
		//in.replace("\0", "\n");
		UIManager.put("OptionPane.okButtonText", "Ok");
		JOptionPane.showMessageDialog(null, "Course Info: " + in);
	}
	
	/**
	 * Displays the pane to gather info for adding a course
	 * @return The string with addcourse info. Null if cancel.
	 */
	public String addCourseDisplay() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "Add Course"); //add ok button
		JTextField studentId = new JTextField(10);
		JTextField courseName = new JTextField(10);
		JTextField courseNum = new JTextField(10);
		JTextField courseSection = new JTextField(10); 
		Object[] inputFile = {"Enter the Student ID", studentId, "Enter the Course Name", courseName, "Enter the Course Number", courseNum, "Enter the Course Section Number", courseSection};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Add Course", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		//if the fields are not null then proceed
		if (option == JOptionPane.OK_OPTION && !studentId.getText().contentEquals("") && !courseName.getText().contentEquals("") && !courseNum.getText().contentEquals("") && !courseSection.getText().contentEquals("")) {
			String id = studentId.getText().toString();
			String cName = courseName.getText().toString();
			String cNum = courseNum.getText().toString();
			String cSec = courseSection.getText().toString();
			String stringOut = id + cName + cNum + cSec;
			//System.out.println("You have the string: "+ stringOut + " ready to send");
			return stringOut;
		}
		return "";
	}
	
	/**
	 * Displays a ane to notify of course addition success.
	 * @param in The string to be displayed in the pane.
	 */
	public void addCourseSuccess(String in) {
		UIManager.put("OptionPane.okButtonText", "Ok");
		JOptionPane.showMessageDialog(null, in);
	}
	
	/**
	 * Displays a pane to gather information to remove a course
	 * @return The string with info to remove a course. Null if cancel.
	 */
	public String removeCourseDisplay() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "Remove Course"); //add ok button
		JTextField studentId = new JTextField(10);
		JTextField courseName = new JTextField(10);
		JTextField courseNum = new JTextField(10);
		JTextField courseSection = new JTextField(10); 
		Object[] inputFile = {"Enter the Student ID", studentId, "Enter the Course Name", courseName, "Enter the Course Number", courseNum, "Enter the Course Section Number", courseSection};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Remove Course", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		//if the fields are not null then proceed
		if (option == JOptionPane.OK_OPTION && !studentId.getText().contentEquals("") && !courseName.getText().contentEquals("") && !courseNum.getText().contentEquals("") && !courseSection.getText().contentEquals("")) {
			String id = studentId.getText().toString();
			String cName = courseName.getText().toString();
			String cNum = courseNum.getText().toString();
			String cSec = courseNum.getText().toString();
			String stringOut = id + cName + cNum + cSec;
			System.out.println("You have the string: "+ stringOut + " ready to send");
			return stringOut;
		}
		return "";
	}
	
	/**
	 * Displays a pane for success removing a course
	 * @param in the string to be displayed on the pane.
	 */
	public void removeCourseSuccess(String in) {
		UIManager.put("OptionPane.okButtonText", "Ok");
		JOptionPane.showMessageDialog(null, in);
	}
	
	/**
	 * Displays a pane for remove course failure
	 */
	public void removeCourseFailure() {
		UIManager.put("OptionPane.okButtonText", "Ok");
		JOptionPane.showMessageDialog(null, "Course Removal Failed. Please Try Again.");
	}
	
	/**
	 * Displays Course Catalogue from server
	 * @param text the Course info in a string format
	 */
	public void displayAllCourses(String text) {
		myText.setText(text);
	}
	
	/**
	 * Displays a pane to gather student info and display their courses.
	 * @return The string with info necessary to display.
	 */
	public String viewStudentCoursesDisplay() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "View Students Courses"); //add ok button
		JTextField studentId = new JTextField(10);
	
		Object[] inputFile = {"Enter the Student ID", studentId};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "View Students Courses", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		//if the fields are not null then proceed
		if (option == JOptionPane.OK_OPTION && !studentId.getText().contentEquals("")) {
			String id = studentId.getText().toString();
			String stringOut = id;
			System.out.println("You have the string: "+ stringOut + " ready to send");
			return stringOut;
		}
		return "";
	}
	
	/**
	 * The display pane to create a course for an admin.
	 * @return A string for the server to create a new course.
	 */
	public String createCourseDisplay() {
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "Add Course to Catalogue"); //add ok button
		JTextField courseName = new JTextField(10);
		JTextField courseNum = new JTextField(10);
		JTextField courseSection = new JTextField(10); 
		JTextField courseSecCap = new JTextField(10);
		Object[] inputFile = {"Enter the New Course Name", courseName, "Enter the New Course Number", courseNum, "Enter the Number of Sections", courseSection, "Enter the Section Capacity", courseSecCap};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Create Course", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		//if the fields are not null then proceed
		if (option == JOptionPane.OK_OPTION && !courseName.getText().contentEquals("") && !courseNum.getText().contentEquals("") && !courseSection.getText().contentEquals("")) {
			String cName = courseName.getText().toString();
			String cNum = courseNum.getText().toString();
			String cSec = courseSection.getText().toString();
			String secCap = courseSecCap.getText().toString();
			String stringOut = cName + cNum + cSec + secCap;
			//System.out.println("You have the string: "+ stringOut + " ready to send");
			return stringOut;
		}
		return "";
	}
	
	/**
	 * Used to display a students course info.
	 * @param s A String containing student course info.
	 */
	public void viewStudentCoursesResult(String s) {
		UIManager.put("OptionPane.okButtonText", "Ok");
		JOptionPane.showMessageDialog(null, "Student Course Info: " + s);
	}
	
	/**
	 * Used to add a action listener for search button
	 * @param listenForStudentButton The action listener.
	 */
	public void addSearchButtonListener(ActionListener listenForStudentButton) {
		searchButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for add course button
	 * @param listenForStudentButton
	 */
	public void addAddCourseButtonListener(ActionListener listenForStudentButton) {
		addCourseButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for remove course button
	 * @param listenForStudentButton The action listener.
	 */
	public void addRemoveCourseButtonListener(ActionListener listenForStudentButton) {
		removeCourseButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for view all courses button
	 * @param listenForStudentButton The action listener.
	 */
	public void addViewAllCoursesButtonListener(ActionListener listenForStudentButton) {
		viewAllCoursesButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for view all student courses button
	 * @param listenForStudentButton The action listener.
	 */
	public void addViewAllStudentCoursesButtonListener(ActionListener listenForStudentButton) {
		viewAllStudentCoursesButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for create course button
	 * @param listenForStudentButton The action listener.
	 */
	public void addCreateCourseButtonListener(ActionListener listenForStudentButton) {
		createCourseButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for log out button
	 * @param listenForStudentButton The action listener.
	 */
	public void addLogOutButtonListener(ActionListener listenForStudentButton) {
		logOutButton.addActionListener(listenForStudentButton);
	}
	
	/**
	 * Used to add a action listener for quit button
	 * @param listenForStudentButton The action listener.
	 */
	public void addQuitButtonListener(ActionListener listenForStudentButton) {
		quitButton.addActionListener(listenForStudentButton);
	}

	/**
	 * Used to se the admin boolean. Calls function to add admin features.
	 * @param b true for adding an admin
	 */
	public void setAdmin(boolean b) {
		this.admin  = b;
		System.out.println("Admin user detected!");
		addAdminFeatures();
		
	}
	
	/**
	 * Adds admin button and label to mainview frame.
	 */
	private void addAdminFeatures() {
		west.add(createCourseButton);
		north.add(new JLabel("Admin mode enabled!"));
	}
}
