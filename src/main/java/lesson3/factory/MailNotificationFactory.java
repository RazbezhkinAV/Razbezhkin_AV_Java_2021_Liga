package lesson3.factory;

import lesson3.MailNotification;
import lesson3.Notification;
import lesson3.User;

public class MailNotificationFactory implements NotificationFactory {
    public Notification makeNotification(User user) {
        return new MailNotification(user, hasAdv(user));
    }
    private boolean hasAdv(User user) {
        return user.getId() % 2 == 0;
    }
}
