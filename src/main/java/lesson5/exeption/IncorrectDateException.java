package lesson5.exeption;

public class IncorrectDateException extends Exception{
    public IncorrectDateException(String message) {
        super(message);
    }
}
