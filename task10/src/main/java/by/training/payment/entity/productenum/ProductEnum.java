package by.training.payment.entity.productenum;

public enum ProductEnum {
    COMPUTER(700),
    TV(500),
    REFRIGERATOR(591),
    WASHER(395),
    MILK(1.13),
    BUTTER(2.08),
    CURD(2.95),
    CHEESE(3.09),
    APPLE(0.76),
    BANANA(0.88),
    MANDARIN(0.12),
    PEAR(0.45);

    private double price;

    ProductEnum(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
