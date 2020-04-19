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
			LogInView view1 = new LogInView();
			MainView view2 = new MainView();
			GUIController GUI = new GUIController(view1, view2, this);
			
			
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
	
	public static void main(String[] args) {
		ClientController client = new ClientController("localhost", 9090);
		//client.communicate();
	}
	
	
	
}
