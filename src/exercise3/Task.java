package exercise3;

import javax.swing.DefaultListModel;

import com.mysql.jdbc.Connection;

public abstract class Task implements TaskTypes{
	/** The tasks query to deliver to the database */
	protected Client client;
	protected int type;
	protected boolean isFinished;
	
	public boolean isFinished(){
		return isFinished;
	}
	
	abstract public void perform(Connection connect, DefaultListModel<Client> list, Client client, clientManagement gui);
	/** get the type of task */
	public int type(){
		return type;
	}
	/** get the client */
	public Client client(){
		return client;
	}
}
