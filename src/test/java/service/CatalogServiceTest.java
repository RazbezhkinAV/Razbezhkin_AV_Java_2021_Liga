package service;

import model.Catalog;
import model.product.NameProduct;
import model.product.Phone;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.product.NameProduct.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatalogServiceTest {
    private final Catalog catalog = new Catalog();
    private final CatalogService catalogService = new CatalogService(catalog);

    private final NameProduct nameProduct = IPHONE_X;
    private final Product product = new Phone(IPHONE_X,80_000);

    @DisplayName("Check of a product, does not exist in the catalog")
    @Test
    void checkExistProductInCatalog_invalidProduct_False() {
        catalogService.addProductInCatalog(nameProduct);
        assertFalse(catalogService.checkExistProductInCatalog(IPHONE_XS_MAX));
    }

    @DisplayName("Check of find a invalid product in the catalog")
    @Test
    void findProductInCatalog_invalidProduct_Null() {
        assertEquals(catalogService.findProductInCatalog(nameProduct), null);
    }

    @DisplayName("Check of add a product in the catalog")
    @Test
    void addProductInCatalog_correct() {
        catalogService.addProductInCatalog(IPHONE_X);
        assertEquals(catalogService.findProductInCatalog(IPHONE_X), catalog.findProduct(IPHONE_X));
    }

    @DisplayName("Check of remove a product in the catalog")
    @Test
    void removeProductInCatalog_correct() {
        catalogService.removeProductInCatalog(IPHONE_X);
        assertEquals(catalog.findProduct(IPHONE_X), null);
    }


}