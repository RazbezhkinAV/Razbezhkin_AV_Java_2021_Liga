package com.razbejkin.lesson10.dto;

import com.razbejkin.lesson10.entity.School;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonDto {

    private UUID id;

    private String name;

    private String surname;

    private School school;

    private List<PostDto> posts;

    private List<FriendDto> friends;

}
