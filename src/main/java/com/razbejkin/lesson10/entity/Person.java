package com.razbejkin.lesson10.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class    Person extends EntityBase{

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(cascade = CascadeType.ALL
            , mappedBy = "person")
    private List<Posts> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "friends_id", referencedColumnName = "id")}
    )
    private List<Person> friends;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "school_id")
    private School school;


    public Person(String firstName, String lastName, School school) {
        this.name = firstName;
        this.surname = lastName;
        this.school = school;
    }

    public void addPost(String context) {
        if (posts != null)
            posts = new ArrayList<>();
        posts.add(new Posts(context, this));
    }

    public void addFriend(Person person) {
        if (friends == null)
            friends = new ArrayList<>();
        if (!person.equals(this) && !friends.contains(friends))
            friends.add(person);
    }

    public void removeFriend(Person person){
        if (friends.contains(person))
            friends.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(posts, person.posts) && Objects.equals(friends, person.friends) && Objects.equals(school, person.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, posts, friends, school);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", school=" + school +
                '}';
    }
}
