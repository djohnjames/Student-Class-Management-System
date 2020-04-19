package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class RegistrationApp implements Runnable{
	
	
	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader socketIn;
	
	private CourseCatalogue cat = new CourseCatalogue ();
	private ArrayList <Student> studentList = new ArrayList<Student>();
	private DBManager db = new DBManager();
	
	
	public RegistrationApp(Socket s) {
		//studentList = db.readStFromDataBase();
		aSocket = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Connected!");
		sendString("Connected to a Student Record App!");
	}
	
	public void run() {
		this.menu();
	}
	
	public void menu() {
		studentList = db.readStFromDataBase();
		String read = "";
		String cName = "";
		String cNum = "";
		String cSec = "";
		String sName = "";
		String id = "";
		int selection = 0;
		while(true) {
			try {
				read = socketIn.readLine();
				try {
				selection = Character.getNumericValue((read.charAt(0)));
				} catch(StringIndexOutOfBoundsException e) {
					System.err.println("Input string to small");
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch(selection) {
			case 1: //Course catalogue search
				cName = read.substring(1,5);
				 cNum = read.substring(5,8);
				//System.out.println("The courseNum is: " + courseNum + "and the course name is: " + courseName);
				System.out.println(cat.searchCat(cName, Integer.parseInt(cNum)).toString());
				sendString(cat.searchCat(cName, Integer.parseInt(cNum)).toString());
				break;
				
			case 2: //Add course to student courses
				id = read.substring(1,2);
				cName = read.substring(2,6);
				cNum = read.substring(6,9);
				cSec = read.substring(9,10);
				//System.out.println("The id is: " + id + " the cname is " + cName + " the course number is " + cNum + " and the cSec is: " + cSec);
						
				Registration reg = new Registration ();
				reg.completeRegistration(studentSearch(studentList, Integer.parseInt(id)), cat.searchCat(cName, Integer.parseInt(cNum)).getCourseOfferingAt(Integer.parseInt(cSec)-1));
				
				System.out.println("The registration: " + reg + "has been successfuly created for the student.");
				sendString("The registration: " + reg + "has been successfuly created for the student.");
				break;
				
			case 3: //Remove course from student courses
				id = read.substring(1,2);
				cName = read.substring(2,6);
				cNum = read.substring(6,9);
				cSec = read.substring(9,10);
				studentSearch(studentList, Integer.parseInt(id)).removeRegistration(cName, Integer.parseInt(cNum), Integer.parseInt(cSec));
				sendString("The registration for student ID: " + id + " has been successfully removed!");
				break;
				
			case 4: //View the course catalogue
				sendString(cat.toString());
				break;
				
			case 5: //View student registrations
				id = read.substring(1,2);
				String out = studentSearch(studentList, Integer.parseInt(id)).printRegs();
				sendString(out);
				break;
				
			case 9: //Check student login
				String [] userPass = read.split("/0");
				//sendString(db.validateLogin(userPass[0], userPass[1]));
				
				
				
			default:
				sendString("Invalid Entry!");
			}	
		}
	}
	
	private void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	private Student studentSearch(ArrayList<Student> studentList, int studentID) {
		for(Student student: studentList) {
			if(studentID == student.getStudentId()) {
				return student;
			}
		}
		return null;
	}
}
