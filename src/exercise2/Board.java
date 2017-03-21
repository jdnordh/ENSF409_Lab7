package exercise2;

import java.io.PrintWriter;

//STUDENTS SHOULD ADD CLASS COMMENT, METHOD COMMENTS, FIELD COMMENTS 

/**
 * To be used as a medium for the game to be played on
 * 
 * @author Jordan Nordh
 *
 */
public class Board implements Constants {
	/**
	 * Array of chars for putting marks into to play the game
	 */
	private char theBoard[][];
	/**
	 * Counts how many marks are currently present
	 */
	private int markCount;
	
	/** contains char of last played mark*/
	private char lastPlayed;
	
	/** returns last played char*/
	public char getLast(){
		return lastPlayed;
	}
	
	/**
	 * Constructs blank board
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	
	/**
	 * Get mark at certain row and col even if row or col are >2
	 * @param row which row to select
	 * @param col which col to select
	 * @return the char at specified row and col
	 */
	public char getMark(int row, int col) {
		return theBoard[row%3][col%3];
	}
	
	
	public Boolean isMark(int row, int col){
		if (theBoard[row%3][col%3] != ' ') return true;
		else return false;
	}
	/**
	 * @return if the board is full returns true
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	
	public int getCount(){
		return markCount;
	}
	/**
	 * 
	 * @return if xPlayer wins, returns true
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return if oPlayer wins, returns true
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	
	/** Displays the board as an output to the console */
	public void display(PrintWriter out) {
		String output = "B";
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				output += theBoard[i][j];
			}
		}
		//System.out.println("String length is: " + output.length());
		String temp = new String(output);
		out.println(temp);
		this.display();
	}

	/** * Add a mark at a certain location
	 * @param row Which row to add to 
	 * @param col Which col to add to
	 * @param mark What mark to put at location */
	public void addMark(int row, int col, char mark) {
		int r = row%3, c= col%3;
		if (getMark(row,col) == ' '){
			theBoard[r][c] = mark;
			markCount++;
			if ((r==0 && c==1) || (r==1 && c==0) || (r==1 && c==2) || (r==2 && c==1)){
				lastPlayed = 'e';
			}
			else if ((r==0 && c==0) || (r==2 && c==0) || (r==0 && c==2) || (r==2 && c==2)){
				lastPlayed = 'c';
			}
			else if (r==1 && c==1){
				lastPlayed = 'm';
			}
		}
	}
	
	/** * Clears the board of all marks */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	
	/** * Checks if either oPlayer or xPlayer have won the game
	 * @param mark Mark of which player to check
	 * @return 1 if the specified mark is in a line of three anywhere on the board*/
	int checkWinner(char mark) {
		int row, col;
		int result = 0;
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	
	/** * Displays column headers */
	void displayColumnHeaders(PrintWriter out) {
		out.print("          ");
		out.flush();
		for (int j = 0; j < 3; j++){
			out.print("|col " + j);
			out.flush();
		}
		out.println();
		out.flush();
	}
	
	/** * Add hyphens to output console */
	void addHyphens(PrintWriter out) {
		out.print("          ");
		out.flush();
		for (int j = 0; j < 3; j++){
			out.print("+-----");
			out.flush();
		}
		out.println("+");
		out.flush();
	}
	
	/** * Add spaces to output console */
	void addSpaces(PrintWriter out) {
		out.print("          ");
		out.flush();
		for (int j = 0; j < 3; j++){
			out.print("|     ");
			out.flush();
		}
		out.println("|");
		out.flush();
	}
	
	/** Displays the board as an output to the console */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}
	/** * Displays column headers */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}
	
	/** * Add hyphens to output console */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	
	/** * Add spaces to output console */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
	
}
