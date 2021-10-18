package lesson5.model;

import lesson5.model.product.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog1 = (Catalog) o;
        return Objects.equals(catalog, catalog1.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog);
    }
}
