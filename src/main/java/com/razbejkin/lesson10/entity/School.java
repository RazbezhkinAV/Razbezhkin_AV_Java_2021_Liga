package com.razbejkin.lesson10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
public class School extends EntityBase {

    @Column(name = "name")
    private String name;

    public School(String name) {
        this.name = name;
    }
}
