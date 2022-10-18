import java.io.IOException;

public abstract class Person {

	public Person(ProductMenu productMenu) {
		this.productMenu = productMenu;
	}

	protected ProductMenu productMenu;

	public abstract void showMenu() throws IOException;


	public abstract ProductMenu CreateProductMenu();

}
