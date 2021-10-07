package model;

import model.product.*;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private final Map<NameProduct, Product> catalog;

    public Catalog() {
        catalog = new HashMap<>();
    }

    public Catalog(Map<NameProduct, Product> catalog) {
        this.catalog = catalog;
    }

    public void addProduct(NameProduct nameProduct) {
        catalog.put(nameProduct, ProductFactory.createProduct(nameProduct));
    }

    public void removeProduct(NameProduct nameProduct) {
        catalog.remove(nameProduct);
    }

    public boolean isProductExist(NameProduct nameProduct) {
        return catalog.containsKey(nameProduct);
    }

    public Product findProduct(NameProduct nameProduct) {
        return catalog.get(nameProduct);
    }


}
