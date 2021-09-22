package lesson5;

import lesson5.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {
    private List<Product> basketShop = new ArrayList<>();

    public void add(Product product) {
        if (product != null)
            basketShop.add(product);
        else
            System.out.println("Выберете продукт");
    }

    public void remove(Product product) {
        if (product != null && basketShop.contains(product))
            basketShop.remove(product);
        else
            System.out.println("Выберете продукт");
    }

    public long total() {
        return basketShop.stream().mapToLong(Product::getPrice).sum();
    }

    public void getBasketShop() {
        System.out.println(basketShop.toString());
    }

    public void buy() {
        System.out.println("В вашей карзине находится \n" + basketShop.toString()
                + "\nСумма заказа составляет = " + total() + " рублей."
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket basket)) return false;
        return Objects.equals(basketShop, basket.basketShop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketShop);
    }
}
