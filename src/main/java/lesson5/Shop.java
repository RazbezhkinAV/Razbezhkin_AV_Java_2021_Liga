package lesson5;

import lesson5.catalog.Catalog;
import lesson5.person.Customer;
import lesson5.product.NameProduct;

public class Shop {
    public static void main(String[] args) {
        Customer customer = new Customer(1,"Alex",120000);

        customer.getBasket().add(Catalog.getProduct(NameProduct.IPHONE_X));
        customer.getBasket().add(Catalog.getProduct(NameProduct.GALAXY_TABLE_A));
        customer.getBasket().getOrderInfo();
        System.out.println(customer.buy());
    }
}
