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

/**
 * A server-side application for a student registration app.
 * Based on client request, sends back required info for GUI.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 20, 2020
 */
public class RegistrationApp implements Runnable{
	/**
	 * The printwriter to act as socket out
	 */
	private PrintWriter socketOut;
	
	/**
	 * The server socket to connect to the client.
	 */
	private Socket aSocket;
	
	/**
	 * The buffered reader to act as socket in
	 */
	private BufferedReader socketIn;
	
	/**
	 * The course catalogue used by the app.
	 */
	private CourseCatalogue cat;
	
	/**
	 * The student list used by the app.
	 */
	private ArrayList <Student> studentList;
	
	/**
	 * The database manager for the app.
	 */
	private DBManager db;
	
	/**
	 * The constructor for the application. Sends to client a successmessage when complete.
	 * @param s The socket to connect to.
	 * @param db The database to use.
	 */
	public RegistrationApp(Socket s, DBManager db) {
		aSocket = s;
		this.db = db;
		studentList = db.getStudentList();
		cat = db.getCourseCatalogue();
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendString("Connected to a Student Record App!");
	}
	
	/**
	 * The run function for multi threading.
	 */
	public void run() {
		this.menu();
		System.out.println("User disconnected.");
	}
	
	/**
	 * The central function for the application. 
	 * Reads client message and executes desired command.
	 */
	public void menu() {
		String read = "";
		String cName = "";
		String cNum = "";
		String cSec = "";
		String sName = "";
		String id = "";
		String cSecCap = "";
		int selection = 0;
		while(true) {
			try {
				read = socketIn.readLine();
				//System.out.println("Received: " + read + " from client");
				try {
				selection = Character.getNumericValue((read.charAt(0)));
				} catch(StringIndexOutOfBoundsException e) {
					System.err.println("Input string to small");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch(selection) {
			case 0:
				close();
				return;
			
			case 1: //Course catalogue search
				cName = read.substring(1,5);
				cNum = read.substring(5,8);
				//System.out.println("The courseNum is: " + courseNum + "and the course name is: " + courseName);
				//System.out.println(cat.searchCat(cName, Integer.parseInt(cNum)).toString());
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
				
			case 6: //Add a course to course catalogue
				cName = read.substring(1,5); //Course name (ENGG)
				cNum = read.substring(5,8); //Course num (233)
				cSec = read.substring(8,9); //Number of sections/offerings
				cSecCap = read.substring(9); //Section capacity (150)
				Course c = new Course(cName, Integer.parseInt(cNum));
				for(int i = 1; i<=Integer.parseInt(cSec); i++) {
					c.addOffering(new CourseOffering(i, Integer.parseInt(cSecCap)));			
				}
				cat.addCourse(c);
				sendString("The course: " + c + " has been successfully added to the catalogue!");
				break;
				
			case 9: //Check student login
				String [] userPass = read.substring(1).split(";");
				sendString(db.validateLogin(userPass[0], userPass[1]));
				break;
				
			default:
				sendString("Invalid Entry!");
			}	
		}
	}
	
	/**
	 * Used for sending a string through teh socketout.
	 * @param toSend The string to send.
	 */
	private void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
		//System.out.println("Sent: " + toSend + " to client");
	}
	
	/**
	 * Used to close socket connections.
	 */
	public void close() {
		try {
			aSocket.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used for searching for a student in the student list.
	 * @param studentList The list to search through.
	 * @param studentID The id to search for.
	 * @return The student with matching credentials
	 */
	private Student studentSearch(ArrayList<Student> studentList, int studentID) {
		for(Student student: studentList) {
			if(studentID == student.getStudentId()) {
				return student;
			}
		}
		return null;
	}
}
