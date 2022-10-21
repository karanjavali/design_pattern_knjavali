import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Person {
	private DatabaseHelper helper = DatabaseHelper.getInstance();
	public OfferingList getOfferingList() {
		return offeringList;
	}


	private OfferingList offeringList = new OfferingList();
	public Scanner getSc() {
		return sc;
	}


	private Scanner sc = new Scanner(System.in);
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public Person(ProductMenu productMenu) {
		this.productMenu = productMenu;
	}

	protected ProductMenu productMenu;

	public abstract void showMenu() throws IOException;

	public abstract  void startOperation() throws IOException;



}
