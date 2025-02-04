package payment;

import customer.Customer;
import order.Order;

public class Paypal implements IPaymentMethod {

    //-- METHODS FROM INTERFACE --\\

    @Override
    public void pay(Customer customer, Order order, double amount) {
        System.out.println(customer.getName() +
                " a effectué(e) un paiement de " +
                amount + "€ par Paypal pour la commande n°" +
                order.getId() + ".");
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    //-- TO STRING --\\

    @Override
    public String toString() {
        return "Méthode de payement utilisé : Paypal.";
    }
}
