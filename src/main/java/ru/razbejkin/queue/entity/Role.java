package ru.razbejkin.queue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends EntityBase {
    @Column(name = "name", nullable = false)
    private String name;
}
