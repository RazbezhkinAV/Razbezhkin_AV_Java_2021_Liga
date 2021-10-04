package ru.philosophyit.orm.basic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "ru.philosophyit.orm.util.UUIDCustomGenerator")
    private UUID id;

    @Column(name = "FIRST_NAME", unique = true, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "PERSON_POST",
            joinColumns = {@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POSTS_ID", referencedColumnName = "ID")}
    )
    private List<Posts> posts;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "PERSON_FRIENDS",
            joinColumns = {@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "FRIENDS_ID", referencedColumnName = "ID")}
    )
    private List<Person> friends;

    @OneToOne
    @JoinColumn(name = "school_id")
    private School school;

    public School getSchool() {
        return school;
    }

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
