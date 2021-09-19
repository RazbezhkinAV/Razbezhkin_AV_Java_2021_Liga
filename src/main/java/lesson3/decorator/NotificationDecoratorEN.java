package lesson3.decorator;

import lesson3.Notification;
import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class NotificationDecoratorEN extends NotificationDecorator {

    public NotificationDecoratorEN(Notification notification) {
        super(notification);
    }

    @Override
    public String getWelcomeText() {
        return super.getWelcomeText() + "\n" + Source.getWelcomeMessage(UserLanguage.en) + "\n";
    }

    @Override
    public String getByText() {
        return super.getWelcomeText() + "\n" + Source.getByMessage(UserLanguage.en) + "\n";
    }

    @Override
    public UserLanguage userCountry() {
        return UserLanguage.en;
    }
}
