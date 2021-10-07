import exeption.IncorrectDateException;
import exeption.ProductNotCorrectlyException;
import exeption.CreateNewOrderException;
import model.Basket;
import model.Catalog;
import model.person.Customer;
import model.product.NameProduct;
import service.BasketService;
import service.CatalogService;
import service.CreatePersonService;
import service.OrderService;

public class Controller {
    private final CreatePersonService personService = new CreatePersonService();
    private final CatalogService catalogService = new CatalogService(new Catalog());
    private final BasketService basketService = new BasketService(catalogService);
    private final OrderService orderService = new OrderService();


    public void goToShop() throws ProductNotCorrectlyException, IncorrectDateException, CreateNewOrderException {
        fillingCatalog();
        Customer customer = personService.getNewCustomer("Sasha", "89996690413");
        Basket basket = basketService.createNewBasket();

        basketService.addProductToBasket(basket, NameProduct.IPHONE_X);

        orderService.createNewOrder(customer, basket);

        System.out.println(orderService.getOrderDescription(customer,basketService.getBasketDescription(basket)));


    }

    private void fillingCatalog() {
        catalogService.addProductInCatalog(NameProduct.IPHONE_X);
        catalogService.addProductInCatalog(NameProduct.GALAXY_TABLE_A);
        catalogService.addProductInCatalog(NameProduct.MI_BAND_6);
    }
}
