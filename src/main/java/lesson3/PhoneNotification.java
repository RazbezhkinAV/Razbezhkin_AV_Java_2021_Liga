package lesson3;

import lesson3.decorator.NotificationDecorator;
import lesson3.resource.NotificationType;
import lesson3.resource.UserLanguage;

public class PhoneNotification implements Notification {

    private User user;

    public PhoneNotification(User user) {
        this.user = user;
    }

    @Override
    public UserLanguage userLanguage() {
        return user.getLanguage();
    }

    @Override
    public String sendNotification(NotificationType notificationType) {
        return user.getLanguage().name() + ": " + new NotificationDecorator(this).sendNotification(notificationType);
    }
}
