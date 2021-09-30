package lesson3;

public interface Notification {
    User getUser();
    void setMessage(String message);
    String sendNotification();
}
