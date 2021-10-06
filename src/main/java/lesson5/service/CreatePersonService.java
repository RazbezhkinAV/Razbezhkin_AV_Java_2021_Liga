package lesson5.service;

import lesson5.exeption.IncorrectDateException;
import lesson5.model.person.Customer;

public class CreatePersonService implements Service {

    public Customer getNewCustomer(String name, String phone) throws IncorrectDateException {
        String nameCustomer;
        String phoneCustomer;

        if (isNameCorrect(name))
            nameCustomer = name;
        else
            throw new IncorrectDateException(String.format("%s - введено не верно", name));

        if (isPhoneCorrect(phone))
            phoneCustomer = phone;
        else
            throw new IncorrectDateException(String.format("%s - введено не верно", phone));

        return new Customer(nameCustomer, phoneCustomer);
    }

    private static boolean isNameCorrect(String name) {
        if (name == null) return false;
        String regex = "^[A-Za-z]{3,15}$";
        return name.matches(regex);
    }

    private static boolean isPhoneCorrect(String phone) {
        String regex = "^((8|\\+7)?)[0-9]{10}$";
        return phone.matches(regex);
    }

    private static boolean isCashCorrect(long cash) {
        return cash >= 0;
    }

}
