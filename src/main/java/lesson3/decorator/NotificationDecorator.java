package lesson3.decorator;

import lesson3.Notification;
import lesson3.User;
import lesson3.resource.NotificationType;
import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class NotificationDecorator implements Notification {
    private Notification notification;
    private User user;
    private NotificationType notificationType;

    public NotificationDecorator(Notification notification, User user, NotificationType notificationType) {
        this.notification = notification;
        this.user = user;
        this.notificationType = notificationType;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setMessage(String message) {
        notification.setMessage(message);

    }

    public String sendNotification() {
        setMessage(translateMessage());
        return String.format(notification.sendNotification(),
                user.getName());
    }

    private String translateMessage() {
        if (Source.getNotification(user.getLanguage()) != null)
            return Source.getNotification(user.getLanguage()).get(notificationType);
        else
            return Source.getNotification(UserLanguage.en).get(notificationType);
    }

}
