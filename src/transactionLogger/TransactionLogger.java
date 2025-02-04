package transactionLogger;

import customer.Customer;
import order.Order;
import product.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    //-- VARIABLES --\\

    // Ability to add a variable that formats date and time --> [00:00:00 to 00/00/0000]

    //-- METHODS --\\

    public void log(String message) {
        // Possibility of adding a variable that retrieves the current date and time and adapts it according to the variable in the class defined above.
        System.out.println("[" + "] >>> " + message);
    }

    //-- METHODS RELATED TO ORDER CLASS --\\

    public void logCreateOrder(Order order) {
        log("Nouvelle commande crée => Commande n°" + order.getId() + " | Statut : " + order.getStatus() +  " | Prix : "  + order.getTotalPrice() + "€.");
    }

    public void logUpdateStatusOrder(Order order) {
        log("Mise à jour pour la commande n°" + order.getId() + " => Nouveau statut : " + order.getStatus());
    }

    //-- METHODS RELATED TO CUSTOMER CLASS --\\

    public void logRegisterCustomer(Customer customer) {
        log("Nouveau client enregistré => Client n°" + customer.getId() + " | Nom : " + customer.getName() + ".");
    }

    //-- METHODS RELATED TO PRODUCT CLASS --\\

    public void logAddedProduct(Product product) {
        String quantityStringFormat = product.getQuantity() > 1 ? "unités" : "unité";
        log("Nouveau produit ajouté => Produit n°" + product.getId() + " | Nom : " + product.getName() + " | Prix : " + product.getPrice() + "€ | Quantité : " + product.getQuantity() + " " + quantityStringFormat + ".");
    }
}