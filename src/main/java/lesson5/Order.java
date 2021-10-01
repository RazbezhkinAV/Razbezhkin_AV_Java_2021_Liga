package lesson5;

import lesson5.exeption.NotEnoughMoneyException;
import lesson5.model.person.Customer;

public class Order {
    private Basket basket;

    public Order(Basket basket) {
        this.basket = basket;
    }

//    public String createOrder(Customer customer) {
//
//        long sumBasket = basket.total();
//
//        if (sumBasket <= customer.getCash()) {
//            return "\nЗаказ успешно оформлен\n";
//        } else {
//            try {
//                throw new NotEnoughMoneyException();
//            } catch (NotEnoughMoneyException e) {
//              return e.getMessage();
//            }
//        }
//    }
}
