package service;

import exeption.IncorrectDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonServiceTest {

    private final CreatePersonService personService = new CreatePersonService();

    @Test
    @DisplayName("Checking for exception on create customer whit invalid name")
    void getNewCustomer_invalidName_Exception() {
        String failName = "Вася";
        String numberPhone = "89996690413";
        assertThrows(IncorrectDateException.class,  ()->{
            personService.getNewCustomer(failName,numberPhone);
        });
    }

    @Test
    @DisplayName("Checking for exception on create customer whit invalid number phone")
    void getNewCustomer_invalidPhone_Exception() {
        String name = "Sasha";
        String failNumberPhone = "911";
        assertThrows(IncorrectDateException.class,  ()->{
            personService.getNewCustomer(name,failNumberPhone);
        });
    }
}