package com.johndoe.workoutbuddy.adapter.repository.entity;

import com.johndoe.workoutbuddy.adapter.repository.entity.GenderEntity;
import com.johndoe.workoutbuddy.adapter.repository.entity.UserEntity;
import com.johndoe.workoutbuddy.domain.user.dto.GenderDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .roles(userDto.getRoles())
                .active(userDto.isActive())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .gender(genderToEntity(userDto.getGender()))
                .birthDate(userDto.getBirthDate())
                .weight(userDto.getWeight())
                .height(userDto.getHeight())
                .build();
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .active(userEntity.isActive())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .gender(genderToDto(userEntity.getGender()))
                .birthDate(userEntity.getBirthDate())
                .weight(userEntity.getWeight())
                .height(userEntity.getHeight())
                .build();
    }

    GenderDto genderToDto(GenderEntity genderEntity) {
        return genderEntity == GenderEntity.MALE ? GenderDto.MALE : GenderDto.FEMALE;
    }

    GenderEntity genderToEntity(GenderDto genderDto) {
        return genderDto == GenderDto.MALE ? GenderEntity.MALE : GenderEntity.FEMALE;
    }
}
