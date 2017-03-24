package exercise3;

import java.sql.Statement;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DataThread extends Thread implements TaskTypes{
	private Statement state;
	private Queue<Task> tasks;
	protected JTextArea textArea;
	protected JList display;
	private boolean running;
	private Client client;
	
	/** constructor */
	public DataThread(Statement s, Queue<Task> t, JList d){
		super("DataThread");
		tasks = t;
		state = s;
		display = d;
	}
	
	public void setClient(Client c){
		client = c;
	}
	
	/** add a task to the queue */
	public void addTask(Task t){
		tasks.enQueue(t);
	}
	
	public void run(){
		running = true;
		while (running){
			try{
				if (!tasks.isEmpty()){
					Task temp = tasks.deQueue();
					temp.perform(state);
					if (temp.isFinished()){
						if (temp.type() == SEARCH){
							textArea.setText(temp.getResult());
						}
						else if (temp.type() == ADD){
							textArea.setText("The client "+ temp.client().getFirstName() + " " + 
						temp.client().getLastName() + " was added.\n");
						}
						else if (temp.type() == SAVE){
							textArea.setText("The client "+ temp.client().getFirstName() + " " + 
									temp.client().getLastName() + " was saved.\n");
						}
						else if (temp.type() == DELETE){
							textArea.setText("The client "+ temp.client().getFirstName() + " " + 
									temp.client().getLastName() + " was deleted.\n");
						}
					}
				}
				sleep(1);
			} catch (InterruptedException e){
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
			}
		}
	}
}
