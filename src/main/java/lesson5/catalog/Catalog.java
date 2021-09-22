package lesson5.catalog;

import lesson5.product.*;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private static Map<NameProduct, Product> catalog = new HashMap<>();

    static {
        catalog.put(NameProduct.IPHONE_X, new Phone(NameProduct.IPHONE_X,80000L, Manufacturer.APPLE));
        catalog.put(NameProduct.GALAXY_TABLE_A, new Tablet(NameProduct.GALAXY_TABLE_A,80000L, Manufacturer.SAMSUNG));
        catalog.put(NameProduct.MI_BAND_6, new Watch(NameProduct.MI_BAND_6,80000L, Manufacturer.XIAOMI));
    }

    public static Product getProduct(NameProduct nameProduct){
        return catalog.get(nameProduct);
    }
}
