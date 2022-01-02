package ru.razbejkin.queue.service;

import ru.razbejkin.queue.entity.Reception;

public interface ManagerService {
    String registrationVisit(String personPhone);

    String endMeeting();

    Reception checkReception();
}
