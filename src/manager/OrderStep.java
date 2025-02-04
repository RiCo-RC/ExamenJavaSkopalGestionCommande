package manager;

import customer.Customer;
import order.EOrderStatus;
import order.Order;
import product.Product;

import java.util.Map;

public class OrderStep {

    //-- VARIABLES --\\

    private EOrderStep step;
    private Customer customer;
    private Order order;
    private String conclusion = "";

    //-- CONSTRUCTOR --\\

    public OrderStep(EOrderStep step, Customer customer, Order order) {
        this.step = step;
        this.customer = customer;
        this.order = order;
    }

    //-- SETTERS & GETTERS --\\

    public void setStep(EOrderStep step) {
        this.step = step;
    }

    public EOrderStep getStep() {
        return this.step;
    }

    //-- GETTERS --\\

    public String getConclusion() {
        return this.conclusion;
    }

    //-- GETTERS FROM CUSTOMER CLASS --\\

    public double getCustomerFunds() {
        return this.customer.getFunds();
    }

    //-- GETTERS FROM ORDER CLASS --\\

    public Map<Product, Integer> getOrderProducts() {
        return this.order.getProducts();
    }

    public double getOrderTotalPrice() {
        return this.order.getTotalPrice();
    }

    public int getOrderId() {
        return this.order.getId();
    }

    //-- METHODS --\\

    public void display() {
        System.out.println(this.conclusion);
    }
}
