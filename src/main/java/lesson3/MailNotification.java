package lesson3;

import lesson3.resource.UserLanguage;

public class MailNotification implements Notification {

    private User user;
    private boolean hasAdvertisement;

    public MailNotification(User user, boolean hasAdvertisement) {
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    @Override
    public String getWelcomeText() {
        return "...email...";
    }

    @Override
    public String getByText() {
        return "...email...";
    }

    @Override
    public UserLanguage userCountry() {
        return user.getCounty();
    }


}
