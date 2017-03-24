package exercise3;

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
}
