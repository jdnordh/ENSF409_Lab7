package exercise3;

import java.sql.Statement;
import javax.swing.JOptionPane;

public class DataThread extends Thread implements TaskTypes{
	private Statement state;
	private Queue<Task> tasks;
	
	public DataThread(Statement s, Queue<Task> t){
		super("DataThread");
		tasks = t;
		state = s;
	}
	
	public void addTask(Task t){
		tasks.enQueue(t);
	}
	
	public void run(){
		try{
			if (!tasks.isEmpty()){
				Task temp = tasks.deQueue();
				temp.perform(state);
			}
			
			sleep(1);
		} catch (InterruptedException e){
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		}
	}
}
