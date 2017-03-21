package exercise2;

import java.io.BufferedReader;
import java.io.IOException;

public class GUIThread extends Thread{
	private BufferedReader in;
	private InputField messages;
	private GFrame frame;
	public GUIThread(String name, GFrame g, BufferedReader i, InputField o){
		super(name);
		in = i;
		messages = o;
		frame = g;
	}
	
	public void run(){
		try{
			while (true){
				String temp = in.readLine();
				if (temp.charAt(0) == 'B'){
					//System.out.println("Updating board...");
					frame.boardP.update(temp);
				}
				else if (temp.charAt(0) == 'M'){
					char [] input = new char[temp.length()-1];
					for (int i = 0;i < input.length; i++){
						input[i] = temp.charAt(i+1);
					}
					messages.updateText(new String(input));
				}
				else if (temp.charAt(0) == 'P'){
					frame.pName.setEditable(false);
				}
				else if (temp.charAt(0) == 'F'){
					char [] input = new char[temp.length()-1];
					for (int i = 0;i < input.length; i++){
						input[i] = temp.charAt(i+1);
					}
					String s = new String(input);
					messages.updateText(s + " wins!");
					break;
				}
				else if (temp.charAt(0) == 'T'){
					messages.updateText("It's a tie!");
					break;
				}
				else if (temp.equals("QUIT")){
					messages.updateText("Exiting...");
					System.exit(0);
				}
				sleep(1);
			}
		} catch (IOException e) {
			System.out.println("Error (I/O): " + e.getLocalizedMessage());
			messages.updateText("Error: " + e.getLocalizedMessage());
		} catch (InterruptedException e){
			System.out.println("Error (Interupt): " + e.getLocalizedMessage());
			messages.updateText("Error: " + e.getLocalizedMessage());
		}
	}
}
