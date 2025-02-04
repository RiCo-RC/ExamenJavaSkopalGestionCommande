package manager;

public interface IOrderValidation {

    //-- METHODS --\\

    public void setNext(IOrderValidation next);
    public void handleOrder(OrderStep order);
}
