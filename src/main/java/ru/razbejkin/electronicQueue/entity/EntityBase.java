package ru.razbejkin.electronicQueue.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class EntityBase {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator", strategy = "ru.razbejkin.electronicQueue.util.UUIDCustomGenerator")
    protected UUID id;
}
