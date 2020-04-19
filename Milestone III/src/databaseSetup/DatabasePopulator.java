package databaseSetup;

import java.sql.*;

public class DatabasePopulator implements IDBCredentials{

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
	
	public void insertUser(int id, String name, String password) {
		try {
			String query = "INSERT INTO STUDENT (ID, name, password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}

	public void createStudentTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				 + " password VARCHAR(255), "+ " PRIMARY KEY ( id ))";
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
	
	public void addStudents() {
		insertUser(1, "Tyler", "bubblegum2");
		insertUser(2, "Dylan", "javamaster69");
		insertUser(3, "Cam", "fbrmaster420");
		insertUser(4, "Michele", "WingBats11");
		insertUser(5, "Aidan", "TicTocs87");
		insertUser(6, "Luke", "TinWits23");
		insertUser(7, "Guillaume", "Raymond-Fauteux");
	}

	
	public static void main(String[] args) {
		DatabasePopulator pop = new DatabasePopulator();
		pop.initializeConnection();
		pop.createStudentTable();
		pop.addStudents();
		pop.close();
	}

}
