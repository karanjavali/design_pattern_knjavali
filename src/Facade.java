import java.io.IOException;
import java.util.Scanner;

public class Facade {

	private int UserType;

	private Product theSelectedProduct;

	private int nProductCategory;

	private ClassProductList theProductList;

	private Person thePerson;

	private Login loginObject = new Login();
	private Bridge bridgeObject = new Bridge();

	public void addTrading() {

	}

	public void viewTrading() {

	}

	public void decideBidding() {

	}

	public void discussBidding() {

	}

	public void submitBidding() {

	}

	public void remind() {

	}

	public void createUser(UserInfoItem userinfoitem) {

	}

	public void createProductList() {

	}

	public void AttachProductToUser() {

	}

	public Product SelectProduct() {
		return null;
	}

	public void productOperation() {

	}

	public void startOperation() throws IOException {
		Scanner sc = new Scanner(System.in);
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
			return;
		}

		System.out.println("What products are you interested in ?");
		System.out.println("1. Meat\n2. Produce");
		int productChoice = sc.nextInt();
		String productType = "";
		switch (productChoice) {
			case 1:
				productType = "Meat";
				break;
			case 2:
				productType = "Produce";
				break;
			default:
				System.out.println("Invalid product type");
				return;
		}

		startFunctionality(bridgeObject.createInstance(userType,productType));


	}

	public void startFunctionality(Person user) {

	}

}
