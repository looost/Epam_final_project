package by.training.payment.view;

import by.training.payment.entity.Payment;
import by.training.payment.entity.productenum.ProductEnum;

import java.util.List;

public class ViewPayment {

    public void availableProduct() {
        System.out.println("Товары в наличие: ");
        System.out.println("-----------------------");
        for (ProductEnum e : ProductEnum.values()
        ) {
            System.out.println(e.name() + " Стоимость: " + e.getPrice());
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
