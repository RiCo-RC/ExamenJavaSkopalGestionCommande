package product;

import transactionLogger.STransactionLogger;
import transactionLogger.TransactionLogger;

import java.util.ArrayList;
import java.util.List;

public class Product {

    //-- VARIABLES --\\

    private int id;
    private String name;
    private double price;
    private String category;
    private int quantity;

    private final TransactionLogger logger = STransactionLogger.getInstance();

    //-- CONSTRUCTOR --\\

    public Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
        this.quantity = builder.quantity;
    }

    //-- SETTERS & GETTERS --\\

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        //return String.format("%.2f€", this.price).replaceAll("\\.00", "");
        return this.price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    //-- METHODS --\\

    public Product display() {
        System.out.println(this);
        return this;
    }

    public void log() {
        this.logger.logAddedProduct(this);
    }

    //-- DESIGN PATTERN: BUILDER --\\

    public static class ProductBuilder implements IProduct {

        //-- VARIABLES --\\

        private static int idAutoIncrement = 1;
        private int id;
        private String name;
        private double price;
        private String category;
        private int quantity;

        //-- CONSTRUCTOR --\\

        public ProductBuilder(){
            this.id = idAutoIncrement++;
        }

        //-- SETTERS --\\

        public ProductBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        //-- METHODS --\\

        public Product build() {
            return new Product(this);
        }
    }

    //-- TO STRING --\\

    @Override
    public String toString() {
        String quantityStringFormat = this.quantity > 1 ? "unités" : "unité";
        return "Produit n°" + this.id + " : " +
                this.name + " | Prix : " +
                this.price + " | Catégorie : " +
                this.category + " | Stock : " +
                this.quantity + " " + quantityStringFormat + ".";
    }

}