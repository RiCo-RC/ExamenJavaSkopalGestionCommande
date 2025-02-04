package manager;

import order.EOrderStatus;
import product.Product;

import java.util.Map;

public class OrderStepStockCheck implements IOrderValidation {

    //-- VARIABLES --\\

    public IOrderValidation next = null;

    //-- METHODS FROM INTERFACE --\\

    @Override
    public void setNext(IOrderValidation next) {
        this.next = next;
    }

    @Override
    public void handleOrder(OrderStep order) {
        boolean canContinue = true;
        if (order.getStep() == EOrderStep.STOCK_CHECK) {
            for (Map.Entry<Product, Integer> cmd : order.getOrderProducts().entrySet()) {
                Product product = cmd.getKey();
                int orderQuantity = cmd.getValue();
                if (product.getQuantity() < orderQuantity) {
                    System.out.println("Commande n°" + order.getOrderId() + " : " +
                            "Etape de vérification : Stock | " +
                            " Succès : Erreur | " +
                            " Commentaire : Un ou plusieurs produits non pas assez de stock !");
                    order.setOrderStatus(EOrderStatus.CANCELLED);
                    canContinue = false;
                } else {
                    System.out.println("Commande n°" + order.getOrderId() + " : " +
                            "Etape de vérification : Stock | " +
                            " Succès : Bon | " +
                            " Commentaire : Les produits sont en stock !");
                    order.setOrderStatus(EOrderStatus.IN_PROGRESS);
                }
            }
        }
        if (canContinue && this.next != null) {
            order.setStep(EOrderStep.PAYMENT_CHECK);
            this.next.handleOrder(order);
        }
    }


}
