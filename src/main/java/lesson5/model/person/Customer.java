package lesson5.model.person;

public class Customer extends Person {
    private String phone;
    private long cash;
    private static int id = 1;

    public Customer(String name, String phone, long cash) {
        super(id, name);
        this.phone = phone;
        this.cash = cash;
        id++;
    }

    public String getPhone() {
        return phone;
    }

    public long getCash() {
        return cash;
    }

    public int getId() {
        return super.getId();
    }
}
