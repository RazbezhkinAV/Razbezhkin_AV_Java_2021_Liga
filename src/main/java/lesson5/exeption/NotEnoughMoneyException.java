package lesson5.exeption;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException() {
        super("\nЗаказ не оформлен, у вас не хватает денег\n");
    }
}
