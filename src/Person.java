public abstract class Person {

	public Person(ProductMenu productMenu) {
		this.productMenu = productMenu;
	}

	protected ProductMenu productMenu;

	public abstract void showMenu();

	public void showAddButton() {

	}

	public void showViewButton() {

	}

	public void showRadioButton() {

	}

	public void showLabels() {

	}

	public abstract ProductMenu CreateProductMenu();

}
