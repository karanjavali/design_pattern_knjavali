import java.io.IOException;
import java.util.ArrayList;

public class MeatProductMenu implements ProductMenu {
	DatabaseHelper db = new DatabaseHelper();
	@Override
	public void showMenu() throws IOException {
		System.out.println("Displaying available meat items");
		ArrayList<String> meatItems = db.getValues("Meat","ProductInfo.txt");
		for(int i=0;i<meatItems.size();i++) {
			System.out.println((i+1)+". "+meatItems.get(i));
		}
	}


}
