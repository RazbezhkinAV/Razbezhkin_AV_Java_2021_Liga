package com.razbejkin.lesson10.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PostDTO {

    private UUID id;

    private String context;
}
