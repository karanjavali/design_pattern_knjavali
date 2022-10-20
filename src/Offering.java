public class Offering {

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String product;
	private String userName;

	Offering(String product, String userName) {
		this.product = product;
		this.userName = userName;
	}

}
