package model;

import model.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final Map<Product, Integer> basketCustomer;

    public Basket() {
        basketCustomer = new HashMap<>();
    }

    public Map<Product, Integer> getBasketCustomer() {
        return basketCustomer;
    }

    public void putProduct(Product product, int number) {
        basketCustomer.put(product, number);
    }

    public void removeProduct(Product product) {
        basketCustomer.remove(product);
    }

    public boolean isBasketContainsProduct(Product product) {
        return basketCustomer.containsKey(product);
    }

    public Integer getProductQuantity(Product product) {
        return basketCustomer.get(product);
    }

    public boolean isBasketEmpty() {
        return basketCustomer.isEmpty();
    }

    public long getBasketAmount() {
        long result = 0L;
        for (Map.Entry<Product, Integer> entry :
                basketCustomer.entrySet()) {
            result += entry.getKey().getPrice() * entry.getValue();
        }
        return result;
    }

}
