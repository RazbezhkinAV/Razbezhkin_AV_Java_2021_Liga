package service;

import exeption.IncorrectDateException;
import exeption.CreateNewOrderException;
import model.Basket;
import model.Order;
import model.person.Customer;

import java.util.HashMap;
import java.util.Map;

public class OrderService implements Service {
    private final Map<Integer, Order> orderHashMap = new HashMap<>();

    public void createNewOrder(Customer customer, Basket basket) throws CreateNewOrderException {
        if (customer == null)
            throw new CreateNewOrderException("Для оформления заказа необходимо зарегистрироваться!");

        if (basket.isBasketEmpty())
            throw new CreateNewOrderException("Невохможно создать заказ!\n Корзина пустая!");

        orderHashMap.put(customer.getId(), new Order(customer, basket));
    }

    public String getOrderDescription(Customer customer, String basketDescription) {
        Order order = orderHashMap.get(customer.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ оформлен на " + order.getCustomer().getName() + "\n")
                .append("Курьер свяжется с вами по номеру :" + order.getCustomer().getPhone() + "\n")
                .append(basketDescription);
        return sb.toString();
    }

    public Order findOrder(Customer customer) throws IncorrectDateException {
        if(orderHashMap.get(customer.getId()) == null)
            throw new IncorrectDateException(String.format("Заказ для пользователя %s отсутствует",customer.getName()));
        return orderHashMap.get(customer.getId());
    }

    public boolean OrdersIsEmpty(){
        return orderHashMap.isEmpty();
    }
}
