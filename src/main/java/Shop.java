import exeption.IncorrectDateException;
import exeption.NoSuchProductExistsException;
import exeption.ProductNotCorrectlyException;
import exeption.CreateNewOrderException;


public class Shop {
    public static void main(String[] args) throws ProductNotCorrectlyException, IncorrectDateException, CreateNewOrderException {

        Controller controller = new Controller();

        controller.goToShop();
    }
}
