package exercise2;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;

public class GFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField boardTitle;
	private JTextField playerName;
	private JTextField item3;
	private JPasswordField pass;
	private BoardPanel boardP;
	private JLabel title;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public GFrame(String name, Game g){
		super(name);
		this.setLayout(new GridLayout(2, 2));
		
		boardP = new BoardPanel(g.getBoard());
		boardTitle = new JTextField("Board");
		boardTitle.setEditable(false);
		playerName = new JTextField("Name");
		playerName.setToolTipText("Inset the name of your player");
		title = new JLabel("Board");
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(title);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(boardP);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(playerName);
		
		
		
		GHandler hand = new GHandler();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
	}
	
	private class GHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			String s = "";
			
			if (event.getSource() == pass){
				s = String.format("Field 1: %s",  event.getActionCommand());
			}
		}
		
	}
	
	
	public static void main(String [] args){
		Game game = new Game();
		GFrame g = new GFrame("Player 1", game);
		g.setVisible(true);
	}
}
