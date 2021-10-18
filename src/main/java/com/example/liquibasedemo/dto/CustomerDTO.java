package com.example.liquibasedemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private UUID id;

    private String name;

}
