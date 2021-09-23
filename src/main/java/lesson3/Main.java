package lesson3;

import lesson3.factory.MailNotificationFactory;
import lesson3.factory.NotificationFactory;
import lesson3.factory.PhoneNotificationFactory;
import lesson3.resource.NotificationType;

public class Main {

    public static void main(String[] args) {
        User user = new User(2L, "Денис", "denis@gmail.com", "+79522668105");
        User user1 = new User(3L, "Саша", "saha@gmail.com", "+700000000");
        User user2 = new User(4L, "Аня", "anna@gmail.com", "+799999999");


        NotificationFactory factory = true ? new MailNotificationFactory() : new PhoneNotificationFactory();

        sendNotification(NotificationType.WELCOME, factory.makeNotification(user));
        sendNotification(NotificationType.BUE, factory.makeNotification(user1));
        sendNotification(NotificationType.WELCOME, factory.makeNotification(user2));

    }

    private static void sendNotification(NotificationType notificationType, Notification notification) {
        System.out.println(notification.sendNotification(notificationType));
    }
}
