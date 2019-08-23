package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.domain.user.dto.GenderDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

class UserEntityConverter {

    UserEntity toEntity(UserDto userDto) {
        var entity = UserEntity.builder()
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
        entity.setId(userDto.getId());
        return entity;
    }

    UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .active(userEntity.isActive())
                .firstName("John")
                .lastName("Doe")
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
