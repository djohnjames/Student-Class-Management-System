package databaseSetup;

import java.sql.*;

public class DatabasePopulator implements IDBCredentials{

	private Connection conn;

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
	
	public void insertUser(int id, String name, String password, String access) {
		try {
			String query = "INSERT INTO STUDENTS (ID, name, password, access) values(?,?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			pStat.setString(4, access);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}

	public void createStudentTable() {
		String sql = "CREATE TABLE STUDENTS " + "(ID INTEGER not NULL, " + " name VARCHAR(255), "
				 + " password VARCHAR(255), " + " access VARCHAR(255), " + " PRIMARY KEY ( id ))";
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
	
	public void createCourseTable() {
		String sql = "CREATE TABLE COURSES " + "(ID INTEGER not NULL, " + " name VARCHAR(255), "
				 + "number INTEGER not NULL, " + "offerings INTEGER not NULL, " + " PRIMARY KEY ( id ))";
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
	
	public void insertCourse(int id, String name, int number, int offerings) {
		try {
			String query = "INSERT INTO COURSES (ID, name, number, offerings) values(?,?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setInt(3, number);
			pStat.setInt(4, offerings);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting course");
			e.printStackTrace();
		}
	}
	
	public void addStudents() {
		insertUser(1, "Tyler", "bubblegum2", "Admin");
		insertUser(2, "Dylan", "javamaster69", "Admin");
		insertUser(3, "Cam", "fbrmaster420", "Student");
		insertUser(4, "Michele", "WingBats11", "Student");
		insertUser(5, "Aidan", "TicTocs87", "Student");
		insertUser(6, "Luke", "TinWits23", "Student");
		insertUser(7, "Guillaume", "Raymond-Fauteux", "Student");
	}
	
	public void addCourses() {
		insertCourse(1, "ENGG", 233, 3);
		insertCourse(2, "ENSF", 409, 1);
		insertCourse(3, "PHYS", 259, 2);
		insertCourse(4, "ENCM", 339, 2);
		insertCourse(5, "ENEL", 353, 3);
	}

	
	public static void main(String[] args) {
		DatabasePopulator pop = new DatabasePopulator();
		pop.initializeConnection();
		pop.createStudentTable();
		pop.addStudents();
		pop.createCourseTable();
		pop.addCourses();
		pop.close();
	}

}
