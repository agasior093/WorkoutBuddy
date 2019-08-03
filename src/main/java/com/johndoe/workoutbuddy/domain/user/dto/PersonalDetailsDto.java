package com.johndoe.workoutbuddy.domain.user.dto;

import com.johndoe.workoutbuddy.domain.user.entity.PersonalDetails;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Getter
public class PersonalDetailsDto {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String birthDate;
    private final String weight;
    private final String height;

    public PersonalDetailsDto(PersonalDetails personalDetails) {
        this.firstName = personalDetails.getFirstName();
        this.lastName = personalDetails.getFirstName();
        this.gender = personalDetails.getGender().getValue();
        this.birthDate = personalDetails.getBirthDate()
                .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.UK));
        this.weight = personalDetails.getWeight() + " kg";
        this.height = personalDetails.getHeight() + " cm";
    }
}
