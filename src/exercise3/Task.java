package exercise3;

import java.sql.Statement;

public abstract class Task implements TaskTypes{
	/** The tasks query to deliver to the database */
	protected Client client;
	protected int type;
	protected boolean isFinished;
	
	public boolean isFinished(){
		return isFinished;
	}
	
	abstract public void perform(Statement state);
	abstract public String getResult();
	/** get the type of task */
	public int type(){
		return type;
	}
	/** get the client */
	public Client client(){
		return client;
	}
}
