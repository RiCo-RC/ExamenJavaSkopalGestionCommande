package payment;

import customer.Customer;
import order.Order;

public interface IPaymentMethod {
    public void pay(Customer customer, Order order, double amount);
    public void display();
}
