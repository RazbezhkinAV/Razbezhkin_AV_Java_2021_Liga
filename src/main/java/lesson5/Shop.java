package lesson5;

import lesson5.exeption.IncorrectDateException;
import lesson5.exeption.NoSuchProductExistsException;
import lesson5.exeption.ProductNotCorrectlyException;
import lesson5.exeption.createNewOrderException;


public class Shop {
    public static void main(String[] args) throws ProductNotCorrectlyException, NoSuchProductExistsException, IncorrectDateException, createNewOrderException {

        Controller controller = new Controller();

        controller.goToShop();
    }
}
