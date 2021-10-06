package lesson5.model;

import lesson5.model.product.*;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private final Map<NameProduct, Product> catalog;

    public Catalog() {
        catalog = new HashMap<>();
    }

    public void addProduct(NameProduct nameProduct){
        catalog.put(nameProduct,ProductFactory.createProduct(nameProduct));
    }

    public void removeProduct(NameProduct nameProduct){
        catalog.remove(nameProduct);
    }

    public boolean isProductExist(NameProduct nameProduct){
        return catalog.containsKey(nameProduct);
    }

    public Product findProduct(NameProduct nameProduct){
        return catalog.get(nameProduct);
    }
}
