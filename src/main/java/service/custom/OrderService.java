package service.custom;

import dto.Order;

public interface OrderService {
    Boolean placeOrder(Order order);
}
