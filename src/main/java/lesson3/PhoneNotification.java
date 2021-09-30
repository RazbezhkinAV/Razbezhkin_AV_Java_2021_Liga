package lesson3;

public class PhoneNotification implements Notification {

    private User user;
    private String message;

    public PhoneNotification(User user) {
        this.user = user;
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
