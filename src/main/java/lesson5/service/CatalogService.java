package lesson5.service;

import lesson5.model.Catalog;
import lesson5.model.product.NameProduct;
import lesson5.model.product.Product;

public class CatalogService implements Service {

    private static final Catalog catalog = new Catalog();

    static {
        catalog.addProduct(NameProduct.IPHONE_X);
        catalog.addProduct(NameProduct.GALAXY_TABLE_A);
        catalog.addProduct(NameProduct.MI_BAND_6);
    }

    public static boolean checkExistProductInCatalog(NameProduct nameProduct) {
        return catalog.isProductExist(nameProduct);
    }

    public static Product findProductInCatalog(NameProduct nameProduct) {
        return catalog.findProduct(nameProduct);
    }

}
