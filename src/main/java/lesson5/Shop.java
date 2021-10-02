package lesson5;

import lesson5.exeption.NoSuchProductExistsException;
import lesson5.exeption.ProductNotCorrectlyException;
import lesson5.model.Basket;
import lesson5.model.product.NameProduct;
import lesson5.service.BasketService;

public class Shop {
    public static void main(String[] args) throws ProductNotCorrectlyException, NoSuchProductExistsException {
        Basket basket = new Basket();

        BasketService basketService = new BasketService();

        basketService.addProductToBasket(basket,NameProduct.IPHONE_X);
        basketService.addProductToBasket(basket,NameProduct.MI_BAND_6);
        basketService.addProductToBasket(basket,NameProduct.IPHONE_X);

        System.out.println(basketService.getBasketDescription(basket));

        basketService.removeProductToBasket(basket,NameProduct.MI_BAND_6);

        System.out.println(basketService.getBasketDescription(basket));

    }
}
