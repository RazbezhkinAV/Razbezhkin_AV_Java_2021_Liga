package ru.philosophyit.orm.basic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "POSTS")
public class Posts {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "ru.philosophyit.orm.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String content;

    public Posts() {
    }

    public Posts(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name) {
        this.content = name;
    }
}
