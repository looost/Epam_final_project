package by.training.payment.entity;


import java.util.ArrayList;
import java.util.List;

public class Payment {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public boolean addProduct(String productName, double price) {
        return this.products.add(new Product(productName, price));
    }

    public boolean removeProduct(String productName) {
        for (Product e : products
        ) {
            if (e.productName.equalsIgnoreCase(productName)) {
                this.products.remove(e);
                return true;
            }
        }
        return false;
    }

    public class Product {
        private String productName;
        private double price;

        private Product(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Product product = (Product) o;

            if (Double.compare(product.price, price) != 0) return false;
            return productName != null ? productName.equals(product.productName) : product.productName == null;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = productName != null ? productName.hashCode() : 0;
            temp = Double.doubleToLongBits(price);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "productName='" + productName + '\'' +
                    ", price=" + price +
                    '}';
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
                "products=" + products +
                '}';
    }
}
