package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.domain.user.model.Gender;
import com.johndoe.workoutbuddy.domain.user.model.User;

class UserEntityConverter {

    UserEntity toEntity(User user) {
        var entity = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .active(user.isActive())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(genderToEntity(user.getGender()))
                .birthDate(user.getBirthDate())
                .build();
        entity.setId(user.getId());
        return entity;
    }

    User toDto(UserEntity userEntity) {
        return User.builder()
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
                .build();
    }

    Gender genderToDto(GenderEntity genderEntity) {
        return genderEntity == GenderEntity.MALE ? Gender.MALE : Gender.FEMALE;
    }

    GenderEntity genderToEntity(Gender gender) {
        return gender == Gender.MALE ? GenderEntity.MALE : GenderEntity.FEMALE;
    }
}
