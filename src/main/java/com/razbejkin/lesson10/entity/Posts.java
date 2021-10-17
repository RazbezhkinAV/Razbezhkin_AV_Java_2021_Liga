package com.razbejkin.lesson10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Posts {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "com.razbejkin.lesson10.util.UUIDCustomGenerator")
    private UUID id;

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
