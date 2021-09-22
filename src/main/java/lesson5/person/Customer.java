package lesson5.person;

import lesson5.Basket;

public class Customer extends Person{
    private Basket basket;

    public Customer(long id, String name) {
        super(id, name);
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }
}
