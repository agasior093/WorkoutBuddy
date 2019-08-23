package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

class ActivationTokenConverter {
    ActivationTokenDto toDto(ActivationTokenEntity token) {
        return ActivationTokenDto.builder()
                .tokenID(token.getId())
                .username(token.getUsername())
                .activated(token.isActivated())
                .expirationDateTime(token.getExpirationDateTime())
                .build();
    }

    ActivationTokenEntity toEntity(ActivationTokenDto dto) {
        var entity = ActivationTokenEntity.builder()
                .username(dto.getUsername())
                .activated(dto.isActivated())
                .expirationDateTime(dto.getExpirationDateTime())
                .build();
        entity.setId(dto.getTokenID());
        return entity;
    }
}
