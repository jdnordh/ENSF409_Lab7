package exercise3;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Client {
	private int ID;
	private String firstName;
	private String lastName;
	private String address;
	private char [] postalCode;
	private char [] phoneNumber;
	private char type;
	
	public Client(){}
	
	public Client(int id, String first, String last, String add, String post, String phone, char t){
		setID(id);
		setFirstName(first);
		setLastName(last);
		setAddress(add);
		setPostalCode(post);
		setPhoneNumber(phone);
		setType(t);
	}
	
	public Client(String id, String first, String last, String add, String post, String phone, String t){
		setID(Integer.parseInt(id));
		setFirstName(first);
		setLastName(last);
		setAddress(add);
		setPostalCode(post);
		setPhoneNumber(phone);
		setType(t.charAt(0));
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char [] getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode.toCharArray();
	}

	public char [] getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber.toCharArray();
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public String toString(){
		String s = "";
		s += Integer.toString(ID);
		s += ",";
		s += firstName;
		s += ",";
		s += lastName;
		s += ",";
		s += address;
		s += ",";
		s += new String(postalCode);
		s += ",";
		s += new String(phoneNumber);
		s += ",";
		s += type;
		return s;
	}

	public void displayInfo(JTextField clientIDIn,JTextField firstNameIn,JTextField lastNameIn,
			JTextField addressIn, JTextField postalIn, JTextField phoneNumIn, JComboBox<String> comboBox) {
		clientIDIn.setText(Integer.toString(ID));
		firstNameIn.setText(firstName);
		lastNameIn.setText(lastName);
		addressIn.setText(address);
		postalIn.setText(new String(postalCode));
		phoneNumIn.setText(new String(phoneNumber));
		if (type == 'R') comboBox.setSelectedItem("Residential");
		else comboBox.setSelectedItem("Commercial");
	}
}
