package order;

import customer.Customer;
import product.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {

    //-- VARIABLES --\\

    private int id;
    private Map<Product, Integer> products;
    private double totalPrice;
    private String status;
    private Customer customer;

    //-- CONSTRUCTOR --\\

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.products = builder.products;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.customer = builder.customer;
    }

    //-- SETTERS & GETTERS --\\

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    //-- GETTERS --\\

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    //-- DESIGN PATTERN: BUILDER --\\

    public static class OrderBuilder implements IOrder {

        //-- VARIABLES --\\

        private static int idAutoIncrement = 1;
        private int id;
        private Map<Product, Integer> products = new HashMap<Product, Integer>();
        private double totalPrice;
        private String status = EOrderStatus.WAITING.toString();
        private Customer customer;

        //-- CONSTRUCTOR --\\

        public OrderBuilder() {
            this.id = idAutoIncrement++;
        }

        //-- SETTERS --\\

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public OrderBuilder setStatus(EOrderStatus status) {
            this.status = this.getStatusToString(status);
            return this;
        }

        //-- METHODS --\\

        public OrderBuilder addProduct(Product product, int quantity) {
            if (quantity > 0 && quantity <= product.getQuantity()) {
                this.products.put(product, quantity);
                //double price = Double.parseDouble(product.getPrice());
                double price = product.getPrice();
                this.totalPrice += price * quantity;
            }
            return this;
        }

        public String getStatusToString(EOrderStatus status) {
            if (status == EOrderStatus.IN_PROGRESS) {
                return "En cours";
            } else if (status == EOrderStatus.DELIVERED) {
                return "Livrée";
            } else if (status == EOrderStatus.CANCELLED) {
                return "Annulée";
            }
            return "En attende";
        }

        public Order build() {
            return new Order(this);
        }
    }

}
