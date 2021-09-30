package lesson3;

public class MailNotification implements Notification {

    private User user;
    private boolean hasAdvertisement;
    private String message;

    public MailNotification(User user, boolean hasAdvertisement) {
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String sendNotification() {
        return message;
    }

}
