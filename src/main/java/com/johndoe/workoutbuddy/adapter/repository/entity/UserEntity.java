package com.johndoe.workoutbuddy.adapter.repository.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Builder
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
