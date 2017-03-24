package exercise3;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.mysql.jdbc.Connection;

public class DataThread extends Thread implements TaskTypes{
	private Connection connect;
	private Queue<Task> tasks;
	protected JTextArea textArea;
	private DefaultListModel<Client> list;
	private boolean running;
	private Client client;
	private clientManagement gui;
	
	/** constructor */
	public DataThread(Connection c, Queue<Task> t, DefaultListModel<Client> d, clientManagement mang){
		super("DataThread");
		tasks = t;
		connect = c;
		list = d;
		gui = mang;
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
					temp.perform(connect, list, client, gui);
					if (temp.isFinished()){
						if (temp.type() == SEARCH){
							
						}
						else if (temp.type() == ADD){
							
						}
						else if (temp.type() == SAVE){
							
						}
						else if (temp.type() == DELETE){
							
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
