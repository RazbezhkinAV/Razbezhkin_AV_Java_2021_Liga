package lesson5.service;

import lesson5.Basket;
import lesson5.exeption.NoSuchProductExistsException;
import lesson5.exeption.ProductNotCorrectlyException;
import lesson5.model.product.NameProduct;
import lesson5.model.product.Product;

import java.util.Map;

public class BasketService implements Service {

    public void addProductToBasket(Basket basket, NameProduct nameProduct) throws ProductNotCorrectlyException {
        if (!CatalogService.checkExistProductInCatalog(nameProduct)) {
            throw new ProductNotCorrectlyException(String.format("%s - нет в наличии", nameProduct));
        }

        Product product = CatalogService.findProductInCatalog(nameProduct);

        if (basket.isBasketContainsProduct(product)) {
            int quantity = basket.getProductQuantity(product);
            basket.putProduct(product, quantity + 1);
        } else {
            basket.putProduct(product, 1);
        }

    }

    public void removeProductToBasket(Basket basket, NameProduct nameProduct) throws ProductNotCorrectlyException, NoSuchProductExistsException {
        if (!CatalogService.checkExistProductInCatalog(nameProduct)) {
            throw new NoSuchProductExistsException(String.format("%s - Неверно выбран товар", nameProduct));
        }

        Product product = CatalogService.findProductInCatalog(nameProduct);
        if (basket.isBasketContainsProduct(product)) {
            int quantity = basket.getProductQuantity(product);

            if (quantity == 1)
                basket.removeProduct(product);
            else {
                basket.putProduct(product, quantity - 1);
            }
        } else {
            throw new ProductNotCorrectlyException(String.format("%s - отсутствует в вашей корзине", nameProduct));
        }
    }

    public String getBasketDescription(Basket basket) {
        StringBuilder sb = new StringBuilder();
        if (basket.isBasketEmpty())
            sb.append("Корзина пустая");
        else {
            sb.append("Товар в вашей корзине:\n");
            long total = 0L;
            for (Map.Entry<Product, Integer> entry :
                    basket.getBasketCustomer().entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                long currentTotal = product.getPrice() * quantity;
                sb.append("Название - " + product.toString())
                        .append(", колличество - " + quantity)
                        .append(", стоимость = " + (currentTotal) + "\n");
                total += currentTotal;
            }
            sb.append("Общая стоимость корзины составляет = " + total);
        }
        return sb.toString();
    }

}
