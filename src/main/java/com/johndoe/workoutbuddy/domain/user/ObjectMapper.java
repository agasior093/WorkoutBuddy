package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import com.johndoe.workoutbuddy.domain.user.dto.UserDto;

class ObjectMapper {

    PersonalDetails personalDetailsToEntity(PersonalDetailsDto detailsDto) {
        if(detailsDto == null) return null;
        return PersonalDetails.builder()
                .firstName(detailsDto.getFirstName())
                .lastName(detailsDto.getLastName())
                .gender(null)
                .birthDate(detailsDto.getBirthDate())
                .weight(detailsDto.getWeight())
                .height(detailsDto.getHeight())
                .build();
    }

    PersonalDetailsDto personalDetailsToDto(PersonalDetails details) {
        if(details == null) return null;
        return PersonalDetailsDto.builder()
                .firstName(details.getFirstName())
                .lastName(details.getLastName())
                .gender(null)
                .birthDate(details.getBirthDate())
                .weight(details.getWeight())
                .height(details.getHeight())
                .build();
    }

    User userToEntity(UserDto user) {
        if(user == null) return null;
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .active(user.isActive())
                .personalDetails(personalDetailsToEntity(user.getPersonalDetails()))
                .build();
    }

    UserDto userToDto(User user) {
        if(user == null) return null;
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .active(user.isActive())
                .personalDetails(personalDetailsToDto(user.getPersonalDetails()))
                .build();
    }
}
