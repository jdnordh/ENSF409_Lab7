package exercise1;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * 
 * @author jeremy phillips and jordan nordh
 * @version 1.0
 *
 */
public class Student_recs extends JFrame{

	/**
	 * serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * All JTextFields used
	 */
	private JTextField inputFileName;
	private JTextField inputStudentNumber;
	private JTextField inputFaculty;
	private JTextField inputMajor;
	private JTextField inputYear;

	/**
	 * buttons on the main window
	 */
	private JButton insert = new JButton("Insert");
	private JButton find = new JButton("Find");
	private JButton browse = new JButton("Browse");
	private JButton ctff = new JButton("Create Tree from File");
	
	/**
	 * buttons on the file input window
	 */
	private JButton okFile;
	private JButton cancelFile;
	
	/**
	 * buttons on the find student window
	 */
	private JButton okFind;
	private JButton cancelFind;
	
	/**
	 * buttons on the found and not found windows
	 */
	private JButton okDisplayFound = new JButton("OK");
	private JButton okDisplayNotFound = new JButton("OK");
	
	/**
	 * buttons on the insert new student window
	 */
	private JButton insertBut;
	private JButton retToMain;
	
	
	private JScrollPane textAreaScrollPane = new JScrollPane();
	private JTextArea textArea = new JTextArea();
	private JFrame fileWindow;
	
	/**
	 * global strings to save input JTextFields
	 */
	private String sn = null;
	private String fac = null;
	private String maj = null;
	private String yr = null;
	
	private JFrame findWindow;
	private JFrame displayFoundWindow;
	private JFrame displayNotFoundWindow;
	private JFrame insertWindow;
	private BinSearchTree bt = new BinSearchTree();		//create empty tree


	/**
	 * default constructor to create the main window and initiate the program
	 */
	public Student_recs() {
		//basic formatting
		this.setTitle("Main Window");
		this.setBounds(300, 200, 500, 300);
		
		//add panels
		this.add(createUpperPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.add(createLowerPanel(), BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * create a panel to put at the TOP of the main window
	 * @return the created upperPanel
	 */
	private JLabel createUpperPanel() {
		JLabel upperpanel = new JLabel("An Application to Maintain Student Records", SwingConstants.CENTER);
		return upperpanel;
	}

	/**
	 * create a panel to put at the CENTER of the main window
	 * @return the created centerPanel
	 */
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 255, 255));
		textAreaScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.setFont(new Font("Courier New", Font.BOLD, 12));
		textArea.setEditable(false);
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setPreferredSize(new Dimension(475, 200));
		centerPanel.add(textAreaScrollPane);
		return centerPanel;
	}
	
	/**
	 * create a panel to put at the BOTTOM of the main window
	 * @return the created lowerPanel
	 */
	private JPanel createLowerPanel() {
		JPanel lowerPanel = new JPanel();
		insert.addActionListener(new ButtonListener());
		find.addActionListener(new ButtonListener());
		browse.addActionListener(new ButtonListener());
		ctff.addActionListener(new ButtonListener());
		lowerPanel.add(insert);
		lowerPanel.add(find);
		lowerPanel.add(browse);
		lowerPanel.add(ctff);
		return lowerPanel;
	}
	
	/**
	 * button listening
	 * @author jeremy phillips and jordan nordh
	 *
	 */
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//insert button pressed on main window
			if (e.getSource() == insert) {
				insertWindow();
			}
			
			//find button pressed on the main window
			else if(e.getSource() == find){
				FindWindow();
			}
			
			//browse button pressed on the main window
			else if(e.getSource() == browse){
				String s = new String();
				PrintWriter out = null;
				StringWriter buffer = new StringWriter();
				textArea.setText(null);
				try {
					out = new PrintWriter(buffer);
					bt.print_tree(bt.getRoot(), out);
					s = buffer.toString();
					textArea.append(s);
				} catch (FileNotFoundException e1) {
					System.err.println("Error: cannot open PrintWriter.");
				} catch (IOException e1) {
					System.err.println("Error: IO exception.");
				}
			}
			
			//create tree from file button pressed on the main window
			else if(e.getSource() == ctff) {
				InputFileWindow();
			}
			
			//OK button pressed on create tree from file popup window
			else if(e.getSource() == okFile) {
				String inputFN = inputFileName.getText();
				bt.destroy();
				
				File file = new File(inputFN);
				try {
					Scanner inputFile = new Scanner(file);
					while(inputFile.hasNextLine()) {
						String[] splitted = inputFile.nextLine().split("\\s+");
						bt.insert(splitted[1], splitted[2], splitted[3], splitted[4]);
					}
					inputFile.close();
				} catch (FileNotFoundException e1) {
					System.err.println("Error: file not found.");
				}
				fileWindow.dispose();
			}
			
			//cancel button pressed on create tree from file popup window
			else if(e.getSource() == cancelFile) {
				fileWindow.dispose();
			}
			
			//OK button pressed on the find popup window
			else if(e.getSource() == okFind) {
				sn = inputStudentNumber.getText();
				Node found = bt.find(bt.getRoot(), sn);
				findWindow.dispose();
				
				if(found != null)
				{
					sn = found.toString();
					displayFind();
				}
				else if(found == null)
					displayNotFound();
				
			}
			
			//cancel button pressed on the find popup window
			else if(e.getSource() == cancelFind) {
				findWindow.dispose();
			}
			
			//OK pressed on the displayed student window
			else if(e.getSource() == okDisplayFound) {
				displayFoundWindow.dispose();
			}
			
			//OK pressed on the student not found window
			else if(e.getSource() == okDisplayNotFound) {
				displayNotFoundWindow.dispose();
			}
			
			//insert button pressed on insert popup window
			else if(e.getSource() == insertBut) {
				//insert
				sn = inputStudentNumber.getText();
				fac = inputFaculty.getText();
				maj = inputMajor.getText();
				yr = inputYear.getText();
				if(sn.length() != 5 || fac.length() != 2 || maj.length() != 4 || yr.length() != 1)
					System.err.println("Error: one or more of the input lines where incorrect.");
				else
				{
					fac.toUpperCase();
					maj.toUpperCase();
					bt.insert(sn, fac, maj, yr);
				}

				insertWindow.dispose();
			}
			
			//return to main menu button pressed on insert popup window
			else if(e.getSource() == retToMain) {
				insertWindow.dispose();
			}
		}

		/**
		 * creates the popup window when the user presses the insert button
		 */
		private void insertWindow() {
			insertWindow = new JFrame();
			//frame formating
			insertWindow.setBounds(325, 225, 450, 160);
			insertWindow.setVisible(true);
			
			//paneling
			//north
			JPanel upperPanel = new JPanel();
			JLabel label1 = new JLabel("Insert a New Node");
			upperPanel.add(label1);
			
			//center
			JPanel centerPanel = new JPanel();
			JLabel label2 = new JLabel("Enter the student's id number:");
			inputStudentNumber = new JTextField(5);
			JLabel label3 = new JLabel("Enter faculty:");
			inputFaculty = new JTextField(2);
			JLabel label4 = new JLabel("Enter student's major:");
			inputMajor = new JTextField(4);
			JLabel label5 = new JLabel("Enter year:");
			inputYear = new JTextField(1);
			centerPanel.add(label2);
			centerPanel.add(inputStudentNumber);
			centerPanel.add(label3);
			centerPanel.add(inputFaculty);
			centerPanel.add(label4);
			centerPanel.add(inputMajor);
			centerPanel.add(label5);
			centerPanel.add(inputYear);
			
			//south
			JPanel lowerPanel = new JPanel();
			insertBut = new JButton("Insert");
			lowerPanel.add(insertBut);
			insertBut.addActionListener(new ButtonListener());
			retToMain = new JButton("Return to Main Window");
			lowerPanel.add(retToMain);
			retToMain.addActionListener(new ButtonListener());
			
			//adding to frame
			insertWindow.add(upperPanel, BorderLayout.NORTH);
			insertWindow.add(centerPanel, BorderLayout.CENTER);
			insertWindow.add(lowerPanel, BorderLayout.SOUTH);
		}

		/**
		 * window that is displayed when the student doesn't exist
		 */
		private void displayNotFound() {
			displayNotFoundWindow = new JFrame();

			//frame formating
			displayNotFoundWindow.setTitle("Message");
			displayNotFoundWindow.setBounds(325, 225, 450, 100);
			displayNotFoundWindow.setVisible(true);
			
			//paneling
			//center
			JPanel centerPanel = new JPanel();
			JLabel label = new JLabel("The target record was not found.");
			inputStudentNumber = new JTextField(5);
			centerPanel.add(label);
			
			//south
			JPanel lowerPanel = new JPanel();
			lowerPanel.add(okDisplayNotFound);
			okDisplayNotFound.addActionListener(new ButtonListener());
			
			//adding to frame
			displayNotFoundWindow.add(centerPanel, BorderLayout.CENTER);
			displayNotFoundWindow.add(lowerPanel, BorderLayout.SOUTH);

		}

		/**
		 * window that is displayed when the studend is found
		 */
		private void displayFind() {
			displayFoundWindow = new JFrame();
			//frame formating
			displayFoundWindow.setTitle("Message");
			displayFoundWindow.setBounds(325, 225, 450, 100);
			displayFoundWindow.setVisible(true);
			
			//paneling
			//center
			JPanel centerPanel = new JPanel();
			JLabel label = new JLabel(sn);
			inputStudentNumber = new JTextField(5);
			centerPanel.add(label);
			
			//south
			JPanel lowerPanel = new JPanel();
			lowerPanel.add(okDisplayFound);
			okDisplayFound.addActionListener(new ButtonListener());
			
			//adding to frame
			displayFoundWindow.add(centerPanel, BorderLayout.CENTER);
			displayFoundWindow.add(lowerPanel, BorderLayout.SOUTH);

		}

		/**
		 * window that is displayed when the user presses the find button on the main window
		 */
		private void FindWindow() {
			findWindow = new JFrame();
			//frame formating
			findWindow.setTitle("Input");
			findWindow.setBounds(325, 225, 450, 100);
			findWindow.setVisible(true);
			
			//paneling
			JPanel centerPanel = new JPanel();
			JLabel label = new JLabel("Please enter the student's id number:");
			inputStudentNumber = new JTextField(5);
			centerPanel.add(label);
			centerPanel.add(inputStudentNumber);
			
			JPanel lowerPanel = new JPanel();
			okFind = new JButton("OK");
			lowerPanel.add(okFind);
			okFind.addActionListener(new ButtonListener());
			cancelFind = new JButton("Cancel");
			lowerPanel.add(cancelFind);
			cancelFind.addActionListener(new ButtonListener());
			
			//adding to frame
			findWindow.add(centerPanel, BorderLayout.CENTER);
			findWindow.add(lowerPanel, BorderLayout.SOUTH);
		}

		/**
		 * window that is displayed when the user presses the create tree from file button on the main window
		 */
		private void InputFileWindow() {
			fileWindow = new JFrame();
			//frame formating
			fileWindow.setTitle("Input");
			fileWindow.setBounds(325, 225, 450, 125);
			fileWindow.setVisible(true);
			
			//paneling
			//center
			JPanel centerPanel = new JPanel();
			JLabel label = new JLabel("Enter the file name:");
			inputFileName = new JTextField(35);
			centerPanel.add(label);
			centerPanel.add(inputFileName);
			
			//south
			JPanel lowerPanel = new JPanel();
			okFile = new JButton("OK");
			lowerPanel.add(okFile);
			okFile.addActionListener(new ButtonListener());
			cancelFile = new JButton("Cancel");
			lowerPanel.add(cancelFile);
			cancelFile.addActionListener(new ButtonListener());
			
			//adding to frame
			fileWindow.add(centerPanel, BorderLayout.CENTER);
			fileWindow.add(lowerPanel, BorderLayout.SOUTH);
		}
	}

	public static void main(String[] args) {
		new Student_recs();
	}

}
