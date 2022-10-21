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

	// This function will create OfferingList
	// This OfferingList will contain the bids of all buyers
	public void createBuyerOfferingList() throws IOException {
		ArrayList<String[]> allUserInfo = helper.getAllFileData("UserProduct.txt");
		ArrayList<String> productList = this.productMenu.getProductList();
		HashMap<String,String> sellerList = helper.getFileData("SellerInfo.txt");
		ArrayList<ArrayList<String>> finalList = new ArrayList<ArrayList<String>>();
		ArrayList<String> users = new ArrayList<String>();
		for(int i=0;i<allUserInfo.size();i++) {
			String[] keyValuePair = allUserInfo.get(i);
			// person should be a seller
			if (!sellerList.containsKey(keyValuePair[0])) {
				// no repeat values
				if (!users.contains(keyValuePair[0])) {
					users.add(keyValuePair[0]);
					finalList.add(new ArrayList<>());
				}
				// If the line is empty. Without this, error is thrown (ArrayOutOfBounds Exception)
				if(keyValuePair.length==1){
					continue;
				}

				// if user already present, add the product.

				if(productList.contains(keyValuePair[1])) {
					finalList.get(users.indexOf(keyValuePair[0])).add(keyValuePair[1]);
				}


			}
		}
		// final list will contain buyers and all their bids
		// create Offering and add it to OfferingList
		for(int i=0;i<finalList.size();i++) {
			for(int j=0;j<finalList.get(i).size();j++){
				Offering o = new Offering(finalList.get(i).get(j),users.get(i));
				offeringListObj.addOffering(o);
			}

		}

	}

	// view bids placed by buyers
	// Use iterator to iterate through all offerings
	public void viewBids() {
		Iterator iterator = offeringListObj.getIterator();
		System.out.println("Bids placed for following products :- ");
		while(iterator.hasNext()) {
			Offering o =  iterator.next();
			System.out.println(o.getUserName()+" -> "+o.getProduct());
		}
	}

	// sell to a buyer
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

		// iterate again to reach offering
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

		// sell to buyer if seller has the product available
		ArrayList<String> availableProducts =  helper.getValues(getUserName(),"UserProduct.txt");
		if (availableProducts.contains(offer.getProduct()))
		{
			// remove buyer bid after sale
			String lineContent = offer.getUserName()+":"+offer.getProduct();
			helper.removeLine(lineContent,"UserProduct.txt");
			offeringListObj.remove(offer);
			System.out.println("Successfully sold to buyer!");
			// create reminder for the buyer
			String reminder = "\n"+offer.getUserName()+":Seller "+getUserName()+" has sold you product - "+offer.getProduct();
			helper.addContentToFile(reminder,"Reminders.txt");
		}
		else {
			System.out.println("Product not available to sell");
		}
	}

	// add product to offering list and UserProduct.txt
	// add product to ProductInfo.txt if it is not there
	public void addProduct() throws IOException {
		Scanner sc = getSc();
		System.out.println("Enter the product to add");
		sc.nextLine();
		String input = sc.nextLine();
		String productType = productMenu.getProductType();
		// meat/produce product list
		ArrayList<String> productTypeValues = helper.getValues(productType,"ProductInfo.txt");
		// offers placed by the seller
		ArrayList<String> bidValues = helper.getValues(getUserName(),"UserProduct.txt");
		if(!bidValues.contains(input)) {
			helper.addContentToFile("\n"+getUserName()+":"+input,"UserProduct.txt");
			// add to ProductInfo.txt if not present
			if(!productTypeValues.contains(input)) {
				helper.addContentToFile("\n"+productMenu.getProductType()+":"+input,"ProductInfo.txt");
			}
			System.out.println("Product added successfully");
			// Send reminders to buyers that a new product has been added
			String reminder = "\nBuyer:Seller "+getUserName()+" has added "+input+" for sale";
			helper.addContentToFile(reminder,"Reminders.txt");
		}

		else {
			System.out.println("Product already present");
		}


	}

	// remove product from sale
	public void removeProduct() throws IOException {
		ArrayList<String[]> allData = helper.getAllFileData("UserProduct.txt");
		ArrayList<String> products = productMenu.getProductList();
		// display all products on sale. seller must choose a product to remove
		System.out.println("Please the offering which you want to remove");
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i=0;i<allData.size();i++){
			String s[] = allData.get(i);
			if(s[0].equals(getUserName()) && products.contains(s[1])) {
				indexes.add(i);
			}
		}
		for(int i=0;i<indexes.size();i++){
			System.out.println((i+1)+". "+allData.get(indexes.get(i))[1]);
		}
		int input = getSc().nextInt();
		if(input<=0 || input>indexes.size()) {
			System.out.println("Invalid input");
			return;
		}

		// remove from UserProduct.txt
		int index = indexes.get(input-1);
		String lineContentArray[] = allData.get(index);
		String lineContent = lineContentArray[0]+":"+lineContentArray[1];
		helper.removeLine(lineContent,"UserProduct.txt");
		allData.remove(index);
		System.out.println(lineContentArray[1]+" successfully removed from sale.");

	}


	// entry point of Seller
	public void startOperation() throws IOException {
		createBuyerOfferingList();
		while (true) {
			Scanner sc = getSc();
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Show menu");
			System.out.println("2. Add product");
			System.out.println("3. Remove product from sale");
			System.out.println("4. View buyer bids");
			System.out.println("5. Complete transaction");
			System.out.println("6. Exit");
			int userInput = sc.nextInt();
			switch (userInput) {
				case 1:
					showMenu();
					break;
				case 2:
					addProduct();
					break;
				case 3:
					removeProduct();
					break;
				case 4:
					viewBids();
					break;
				case 5:
					sellToBuyer();
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid choice");
			}
		}
	}


}
