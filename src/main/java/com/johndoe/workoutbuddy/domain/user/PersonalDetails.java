package com.johndoe.workoutbuddy.domain.user;

import com.johndoe.workoutbuddy.domain.user.dto.PersonalDetailsDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Getter
@Builder
class PersonalDetails {
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final LocalDate birthDate;
    private final Double weight;
    private final Double height;

    PersonalDetailsDto toDto() {
        String birthDateString = null;
        if(this.birthDate != null) {
            birthDateString = this.birthDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                    .withLocale(Locale.UK));
        }
        return PersonalDetailsDto.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .gender(this.getGender().getValue())
                .birthDate(birthDateString)
                .weight(this.getWeight())
                .height(this.getHeight())
                .build();
    }

    static PersonalDetails fromDto(PersonalDetailsDto dto) {
        LocalDate birthDate = null;
        if(dto.getBirthDate() != null) {
            birthDate = LocalDate.parse(dto.getBirthDate(),
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK));
        }
        return PersonalDetails.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(Gender.fromString(dto.getGender()))
                .birthDate(birthDate)
                .weight(dto.getWeight())
                .height(dto.getHeight())
                .build();
    }
}
