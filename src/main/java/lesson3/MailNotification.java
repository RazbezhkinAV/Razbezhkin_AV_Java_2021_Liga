package lesson3;

import lesson3.decorator.NotificationDecorator;
import lesson3.resource.NotificationType;
import lesson3.resource.UserLanguage;

public class MailNotification implements Notification {

    private User user;
    private boolean hasAdvertisement;

    public MailNotification(User user, boolean hasAdvertisement) {
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
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
