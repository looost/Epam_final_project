package by.training.payment.entity;

import java.util.ArrayList;
import java.util.List;

public class Market {
    private List<Product> productList;

    public Market() {
        this.productList = new ArrayList<>();
    }

    public Market(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public boolean addProduct(Product product) {
        return this.productList.add(product);
    }

    public boolean removeProduct(Product product) {
        return this.productList.remove(product);
    }
}
