package lesson5.product;

import java.util.Objects;

public abstract class Product {
    private NameProduct productName;
    private long price;
    private Manufacturer manufacturer;

    public Product(NameProduct productName, long price, Manufacturer manufacturer) {
        this.productName = productName;
        this.price = price;
        this.manufacturer = manufacturer;
    }


    public NameProduct getProductName() {
        return productName;
    }

    public long getPrice() {
        return price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return manufacturer + " " + productName ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return price == product.price && productName == product.productName && manufacturer == product.manufacturer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price, manufacturer);
    }
}
