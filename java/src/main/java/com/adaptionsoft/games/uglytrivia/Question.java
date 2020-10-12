package com.adaptionsoft.games.uglytrivia;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Question {

    private Category category;

    private UUID id;

    private String description;
}
