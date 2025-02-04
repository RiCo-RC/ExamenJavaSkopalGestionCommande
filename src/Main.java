import customer.Customer;
import manager.*;
import notify.ONotifyOrder;
import order.EOrderStatus;
import order.Order;
import payment.EPaymentMethod;
import payment.FPaymentMethod;
import payment.IPaymentMethod;
import product.Product;
import transactionLogger.STransactionLogger;
import transactionLogger.TransactionLogger;

public class Main {
    public static void main(String[] args) {

        //-- CREATE PRODUCTS --\\

        Product macbookAirM2 = new Product.ProductBuilder()
                .setName("Apple MackBook Air M2")
                .setPrice(1299)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();
                //.display();
        Product ps5 = new Product.ProductBuilder()
                .setName("PlayStation 5")
                .setPrice(499.99)
                .setCategory("Console")
                .setQuantity(200)
                .build();
        Product xboxSeriesX = new Product.ProductBuilder()
                .setName("Xbox Series X")
                .setPrice(499.99)
                .setCategory("Console")
                .setQuantity(200)
                .build();
        Product samsungGalaxyS23 = new Product.ProductBuilder()
                .setName("Samsung Galaxy S23")
                .setPrice(959.99)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();
        Product airPodsPro2 = new Product.ProductBuilder()
                .setName("AirPods Pro 2")
                .setPrice(299)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();

        Product createSite = new Product.ProductBuilder()
                .setName("Creation de site")
                .setPrice(1000)
                .setCategory("Service")
                .setQuantity(100)
                .build();
        Product hostingCloud = new Product.ProductBuilder()
                .setName("Hébergement cloud")
                .setPrice(10)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product upkeepAndupdate = new Product.ProductBuilder()
                .setName("Maintenance et mise à jour de logiciels")
                .setPrice(100)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product createApp = new Product.ProductBuilder()
                .setName("Creation d'app mobile")
                .setPrice(5000)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product cybersecurity = new Product.ProductBuilder()
                .setName("Consultation en cybersécurité")
                .setPrice(500)
                .setCategory("Service")
                .setQuantity(10)
                .build();

        //-- CREATE PAYMENT METHODS --\\

        IPaymentMethod creditsCards = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CREDIT_CARD);
        IPaymentMethod paypal = FPaymentMethod.createMeansOfPayment(EPaymentMethod.PAYPAL);
        IPaymentMethod cryptocurrency = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CRYPTOCURRENCY);

        //-- CREATE CUSTOMERS --\\

        Customer rico = new Customer("RiCo", 2000);
        Customer lip = new Customer("Lip", 100);

        //-- CREATE ORDER --\\

        Order rico_order = new Order.OrderBuilder()
                .setCustomer(rico)
                .addProduct(ps5, 1)
                .addProduct(xboxSeriesX, 1)
                .addProduct(hostingCloud, 1)
                .setStatus(EOrderStatus.WAITING)
                .build();
        Order lip_order = new Order.OrderBuilder()
                .setCustomer(lip)
                .addProduct(samsungGalaxyS23, 1)
                .addProduct(airPodsPro2, 1)
                .setStatus(EOrderStatus.WAITING)
                .build();

        //-- CREATE AND ATTACH OBSERVER --\\

        ONotifyOrder general_observer = new ONotifyOrder();
        ONotifyOrder rico_observer = new ONotifyOrder();
        ONotifyOrder lip_observer = new ONotifyOrder();

        general_observer.addObserver(rico);
        rico_observer.addObserver(rico);

        general_observer.addObserver(lip);
        lip_observer.addObserver(lip);

        //-- VALIDATE ORDER (CHAIN OF RESPONSABILITY) --\\

        IOrderValidation stockCheck = new OrderStepStockCheck();
        IOrderValidation paymentCheck = new OrderStepPaymentCheck();
        IOrderValidation orderDispatch = new OrderStepDispatch();

        stockCheck.setNext(paymentCheck);
        paymentCheck.setNext(orderDispatch);

        OrderStep rico_step = new OrderStep(EOrderStep.STOCK_CHECK, rico, rico_order);
        OrderStep lip_step = new OrderStep(EOrderStep.STOCK_CHECK, lip, lip_order);

        //-- LOG --\\

        TransactionLogger logger = STransactionLogger.getInstance();
        TransactionLogger anotherLogger = STransactionLogger.getInstance();

        //-- DISPLAY --\\

        System.out.println("---------- TEST ----------");

        ps5.display();
        xboxSeriesX.display();

        rico.display();
        lip.display();

        System.out.print("\n");

        general_observer.notify("Votre commande à bien été reçu !");
        rico_observer.notify("Votre commande est en cours de préparation !");
        lip_observer.notify("Votre commande est en attende de produits disponible !");

        System.out.print("\n");

        System.out.println("Avant : " + rico.getFunds());
        creditsCards.pay(rico, rico_order, rico_order.getTotalPrice());
        System.out.println("Après : " + rico.getFunds());

        System.out.print("\n");

        stockCheck.handleOrder(rico_step);
        stockCheck.handleOrder(lip_step);

        System.out.print("\n");

        rico_step.display();
        lip_step.display();

        logger.log("Hey sd!");

        System.out.print("\n");
        System.out.print("\n");

        //-- LOG --\\

        System.out.println("---------- LOG ----------");

        ps5.log();
        xboxSeriesX.log();
        rico.log();
        lip.log();
        rico_order.log();
        lip_order.log();
    }
}

