public class Factory {

    // This function returns a MeatProductMenu object or ProduceProductMenu object based on user input
    public ProductMenu createProduct(String productType) {
        if(productType == null || productType.isEmpty()) {
            System.out.println("No product type entered");
            return null;
        }
        switch (productType) {
            case "Meat":
                return new MeatProductMenu();
            case "Produce":
                return new ProduceProductMenu();
            default:
                System.out.println("Unknown product type");
                return null;
        }
    }


    // This function returns Buyer/Seller Object based on user input.
    public Person createUser(String userType, ProductMenu product) {
        if(userType == null || userType.isEmpty()) {
            System.out.println("No usertype entered");
            return null;
        }

        switch (userType) {
            case "Buyer":
                return new Buyer(product);
            case "Seller":
                return new Seller(product);
            default:
                System.out.println("Invalid user type");
                return null;
        }
    }
}
