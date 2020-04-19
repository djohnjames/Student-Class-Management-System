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

	public void createStudentTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				+ " PRIMARY KEY ( id ))";
		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created table in given database...");
	}
	
	public void insertUser(int id, String name) {
		try {
			String query = "INSERT INTO STUDENT (ID, name) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
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
