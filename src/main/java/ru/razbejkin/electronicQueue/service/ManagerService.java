package ru.razbejkin.electronicQueue.service;

import ru.razbejkin.electronicQueue.model.Reception;

public interface ManagerService {
    String registrationVisit(String personPhone);

    String endMeeting();

    Reception checkReception();
}
