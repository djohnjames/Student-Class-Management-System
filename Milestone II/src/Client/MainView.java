package Client;

import java.awt.BorderLayout;

import javax.swing.*;


public class MainView extends JFrame {

	private JButton searchButton = new JButton ("Search Catalogue Courses");
	private JButton addCourseButton = new JButton ("Add Course to Student Courses");
	private JButton removeCourseButton = new JButton ("Remove Course from Student Course");
	private JButton viewAllCoursesButton  = new JButton ("View all Courses");
	private JButton viewAllStudentCoursesButton  = new JButton ("View all Courses Taken by a Student");
	private JButton createCourseButton = new JButton("Create a New Course");
	private JButton quitButton = new JButton ("Quit");
	
	boolean userType;
	
	public MainView() {
		
		super("Main Menu");
		setSize(500, 500);
		setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.add(new JLabel("Welcome to the main menu. Please select from the options below."));
		add("North", north);
		
		//Center
		JPanel center = new JPanel();
		center.add(searchButton);
		center.add(addCourseButton);
		center.add(removeCourseButton);
		center.add(viewAllCoursesButton);
		center.add(quitButton);
		if(userType == true)
			center.add(createCourseButton);
		add("Center", center);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void setUserType(boolean b) {
		this.userType = b;
	}
	
	
	
}
