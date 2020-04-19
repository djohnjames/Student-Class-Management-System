package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.WindowConstants;


public class ClientController {

	private PrintWriter socketOut;
	
	private Socket aSocket;
	
	//private Socket GUISocket;
	
	private BufferedReader stdIn;
	
	private BufferedReader socketIn;
	

	
	public ClientController (String serverName, int portNumber) {
		try {
			//Set up sockets
			aSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			
			//set up GUI
			EntryView view1 = new EntryView();
			LogInView view2 = new LogInView();
			MainView view3 = new MainView();
			GUIController GUI = new GUIController(view1, view2, view3, this);
			
			
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	public void sendCommand(String s) {
		socketOut.println(s);
		socketOut.flush();
	}
	
	public String receiveCommand() {
		String data = "";
		try {
			data = socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(data.contains("\0")) {
			data = data.replace("\0", "\n");
		}
		
		return data;
	}
	
	
//	public void communicate () {
//		//String toServer = "";
//		//String fromServer = "";
//		try {
//			while (true) {
//				String line = "";
//				//while (true) {
//				line = socketIn.readLine();
//						//if (line.contains("\0")) {
//						//	line = line.replace("\0",  "");
//					//		System.out.println(line);
//					//		break;
//					//	}
//				System.out.println(line);
//			
//				line = stdIn.readLine();
//				socketOut.println(line);
//				socketOut.flush();
//			}
//		} catch (IOException e) {
//			System.out.println("Sending error: " + e.getMessage());
//		}
//		
//		finally {
//			try {
//				stdIn.close();
//				aSocket.close();
//				socketOut.close();
//			}
//			catch( IOException e) {
//				System.out.println("Closing error: " + e.getMessage());
//			}
//		}
//	}
	
	
	
	public static void main(String[] args) {
		ClientController client = new ClientController("localhost", 9090);
		//client.communicate();
	}
	
	
	
}
