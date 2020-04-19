package server;

public class Registration {
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	public Registration() {
		
	}
	
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	
	private void addRegistration () {
		theOffering.addRegistration(this);
		theStudent.addRegistration(this);
		
	}
	
	
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
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
