package lesson5.model.product;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return price == product.price && productName == product.productName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price);
    }
}
