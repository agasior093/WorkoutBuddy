package com.johndoe.workoutbuddy.domain.email;

import com.johndoe.workoutbuddy.domain.email.dto.EmailMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
class EmailMessage {
    private final String receiver;
    private final String subject;
    private final String body;

    static EmailMessage fromDto(EmailMessageDto dto) {
        return EmailMessage.builder()
                .receiver(dto.getReceiver())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .build();
    }
}
