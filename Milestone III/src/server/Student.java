package server;
import java.util.ArrayList;

/**
 * A class used for managing and storing a students info.
 * @author Dylan Rae and Tyler Sawatzky
 * @version 1.0
 * @since April 20, 2020
 */
public class Student {
	
	/**
	 * The students name.
	 */
	private String studentName;
	
	/**
	 * The students ID.
	 */
	private int studentId;
	
	/**
	 * The list of the students registrations.
	 */
	private ArrayList<Registration> studentRegList;
	
	/**
	 * The constructor for the student.
	 * @param studentName The students name.
	 * @param studentId The students ID.
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	/**
	 * The getter for the students name,
	 * @return The students name.
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * The setter for the student name.
	 * @param studentName The student name to set.
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * A getter for the student ID.
	 * @return The student's ID.
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * A setter for the students ID.
	 * @param studentId The students ID to set.
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * The toString method for printing a students info.
	 */
	@Override
	public String toString () {
		String st = getStudentName() + "\0" +
				"Student Id: " + getStudentId() + "\0\0";
		return st;
	}
	
	/**
	 * Used to print a students registrations.
	 * @return The string of registrations.
	 */
	public String printRegs() {
		//String st = "All courses taken by student: ";
		String st = "";
		for(Registration reg : studentRegList) {
			st += reg;
			st+= "\0";
		}
		return st;
	}

	/**
	 * Adds a registration for the student.
	 * @param registration The registration to add.
	 */
	public void addRegistration(Registration registration) {
		if(studentRegList.size() > 6 ) {
			System.out.println("Student has maximum number of registrations, course not added.");
			return;
		}
		studentRegList.add(registration);
	}
	
	/**
	 * Removes a registration from a student.
	 * @param name The name of the course to remove.
	 * @param courseID The ID of the course to remove.
	 * @param secID The secID of the course to remove.
	 */
	public void removeRegistration(String name, int courseID, int secID) {
		for(Registration reg : this.studentRegList) {
			//System.out.println("here");
			//System.out.println("Course name: " + name + "courseID: " + courseID +  "Section number: " + secID);
			if(secID ==  reg.getTheOffering().getSecNum() && courseID == reg.getTheOffering().getTheCourse().getCourseNum()
					&& reg.getTheOffering().getTheCourse().getCourseName().equals(name)) {
				studentRegList.remove(reg);
				System.out.println("The registration: " + reg + "has been removed.");
				return;
			}
		}
	}
	
	/**
	 * A getter for the students registration list.
	 * @return The students RegList.
	 */
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	/**
	 * A setter for the students registration list.
	 * @param studentRegList The RegList to set.
	 */
	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}
}
