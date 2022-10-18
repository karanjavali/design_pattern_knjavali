import java.io.IOException;
import java.util.Scanner;

public class Facade {
	private Scanner sc = new Scanner(System.in);
	private Login loginObject = new Login();
	private Bridge bridgeObject = new Bridge();

	public String userLogin() throws IOException {

		System.out.println("What are you logging in as ? ");
		System.out.println("1. Buyer");
		System.out.println("2. Seller");
		int loginChoice = sc.nextInt();
		String userType = "";
		switch (loginChoice) {
			case 1:
				System.out.println("You have chosen Buyer login. Please complete login process.");
				userType = "Buyer";
				// buyer login
				break;
			case 2:
				System.out.println("You have chosen Seller login. Please complete login process.");
				userType = "Seller";
				break;
			default:
				System.out.println("Invalid input.");
		}

		if(!loginObject.login(userType)) {
			return "Invalid User";
		}
		else {
			return userType;
		}
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

		String userType = userLogin();
		if (userType == "Invalid User") {
			return;
		}

		String productType = getProductType();
		if (productType == "Invalid") {
			return;
		}

		startFunctionality(bridgeObject.createInstance(userType,productType));


	}

	public void startFunctionality(Person user) throws IOException {
		user.showMenu();
	}

}
