package exercise2;

import javax.swing.JTextPane;

public class InputField extends JTextPane{

	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor
	 */
	public InputField(){
		super();
		this.setEditable(false);
	}
	/**
	 * Update the messages field
	 * @param t String to update with
	 */
	public void updateText(String t){
		this.setText(t);
	}
}
