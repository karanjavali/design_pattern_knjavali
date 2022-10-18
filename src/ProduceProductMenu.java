import java.io.IOException;
import java.util.ArrayList;

public class ProduceProductMenu implements ProductMenu {

	private ProductMenu productMenu;
	private DatabaseHelper db = new DatabaseHelper();
	@Override
	public void showMenu() throws IOException {
		System.out.println("Displaying available produce items");
		ArrayList<String> meatItems = db.getValues("Produce","ProductInfo.txt");
		for(int i=0;i<meatItems.size();i++) {
			System.out.println((i+1)+". "+meatItems.get(i));
		}
	}

}
