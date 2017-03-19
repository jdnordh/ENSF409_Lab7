package exercise2;

import javax.swing.*;

import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.*;

public class GFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField item1;
	private JTextField item2;
	private JTextField item3;
	private JPasswordField pass;
	private BoardPanel boardP;
	
	public GFrame(String name, Game g){
		super(name);
		this.setLayout(new FlowLayout());
		boardP = new BoardPanel(g.getBoard());
		add(boardP);
		
		
		GHandler hand = new GHandler();
		pass.addActionListener(hand);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
	}
	
	private class GHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			String s = "";
			
			if (event.getSource() == item1){
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
