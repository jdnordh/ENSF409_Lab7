package exercise3;

import java.sql.Statement;

public class ClientTask extends Task{
	
	private String res;
	public ClientTask(Client c, int t) {
		client = c;
		type = t;
		isFinished = false;
	}

	@Override
	public void perform(Statement state) {
		// TODO MAKE IT WORK AND STUFF
		isFinished = true;
		System.out.println("Client Task finished");
	}

	@Override
	public String getResult() {
		res = "";
		if (type == ADD){
			res = "The client "+ client.getFirstName() + " " + 
					client.getLastName() + " was added.\n";
		}
		else if (type == SAVE){
			res = "The client "+ client.getFirstName() + " " + 
					client.getLastName() + " was saved.\n";
		}
		else if (type == DELETE){
			res = "The client "+ client.getFirstName() + " " + 
					client.getLastName() + " was deleted.\n";
		}
		return res;
	}
}
