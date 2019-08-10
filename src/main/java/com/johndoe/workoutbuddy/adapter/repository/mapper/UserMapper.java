package com.johndoe.workoutbuddy.adapter.repository.mapper;

import com.johndoe.workoutbuddy.adapter.repository.entity.UserEntity;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        return UserEntity.builder().username(userDto.getUsername()).password(userDto.getPassword()).email(userDto.getEmail()).build();
    }

    public UserDto toDto(UserEntity userDto) {
        return UserDto.builder().username(userDto.getUsername()).password(userDto.getPassword()).email(userDto.getEmail()).build();
    }
}
