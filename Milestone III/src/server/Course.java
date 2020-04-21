package server;
import java.util.ArrayList;

/**
 * A class for storing and managing course info.
 * @author Dylan Rae and Tyler Sawatzky
 * @version 1.0
 * @since April 20th, 2020
 */
public class Course {

	/**
	 * The string representing the course name. 4 char long
	 */
	private String courseName;
	
	/**
	 * The int representing the course number. 3 digits long.
	 */
	private int courseNum;
	
	/**
	 * A list of prereq's for a course.
	 */
	private ArrayList<Course> preReq;
	
	/**
	 * A list of offerings for a course.
	 */
	private ArrayList<CourseOffering> offeringList;

	/**
	 * A constructor for a course.
	 * @param courseName The name of the course.
	 * @param courseNum The number of the course.
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * Adds an offering to a course.
	 * @param offering The offering to be added.
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			offeringList.add(offering);
		}
	}

	/**
	 * A getter for course name.
	 * @return The course name.
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * A setter for the course name.
	 * @param courseName The course name.
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * A getter for the course num.
	 * @return The course num.
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * A setter for the course num.
	 * @param courseNum The course number.
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	/**
	 * The toString method for the Course.
	 */
	@Override
	public String toString () {
		String st = "\0";
		st += getCourseName() + " " + getCourseNum ();
		st += "\0All course sections:\0";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\0-------\0";
		return st;
	}

	/**
	 * A getter for the course offering.
	 * @param i the index of the course offering.
	 * @return The course offering at i.
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}
}
