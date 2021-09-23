package lesson3;

import lesson3.resource.NotificationType;
import lesson3.resource.UserLanguage;

public interface Notification {
    String sendNotification(NotificationType notificationType);

    UserLanguage userLanguage();

}
