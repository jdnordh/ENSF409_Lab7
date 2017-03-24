package exercise3;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 
 * @author jeremy phillips and jordan nordh
 * @version 1.0
 *
 */


public class clientManagement extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/** currently selected client */
	private Client client;
	
	/** connection to database */
	private Connection connect;
	private DataThread dThread;
	private Queue<Task> tasks;
	
	/** Text fields */
	protected JTextField clientIDIn;
	protected JTextField firstNameIn;
	protected JTextField lastNameIn;
	protected JTextField addressIn;
	protected JTextField postalIn;
	protected JTextField phoneNumIn;
	protected JTextField searchIn;
	
	// This is unused at the moment
	protected JTextArea textArea;
	
	/** This is the list of clients */
	protected JList<Client> display;
	private DefaultListModel<Client> list;
	
	/** Combo box */
	protected JComboBox<String> comboBox;
	protected char comboBoxSelection;
	
	/** Action listener */
	private GHandler listen;

	/** Search Buttons */
	private JButton search;
	private JButton clearSearch;
	
	/** Client buttons */
	private JButton save;
	private JButton addNew;
	private JButton delete;
	private JButton clearData;
	
	/** Radio buttons */
	private JRadioButton clientId;
	private JRadioButton lastname;
	private JRadioButton clientType;
	private int radioSelector;
	
	public clientManagement(){
		//basic formatting
		this.setTitle("Client Management System");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set an action listener, then apply it to all the buttons
		listen = new GHandler();
		
		//grid bag formatting
		GridBagLayout layo = new GridBagLayout();
		this.setLayout(layo);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.5;
		c.weighty = 1.0;
		c.gridheight = 1;
		c.gridwidth = 1;



		//make leftUpper Panel
		c.gridx = 0;
		c.gridy = 0;
		JPanel leftUpper = makeLeftUpper();
		layo.setConstraints(leftUpper, c);
		this.add(leftUpper, c);
		

		
		//make leftLower Panel
		c.gridx = 0;
		c.gridy = 1;		
		JPanel leftLower = makeLeftLower();
		layo.setConstraints(leftLower, c);
		this.add(leftLower, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.weighty = 1.0;
		JPanel rightPane = makeRightPanel();
		layo.setConstraints(rightPane, c);
		this.add(rightPane, c);

		
		this.pack();
		//this.setSize(1000, 700);
		this.setVisible(true);
	}
	
	/**
	 * Handles actions such as buttons and text fields
	 * @author Jordan Nordh and Jeremy Philips
	 *
	 */
	private class GHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == search || e.getSource() == searchIn){
				list.clear();
				Task temp = new SearchTask(searchIn.getText(), radioSelector);
				tasks.enQueue(temp);
			}
			else if (e.getSource() == clearSearch){
				searchIn.setText("");
				textArea.setText("");
			}
			else if (e.getSource() == clientId){
				radioSelector = Search.SEARCH_ID;
			}
			else if (e.getSource() == lastname){
				radioSelector = Search.SEARCH_NAME;
			}
			else if (e.getSource() == clientType){
				radioSelector = Search.SEARCH_TYPE;
			}
			else if (e.getSource() == save){
				try {
					Client temp = new Client(Integer.parseInt(clientIDIn.getText()), 
							firstNameIn.getText(), lastNameIn.getText(), 
							addressIn.getText(), postalIn.getText(), 
							phoneNumIn.getText(), comboBoxSelection);
					Task todo = new ClientTask(temp, TaskTypes.SAVE);
					tasks.enQueue(todo);
				} catch (Exception x){
					JOptionPane.showMessageDialog(null,  "Error: " + x.getMessage());
				}
			}
			else if (e.getSource() == addNew){
				try {
					Client temp = new Client(Integer.parseInt(clientIDIn.getText()), 
							firstNameIn.getText(), lastNameIn.getText(), 
							addressIn.getText(), postalIn.getText(), 
							phoneNumIn.getText(), comboBoxSelection);
					Task todo = new ClientTask(temp, TaskTypes.ADD);
					tasks.enQueue(todo);
				} catch (Exception x){
					JOptionPane.showMessageDialog(null,  "Error: " + x.getMessage());
				}
			}
			else if (e.getSource() == delete){
				try {
					Client temp = new Client(Integer.parseInt(clientIDIn.getText()), 
							firstNameIn.getText(), lastNameIn.getText(), 
							addressIn.getText(), postalIn.getText(), 
							phoneNumIn.getText(), comboBoxSelection);
					Task todo = new ClientTask(temp, TaskTypes.DELETE);
					tasks.enQueue(todo);
				} catch (Exception x){
					JOptionPane.showMessageDialog(null,  "Error: " + x.getMessage());
				}
			}
			else if (e.getSource() == clearData){
				clientIDIn.setText("");
				firstNameIn.setText("");
				lastNameIn.setText("");
				addressIn.setText("");
				postalIn.setText("");
				phoneNumIn.setText("");
				comboBox.setSelectedItem("Residential");
			}
			else if (e.getSource() == comboBox){
				if (comboBox.getSelectedItem() == "Commercial"){
					comboBoxSelection = 'C';
				}
				else {
					comboBoxSelection = 'R';
				}
			}
		}
		
	}
	/**
	 * Makes the right side panel
	 * @return JPanel for the right side
	 */
	private JPanel makeRightPanel(){
		JPanel rightPanel = new JPanel();
		//rightPanel.setPreferredSize(new Dimension(300, 600));
		BoxLayout b = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		rightPanel.setLayout(b);

		JLabel label1 = new JLabel("Client Information");
		Font boldFont = new Font("Client Information", Font.BOLD, 15);
		label1.setFont(boldFont);
		rightPanel.add(label1);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,20)));

		/*JLabel label2 = new JLabel("Client ID:");
		clientIDIn = new JTextField(5);
		rightPanel.add(label2);
		rightPanel.add(clientIDIn);*/
		JLabel label9 = new JLabel("Client ID:");
		clientIDIn = new JTextField(1);
		rightPanel.add(label9);
		rightPanel.add(clientIDIn);		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		JLabel label3 = new JLabel("First Name:");
		firstNameIn = new JTextField(20);
		rightPanel.add(label3);
		rightPanel.add(firstNameIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		JLabel label4 = new JLabel("Last Name:");
		lastNameIn = new JTextField(20);
		rightPanel.add(label4);
		rightPanel.add(lastNameIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		JLabel label5 = new JLabel("Address:");
		addressIn = new JTextField(20);
		rightPanel.add(label5);
		rightPanel.add(addressIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		JLabel label6 = new JLabel("Postal Code:");
		postalIn = new JTextField(7);
		rightPanel.add(label6);
		rightPanel.add(postalIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		JLabel label7 = new JLabel("Phone Number:");
		phoneNumIn = new JTextField(12);
		rightPanel.add(label7);
		rightPanel.add(phoneNumIn);

		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		/*JLabel label8 = new JLabel("Client Type:");
		clientIDIn = new JTextField(1);
		rightPanel.add(label8);
		rightPanel.add(clientIDIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));*/

		
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
		
		// add combo box for client type
		rightPanel.add(new JLabel("Client type:"));
		String [] temp = {"Residential", "Commercial"};
		comboBox = new JComboBox<String>(temp);
		comboBox.setEditable(false);
		comboBox.addActionListener(listen);
		rightPanel.add(comboBox);
		
		// add buttons to bottom panel
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		save = new JButton("Save");
		save.addActionListener(listen);
		buttonPanel.add(save, gbc);
		
		addNew = new JButton("Add new");
		addNew.addActionListener(listen);
		gbc.gridx++;
		buttonPanel.add(addNew, gbc);
		
		delete = new JButton("Delete");
		delete.addActionListener(listen);
		gbc.gridx++;
		buttonPanel.add(delete, gbc);
		
		clearData = new JButton("Clear");
		clearData.addActionListener(listen);
		gbc.gridx++;
		buttonPanel.add(clearData, gbc);
		
		rightPanel.add(buttonPanel);
		
		return rightPanel;
	}

	/**
	 * @return Returns a JPanel for the lower left side
	 */
	private JPanel makeLeftLower() {
		JPanel leftLower = new JPanel();
		BoxLayout b = new BoxLayout(leftLower, BoxLayout.Y_AXIS);
		leftLower.setLayout(b);
		
		leftLower.add(Box.createRigidArea(new Dimension(0,5)));
		
		JLabel label = new JLabel("Search Results");
		leftLower.add(label);
		
		leftLower.add(Box.createRigidArea(new Dimension(0,10)));
		
		list = new DefaultListModel<Client>();
		display = new JList<Client>(list);
		
		display.setFont(new Font("Courier New", Font.BOLD, 12));
		display.addListSelectionListener(new ListAction());
		display.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		display.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		display.setVisibleRowCount(-1);
		
		JScrollPane textAreaScrollPane = new JScrollPane();
		textArea = new JTextArea();
		textAreaScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.setFont(new Font("Courier New", Font.BOLD, 12));
		textArea.setEditable(false);
		textAreaScrollPane = new JScrollPane(display);
		textAreaScrollPane.setPreferredSize(new Dimension(450, 300));
		leftLower.add(textAreaScrollPane);

		leftLower.add(Box.createRigidArea(new Dimension(0,10)));

		leftLower.setBorder(BorderFactory.createLineBorder(Color.black));
		return leftLower;
	}
	
	private class ListAction implements ListSelectionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void valueChanged(ListSelectionEvent e) {
			client = ((JList<Client>)e.getSource()).getSelectedValue();
			client.displayInfo(clientIDIn, firstNameIn, lastNameIn, addressIn, postalIn, phoneNumIn, comboBox);
			dThread.setClient(client);
		}

		
	}
	
	/**
	 * Make a JPanel
	 * @return Upper left panel for the gui
	 */
	private JPanel makeLeftUpper() {
		JPanel leftUpper = new JPanel();
		//leftUpper.setPreferredSize(new Dimension(450, 300));
		BoxLayout b = new BoxLayout(leftUpper, BoxLayout.Y_AXIS);
		leftUpper.setLayout(b);
		
		JLabel label1 = new JLabel("Search Clients");
		Font boldFont = new Font("Search Clients", Font.BOLD, 15);
		label1.setFont(boldFont);
		leftUpper.add(label1);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0,20)));
		
		JLabel label2 = new JLabel("Select Type of Search to be performed: ");
		leftUpper.add(label2);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
	    clientId = new JRadioButton("Client ID");
	    clientId.addActionListener(listen);
	    leftUpper.add(clientId);
	    
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));

	    lastname = new JRadioButton("Last Name");
	    lastname.addActionListener(listen);
	    leftUpper.add(lastname);
	    
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
	    
	    clientType = new JRadioButton("Client Type");
	    clientType.addActionListener(listen);
	    leftUpper.add(clientType);
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(clientId);
	    group.add(lastname);
	    group.add(clientType);
	    clientId.setSelected(true);
		leftUpper.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel label3 = new JLabel("Enter the search parameter below: ");
		leftUpper.add(label3);

		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		searchIn = new JTextField(20);
		searchIn.addActionListener(listen);
		leftUpper.add(searchIn);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		search = new JButton("Search");
		search.addActionListener(listen);
		leftUpper.add(search);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		clearSearch = new JButton("Clear Search");
		clearSearch.addActionListener(listen);
		leftUpper.add(clearSearch);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 15)));
		
		leftUpper.setBorder(BorderFactory.createLineBorder(Color.black));
		return leftUpper;
	}
	
	public void connectToData(){
		try {
			connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ENSF409_Lab7", "labuser", "This is a lab user!");
			tasks = new Queue<Task>();
			dThread = new DataThread(connect, tasks, list, this);
			dThread.start();
		} catch (SQLException e) {
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		clientManagement client = new clientManagement();
		client.connectToData();
	}

}
