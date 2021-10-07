package model.person;

public class Customer extends Person {
    private String phone;
    private static int id = 1;

    public Customer(String name, String phone) {
        super(id, name);
        this.phone = phone;
        id++;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return super.getId();
    }
}
