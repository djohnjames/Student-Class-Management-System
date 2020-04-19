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
			System.out.println("Connected to Database");
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
	
	public String validateLogin(String id, String password) {
		initializeConnection();
		try {
			String query = "SELECT * FROM STUDENTS WHERE id = ? and password = ?"; 
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, id);
			pStat.setString(2, password);
			rs = pStat.executeQuery();
			if (rs.next()) {
				close();
				return "1";
			}
		} catch (SQLException e) {
			System.out.println("Error validating login");
		}
		close();
		return "nope";
	}

	public ArrayList <Course> readFromDataBase() {
		initializeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM COURSES";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Course c = new Course(rs.getString("name"), rs.getInt("number"));
				for(int i = 1; i <= rs.getInt("offerings"); i++) {
					c.addOffering(new CourseOffering(i, 150));
				}
				courseList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
//		courseList.add(new Course ("ENGG", 233));
//		courseList.get(0).addOffering(new CourseOffering(1, 100));
//		courseList.get(0).addOffering(new CourseOffering(2, 200));
//		courseList.get(0).addOffering(new CourseOffering(3, 300));
		
		return courseList;
	}
	
	public ArrayList <Student> readStFromDataBase() {
		initializeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM STUDENTS";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				studentList.add(new Student(rs.getString("name"), rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
//		studentList.add(new Student("Sara", 1));
//		studentList.add(new Student("Sam", 2));
//		studentList.add(new Student("Sad", 3));
		
		return studentList;
	}
	
	
	
	
	
	
	
	
	

}
