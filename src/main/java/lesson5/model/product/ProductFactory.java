package lesson5.model.product;

public class ProductFactory {

    public static Product createProduct(NameProduct nameProduct){
        switch (nameProduct){
            case IPHONE_X: return new Phone(nameProduct,80_000);
            case MI_BAND_6: return new Watch(nameProduct,5_000);
            case GALAXY_TABLE_A: return new Tablet(nameProduct,45_000);
        }
        return null;
    }
}
