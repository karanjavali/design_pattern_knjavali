import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Facade {
	private Scanner sc = new Scanner(System.in);
	private Login loginObject = new Login();
	private Bridge bridgeObject = new Bridge();



	private DatabaseHelper helper = DatabaseHelper.getInstance();

	// get list of buyers from BuyerInfo.txt
	public ArrayList<String> getBuyerList() throws IOException {
		ArrayList<String> buyers = new ArrayList<String>();
		HashMap<String,String> listOfBuyers = helper.getFileData("BuyerInfo.txt");
		for(String b :listOfBuyers.keySet()) {
			buyers.add(b);
		}
		return buyers;
	}

	// get list of sellers from SellerInfo.txt
	public ArrayList<String> getSellerList() throws IOException {
		ArrayList<String> sellers = new ArrayList<String>();
		HashMap<String,String> listOfSellers = helper.getFileData("SellerInfo.txt");
		for(String b :listOfSellers.keySet()) {
			sellers.add(b);
		}
		return sellers;
	}



	// ask the user the kind of product they want to look into
	public String getProductType() {
		System.out.println("What products are you interested in ?");
		System.out.println("1. Meat\n2. Produce");
		int productChoice = sc.nextInt();
		String productType = "";
		switch (productChoice) {
			case 1:
				 return "Meat";
			case 2:
				return "Produce";
			default:
				return "Invalid";
		}
	}

	// get all the reminders which have the key as their username or common reminders(Buyer/Seller reminders) from Reminders.txt
	// Reminder format -   key:value . Key will be the username/Buyer/Seller , value will be the message.
	public ArrayList<Reminder> getReminders(String[] userDetails) throws IOException {
		ArrayList<Reminder> reminders = new ArrayList<Reminder>();
		ArrayList<String> reminderList = helper.getValues(userDetails[0],"Reminders.txt");
		ArrayList<String> commonReminderList = helper.getValues(userDetails[1],"Reminders.txt");
		// username reminders
		for(String s:reminderList) {
			reminders.add(new Reminder(userDetails[0],s));
		}
		// common reminders
		for(String s:commonReminderList){
			reminders.add(new Reminder(userDetails[1],s));
		}
		return reminders;
	}

	// the starting point of the facade class
	public void startOperation() throws IOException {
		// complete login here, then start operation for particular user
		String userDetails[] = loginObject.userLogin();
		String userName = userDetails[1];
		if (userName == "Invalid_User") {
			return;
		}
		// ask product type
		String productType = getProductType();
		if (productType == "Invalid") {
			return;
		}

		// visit and print any reminders that have been generated. NOTE: This will print all the reminders, irrespective of if they were already seen or not.
		ReminderVisitor visitor = new ReminderVisitor();
		ArrayList<Reminder> reminders = getReminders(userDetails);
		System.out.println("----------------------------------------------------------------");
		System.out.println("\nHere are some reminders for you :-\n");
		// visit each reminder
		for(Reminder r:reminders) {
			r.visitReminder(visitor);
		}
		System.out.println("\n----------------------------------------------------------------");

		// use bridge to obtain the person object(buyer/seller)
		Person user = bridgeObject.createInstance(userDetails[0],productType);
		// set username of the person object
		user.setUserName(userDetails[1]);
		// start respective operation
		user.startOperation();
	}

	public void getMenu(Person user) throws IOException {
		// show product menu
		user.showMenu();
	}

}
