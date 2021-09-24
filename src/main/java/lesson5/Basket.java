package lesson5;

import lesson5.product.NameProduct;
import lesson5.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> basketCustomer = new ArrayList<>();

    public void add(Product product) {
        if (product != null)
            basketCustomer.add(product);
        else
            System.out.println("Выберете продукт");
    }

    public void remove(Product product) {
        if (product != null && basketCustomer.contains(product))
            basketCustomer.remove(product);
        else
            System.out.println("Выберете продукт для удаления из корзины");
    }

    public long total() {
        return basketCustomer.stream().mapToLong(Product::getPrice).sum();
    }

    public void getBasketCustomer() {
        System.out.println(basketCustomer.toString());
    }

    public void getOrderInfo() {
        System.out.println("В вашей карзине находится \n" + basketCustomer.toString()
                + "\nСумма заказа составляет = " + total() + " рублей."
        );
    }

}
