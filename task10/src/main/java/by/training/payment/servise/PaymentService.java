package by.training.payment.servise;

import by.training.payment.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    public double totalPrice(Payment payment) {
        double totalPrice = 0;
        for (Payment.Product p : payment.getProducts()
        ) {
            totalPrice += p.getProduct().getPrice();
        }
        return totalPrice;
    }

    public List<String> productList(Payment payment) {
        List<String> list = new ArrayList<>();
        for (Payment.Product p : payment.getProducts()
        ) {
            list.add(p.getProductName());
        }
        return list;
    }

}
