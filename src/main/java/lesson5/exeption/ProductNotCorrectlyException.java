package lesson5.exeption;

public class ProductNotCorrectlyException extends Exception{
    public ProductNotCorrectlyException(String message) {
        super(message);
    }
}
