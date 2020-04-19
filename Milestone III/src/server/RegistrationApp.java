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
			case 2: //
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
			case 3: 
				id = read.substring(1,2);
				cName = read.substring(2,6);
				cNum = read.substring(6,9);
				cSec = read.substring(9,10);
				studentSearch(studentList, Integer.parseInt(id)).removeRegistration(cName, Integer.parseInt(cNum), Integer.parseInt(cSec));
				sendString("The registration for student ID: " + id + " has been successfully removed!");
				break;
			case 4: 
				sendString(cat.toString());
				break;
			case 5:
				id = read.substring(1,2);
				String out = studentSearch(studentList, Integer.parseInt(id)).printRegs();
				sendString(out);
				break;
			default:
				sendString("Invalid Entry!");
			}
			
			
			//sendString("The selection value is: " + selection);			
			
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
	
	
//	public static void main (String [] args) {
//		CourseCatalogue cat = new CourseCatalogue ();
//		ArrayList <Student> studentList = new ArrayList<Student>();
//		DBManager db = new DBManager();
//		studentList = db.readStFromDataBase();
//		
//		Boolean run = true;
//		char selection;
//		Scanner sel = new Scanner(System.in);
//		Scanner textin = new Scanner(System.in);
//		
//		while(run == true) {
//			printMenuChoices();
//			sel = new Scanner(System.in);
//			selection = sel.next().charAt(0);
//			String input, input1, input2, input3 = null;
//			
//			switch(selection) {
//			case '1': //Course catalogue search
//				System.out.println("You have chosen to search catalogue courses.");
//				System.out.println("Please enter the course name or press 0 to exit.");
//				input = textin.nextLine(); //input takes course name
//				exitProgram(input);
//				
//				System.out.println("Please enter course number or press 0 to exit.");
//				
//				input1 = textin.nextLine(); // input1 takes course number
//				exitProgram(input);
//				
//				System.out.println("You entered the course name: " + input + ", and the course number: " + input1);
//				System.out.println(cat.searchCat(input, Integer.parseInt(input1)));
//				break;
//				
//			case '2': //Add course to students courses.
//				System.out.println("You have chosen to add course to student courses.");
//				System.out.println("Please enter student ID");
//				input = textin.nextLine();
//				
//				System.out.println("Please enter the course name");
//				input1 = textin.nextLine(); //input takes course name
//			
//				
//				System.out.println("Please enter course number.");
//				input2 = textin.nextLine(); // input1 takes course number
//				
//				
//				System.out.println("You entered the course name: " + input1 + ", and the course number: " + input2);
//				System.out.println(cat.searchCat(input1, Integer.parseInt(input2)));
//
//				System.out.println("Please enter the course section number you would like to add");
//				input3 = textin.nextLine();
//				
//				
//				Registration reg = new Registration ();
//				reg.completeRegistration(studentSearch(studentList, Integer.parseInt(input)),    cat.searchCat(input1, Integer.parseInt(input2)).getCourseOfferingAt(Integer.parseInt(input3)-1));
//				
//				System.out.println("The registration: " + reg + "has been successfuly created for the student.");
//	
//				break;
//			
//			case '3': //Remove course from student courses
//				System.out.println("You have chosen to remove course from student courses.");
//				System.out.println("Please enter student ID");
//				input = textin.nextLine();//student ID
//				
//				System.out.println("Please enter the Course name you would like to remove");
//				input1 = textin.nextLine(); //input takes course name
//			
//				
//				System.out.println("Please enter course number you would like to remove.");
//				input2 = textin.nextLine(); // input2 takes course number
//				
//				
//				System.out.println("You entered the course name: " + input1 + ", and the course number: " + input2);
//				System.out.println(cat.searchCat(input1, Integer.parseInt(input2)));
//
//				System.out.println("Please enter the course section number you would like to remove");
//				input3 = textin.nextLine(); //input 3 takes the secID
//				studentSearch(studentList, Integer.parseInt(input)).removeRegistration(input1, Integer.parseInt(input2), Integer.parseInt(input3));
//				
//				break;
//			
//			case '4': //View all courses in catalogue
//				System.out.println("You have selected to view all courses in catalogue.");
//				System.out.println(cat);
//				break;
//			
//			case '5': //View all courses taken by student
//				input = null;
//				System.out.println("You have selected to view all courses taken by a student");
//				System.out.println("Please enter the students ID number or press 0 to exit");
//				input = textin.nextLine();
//				exitProgram(input);
//				studentSearchPrint(studentList, Integer.parseInt(input));
//				break;
//				
//			case '6': //Quit program
//				System.out.println("You have chosen to close the program. Goodbye.");
//				run = false;
//				break;
//			}	
//		}
//		
//	}
//		
//		private static Student studentSearch(ArrayList<Student> studentList, int studentID) {
//			for(Student student: studentList) {
//				if(studentID == student.getStudentId()) {
//					return student;
//				}
//			}
//			return null;
//		}
//
//		private static void studentSearchPrint(ArrayList<Student> studentList, int studentID) {
//			for(Student student: studentList) {
//				if(studentID == student.getStudentId()) {
//					System.out.println("Made it inside if statement");
//					student.printRegs();
//				}
//			}
//		}
//	
//	
//	private static void printMenuChoices() {
//		System.out.println("\n\n********************************************\n\t\tMAIN MENU\n********************************************");
//		System.out.println("Welcome to the main menu. Please choose from the\nfollowing selections:\n\n" + 
//				"1: Search catalogue courses by course num.\n" +
//				"2: Add course to student courses.\n" +
//				"3: Remove course from student courses.\n" +
//				"4: View all courses in catalogue.\n" +
//				"5: View all courses taken by student.\n" +
//				"6: Quit.\n"
//				);
//	}
//
//	public static void exitProgram(String input) {
//		if(Objects.equals(input, "0")) {
//			System.out.println("You have chosen to close the program. Goodbye.");
//			System.exit(1);
//		}
//	}

}
