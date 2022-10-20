import java.io.IOException;
import java.util.ArrayList;

public interface ProductMenu {
//
//	Person person;
//
//	ProduceProductMenu produceProductMenu;

	public abstract void showMenu() throws IOException;

	public abstract ArrayList<String> getProductList() throws IOException;
}
