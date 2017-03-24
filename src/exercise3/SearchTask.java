package exercise3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SearchTask extends Task{
	
	private String search;
	private JList res;
	private int searchType;
	/** Create a search task */
	public SearchTask(String s, int t){
		searchType = t;
		type = SEARCH;
		search = s;
		isFinished = false;
	}

	/**
	 * Perform this task 
	 */
	@Override
	public void perform(Statement state) {
		// TODO MAKE IT WORK
		try {
			ResultSet result = state.executeQuery("select * from clients");
			if (search == null || search.equals("")){
				
				res = "Blank search, so displaying all clients...\n\n";
				res += "ID,First Name,Last Name,Address,Postal Code,Phone Number,Type\n\n";
				while (result.next()){
					this.addResult(result);
				}
			}
			else {
				boolean none = true;
				res = "ID,First Name,Last Name,Address,Postal Code,Phone Number,Type\n\n";
				if (searchType == Search.SEARCH_ID || searchType == Search.SEARCH_NAME){
					while (result.next()){
						if (result.getString(searchType).equalsIgnoreCase(search)){
							this.addResult(result);
							none = false;
						}
					}
				}
				else if (searchType == Search.SEARCH_TYPE){
					res = "Searching for clients by type...\n";
					res += "ID,First Name,Last Name,Address,Postal Code,Phone Number,Type\n\n";
					if (search.toUpperCase().charAt(0) == 'R'){
						while (result.next()){
							if (result.getString(searchType).charAt(0) == 'R'){
								this.addResult(result);
								none = false;
							}
						}
					}
					else if (search.toUpperCase().charAt(0) == 'C'){
						while (result.next()){
							if (result.getString(searchType).charAt(0) == 'C'){
								this.addResult(result);
								none = false;
							}
						}
					}
					else res = "Invalid client type\n";
				}
				if (none) res += "No results";
			}
			
		} catch (SQLException e) {
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		}
		isFinished = true;
	}
	/** get the result of the task */
	@Override
	public String getResult(){
		return res;
	}
	
	public void addResult(ResultSet set) throws SQLException{
		for (int i =1; i< 8; i++){
			res += set.getString(i);
			if (i < 7) res += ",";
		}
		res +="\n";
	}
}
