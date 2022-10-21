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

	public void viewBids() {
		Iterator iterator = offeringListObj.getIterator();
		System.out.println("Bids placed for following products :- ");
		int i = 1;
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			System.out.println(i+". "+o.getProduct());
			i++;
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
		if(userInput<=0 || userInput>productList.size()) {
			System.out.println("Invalid input");
			return;
		}
		String wantedProduct = productList.get(userInput-1);
		Iterator iterator = offeringListObj.getIterator();
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
		String reminder = "\nSeller:Buyer "+getUserName()+" has placed a bid for "+wantedProduct;
		helper.addContentToFile(reminder,"Reminders.txt");

	}

	public void removeBid() throws IOException {
		viewBids();
		Scanner sc = getSc();
		System.out.println("Which bid do you want to remove?");
		int input = sc.nextInt();
		int i = 0;
		Iterator iterator = offeringListObj.getIterator();
		Offering offer = null;
		while(i<input && iterator.hasNext()) {
			offer = iterator.next();
			i++;
		}
		if(offer == null || input>i) {
			System.out.println("Invalid input");
			return;
		}
		String lineContent = offer.getUserName()+":"+offer.getProduct();
		helper.removeLine(lineContent,"UserProduct.txt");
		offeringListObj.remove(offer);
		System.out.println("Bid successfully removed");
		String reminder = "\nSeller:Buyer "+offer.getUserName()+" has removed the bid for "+offer.getProduct();
		helper.addContentToFile(reminder,"Reminders.txt");
	}

	public void startOperation() throws IOException {
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
