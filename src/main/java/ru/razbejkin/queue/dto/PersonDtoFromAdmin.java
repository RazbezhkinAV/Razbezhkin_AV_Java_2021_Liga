package ru.razbejkin.queue.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.razbejkin.queue.entity.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonDtoFromAdmin {
    private UUID id;
    private String login;
    private String password;
    private Role role;
}
