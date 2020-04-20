package server;
import java.util.ArrayList;

/**
 * A class for managing the course catalogue.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 20th, 2020
 */
public class CourseCatalogue {
	
	/**
	 * The list of courses in the catalogue.
	 */
	private ArrayList <Course> courseList;
	
	/**
	 * The constructor for the catalogue.
	 * @param courseList The courselist to add to the catalogue.
	 */
	public CourseCatalogue (ArrayList <Course> courseList) {
		this.courseList = courseList;
		
	}
	
	/**
	 * Adds a course offering to a course in the list.
	 * @param c The course to add offerings to.
	 * @param secNum The section number of the offering.
	 * @param secCap The cap for the section.
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	/**
	 * Used to search through the catalogue for a course.
	 * @param courseName The course name to search for
	 * @param courseNum The course num to search for
	 * @return The course matching the above parameters.
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	
	/**
	 * Adds a course to the course list.
	 * @param c The course to add
	 */
	public void addCourse(Course c) {
		courseList.add(c);
	}
	
	/**
	 * Used to display error if course not found.
	 */
	private void displayCourseNotFoundError() {
		System.err.println("Course was not found!");
	}
	
	/**
	 * A getter for the course list.
	 * @return The list for the catalogue.
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/**
	 * A setter for the course list.
	 * @param courseList The courselist to set for the catalogue.
	 */
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * The toString method for the catalogue.
	 */
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \0";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\0";
		}
		return st;
	}
}
