package lesson3.resource;

import java.util.HashMap;
import java.util.Map;

public class Source {
    private static Map<String, UserLanguage> allLanguage = new HashMap<>();

    private static Map<UserLanguage, Map<NotificationType, String>> notification = new HashMap<>();
    private static Map<NotificationType, String> notificationLanguageRU = new HashMap<>();
    private static Map<NotificationType, String> notificationLanguageEN = new HashMap<>();
    private static Map<NotificationType, String> notificationLanguageDE = new HashMap<>();


    static {
        notification.put(UserLanguage.ru, notificationLanguageRU);
        notification.put(UserLanguage.de, notificationLanguageDE);
        notification.put(UserLanguage.en, notificationLanguageEN);

        notificationLanguageRU.put(NotificationType.WELCOME, "Добрый день, %s!");
        notificationLanguageRU.put(NotificationType.BUE, "Досвидания, %s!");

        notificationLanguageEN.put(NotificationType.WELCOME, "Good morning, %s!");
        notificationLanguageEN.put(NotificationType.BUE, "Goodbye, %s!");

        notificationLanguageDE.put(NotificationType.WELCOME, "Guten Morgen, %s!");
        notificationLanguageDE.put(NotificationType.BUE, "Auf Wiedersehen, %s!");

    }

    public static Map<NotificationType, String> getNotification(UserLanguage userLanguage) {
        return notification.get(userLanguage);
    }
}
