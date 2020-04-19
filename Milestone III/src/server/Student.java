package server;
import java.util.ArrayList;

public class Student {
	
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
	@Override
	public String toString () {
		String st = getStudentName() + "\0" +
				"Student Id: " + getStudentId() + "\0\0";
		return st;
	}
	
	public String printRegs() {
		//String st = "All courses taken by student: ";
		String st = "";
		for(Registration reg : studentRegList) {
			st += reg;
			st+= "\0";
		}
		return st;
	}

	public void addRegistration(Registration registration) {
		if(studentRegList.size() > 6 ) {
			System.out.println("Student has maximum number of registrations, course not added.");
			return;
		}
		studentRegList.add(registration);
	}
	
	public void removeRegistration(String name, int courseID, int secID) {
		//System.out.println("removereg has been called for" + this.toString());
		for(Registration reg : this.studentRegList) {
			//System.out.println("in for loop");
			if(secID ==  reg.getTheOffering().getSecNum() && courseID == reg.getTheOffering().getTheCourse().getCourseNum()
					&& reg.getTheOffering().getTheCourse().getCourseName().equals(name)) {
				studentRegList.remove(reg);
				System.out.println("The registration: " + reg + "has been removed.");
				return;
			}
		}
	}
	

	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public void setStudentRegList(ArrayList<Registration> studentRegList) {
		this.studentRegList = studentRegList;
	}
}
