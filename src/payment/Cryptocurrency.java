package payment;

import customer.Customer;
import order.Order;

public class Cryptocurrency implements IPaymentMethod {

    //-- METHODS FROM INTERFACE --\\

    @Override
    public void pay(Customer customer, Order order, double amount) {
        customer.setFunds(customer.getFunds() - amount);
        // Here you can check whether the customer has enough funds
        System.out.println(customer.getName() +
                " a effectué(e) un paiement de " +
                amount + "€ par cryptomonnaie pour la commande n°" +
                order.getId() + ".");
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    //-- TO STRING --\\

    @Override
    public String toString() {
        return "Méthode de payement utilisé : Cryptomonnaie.";
    }
}
