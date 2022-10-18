import java.io.IOException;

public class Buyer extends Person {

	public Buyer(ProductMenu productMenu) {
		super(productMenu);
	}

	public void showMenu() throws IOException {
		productMenu.showMenu();
	}

	public ProductMenu CreateProductMenu() {
		return null;
	}

}
