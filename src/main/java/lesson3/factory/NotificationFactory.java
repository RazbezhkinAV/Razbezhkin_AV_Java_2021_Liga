package lesson3.factory;

import lesson3.Notification;
import lesson3.User;

public interface NotificationFactory {
    Notification makeNotification(User user);
}
