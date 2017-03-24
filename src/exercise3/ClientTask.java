package exercise3;

import java.sql.Statement;

public class ClientTask extends Task{
	
	
	public ClientTask(Client c, int t) {
		client = c;
		type = t;
	}

	@Override
	public void perform(Statement state) {
		// TODO Auto-generated method stub
		
	}
}
