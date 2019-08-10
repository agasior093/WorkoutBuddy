package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.GenderDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

class ObjectMapper {
    User userToEntity(UserDto user) {
        if(user == null) return null;
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .active(user.isActive())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(genderToEntity(user.getGender()))
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }


    UserDto userToDto(User user) {
        if(user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .active(user.isActive())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .gender(genderToDto(user.getGender()))
                .weight(user.getWeight())
                .height(user.getHeight())
                .build();
    }

    Gender genderToEntity(GenderDto genderDto) {
        return genderDto == GenderDto.MALE ? Gender.MALE : Gender.FEMALE;
    }

    GenderDto genderToDto(Gender gender) {
        return gender == Gender.MALE ? GenderDto.MALE : GenderDto.FEMALE;
    }
}
