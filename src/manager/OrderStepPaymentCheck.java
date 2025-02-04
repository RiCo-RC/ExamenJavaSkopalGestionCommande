package manager;

import order.EOrderStatus;

public class OrderStepPaymentCheck implements IOrderValidation {

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
        if (order.getStep() == EOrderStep.PAYMENT_CHECK) {
            if (order.getCustomerFunds() < order.getOrderTotalPrice()) {
                System.out.println("Commande n°" + order.getOrderId() + " : " +
                        "Etape de vérification : Payement | " +
                        " Succès : Erreur | " +
                        " Commentaire : Manque de fonds !");
                order.setOrderStatus(EOrderStatus.CANCELLED);
                canContinue = false;
            } else {
                System.out.println("Commande n°" + order.getOrderId() + " : " +
                        "Etape de vérification : Payement | " +
                        " Succès : Bon | " +
                        " Commentaire : Le payment a été accepté !");
                order.setOrderStatus(EOrderStatus.IN_PROGRESS);
            }


        }
        if (canContinue && this.next != null) {
            order.setStep(EOrderStep.ORDER_DISPATCH);
            this.next.handleOrder(order);
        }
    }


}
