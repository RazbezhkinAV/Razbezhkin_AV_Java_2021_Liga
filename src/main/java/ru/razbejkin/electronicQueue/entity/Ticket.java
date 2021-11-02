package ru.razbejkin.electronicQueue.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket extends EntityBase{

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "time")
    private String time;

    @Column(name = "ticket_free")
    private boolean free;

    @Column(name = "visit_person")
    private boolean visit;

    @Column(name = "confirm")
    private boolean confirm;

    public Ticket(String time) {
        this.time = time;
        this.free = true;
        this.visit = false;
        this.confirm = false;
    }

}
