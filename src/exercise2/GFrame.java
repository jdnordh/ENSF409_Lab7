package exercise2;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GFrame extends JFrame{
	private BufferedReader in;
	private PrintWriter out;
	
	private static final long serialVersionUID = 1L;
	
	protected Board board;
	
	protected JTextField pName;
	private JButton lockin;
	protected BoardPanel boardP;
	private JPanel bottomLeft;
	private JPanel topRight;
	protected InputField messages;
	private JPanel bottomLeftExterior;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private int [] selection;
	private SelectionLabel selText;
	
	
	/**
	 * Constructs a new frame
	 * @param name name of the client 
	 * @param i Input to the client
	 */
	public GFrame(String name, BufferedReader i, PrintWriter o){
		super(name);
		in = i;
		out = o;
		this.setLayout(new GridBagLayout());
		selection = new int[2];
		board = new Board();
		boardP = new BoardPanel(board);
		
		pName = new JTextField(20);
		pName.setEditable(true);
		
		lockin = new JButton("Play on selection");
		lockin.setOpaque(true);
		lockin.setBackground(Color.CYAN);
		
		topRight = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		topRight.add(new JLabel("Message window"), gbc);
		gbc.gridy++;
		topRight.add(new JSeparator(), gbc);
		gbc.gridy++;
		messages = new InputField();
		topRight.add(messages, gbc);
		
		bottomLeft = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 0;
		bottomLeft.add(new JLabel("Player: "), gbc);
		gbc.gridy++;
		bottomLeft.add(new JLabel("Current selection: "), gbc);
		selText = new SelectionLabel(selection);
		gbc.gridy++;
		bottomLeft.add(new JLabel("Player name: "), gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		gbc.gridy = 0;
		JLabel temp = new JLabel();
		if (name.equals("Player 1")){
			temp.setText("X");
		} else {
			temp.setText("O");
		}
		bottomLeft.add(temp, gbc);
		gbc.gridy++;
		bottomLeft.add(selText, gbc);
		
		gbc.gridy++;
		pName.setToolTipText("Insert the name of your player");
		pName.setMinimumSize(null);
		bottomLeft.add(pName, gbc);
		
		bottomLeftExterior = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		bottomLeftExterior.add(new JPanel().add(lockin), gbc);
		gbc.gridy++;
		bottomLeftExterior.add(new JLabel(" "), gbc);
		gbc.gridy++;
		bottomLeftExterior.add(bottomLeft, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(boardP, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(topRight, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(bottomLeftExterior, gbc);
		GHandler hand = new GHandler();
		hand.setLabel(selText);
		boardP.addActionListener(hand);
		lockin.addActionListener(hand);
		pName.addActionListener(hand);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setResizable(false);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Action event listener
	 * Outputs from the gui to the server when input is given
	 * @author Jordan
	 *
	 */
	private class GHandler implements ActionListener{
		private SelectionLabel label;
		
		public void setLabel(SelectionLabel j){
			label = j;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == boardP.buttons[0][0]){
				selection[0] = 0;
				selection[1] = 0;
				boardP.updateColor(0, 0);
			}
			else if (event.getSource() == boardP.buttons[0][1]){
				selection[0] = 0;
				selection[1] = 1;
				boardP.updateColor(0, 1);
			}
			else if (event.getSource() == boardP.buttons[0][2]){
				selection[0] = 0;
				selection[1] = 2;
				boardP.updateColor(0, 2);
			}
			else if (event.getSource() == boardP.buttons[1][0]){
				selection[0] = 1;
				selection[1] = 0;
				boardP.updateColor(1, 0);
			}
			else if (event.getSource() == boardP.buttons[1][1]){
				selection[0] = 1;
				selection[1] = 1;
				boardP.updateColor(1, 1);
			}
			else if (event.getSource() == boardP.buttons[1][2]){
				selection[0] = 1;
				selection[1] = 2;
				boardP.updateColor(1, 2);
			}
			else if (event.getSource() == boardP.buttons[2][0]){
				selection[0] = 2;
				selection[1] = 0;
				boardP.updateColor(2, 0);
			}
			else if (event.getSource() == boardP.buttons[2][1]){
				selection[0] = 2;
				selection[1] = 1;
				boardP.updateColor(2, 1);
			}
			else if (event.getSource() == boardP.buttons[2][2]){
				selection[0] = 2;
				selection[1] = 2;
				boardP.updateColor(2, 2);
			}
			else if (event.getSource() == lockin){
				out.println(selection[0] + "P" + selection[1]);
				out.flush();
			}
			else if (event.getSource() == pName){
				out.println("N" + pName.getText());
				out.flush();
			}
			label.updateText();
		}
		
	}
	
	public void start(){
		try{
			while (true){
				String temp = in.readLine();
				if (temp.charAt(0) == 'B'){
					boardP.update(temp);
				}
				else if (temp.charAt(0) == 'M'){
					char [] input = new char[temp.length()-1];
					for (int i = 0;i < input.length; i++){
						input[i] = temp.charAt(i+1);
					}
					messages.updateText(new String(input));
				}
				else if (temp.charAt(0) == 'P'){
					pName.setEditable(false);
				}
				else if (temp.equals("QUIT")){
					messages.updateText("Exiting...");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getLocalizedMessage());
			messages.updateText("Error: " + e.getLocalizedMessage());
		}
	}
	
	public static void main(String [] args){
		GFrame g = new GFrame("Player 1", new BufferedReader(new InputStreamReader(System.in)), 
				new PrintWriter(System.out));
		g.setVisible(true);
	}
	
}
