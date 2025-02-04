package notify;

import order.Order;

public interface INotify {

    public void update(String message);
    public void update(String message, Order order);
}