package server;
import java.util.ArrayList;
import java.sql.*;


//This class is simulating a database for our
//program
public class DBManager implements IDBCredentials {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;
	
	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}
	
	private Connection conn;
	private ResultSet rs;
	
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList readFromDataBase() {
		courseList.add(new Course ("ENGG", 233));
		courseList.get(0).addOffering(new CourseOffering(1, 100));
		courseList.get(0).addOffering(new CourseOffering(2, 200));
		courseList.get(0).addOffering(new CourseOffering(3, 300));
		
		courseList.add(new Course ("ENSF", 409));
		courseList.get(1).addOffering(new CourseOffering(1, 100));
		courseList.get(1).addOffering(new CourseOffering(2, 200));
		courseList.get(1).addOffering(new CourseOffering(3, 300));
		
		courseList.add(new Course ("PHYS", 259));
		courseList.get(2).addOffering(new CourseOffering(1, 100));
		courseList.get(2).addOffering(new CourseOffering(2, 200));
		courseList.get(2).addOffering(new CourseOffering(3, 300));

		return courseList;
	}
	
	public ArrayList readStFromDataBase() {
		studentList.add(new Student("Sara", 1));
		studentList.add(new Student("Sam", 2));
		studentList.add(new Student("Sad", 3));
		
		return studentList;
	}
	
	
	
	
	
	
	
	
	

}
