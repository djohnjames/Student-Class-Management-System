package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.WindowConstants;


public class ClientController {

	
	
	//other server socket stuff
	
	
	public static void main(String[] args) {
		EntryView view1 = new EntryView();
		LogInView view2 = new LogInView();
		MainView view3 = new MainView();
		
		GUIController GUI = new GUIController(view1, view2, view3);
		view1.setVisible(true);
		
	}
	
	public void communicate () {
		
	}
	
}
