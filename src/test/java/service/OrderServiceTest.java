package service;

import exeption.CreateNewOrderException;
import exeption.IncorrectDateException;
import exeption.ProductNotCorrectlyException;
import model.Basket;
import model.Catalog;
import model.person.Customer;
import model.product.NameProduct;
import model.product.Phone;
import model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.product.NameProduct.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class OrderServiceTest {
    private final CatalogService catalogServiceMock = mock(CatalogService.class);
    private final BasketService basketService = new BasketService(catalogServiceMock);
    private final OrderService orderService = new OrderService();
    private final Basket basket = new Basket();
    private final Customer customer = new Customer("Sacha", "89996690413");
    private final NameProduct nameProduct = IPHONE_X;
    private final Product phone = new Phone(nameProduct,80_000);

    @BeforeEach
    void initialise(){
        when(catalogServiceMock.checkExistProductInCatalog(nameProduct)).thenReturn(true);
        when(catalogServiceMock.findProductInCatalog(nameProduct)).thenReturn(phone);
    }

    @DisplayName("Check of get Exception when there is no customer")
    @Test
    void createNewOrder_noCustomer_Exception() throws ProductNotCorrectlyException {
        basketService.addProductToBasket(basket, nameProduct);

        assertThrows(CreateNewOrderException.class, () -> {
            orderService.createNewOrder(null, basket);
        });
    }

    @DisplayName("Check of get Exception when the basket is empty")
    @Test
    void createNewOrder_basketIsEmpty_Exception() {
        assertThrows(CreateNewOrderException.class, () -> {
            orderService.createNewOrder(customer, basket);
        });
    }

    @DisplayName("Check of correct create new Order")
    @Test
    void createNewOrder_correct() throws ProductNotCorrectlyException, CreateNewOrderException {
        basketService.addProductToBasket(basket, nameProduct);
        orderService.createNewOrder(customer,basket);
        assertFalse(orderService.OrdersIsEmpty());
    }

    @DisplayName("Check of get Exception, when there is no order for the customer")
    @Test
    void findOrder_noOrderForCustomer_Exception() throws ProductNotCorrectlyException, CreateNewOrderException {
        basketService.addProductToBasket(basket, nameProduct);
        orderService.createNewOrder(customer,basket);

        assertThrows(IncorrectDateException.class,()->{
            orderService.findOrder(new Customer("Anna","89993215689"));
        });
    }

    @DisplayName("Check of correctness description order")
    @Test
    void getOrderDescription_correct() throws ProductNotCorrectlyException, CreateNewOrderException {
        basketService.addProductToBasket(basket, nameProduct);
        orderService.createNewOrder(customer,basket);

        String orderDescription = "Заказ оформлен на Sacha\n" +
                "Курьер свяжется с вами по номеру :89996690413\n" +
                "Товар в вашей корзине:\n" +
                "Название - IPHONE_X, колличество - 1, стоимость = 80000\n" +
                "Общая стоимость корзины составляет = 80000\n";

        assertEquals(orderService.getOrderDescription(customer, basketService.getBasketDescription(basket)), orderDescription);
    }


}