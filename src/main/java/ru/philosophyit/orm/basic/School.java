package ru.philosophyit.orm.basic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "SCHOOL")
public class School {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "ru.philosophyit.orm.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
