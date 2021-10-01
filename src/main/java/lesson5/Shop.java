package lesson5;

import lesson5.exeption.ProductNotCorrectlyException;
import lesson5.model.person.Customer;
import lesson5.model.product.NameProduct;
import lesson5.service.BasketService;
import lesson5.service.CatalogService;

public class Shop {
    public static void main(String[] args) throws ProductNotCorrectlyException {
        Customer customer = new Customer(1,"Alex",120000);

        Basket basket = new Basket();

        BasketService basketService = new BasketService();

        basketService.addProductToBasket(basket,NameProduct.IPHONE_X);
        basketService.addProductToBasket(basket,NameProduct.MI_BAND_6);
        basketService.addProductToBasket(basket,NameProduct.IPHONE_X);

        System.out.println(basketService.getBasketDescription(basket));
    }
}
