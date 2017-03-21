package exercise2;

import javax.swing.JLabel;

public class SelectionLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	/** Pointer to the selection array */
	private int [] selection;
	/**
	 * Default constructor
	 * @param arr selection array
	 */
	public SelectionLabel(int [] arr){
		super("(" + arr[0] + ", " + arr[1] + ")");
		selection = arr;
	}
	/**
	 * Update the text displayed
	 */
	public void updateText(){
		setText("(" + selection[0] + ", " + selection[1] + ")");
	}
}
