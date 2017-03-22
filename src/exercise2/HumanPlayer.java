package exercise2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HumanPlayer extends Player{
	/**Constructs a human player
	 * @param s Name
	 * @param x	Mark
	 */
	public HumanPlayer(String s, char x){
		super(s,x);
	}
	/**
	 * Player is able to make a move. Takes input from keyboard and check whether it is a proper input or not
	 * @throws IOException Catch errors
	 */
	public void makeMove(BufferedReader in, PrintWriter out) throws IOException{
		int row=0, col=0;
		do {
			out.println("M" + name + ", where should your next " + mark + " be placed? ");
			out.flush();
			String input= in.readLine();
			if (input != null){
				char [] temp = input.toCharArray();
				if (temp[0] == 'P' && temp [2] == 'P'){
					row = temp[1] - 48;
					col = temp[3] - 48;
				}
			}
			if (board.getMark(row,col) != ' ') {
				out.println("MSpace is already in use");
				out.flush();
			}
		} while (board.getMark(row,col) != ' ');
		board.addMark(row, col, mark);
	}
}
