package lesson3.decorator;

import lesson3.Notification;

abstract class NotificationDecorator implements Notification {
    Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override

    public String getWelcomeText() {
        return notification.getWelcomeText() + "\n" + "-----------";
    }

    public String getByText() {
        return notification.getByText() + "\n" + "-----------";
    }


}
