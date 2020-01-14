package by.training.payment.servise;

import by.training.payment.entity.Payment;
import by.training.payment.servise.exeception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private final Parser parser = new Parser();

    public Payment getPayment() throws ServiceException {
        return parser.parseFromFile();
    }

    public double totalPrice(Payment payment) {
        double totalPrice = 0;
        for (Payment.Product p : payment.getProducts()
        ) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public List<String> productList() throws ServiceException {
        List<String> list = new ArrayList<>();
        for (Payment.Product p : parser.parseFromFile().getProducts()
        ) {
            list.add(p.getProductName());
        }
        return list;
    }

}
