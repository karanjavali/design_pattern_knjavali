import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

	// view bids placed by buyer
	public void viewBids() {
		// use iterator to access all bids
		Iterator iterator = offeringListObj.getIterator();
		System.out.println("Bids placed for following products :- ");
		int i = 1;
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			System.out.println(i+". "+o.getProduct());
			i++;
		}
	}

	// place bid on an item
	public void placeBid() throws IOException {
		// display list of items to place bid for
		System.out.println("Which item do you want to place a bid for?");
		ArrayList<String> productList = productMenu.getProductList();
		for(int i=0;i<productList.size();i++){
			System.out.println((i+1)+". "+productList.get(i));
		}
		Scanner sc = getSc();
		int userInput = sc.nextInt();
		if(userInput<=0 || userInput>productList.size()) {
			System.out.println("Invalid input");
			return;
		}

		// wanted product input
		String wantedProduct = productList.get(userInput-1);

		// check if bid already placed
		Iterator iterator = offeringListObj.getIterator();
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			if (o.getProduct().equals(wantedProduct)) {
				System.out.println("Bid already placed");
				return;
			}
		}

		// create new offering and add it to offering list and database(UserProduct.txt)
		Offering newOffering = new Offering(wantedProduct,getUserName());
		offeringListObj.addOffering(newOffering);
		helper.addContentToFile("\n"+getUserName()+":"+wantedProduct,"UserProduct.txt");
		System.out.println("Successfully placed bid for "+wantedProduct);
		// create reminder for all sellers that a new bid has been created
		String reminder = "\nSeller:Buyer "+getUserName()+" has placed a bid for "+wantedProduct;
		helper.addContentToFile(reminder,"Reminders.txt");

	}

	// remove bid
	public void removeBid() throws IOException {
		// view all bids first
		viewBids();
		Scanner sc = getSc();
		// take user input
		System.out.println("Which bid do you want to remove?");
		int input = sc.nextInt();
		int i = 0;
		// iterate through OfferingList
		Iterator iterator = offeringListObj.getIterator();
		Offering offer = null;
		while(i<input && iterator.hasNext()) {
			offer = iterator.next();
			i++;
		}
		// return if input invalid
		if(offer == null || input>i) {
			System.out.println("Invalid input");
			return;
		}

		// if input is valid, remove the bid (userName:product) from database (UserProduct.txt)
		String lineContent = offer.getUserName()+":"+offer.getProduct();
		helper.removeLine(lineContent,"UserProduct.txt");
		offeringListObj.remove(offer);
		System.out.println("Bid successfully removed");
		// create reminder for sellers that a buyer has removed bid for the product
		String reminder = "\nSeller:Buyer "+offer.getUserName()+" has removed the bid for "+offer.getProduct();
		helper.addContentToFile(reminder,"Reminders.txt");
	}

	// starting point of buyer from facade
	public void startOperation() throws IOException {
		// create OfferingList. This list will contain bids placed by the particular buyer.
		// Show menu and provide options for actions
		createPersonalOfferingList(getUserName());
		while (true) {
			Scanner sc = getSc();
			String userName = getUserName();
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Show menu");
			System.out.println("2. View bids");
			System.out.println("3. Place bid");
			System.out.println("4. Remove bid");
			System.out.println("5. Exit");
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
					removeBid();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	// Create OfferingList for buyer
	public void createPersonalOfferingList(String userName) throws IOException {
		// Get all products the buyer has placed bids on
		ArrayList<String> userProductList = helper.getValues(userName,"UserProduct.txt");
		// Get list of particular type of products
		ArrayList<String> producList = this.productMenu.getProductList();
		// final list
		ArrayList<String> finalList = new ArrayList<String>();
		for(int i=0;i<userProductList.size();i++){
			// if product type matches, add the product in final list
			String product = userProductList.get(i);
			if (producList.contains(product)) {
				finalList.add(product);
			}
		}
		// create offerings and add it to OfferingList object
		for(int i=0;i<finalList.size();i++) {
			Offering o = new Offering(finalList.get(i),userName);
			offeringListObj.addOffering(o);
		}
	}


}
