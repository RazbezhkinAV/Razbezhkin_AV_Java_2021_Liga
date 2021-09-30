package lesson3.factory;

import lesson3.Notification;
import lesson3.PhoneNotification;
import lesson3.User;

public class PhoneNotificationFactory implements NotificationFactory {
    public Notification makeNotification(User user) {
        return new PhoneNotification(user);
    }
}
