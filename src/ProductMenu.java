import java.io.IOException;
import java.util.ArrayList;
// ProductMenu interface
public interface ProductMenu {
	public abstract void showMenu() throws IOException;

	public abstract String getProductType();
	public abstract ArrayList<String> getProductList() throws IOException;
}
