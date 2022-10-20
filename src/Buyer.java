import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends Person {
	private DatabaseHelper helper = DatabaseHelper.getInstance();
	private OfferingList offeringListObj = getOfferingList();
	public Buyer(ProductMenu productMenu) {
		super(productMenu);
	}

	public void showMenu() throws IOException {
		productMenu.showMenu();
	}

	public void viewBids() {
		Iterator iterator = offeringListObj.getIterator();
		ArrayList<Offering> list = offeringListObj.getOfferingList();
		System.out.println("Bids placed for following products :- ");
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			System.out.println(o.getProduct());
		}
	}

	public void placeBid() throws IOException {
		System.out.println("Which item do you want to place a bid for?");
		ArrayList<String> productList = productMenu.getProductList();
		for(int i=0;i<productList.size();i++){
			System.out.println((i+1)+". "+productList.get(i));
		}
		Scanner sc = getSc();
		int userInput = sc.nextInt();
		String wantedProduct = productList.get(userInput-1);
		Iterator iterator = offeringListObj.getIterator();
		ArrayList<Offering> list = offeringListObj.getOfferingList();
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			if (o.getProduct().equals(wantedProduct)) {
				System.out.println("Bid already placed");
				return;
			}
		}

		Offering newOffering = new Offering(wantedProduct,getUserName());
		offeringListObj.addOffering(newOffering);
		helper.addContentToFile("\n"+getUserName()+":"+wantedProduct,"UserProduct.txt");
		System.out.println("Successfully placed bid for "+wantedProduct);
	}

	public void removeBid() {

	}

	public void startOperation() throws IOException {
		createPersonalOfferingList(getUserName());
		while (true) {
			Scanner sc = getSc();
			String userName = getUserName();
			System.out.println("What would you like to do?");
			System.out.println("1. Show menu");
			System.out.println("2. View bids");
			System.out.println("3. Place bid");
			System.out.println("4. Exit");
			int userInput = sc.nextInt();
			switch (userInput) {
				case 1:
					showMenu();
					break;
				case 2:
					viewBids();
					break;
				case 3:
					placeBid();
					break;
				case 4:
					return;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	public void createPersonalOfferingList(String userName) throws IOException {
		ArrayList<String> userProductList = helper.getValues(userName,"UserProduct.txt");
		ArrayList<String> producList = this.productMenu.getProductList();
		ArrayList<String> finalList = new ArrayList<String>();
		for(int i=0;i<userProductList.size();i++){
			String product = userProductList.get(i);
			if (producList.contains(product)) {
				finalList.add(product);
			}
		}
		for(int i=0;i<finalList.size();i++) {
			Offering o = new Offering(finalList.get(i),userName);
			offeringListObj.addOffering(o);
		}
	}

	public ProductMenu CreateProductMenu() {
		return null;
	}

}
