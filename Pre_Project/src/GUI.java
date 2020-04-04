import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import javax.swing.*;


/**
 * This class creates a GUI that allows user to 
 * import and search through a binary search tree 
 * for student records.
 * @author Dylan Rae and Tyler Sawatzky
 * @version 2.0
 * @since April 2, 2020
 *
 */
public class GUI extends JFrame{

	/**
	 * Buttons for GUI
	 */
	private JButton insert, find, browse, create, b1, b2;
	/**
	 * Text area for GUI
	 */
	private JTextArea myText;
	
	/**
	 * Scanner for use by whole GUI
	 */
	private Scanner txtFile = null;
	
	/**
	 * The BST used by the GUI
	 */
	private BinSearchTree bst;
	
	
	/**
	 * Constructor for GUI. Runs buttons and calls
	 * required methods for user button selection.
	 * @param s Name of window
	 * @param widthInPixels width of window
	 * @param heightInPixels height of window
	 */
	public GUI (String s, int widthInPixels,int heightInPixels) {
		super (s); //setTitle(s);
		setSize(widthInPixels, heightInPixels);
		setLayout(new BorderLayout());
		
		//North
		JPanel north = new JPanel();
		north.add(new JLabel("An Application to Maintain Student Records"));
		add("North", north);
		
		//South
		JPanel south = new JPanel();
		insert = new JButton("Insert");
		insert.addActionListener((ActionEvent e) -> {
			System.out.println("INSERT!");
			insertStudent();
			
		});
		south.add(insert);
		
		find = new JButton("Find");
		find.addActionListener((ActionEvent e) -> {
			System.out.println("FIND");
			findStudent();
		});
		south.add(find);
		
		browse = new JButton("Broswe");
		browse.addActionListener((ActionEvent e) -> {
			System.out.println("BROWSE");
			displayStudents();
		});
		south.add(browse);
		
		create = new JButton("Create Tree from File");
		create.addActionListener((ActionEvent e) -> {
			System.out.println("CREATE");
			createTree();
		});
		south.add(create);
	
		add("South", south);
		
		//Center
		myText = new JTextArea();
		add("Center", new JScrollPane(myText));
		
		//West
		//Not used
		
		//East
		add("East", new Scrollbar(Scrollbar.VERTICAL));
		
		setVisible(true); //displays the frame
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Imports file to create initial tree. Fills tree with records in file.
	 */
	public void createTree() {
		UIManager.put("OptionPane.cancelButtonText", "Cancel"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "OK"); //add ok button
		JTextField treeFile = new JTextField(15); //Width of text field
		Object[] inputFile = {"Enter the file name:", treeFile};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Input", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		if (option == JOptionPane.OK_OPTION) {
			//System.out.println("here");
			String fileName = treeFile.getText().toString();
			if (fileFound(fileName)) {
				bst = new BinSearchTree(); //create a new BST when importing file
				String line = txtFile.nextLine().replace(" ",  "");
				bst.insert(line.substring(0, 5), line.substring(5, 7), line.substring(7, 11), line.substring(11));
				
				while(txtFile.hasNextLine()) {
					line = txtFile.nextLine().replace(" ",  "");
					bst.insert(line.substring(0, 5), line.substring(5, 7), line.substring(7, 11), line.substring(11));
				}
				txtFile.close();
				JOptionPane.showMessageDialog(null, "File successfully imported!");
			}
		}
	}
	
	private boolean fileFound(String file) {
		try {
			txtFile = new Scanner(new FileInputStream(file));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error. File with name : " + file + " could not be found.");
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * method used to find a student record.
	 */
	public void findStudent() {
		if (checkBST() == false)
			return;
		UIManager.put("OptionPane.cancelButtonText", "Cancel"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "OK"); //add ok button
		JTextField studentID = new JTextField(15); //Width of text field
		Object[] inputFile = {"Please enter the student's ID", studentID};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Input", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		if (option == JOptionPane.OK_OPTION) {
			String id = studentID.getText().toString();
			Node record = bst.find(bst.getRoot(), id);
			if (record == null) {
				JOptionPane.showMessageDialog(null, "The student could not be found.");
				return;
			}
			String student = record.toString();
			JOptionPane.showMessageDialog(null, student);
		}
		
	}
	
	/**
	 * method used to insert a student record.
	 */
	public void insertStudent() {
		if (checkBST() == false)
			return;
		UIManager.put("OptionPane.cancelButtonText", "Return to Main Window"); // add cancel button
		UIManager.put("OptionPane.okButtonText", "Insert"); //add ok button
		JTextField studentID = new JTextField(10); //Width of text field
		JTextField faculty = new JTextField(15);
		JTextField major = new JTextField(15);
		JTextField year = new JTextField(10);
		Object[] inputFile = {"Enter the Student ID", studentID, "Enter Faculty", faculty, "Enter Student's Major", major, "Enter year", year};
		int option = JOptionPane.showConfirmDialog(null,  inputFile, "Insert a New Node", JOptionPane.OK_CANCEL_OPTION); //Brings up dialog titled input with input file displayed
		if (option == JOptionPane.OK_OPTION) {
			String id = studentID.getText().toString();
			String fac = faculty.getText().toString();
			String maj = major.getText().toString();
			String ye = year.getText().toString();
			bst.insert(id, fac, maj, ye);
			displayStudents(); //display with updated records
			
		}
		
	}
	
	/**
	 * method used to display all student records to the text area.
	 */
	public void displayStudents() {
		if (checkBST() == false)
			return;
		StringWriter out = new StringWriter();
		PrintWriter content = new PrintWriter(out);
		try {
			bst.print_tree(bst.getRoot(), content);
		} catch (IOException e) {
			System.err.println("Error printing bst!");
			e.printStackTrace();
		}
		myText.setText(out.toString());
	}

	private boolean checkBST() {
		if(bst == null || bst.getRoot() == null) {
			JOptionPane.showMessageDialog(null, "Please import a file first.");
			return false;
		}
		return true;
	}
	
}
