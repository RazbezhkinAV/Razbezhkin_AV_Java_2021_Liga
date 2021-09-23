package lesson3.decorator;

import lesson3.Notification;
import lesson3.resource.NotificationType;
import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class NotificationDecorator implements Notification {
    Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public UserLanguage userLanguage() {
        return notification.userLanguage();
    }

    @Override
    public String sendNotification(NotificationType notificationType) {
        return Source.getNotification(userLanguage()).get(notificationType);
    }

}
