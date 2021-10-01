package lesson5.model.person;

import lesson5.Basket;
import lesson5.Order;

public class Customer extends Person {
    private Basket basket;
    private long cash;


    public Customer(long id, String name, long cash) {
        super(id, name);
        this.cash = cash;
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    public long getCash() {
        return cash;
    }

}
