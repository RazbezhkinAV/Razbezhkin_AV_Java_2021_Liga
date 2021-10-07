package service;

import exeption.ProductNotCorrectlyException;
import model.Basket;
import model.product.NameProduct;
import model.product.Phone;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.product.NameProduct.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {
    private final CatalogService catalogServiceMock = mock(CatalogService.class);
    private final BasketService basketService = new BasketService(catalogServiceMock);
    private final NameProduct nameProduct = IPHONE_X;
    private final Basket basket = new Basket();
    private final Product phone = new Phone(IPHONE_X, 80_000);

    @BeforeEach
    void initialise()  {
        when(catalogServiceMock.checkExistProductInCatalog(nameProduct)).thenReturn(true);
        when(catalogServiceMock.findProductInCatalog(nameProduct)).thenReturn(phone);
    }

    @DisplayName("Check of a product, does not exist in the catalog")
    @Test
    void addProductToBasket_ProductIsNotInCatalog_Exception() {
        when(catalogServiceMock.checkExistProductInCatalog(nameProduct)).thenReturn(false);

        assertThrows(ProductNotCorrectlyException.class, () -> {
            basketService.addProductToBasket(basket, nameProduct);
        });
    }

    @DisplayName("Check of add a product in the basket")
    @Test
    void addProductToBasket_addProductInBasket_Correct() throws ProductNotCorrectlyException {
        basketService.addProductToBasket(basket, nameProduct);

        assertTrue(basket.isBasketContainsProduct(phone));
    }

    @DisplayName("Check of add two identical product in the basket")
    @Test
    void addProductToBasket_addTwoProductInBasket_Correct() throws ProductNotCorrectlyException {
        basketService.addProductToBasket(basket, nameProduct);
        basketService.addProductToBasket(basket, nameProduct);

        assertEquals(basket.getProductQuantity(phone), 2);
    }

    @DisplayName("Check of remove a product, does not exist in the catalog")
    @Test
    void removeProductToBasket_ProductIsNotInCatalog_Exception() {
        when(catalogServiceMock.checkExistProductInCatalog(nameProduct)).thenReturn(false);

        assertThrows(ProductNotCorrectlyException.class, () -> {
            basketService.removeProductToBasket(basket, nameProduct);
        });
    }

    @DisplayName("Check of remove a product, does not exist in the basket")
    @Test
    void removeProductToBasket_ProductIsNotInBasket_Exception() {
        when(catalogServiceMock.findProductInCatalog(nameProduct)).thenReturn(new Phone(IPHONE_XS_MAX, 100_000));

        assertThrows(ProductNotCorrectlyException.class, () -> {
            basketService.removeProductToBasket(basket, nameProduct);
        });
    }

    @DisplayName("Check of remove one product in the basket")
    @Test
    void removeProductToBasket_removeOneProductInBasket_Correct() throws ProductNotCorrectlyException {
        basketService.addProductToBasket(basket, IPHONE_X);
        basketService.removeProductToBasket(basket, IPHONE_X);
        assertTrue(basket.isBasketEmpty());
    }

    @DisplayName("Check of remove one product in the basket, when there are two identical products in the basket")
    @Test
    void removeProductToBasket_removeOneOfTheProductInBasket_Correct() throws ProductNotCorrectlyException {
        basketService.addProductToBasket(basket, IPHONE_X);
        basketService.addProductToBasket(basket, IPHONE_X);
        basketService.removeProductToBasket(basket, IPHONE_X);

        assertEquals(basket.getProductQuantity(phone), 1);
    }

    @DisplayName("Check of the basket description if the basket is empty")
    @Test
    void getBasketDescription_basketIsEmpty() {
        assertEquals(basketService.getBasketDescription(basket), "\nКорзина пустая");
    }

    @DisplayName("Check of the basket description if the basket is not empty")
    @Test
    void getBasketDescription_basketIsNotEmpty() {
        String message = "Товар в вашей корзине:\n" +
                "Название - IPHONE_X, колличество - 1, стоимость = 80000\n" +
                "Общая стоимость корзины составляет = 80000\n";
        basket.putProduct(phone, 1);
        assertEquals(basketService.getBasketDescription(basket), message);
    }
}