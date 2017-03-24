package exercise3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class SearchTask extends Task{
	
	private String search;
	private DefaultListModel<Client> res;
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
	public void perform(Connection connect, DefaultListModel<Client> list, Client client, clientManagement gui) {
		// TODO MAKE IT WORK
		res = list;
		try {
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("select * from clients");
			if (search == null || search.equals("")){
				while (result.next()){
					this.addResult(result);
				}
			}
			else {
				if (searchType == Search.SEARCH_ID || searchType == Search.SEARCH_NAME){
					while (result.next()){
						if (result.getString(searchType).equalsIgnoreCase(search)){
							this.addResult(result);
						}
					}
				}
				else if (searchType == Search.SEARCH_TYPE){
					if (search.toUpperCase().charAt(0) == 'R'){
						while (result.next()){
							if (result.getString(searchType).charAt(0) == 'R'){
								this.addResult(result);
							}
						}
					}
					else if (search.toUpperCase().charAt(0) == 'C'){
						while (result.next()){
							if (result.getString(searchType).charAt(0) == 'C'){
								this.addResult(result);
							}
						}
					}
				}
			}
			
		} catch (SQLException e) {
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		} catch (NullPointerException e){
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		}
		isFinished = true;
	}
	
	public void addResult(ResultSet set) throws SQLException{
		res.addElement(new Client(set.getString(1), set.getString(2), 
				set.getString(3), set.getString(4), set.getString(5), 
				set.getString(6), set.getString(7)));
	}
}
