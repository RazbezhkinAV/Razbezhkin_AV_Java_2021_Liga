package lesson5.service;

import lesson5.exeption.createNewOrderException;
import lesson5.model.Basket;
import lesson5.model.Order;
import lesson5.model.person.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderService implements Service {
    private final BasketService basketService = new BasketService();
    private final Map<Integer, Order> orderHashMap = new HashMap<>();

    public void createNewOrder(Customer customer, Basket basket) throws createNewOrderException {
        if (customer == null)
            throw new createNewOrderException("Для оформления заказа необходимо зарегистрироваться!");

        if (basket.isBasketEmpty())
            throw new createNewOrderException("Невохможно создать заказ!\n Корзина пустая!");

        orderHashMap.put(customer.getId(), new Order(customer, basket));
    }

    public String getOrderDescription(Customer customer) {
        Order order = orderHashMap.get(customer.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ оформлен на " + order.getCustomer().getName() + "\n")
                .append("Курьер свяжется с вами по номеру :" + order.getCustomer().getPhone() +"\n")
                .append(basketService.getBasketDescription(order.getBasket()));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderService)) return false;
        OrderService that = (OrderService) o;
        return Objects.equals(basketService, that.basketService) && Objects.equals(orderHashMap, that.orderHashMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketService, orderHashMap);
    }
}
