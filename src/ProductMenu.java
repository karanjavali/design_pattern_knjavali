import java.io.IOException;
import java.util.ArrayList;

public interface ProductMenu {
	public abstract void showMenu() throws IOException;

	public abstract String getProductType();
	public abstract void setProductType(String productType);
	public abstract ArrayList<String> getProductList() throws IOException;
}
