package by.training.payment.view;

import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;

class ViewPayment {

    void showMarket(Market market) {
        System.out.println("Товары в наличие: ");
        System.out.println("-----------------------");
        for (Product p : market.getProductList()
        ) {
            System.out.println(p.getProductName() + " стоимость: " + p.getPrice());
        }
        System.out.println("-----------------------");
    }

    void showMessage(String str) {
        System.out.println(str);
    }

    void showError(String str) {
        System.err.println(str);
    }

    void showBasket(Payment payment) {
        System.out.print("Товары в вашей корзине - ");
        payment.getPurchases()
                .forEach(purchase -> System.out.print(purchase.getProduct().getProductName() +
                        " - " + purchase.getCountOfProduct() + " шт., "));
        System.out.println();
        System.out.println("Общая цена покупки равна " + payment.getTotalPrice());
    }
}