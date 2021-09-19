package lesson3.decorator;

import lesson3.Notification;
import lesson3.resource.Source;
import lesson3.resource.UserLanguage;

public class NotificationDecoratorDE extends NotificationDecorator {

    public NotificationDecoratorDE(Notification notification) {
        super(notification);
    }

    @Override
    public String getWelcomeText() {
        return super.getWelcomeText() + "\n" + Source.getWelcomeMessage(UserLanguage.de) + "\n";
    }

    @Override
    public String getByText() {
        return super.getWelcomeText() + "\n" + Source.getByMessage(UserLanguage.de) + "\n";
    }

    @Override
    public UserLanguage userCountry() {
        return UserLanguage.de;
    }
}
