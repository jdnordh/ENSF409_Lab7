package exercise2;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class BoardPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private JButton [] [] buttons;
	private GridBagConstraints gbc;
	
	public BoardPanel(Board b){
		super(new GridBagLayout());
		buttons = new JButton[3][3];
		gbc = new GridBagConstraints();
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				buttons[i][j] = new JButton(" ");
				gbc.gridx = j;
				gbc.gridy = i;
				add(buttons[i][j], gbc);
			}
		}
		board = b;
		
	}
	
	
}
