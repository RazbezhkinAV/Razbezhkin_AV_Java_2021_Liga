package com.razbejkin.lesson10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "com.razbejkin.lesson10.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    public School(String name) {
        this.name = name;
    }
}
