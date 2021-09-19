package lesson3;

import lesson3.resource.UserLanguage;

public interface Notification {
    String getWelcomeText();

    String getByText();

    UserLanguage userCountry();

}
