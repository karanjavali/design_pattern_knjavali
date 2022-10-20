import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Facade {
	private Scanner sc = new Scanner(System.in);
	private Login loginObject = new Login();
	private Bridge bridgeObject = new Bridge();



	private DatabaseHelper helper = DatabaseHelper.getInstance();

	public ArrayList<String> getBuyerList() throws IOException {
		ArrayList<String> buyers = new ArrayList<String>();
		HashMap<String,String> listOfBuyers = helper.getFileData("BuyerInfo.txt");
		for(String b :listOfBuyers.keySet()) {
			buyers.add(b);
		}
		return buyers;
	}

	public ArrayList<String> getSellerList() throws IOException {
		ArrayList<String> sellers = new ArrayList<String>();
		HashMap<String,String> listOfSellers = helper.getFileData("SellerInfo.txt");
		for(String b :listOfSellers.keySet()) {
			sellers.add(b);
		}
		return sellers;
	}




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



	public void startOperation() throws IOException {

		String userDetails[] = loginObject.userLogin();
		String userName = userDetails[1];
		if (userName == "Invalid_User") {
			return;
		}

		String productType = getProductType();
		if (productType == "Invalid") {
			return;
		}

		Person user = bridgeObject.createInstance(userDetails[0],productType);
		user.setUserName(userDetails[1]);
		user.startOperation();
	}

	public void getMenu(Person user) throws IOException {
		user.showMenu();
	}

}
