import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Seller extends Person {
	DatabaseHelper helper = DatabaseHelper.getInstance();
	Seller(ProductMenu productMenu) {
		super(productMenu);
	}

	OfferingList offeringListObj = new OfferingList();
	public void showMenu() throws IOException {
		productMenu.showMenu();
	}

	public void createBuyerOfferingList() throws IOException {
		ArrayList<String[]> allUserInfo = helper.getAllFileData("UserProduct.txt");
		ArrayList<String> productList = this.productMenu.getProductList();
		HashMap<String,String> sellerList = helper.getFileData("SellerInfo.txt");
		ArrayList<ArrayList<String>> finalList = new ArrayList<ArrayList<String>>();
		ArrayList<String> users = new ArrayList<String>();
		for(int i=0;i<allUserInfo.size();i++) {
			String[] keyValuePair = allUserInfo.get(i);
			if (!sellerList.containsKey(keyValuePair[0])) {
				if (!users.contains(keyValuePair[0])) {
					users.add(keyValuePair[0]);
					finalList.add(new ArrayList<>());
				}
				if(keyValuePair.length==1){
					continue;
				}
				if(productList.contains(keyValuePair[1])) {
					finalList.get(users.indexOf(keyValuePair[0])).add(keyValuePair[1]);
				}


			}
		}

		for(int i=0;i<finalList.size();i++) {
			for(int j=0;j<finalList.get(i).size();j++){
				Offering o = new Offering(finalList.get(i).get(j),users.get(i));
				offeringListObj.addOffering(o);
			}

		}

	}

	public void viewBids() {
		Iterator iterator = offeringListObj.getIterator();
		System.out.println("Bids placed for following products :- ");
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			System.out.println(o.getUserName()+" -> "+o.getProduct());
		}
	}

	public void sellToBuyer() throws IOException {
		Scanner sc = getSc();
		Iterator iterator = offeringListObj.getIterator();
		ArrayList<String > buyers = new ArrayList<String>();
		System.out.println("Select transaction to complete");
		int i=0;
		while(iterator.hasNext()) {

			Offering o =  iterator.next();
			System.out.println((i+1)+". "+o.getUserName()+" -> "+o.getProduct());
			i++;
		}
		int userInput = sc.nextInt();
		Iterator iterator2 = offeringListObj.getIterator();
		Offering offer = null;
		i=0;
		while (i<userInput){
			offer =  iterator2.next();
			i++;
		}
		if(offer==null || userInput>i) {
			System.out.println("Invalid input");
			return;
		}
		ArrayList<String> availableProducts =  helper.getValues(getUserName(),"UserProduct.txt");
		if (availableProducts.contains(offer.getProduct())) {
			String lineContent = offer.getUserName()+":"+offer.getProduct();
			helper.removeLine(lineContent,"UserProduct.txt");
			offeringListObj.remove(offer);
			System.out.println("Successfully sold to buyer!");
			String reminder = "\n"+offer.getUserName()+":Seller "+getUserName()+" has sold you product - "+offer.getProduct();
			helper.addContentToFile(reminder,"Reminders.txt");
		}
		else {
			System.out.println("Product not available to sell");
		}
	}

	public void addProduct() throws IOException {
		Scanner sc = getSc();
		System.out.println("Enter the product to add");
		sc.nextLine();
		String input = sc.nextLine();
		String productType = productMenu.getProductType();
		ArrayList<String> productTypeValues = helper.getValues(productType,"ProductInfo.txt");
		ArrayList<String> bidValues = helper.getValues(getUserName(),"UserProduct.txt");
		if(!bidValues.contains(input)) {
			helper.addContentToFile("\n"+getUserName()+":"+input,"UserProduct.txt");
			if(!productTypeValues.contains(input)) {
				helper.addContentToFile("\n"+productMenu.getProductType()+":"+input,"ProductInfo.txt");
			}
			System.out.println("Product added successfully");
			String reminder = "\nBuyer:Seller "+getUserName()+" has added "+input+" for sale";
			helper.addContentToFile(reminder,"Reminders.txt");
		}

		else {
			System.out.println("Product already present");
		}


	}

	public void startOperation() throws IOException {
		createBuyerOfferingList();
		while (true) {
			Scanner sc = getSc();
			String userName = getUserName();
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Show menu");
			System.out.println("2. Add product");
			System.out.println("3. View buyer bids");
			System.out.println("4. Complete transaction");
			System.out.println("5. Exit");
			int userInput = sc.nextInt();
			switch (userInput) {
				case 1:
					showMenu();
					break;
				case 2:
					addProduct();
					break;
				case 3:
					viewBids();
					break;
				case 4:
					sellToBuyer();
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	public ProductMenu CreateProductMenu() {
		return null;
	}

}
