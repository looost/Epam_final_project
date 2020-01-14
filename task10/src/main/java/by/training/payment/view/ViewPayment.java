package by.training.payment.view;

import by.training.payment.entity.Payment;

import java.util.List;

public class ViewPayment {

    public void availableProduct(Payment payment) {
        System.out.println("Товары в наличие: ");
        System.out.println("-----------------------");
        for (Payment.Product p : payment.getProducts()
        ) {
            System.out.println(p.getProductName() + " Стоимость: " + p.getPrice());
        }
        System.out.println("-----------------------");
    }

    public void showMessage(String str) {
        System.out.println(str);
    }

    public void showBasket(Payment payment, double price) {
        System.out.print("Товары в вашей корзине - ");
        payment.getProducts()
                .forEach(product -> System.out.print(product.getProductName() + ", "));
        System.out.println();
        System.out.println("Общая цена покупки равна " + price);
    }

    public void showProductList(List<String> productList) {
        System.out.println("Список всех покупок: ");
        productList.forEach(System.out::println);
    }
}
