package exercise3;

import javax.swing.*;
import java.awt.*;


/**
 * 
 * @author jeremy phillips and jordan nordh
 * @version 1.0
 *
 */


public class clientManagement extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField clientIDIn;
	private JTextField firstNameIn;
	private JTextField lastNameIn;
	private JTextField addressIn;
	private JTextField postalIn;
	private JTextField phoneNumIn;
	private JTextField searchIn;


	public clientManagement(){
		//basic formatting
		this.setTitle("Client Management System");
		this.setSize(700, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
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

	}

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

		JLabel label2 = new JLabel("Client ID:");
		clientIDIn = new JTextField(5);
		rightPanel.add(label2);
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

		JLabel label8 = new JLabel("Client Type:");
		clientIDIn = new JTextField(1);
		rightPanel.add(label8);
		rightPanel.add(clientIDIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,10)));

		JLabel label9 = new JLabel("Client ID:");
		clientIDIn = new JTextField(1);
		rightPanel.add(label9);
		rightPanel.add(clientIDIn);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0,20)));

		
		return rightPanel;
	}

	
	private JPanel makeLeftLower() {
		JPanel leftLower = new JPanel();
		BoxLayout b = new BoxLayout(leftLower, BoxLayout.Y_AXIS);
		leftLower.setLayout(b);
		
		leftLower.add(Box.createRigidArea(new Dimension(0,5)));
		
		JLabel label = new JLabel("Search Restults");
		leftLower.add(label);
		
		leftLower.add(Box.createRigidArea(new Dimension(0,10)));
		
		JScrollPane textAreaScrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea();
		textAreaScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.setFont(new Font("Courier New", Font.BOLD, 12));
		textArea.setEditable(false);
		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setPreferredSize(new Dimension(450, 300));
		leftLower.add(textAreaScrollPane);

		leftLower.add(Box.createRigidArea(new Dimension(0,10)));

		leftLower.setBorder(BorderFactory.createLineBorder(Color.black));
		return leftLower;
	}

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
		
	    JRadioButton clientId = new JRadioButton("Client ID");
	    leftUpper.add(clientId);
	    
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));

	    JRadioButton lastname = new JRadioButton("Last Name");
	    leftUpper.add(lastname);
	    
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
	    
	    JRadioButton clientType = new JRadioButton("Client Type");
	    leftUpper.add(clientType);
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(clientId);
	    group.add(lastname);
	    group.add(clientType);

		leftUpper.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JLabel label3 = new JLabel("Enter the search parameter below: ");
		leftUpper.add(label3);

		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		searchIn = new JTextField(20);
		leftUpper.add(searchIn);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JButton search = new JButton("Search");
		leftUpper.add(search);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JButton clear = new JButton("Clear Search");
		leftUpper.add(clear);
		
		leftUpper.add(Box.createRigidArea(new Dimension(0, 15)));
		
		leftUpper.setBorder(BorderFactory.createLineBorder(Color.black));
		return leftUpper;
	}

	public static void main(String[] args) {
		new clientManagement();
	}

}
