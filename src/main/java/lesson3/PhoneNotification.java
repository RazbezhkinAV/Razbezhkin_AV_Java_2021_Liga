package lesson3;

import lesson3.resource.UserLanguage;

public class PhoneNotification implements Notification {

    private User user;

    public PhoneNotification(User user) {
        this.user = user;
    }

    @Override
    public String getWelcomeText() {
        return "...phone...";
    }

    @Override
    public String getByText() {
        return "...phone...";
    }

    @Override
    public UserLanguage userCountry() {
        return user.getCounty();
    }

}
