package lesson5;

import lesson5.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private final Map<Product, Integer> basketCustomer = new HashMap<>();

    public Map<Product, Integer> getBasketCustomer() {
        return basketCustomer;
    }

    public void putProduct(Product product, int number){
        basketCustomer.put(product,number);
    }

    public void removeProduct(Product product){
        basketCustomer.remove(product);
    }

    public boolean isBasketContainsProduct(Product product){
        return basketCustomer.containsKey(product);
    }

    public Integer getProductQuantity(Product product){
        return basketCustomer.get(product);
    }

    public boolean isBasketEmpty(){
        return basketCustomer.isEmpty();
    }

}
