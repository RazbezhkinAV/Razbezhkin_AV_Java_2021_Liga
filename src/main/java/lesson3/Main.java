package lesson3;

import lesson3.factory.MailNotificationFactory;
import lesson3.factory.NotificationFactory;
import lesson3.factory.PhoneNotificationFactory;
import lesson3.resource.Source;

public class Main {

    public static void main(String[] args) {
        User user = new User(2L, "Денис", "denis@gmail.com", "+79522668105", "ru");
        User user1 = new User(3L, "Саша", "saha@gmail.com", "+700000000", "en");
        User user2 = new User(4L, "Аня", "anna@gmail.com", "+799999999", "de");


        NotificationFactory factory = true ? new MailNotificationFactory() : new PhoneNotificationFactory();

        sendWelcomeNotification(factory.makeNotification(user));
        sendWelcomeNotification(factory.makeNotification(user1));
        sendWelcomeNotification(factory.makeNotification(user2));

        sendByNotification(factory.makeNotification(user));
        sendByNotification(factory.makeNotification(user1));
        sendByNotification(factory.makeNotification(user2));
    }

    private static void sendWelcomeNotification(Notification notification) {
        System.out.println(Source.buildDecorator(notification).getWelcomeText());
    }

    private static void sendByNotification(Notification notification) {
        System.out.println(Source.buildDecorator(notification).getByText());
    }
}
