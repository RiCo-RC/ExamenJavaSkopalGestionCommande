package customer;

import notify.INotify;
import order.Order;
import transactionLogger.STransactionLogger;
import transactionLogger.TransactionLogger;

public class Customer implements INotify {

    //-- VARIABLES --\\

    private static int idAutoIncrement = 1;
    private int id;
    private String name;
    private double funds;

    private final TransactionLogger logger = STransactionLogger.getInstance();

    //-- CONSTRUCTOR --\\

    public Customer(String name, double funds) {
        this.id = idAutoIncrement++;
        this.name = name;
        this.funds = funds;
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

    public void setFunds(double funds) {
        this.funds = funds >= 0 ? funds : 0;
    }

    public double getFunds() {
        return this.funds;
    }

    //-- METHODS --\\

    public Customer display() {
        //System.out.println(this.name);
        System.out.println(this);
        return this;
    }

    public void log() {
        this.logger.logRegisterCustomer(this);
    }

    //-- METHODS FROM INTERFACE --\\

    @Override
    public void update(String message) {
        System.out.println("Notification envoyé au client " +
                this.name + " : " + message);
    }

    @Override
    public void update(String message, Order order) {
        System.out.println("Notification envoyé au client " +
                this.name + " pour la commande n°" +
                order.getId() + " : \n" +
                "- Nouveau statut : " + order.getStatus());
    }

    //-- TO STRING --\\

    @Override
    public String toString() {
        return "Client n°" + this.id + " : " +
                this.name + " | Argent : " +
                this.funds + "€.";
    }
}
