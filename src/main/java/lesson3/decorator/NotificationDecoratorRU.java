package lesson3.decorator;

import lesson3.Notification;
import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class NotificationDecoratorRU extends NotificationDecorator {

    public NotificationDecoratorRU(Notification notification) {
        super(notification);
    }

    @Override
    public String getWelcomeText() {
        return super.getWelcomeText() + "\n" + Source.getWelcomeMessage(UserLanguage.ru) + "\n";
    }

    @Override
    public String getByText() {
        return super.getWelcomeText() + "\n" + Source.getByMessage(UserLanguage.ru) + "\n";
    }

    @Override
    public UserLanguage userCountry() {
        return UserLanguage.ru;
    }
}
