package ru.razbejkin.electronicQueue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razbejkin.electronicQueue.model.LiveQueue;
import ru.razbejkin.electronicQueue.dto.PersonDto;
import ru.razbejkin.electronicQueue.entity.Person;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveQueueService {

    private final LiveQueue liveQueue;
    private final PersonServiceImpl personServiceImpl;
    private final ReceptionService receptionService;

    public List<PersonDto> showLiveQueue() {
        return liveQueue.getPersonQueue();
    }

    public String getToLine() {
        Person person = personServiceImpl.info();
        if (liveQueue.addPersonToQueue(person))
            return "Номер в очереди " + liveQueue.numberInQueue(person);
        else
            return "В уже стоите в очереди под номером " + liveQueue.numberInQueue(person);
    }

    public String getOutOfQueue() {
        Person person = personServiceImpl.info();
        liveQueue.removePersonFromQueue(person);
        return "Person " + person.getName() + " ушёл из очереди";
    }

    public String callNextInLiveQueue() {
        PersonDto person = liveQueue.collFirstInQueue();
        if (!receptionService.receptionIsFree())
            return "На данный момент ведётся приём";
        if (person != null) {
            receptionService.startMeeting(person, null);
            return "из очереди приглашён Person " + person.getName();
        } else
            return "Очередь пустая";
    }
}
