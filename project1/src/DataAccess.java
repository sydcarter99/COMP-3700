public interface DataAccess {
    void connect();

    void saveProduct(ProductModel product);

    ProductModel loadProduct(int productID);

    void saveOrder(OrdersModel orders);

    OrdersModel loadOrder(int orderID);

    void saveCustomer(CustomerModel customer);

    CustomerModel loadCustomer(int customerID);
}
