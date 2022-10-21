public class Bridge {
    Factory objectFactory = new Factory();
    public Person createInstance(String userType, String productType) {
        ProductMenu productObject = objectFactory.createProduct(productType);
        return objectFactory.createUser(userType,productObject);
    }
}
