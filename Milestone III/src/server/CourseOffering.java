package server;
import java.util.ArrayList;

/**
 * A class to hold and manage details about a course offering.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 20, 2020
 */
public class CourseOffering {
	
	/**
	 * The number for the section.
	 */
	private int secNum;
	
	/**
	 * The capacity for the section.
	 */
	private int secCap;
	
	/**
	 * The course the offering is for.
	 */
	private Course theCourse;
	
	/**
	 * The list of registrations for the offering.
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * The constructor for an offering.
	 * @param secNum The number for the section.
	 * @param secCap The capacity for the section.
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	/**
	 * A getter for the section number
	 * @return The secNum
	 */
	public int getSecNum() {
		return secNum;
	}
	
	/**
	 * Sets the section number
	 * @param secNum the section capacity.
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	/**
	 * A getter for the secCap
	 * @return the secCap.
	 */
	public int getSecCap() {
		return secCap;
	}
	
	/**
	 * A setter for the secCap
	 * @param secCap the secCap to set.
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/**
	 * A getter for the course.
	 * @return Returns to course.
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * A setter for the course.
	 * @param theCourse The course to be set.
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * The toString mthod for printing the offering.
	 */
	@Override
	public String toString () {
		String st = "\0";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\0";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\0";
		//We also want to print the names of all students in the section
		return st;
	}
	
	/**
	 * Adds a registration to the registration list.
	 * @param registration The registration to add.
	 */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
	}
}
