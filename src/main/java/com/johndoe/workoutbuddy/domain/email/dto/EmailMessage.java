package com.johndoe.workoutbuddy.domain.email.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessage {
    private final String receiver;
    private final String subject;
    private final String body;
}
