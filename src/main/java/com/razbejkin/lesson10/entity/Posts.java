package com.razbejkin.lesson10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Posts extends EntityBase {

    @Column(name = "content", unique = true, nullable = false)
    private String content;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    private Person person;

    public Posts(String content,Person person) {
        this.content = content;
        this.person = person;
    }

}
