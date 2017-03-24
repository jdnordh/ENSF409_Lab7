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
				client = new Client(Integer.parseInt(gui.clientIDIn.getText()), 
						gui.firstNameIn.getText(), gui.lastNameIn.getText(), 
						gui.addressIn.getText(), gui.postalIn.getText(), 
						gui.phoneNumIn.getText(), gui.comboBoxSelection);
				String query = " insert into clients (id, firstname, lastname, address, postalCode, phoneNumber, clientNumber, clientType)"
				        + " values (" + 
						Integer.toString(client.getID()) + 
				        ", " + client.getFirstName() + 
				        ", " + client.getLastName() + 
				        ", " + client.getAddress() + 
				        ", " + new String(client.getPostalCode()) + 
				        ", " + new String(client.getPhoneNumber()) + 
				        ", " + Character.toString(client.getType()) + 
				        ")";
				PreparedStatement state = connect.clientPrepareStatement(query);
				state.executeUpdate();
				System.out.println("Added new client");
			}
			else if (type == SAVE){
				 String query = "UPDATE clients SET id = " + Integer.toString(client.getID()) + " WHERE id = " + Integer.toString(client.getID());
				 PreparedStatement s = connect.prepareStatement(query);
				 s.setString(1,"Sanam We wafafa");
				 s.setInt(2,2005);
				 s.executeUpdate();
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
			list.clear();
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
