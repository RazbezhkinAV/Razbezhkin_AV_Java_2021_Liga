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
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(person, ticket.person) && Objects.equals(time, ticket.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, time);
    }
}
