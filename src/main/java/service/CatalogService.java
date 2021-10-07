package service;

import exeption.ProductNotCorrectlyException;
import model.Catalog;
import model.product.NameProduct;
import model.product.Product;

public class CatalogService implements Service {

    private Catalog catalog;

    public CatalogService(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean checkExistProductInCatalog(NameProduct nameProduct) {
        return catalog.isProductExist(nameProduct);
    }

    public Product findProductInCatalog(NameProduct nameProduct) {
        return catalog.findProduct(nameProduct);
    }

    public void addProductInCatalog(NameProduct nameProduct) {
        catalog.addProduct(nameProduct);
    }

    public void removeProductInCatalog(NameProduct nameProduct) {
        catalog.removeProduct(nameProduct);
    }

}
