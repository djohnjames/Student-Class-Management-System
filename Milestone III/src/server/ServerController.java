package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	 * Construct a Server with Port 9090
	 */
	public ServerController(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
			System.out.println("Student Record Application is now running.");
			
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}

	/**
	* Connects and communicates with a client. 
	* 
	* @throws IOException
	*/
	public void communicate() {
		try {
			while(true) {
				System.out.println("Waiting for a user to connect...");
				
				RegistrationApp aRegApp = new RegistrationApp(serverSocket.accept());
				System.out.println("User connected!");
				pool.execute(aRegApp);
				//aRegApp.menu();
				
				System.out.println("Registration App opened and running for a user.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}

	}
	
	/**
	 * Runs the Server.
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		ServerController aServer = new ServerController(9090);
		aServer.communicate();
	}
}
