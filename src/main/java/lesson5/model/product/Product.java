package lesson5.model.product;

public abstract class Product {
    private NameProduct productName;
    private long price;

    public Product(NameProduct productName, long price) {
        this.productName = productName;
        this.price = price;
    }


    public NameProduct getProductName() {
        return productName;
    }

    public long getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return productName.toString();
    }

}
