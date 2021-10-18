package lesson5.service;

import lesson5.model.Catalog;
import lesson5.model.product.NameProduct;
import lesson5.model.product.Product;

public class CatalogService implements Service {

    private static final Catalog catalog = new Catalog();

    public static boolean checkExistProductInCatalog(NameProduct nameProduct) {
        return catalog.isProductExist(nameProduct);
    }

    public static Product findProductInCatalog(NameProduct nameProduct) {
        return catalog.findProduct(nameProduct);
    }

    public static void addProductInCatalog(NameProduct nameProduct){
        catalog.addProduct(nameProduct);
    }

    public static void removeProductInCatalog(NameProduct nameProduct){
        catalog.removeProduct(nameProduct);
    }

}
