package exercise3;

import java.sql.Statement;

public abstract class Task implements TaskTypes{
	/** The tasks query to deliver to the database */
	protected Client client;
	protected int type;
	
	abstract public void perform(Statement state);
}
