

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainView extends JFrame {

	private JButton searchButton = new JButton ("Search Catalogue Courses");
	private JButton addCourseButton = new JButton ("Add Course to Student Courses");
	private JButton removeCourseButton = new JButton ("Remove Course from Student Course");
	private JButton viewAllCoursesButton  = new JButton ("View all Courses");
	private JButton viewAllStudentCoursesButton  = new JButton ("View all Courses Taken by a Student");
//	private JButton createCourseButton = new JButton("Create a New Course");
	private JButton quitButton = new JButton ("Quit");
	
	//char userType;
	
	public MainView() {
		
		super("Main Menu");
		setSize(500, 200);
		setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.add(new JLabel("Welcome to the Main Menu. Please select from the options below."));
		add("North", north);
		
		//Center
		JPanel center = new JPanel();
		center.add(searchButton);
		center.add(addCourseButton);
		center.add(removeCourseButton);
		center.add(viewAllCoursesButton);
		center.add(viewAllStudentCoursesButton);
		center.add(quitButton);

		add("Center", center);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

//	public void setUserType(char b) {
//		System.out.println("User type is: "+ userType);
//		this.userType = b;
//		System.out.println("User type is: "+ userType);
//	}
	
	
	
	public void addSearchButtonListener(ActionListener listenForStudentButton) {
		searchButton.addActionListener(listenForStudentButton);
	}
	
	public void addAddCourseButtonListener(ActionListener listenForStudentButton) {
		addCourseButton.addActionListener(listenForStudentButton);
	}
	
	public void addRemoveCourseButtonListener(ActionListener listenForStudentButton) {
		removeCourseButton.addActionListener(listenForStudentButton);
	}
	
	public void addViewAllCoursesButtonListener(ActionListener listenForStudentButton) {
		viewAllCoursesButton.addActionListener(listenForStudentButton);
	}
	
	public void addViewAllStudentCoursesButtonListener(ActionListener listenForStudentButton) {
		viewAllStudentCoursesButton.addActionListener(listenForStudentButton);
	}
	
	public void addQuitButtonListener(ActionListener listenForStudentButton) {
		quitButton.addActionListener(listenForStudentButton);
	}
	
}
