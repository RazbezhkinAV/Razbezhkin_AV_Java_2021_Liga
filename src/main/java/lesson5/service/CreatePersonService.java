package lesson5.service;

import lesson5.exeption.IncorrectDateException;
import lesson5.model.person.Customer;
import lesson5.model.person.Person;

public class CreatePersonService implements Service {

    public Person getNewCustomer(String name, String phone, long cash) throws IncorrectDateException {
        String nameCustomer;
        String phoneCustomer;
        long cashCustomer;

        if(isNameCorrect(name))
            nameCustomer = name;
        else
            throw new IncorrectDateException(String.format("%s - введено не верно",name));

        if (isPhoneCorrect(phone))
            phoneCustomer = name;
        else
            throw new IncorrectDateException(String.format("%s - введено не верно",phone));

        if (isCashCorrect(cash))
            cashCustomer = cash;
        else
            throw new IncorrectDateException(String.format("%s - не может быть отрицательный",cash));

        return new Customer(nameCustomer,phoneCustomer,cashCustomer);
    }



    private static boolean isNameCorrect(String name) {
        String regex = "^[a-z0-9_-]{3,15}$";
        return name.matches(regex);
    }

    private static boolean isPhoneCorrect(String phone) {
        String regex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        return phone.matches(regex);
    }

    private static boolean isCashCorrect(long cash) {
        return cash >= 0;
    }

}
