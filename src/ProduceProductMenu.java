import java.io.IOException;
import java.util.ArrayList;

public class ProduceProductMenu implements ProductMenu {

	private ProductMenu productMenu;
	private DatabaseHelper db = DatabaseHelper.getInstance();

	@Override
	public String getProductType() {
		return productType;
	}


	private String productType = "Produce";
	// Display produce products
	@Override
	public void showMenu() throws IOException {
		System.out.println("Displaying available produce items");
		ArrayList<String> produceItems = getProductList();
		for(int i=0;i<produceItems.size();i++) {
			System.out.println((i+1)+". "+produceItems.get(i));
		}
	}

	// return list of produce products from ProductInfo.txt
	@Override
	public ArrayList<String> getProductList() throws IOException {
		ArrayList<String> produceItems = db.getValues("Produce","ProductInfo.txt");
		return produceItems;
	}

}
