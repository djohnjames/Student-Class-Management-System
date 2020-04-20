package server;

/**
 * An interface used to store information required to access the SQL server.
 * @author Dylan Rae & Tyler Sawatzky
 * @version 1.0
 * @since April 20th, 2020
 *
 */
public interface IDBCredentials {
	
	// JDBC driver name and database URL
	/**
	 * The location of the JDBC driver.
	 */
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   
   /**
    * The URL for the SQL server.
    */
   static final String DB_URL = "jdbc:mysql://localhost/mydb?serverTimezone=UTC";

   /**
    * The username to access the server.
    */
   static final String USERNAME = "root";
   
   /**
    * The password to access the server.
    */
   static final String PASSWORD = "NaLyDnAlYd1@";
}
