package lesson3;

import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserLanguage county;
    //String local = Locale.getDefault().getLanguage();

    public UserLanguage getCounty() {
        return county;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public User(Long id, String name, String email, String phone, String county) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.county = Source.getCountry(county);
    }
}
