package ru.razbejkin.electronicQueue.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.razbejkin.electronicQueue.entity.Role;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonDtoFromAdmin {
    private UUID id;
    private String login;
    private String password;
    private Role role;
}
