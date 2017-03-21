package exercise2;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class BoardPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected Board board;
	protected JButton [] [] buttons;
	protected GridBagConstraints gbc;
	protected JPanel grid;
	
	public BoardPanel(Board b){
		super(new GridBagLayout());
		board = b;
		
		grid = new JPanel(new GridBagLayout());
		buttons = new JButton[3][3];
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				buttons[i][j] = new JButton(" ");
				buttons[i][j].setOpaque(true);
				buttons[i][j].setBackground(Color.WHITE);
				gbc.gridx = j;
				gbc.gridy = i;
				grid.add(buttons[i][j], gbc);
			}
		}
		gbc.gridy = 1;
		add(grid, gbc);
	}
	
	public void update(String b){
		if (b.length() != 10) {
			System.out.println("Bad board");
			return;
		}
		int mark = 1;
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				buttons[i][j].setText(Character.toString(b.charAt(mark)));
				mark++;
			}
		}
	}
	
	public void addActionListener(ActionListener l){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				buttons[i][j].addActionListener(l);
			}
		}
	}
	
	public void updateColor(int ii, int jj){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				buttons[i][j].setBackground(Color.WHITE);
			}
		}
		buttons[ii][jj].setBackground(Color.CYAN);
	}
}
