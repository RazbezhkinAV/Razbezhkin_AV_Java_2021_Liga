package model;

import model.person.Customer;

public class Order {
    private Customer customer;
    private Basket basket;

    public Order(Customer customer, Basket basket) {
        this.customer = customer;
        this.basket = basket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Basket getBasket() {
        return basket;
    }
}
