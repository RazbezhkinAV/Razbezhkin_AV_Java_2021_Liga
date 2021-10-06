package lesson5;

import lesson5.exeption.IncorrectDateException;
import lesson5.exeption.NoSuchProductExistsException;
import lesson5.exeption.ProductNotCorrectlyException;
import lesson5.exeption.createNewOrderException;
import lesson5.model.Basket;
import lesson5.model.person.Customer;
import lesson5.model.person.Person;
import lesson5.model.product.NameProduct;
import lesson5.service.BasketService;
import lesson5.service.CreatePersonService;
import lesson5.service.OrderService;

public class Controller {
    private final CreatePersonService personService;
    private final BasketService basketService;
    private final OrderService orderService;

    public Controller() {
        personService = new CreatePersonService();
        basketService = new BasketService();
        orderService = new OrderService();
    }

    public void goToShop() throws ProductNotCorrectlyException, IncorrectDateException, createNewOrderException {

        Customer customer = personService.getNewCustomer("Sasha","89996690413");
        Basket basket = basketService.createNewBasket();

        basketService.addProductToBasket(basket, NameProduct.IPHONE_X);
        basketService.addProductToBasket(basket,NameProduct.MI_BAND_6);
        basketService.addProductToBasket(basket,NameProduct.IPHONE_X);

        orderService.createNewOrder(customer,basket);

        System.out.println(orderService.getOrderDescription(customer));



    }
}
