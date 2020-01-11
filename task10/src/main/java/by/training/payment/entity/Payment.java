package by.training.payment.entity;

import by.training.payment.entity.productenum.ProductEnum;
import by.training.payment.exception.PaymentException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Payment {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(String productName) throws PaymentException {
        try {
            this.products.add(new Product(ProductEnum.valueOf(productName.toUpperCase())));
        } catch (IllegalArgumentException e) {
            throw new PaymentException("Такого товара в магазине нет", e);
        }

    }

    public boolean removeProduct(String productName) {
        for (Product e : products
        ) {
            if (e.productName.name().equalsIgnoreCase(productName)) {
                this.products.remove(e);
                return true;
            }
        }
        return false;
    }

    public class Product {
        private ProductEnum productName;

        public Product(ProductEnum productName) {
            this.productName = productName;
        }

        public ProductEnum getProduct() {
            return productName;
        }

        public String getProductName() {
            return productName.name().toLowerCase();
        }

        public void setProductName(ProductEnum productName) {
            this.productName = productName;
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return products != null ? products.equals(payment.products) : payment.products == null;
    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "goods=" + products +
                '}';
    }
}
