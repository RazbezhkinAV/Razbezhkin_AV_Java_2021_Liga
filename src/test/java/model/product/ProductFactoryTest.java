package model.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.product.NameProduct.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
    private final Product phone = new Phone(IPHONE_X, 80_000);

    @DisplayName("Check of correctness create a product\n")
    @Test
    void createProduct_correct() {
        Product product = ProductFactory.createProduct(IPHONE_X);
        assertEquals(product, phone);
    }
}