package exercise3;

import java.sql.Statement;

public class SearchTask extends Task{
	public static final int BY_ID = 0;
	public static final int BY_NAME = 1;
	public static final int BY_TYPE = 2;
	
	private String search;
	private int type;
	
	public SearchTask(String s, int t){
		type = t;
		search = s;
	}

	@Override
	public void perform(Statement state) {
		// TODO Auto-generated method stub
		
	}
	
	
}
