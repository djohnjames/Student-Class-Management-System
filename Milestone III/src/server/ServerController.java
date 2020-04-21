package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A server-side controller for a student registration application.
 * @author Dylan Rae and Tyler Sawatzky
 * @version 1.0
 * @since April 20th, 2020
 */
public class ServerController {
	
	/**
	 * A serverSocket used for connecting
	 */
	private ServerSocket serverSocket;
	
	/**
	 * Used for multi game support
	 */
	private ExecutorService pool;
	
	/**
	 * The Database manager to be used by the server.
	 */
	DBManager db;
	
	/**
	 * The contructor for the server. 
	 * @param port The port to connect to.
	 */
	public ServerController(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
			
			db = new DBManager();
			db.readFromDataBase();
			db.readStFromDataBase();
			
			System.out.println("Student Record Application is now running.\n\n");
			
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}

	/**
	* Connects and communicates with a client. 
	*/
	public void communicate() {
		try {
			while(true) {
				System.out.println("Waiting for a user to connect...");
				
				RegistrationApp aRegApp = new RegistrationApp(serverSocket.accept(), db);
				System.out.println("User connected!");
				pool.execute(aRegApp);
				
				System.out.println("Registration App opened and running for a user.\n\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}
	
	/**
	 * Runs the Server.
	 * @param args
	 */
	public static void main(String [] args) {
		ServerController aServer = new ServerController(9090);
		aServer.communicate();
	}
}
