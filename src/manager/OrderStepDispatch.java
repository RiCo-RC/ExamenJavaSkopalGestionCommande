package manager;

public class OrderStepDispatch implements IOrderValidation {

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
        if (order.getStep() == EOrderStep.ORDER_DISPATCH) {
            System.out.println("Commande n°" + order.getOrderId() + " : " +
                    "Etape de vérification : Final | " +
                    " Succès : Bon | " +
                    " Tout est bon ! La commande est en cours de préparation !");
        }
        if (canContinue && this.next != null) this.next.handleOrder(order);
    }


}
