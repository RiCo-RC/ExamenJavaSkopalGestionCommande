package payment;

public class FPaymentMethod {

    //-- METHODS --\\

    private static IPaymentMethod getPaymentMethod(EPaymentMethod type) {
        return switch (type) {
            case EPaymentMethod.PAYPAL -> new Paypal();
            case EPaymentMethod.CRYPTOCURRENCY -> new Cryptocurrency();
            case EPaymentMethod.CREDIT_CARD -> new CreditCard();
        };
    }

    public static IPaymentMethod createMeansOfPayment(EPaymentMethod type) {
        return getPaymentMethod(type);
    }
}
