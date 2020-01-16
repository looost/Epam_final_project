package by.training.payment.entity;


import java.util.ArrayList;
import java.util.List;

public class Payment {

    private List<Purchase> purchases = new ArrayList<>();
    private double totalPrice = 0;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public boolean addProduct(Product product, int countOfProduct) {
        for (Purchase p : purchases
        ) {
            if (p.getProduct().getProductName().equalsIgnoreCase(product.getProductName())) {
                p.addCountOfProduct(countOfProduct);
                return true;
            }
        }
        return this.purchases.add(new Purchase(product, countOfProduct));
    }

    public boolean removeProduct(String productName, int countOfProduct) {
        for (Purchase e : this.purchases
        ) {
            if (e.product.getProductName().equalsIgnoreCase(productName)) {
                e.removeCountOfProduct(countOfProduct);
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice() {
        this.totalPrice = 0;
        for (Purchase p : purchases
        ) {
            this.totalPrice += (p.getProduct().getPrice() * p.countOfProduct);
        }
        return this.totalPrice;
    }

    public class Purchase {
        private Product product;
        private int countOfProduct;

        Purchase(Product product, int countOfProduct) {
            this.product = product;
            this.countOfProduct = countOfProduct;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getCountOfProduct() {
            return countOfProduct;
        }

        void addCountOfProduct(int countOfProduct) {
            this.countOfProduct += countOfProduct;
        }

        void removeCountOfProduct(int countOfProduct) {
            this.countOfProduct -= countOfProduct;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Purchase purchase = (Purchase) o;

            if (countOfProduct != purchase.countOfProduct) return false;
            return product != null ? product.equals(purchase.product) : purchase.product == null;
        }

        @Override
        public int hashCode() {
            int result = product != null ? product.hashCode() : 0;
            result = 31 * result + countOfProduct;
            return result;
        }

        @Override
        public String toString() {
            return "Purchase{" +
                    "product=" + product +
                    ", countOfProduct=" + countOfProduct +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return purchases != null ? purchases.equals(payment.purchases) : payment.purchases == null;
    }

    @Override
    public int hashCode() {
        return purchases != null ? purchases.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "products=" + purchases +
                '}';
    }
}
