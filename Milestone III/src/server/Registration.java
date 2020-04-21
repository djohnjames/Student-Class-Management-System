package server;

/**
 * A class for storing and managing registration data.
 * @author Dylan Rae and Tyler Sawatzky
 * @version 1.0
 * @since April 20, 2020
 */
public class Registration {
	/**
	 * The student for the registration.
	 */
	private Student theStudent;
	
	/**
	 * The offering for the registration.
	 */
	private CourseOffering theOffering;
	
	/**
	 * The grade for the registration.
	 */
	private char grade;
	
	/**
	 * Used to complete the registration.
	 * @param st The student for the registration.
	 * @param of The offering for the registration.
	 */
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	
	/**
	 * Adds the registration for the offering and the student.
	 */
	private void addRegistration () {
		theOffering.addRegistration(this);
		theStudent.addRegistration(this);
	}
	
	/**
	 * A getter for the student
	 * @return the student.
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	
	/**
	 * A setter for the student.
	 * @param theStudent The student to be set.
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	/**
	 * A getter for the course offering.
	 * @return The course offering.
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * A setter for the course offering.
	 * @param theOffering The offering to set.
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/**
	 * A getter for the grade.
	 * @return The grade for the registration.
	 */
	public char getGrade() {
		return grade;
	}
	
	/**
	 * A setter for the grade.
	 * @param grade The grade to set.
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * The toString for printing a registration.
	 */
	@Override
	public String toString () {
		String st = "\0\0";
		st += "Student Name: " + getTheStudent() + "\0";
		st += "The Offering: " + getTheOffering () + "\0";
		st += "Grade: " + getGrade();
		st += "\0-----------\0";
		return st;
	}
}
