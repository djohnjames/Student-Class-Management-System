package server;
import java.util.ArrayList;
import java.sql.*;


/**
 * Reads from the SQL database and maintains the lists of students and courses
 * 
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 19, 2020
 *
 */
public class DBManager implements IDBCredentials {
	
	private volatile ArrayList <Course> courseList;
	private volatile ArrayList <Student> studentList;
	private volatile CourseCatalogue cat;
	
	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}
	
	private Connection conn;
	private ResultSet rs;
	
	/**
	 * Initializes Java connection with the MySQL database
	 */
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
	
	/**
	 * Closes connection with MySQL database
	 */
	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Validates a user ID and password
	 * 
	 * @param id user entered id
	 * @param password user entered password
	 * @return "1" if credentials valid, "0" otherwise
	 */
	public String validateLogin(String id, String password) {
		initializeConnection();
		try {
			String query = "SELECT * FROM USERS WHERE id = ? and password = ?"; 
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, id);
			pStat.setString(2, password);
			rs = pStat.executeQuery();
			if (rs.next()) {
				if(rs.getString("Access").equals("Admin")) {
					close();
					return "2";
				}
				close();
				return "1";
			}
		} catch (SQLException e) {
			System.out.println("Error validating login");
		}
		close();
		return "0";
	}

	/**
	 * Loads courses and course offerings from SQL database to member ArrayList <Course> courseList
	 */
	public void readFromDataBase() {
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
		cat = new CourseCatalogue(courseList);
		close();
	}
	
	/**
	 * Loads students from SQL database to member ArrayList <Student> studentList
	 */
	public void readStFromDataBase() {
		initializeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM USERS";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				studentList.add(new Student(rs.getString("name"), rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	/**
	 * Gets the course list
	 * @return the ArrayList of courses
	 */
	public CourseCatalogue getCourseCatalogue() {
		return cat;
	}
	
	/**
	 * Gets the student list
	 * @return the ArrayList of students
	 */
	public ArrayList <Student> getStudentList() {
		return studentList;
	}
}
