public class Bridge {
    // use factory to create ProductMenu and Person classes.
    Factory objectFactory = new Factory();
    public Person createInstance(String userType, String productType) {
        // get the ProductMenu object
        ProductMenu productObject = objectFactory.createProduct(productType);
        // return the user object after creation
        return objectFactory.createUser(userType,productObject);
    }
}
