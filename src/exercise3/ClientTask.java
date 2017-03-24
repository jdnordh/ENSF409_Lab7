package exercise3;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ClientTask extends Task{
	
	public ClientTask(Client c, int t) {
		client = c;
		type = t;
		isFinished = false;
	}

	@Override
	public void perform(Connection connect, DefaultListModel<Client> list, Client client, clientManagement gui) {
		// TODO MAKE IT WORK AND STUFF
		
		try {
			if (type == ADD){
				String query = "INSERT clients VALUES(?,?,?,?,?,?,?)";
				PreparedStatement state = connect.clientPrepareStatement(query);
				state.setString(1, gui.clientIDIn.getText());
				state.setString(2, gui.firstNameIn.getText());
				state.setString(3, gui.lastNameIn.getText());
				state.setString(4, gui.addressIn.getText());
				state.setString(5, gui.postalIn.getText());
				state.setString(6, gui.phoneNumIn.getText());
				state.setString(7, Character.toString(gui.comboBoxSelection));
				state.executeUpdate();
				System.out.println("Added new client");
			}
			else if (type == SAVE){
				String query = "";
				if (!gui.clientIDIn.getText().equals(client.getID())){
					query = "UPDATE clients SET id = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.clientIDIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (!gui.firstNameIn.getText().equals(client.getFirstName())){
					query = "UPDATE clients SET firstname = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.firstNameIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (!gui.lastNameIn.getText().equals(client.getLastName())){
					query = "UPDATE clients SET lastname = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.lastNameIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (!gui.addressIn.getText().equals(client.getAddress())){
					query = "UPDATE clients SET address = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.addressIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (!gui.postalIn.getText().equals(client.getPostalCode())){
					query = "UPDATE clients SET postalCode = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.postalIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (!gui.phoneNumIn.getText().equals(client.getPhoneNumber())){
					query = "UPDATE clients SET phoneNumber = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, gui.phoneNumIn.getText());
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}
				else if (gui.comboBoxSelection != client.getType()){
					query = "UPDATE clients SET clientType = ? WHERE id = ?";
					PreparedStatement state = connect.clientPrepareStatement(query);
					state.setString(1, Character.toString(gui.comboBoxSelection));
					state.setString(2, Integer.toString(client.getID()));
					state.executeUpdate();
				}

				 
				/* query = "UPDATE clients VALUES(?,?,?,?,?,?,?)";
				 PreparedStatement state = connect.clientPrepareStatement(query);
				 state.setString(1, gui.clientIDIn.getText());
				 state.setString(2, gui.firstNameIn.getText());
				 state.setString(3, gui.lastNameIn.getText());
				 state.setString(4, gui.addressIn.getText());
				 state.setString(5, gui.postalIn.getText());
				 state.setString(6, gui.phoneNumIn.getText());
				 state.setString(7, Character.toString(gui.comboBoxSelection));
				 state.executeUpdate();*/
				 System.out.println("Updated client");
			}
			else if (type == DELETE){
				Statement s = (Statement) connect.createStatement();
				String sql = "DELETE FROM clients WHERE id = '" + Integer.toString(client.getID()) + "'";
				int delete = s.executeUpdate(sql);
				if(delete == 1){
					System.out.println("Row is deleted.");
				}
				else{
					//Dispatch a window with an error
					JOptionPane.showMessageDialog(null,  "Error: Entry does not exist");
				}
			}
			//list.clear();
		} catch (SQLException e) {
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
		} catch (NullPointerException e){
			//Dispatch a window with an error
			JOptionPane.showMessageDialog(null,  "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		isFinished = true;
	}
}
