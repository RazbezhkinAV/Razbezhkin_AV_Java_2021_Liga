package lesson3.resource;

import lesson3.Notification;
import lesson3.decorator.NotificationDecoratorDE;
import lesson3.decorator.NotificationDecoratorEN;
import lesson3.decorator.NotificationDecoratorRU;

import java.util.HashMap;
import java.util.Map;

public class Source {
    private static Map<UserLanguage, String> welcomeMessage = new HashMap<>();
    private static Map<UserLanguage, String> byMessage = new HashMap<>();
    private static Map<String, UserLanguage> allCountry = new HashMap<>();


    static {
        allCountry.put("ru",UserLanguage.ru);
        allCountry.put("en",UserLanguage.en);
        allCountry.put("de",UserLanguage.de);

        welcomeMessage.put(UserLanguage.ru,"Добрый день");
        welcomeMessage.put(UserLanguage.en,"Good morning");
        welcomeMessage.put(UserLanguage.de,"Guten Morgen");

        byMessage.put(UserLanguage.ru,"Досвидания");
        byMessage.put(UserLanguage.en,"Goodbye");
        byMessage.put(UserLanguage.de,"Auf Wiedersehen");
    }

    public static String getWelcomeMessage(UserLanguage user){
        return welcomeMessage.get(user);
    }

    public static String getByMessage(UserLanguage user){
        return byMessage.get(user);
    }

    public static UserLanguage getCountry(String country){
        return allCountry.get(country);
    }

    public static Notification buildDecorator(Notification notification){
        switch (notification.userCountry()){
            case ru :
                return new NotificationDecoratorRU(notification);
            case de :
                return new NotificationDecoratorDE(notification);
            case en :
                return new NotificationDecoratorEN(notification);
        }
        return null;
    }
}
