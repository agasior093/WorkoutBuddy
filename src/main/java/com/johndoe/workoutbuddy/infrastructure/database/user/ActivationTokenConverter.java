package com.johndoe.workoutbuddy.infrastructure.database.user;

import com.johndoe.workoutbuddy.domain.user.model.ActivationToken;

class ActivationTokenConverter {
    ActivationToken toDto(ActivationTokenEntity token) {
        return ActivationToken.builder()
                .tokenID(token.getId())
                .username(token.getUsername())
                .activated(token.isActivated())
                .expirationDateTime(token.getExpirationDateTime())
                .build();
    }

    ActivationTokenEntity toEntity(ActivationToken dto) {
        var entity = ActivationTokenEntity.builder()
                .username(dto.getUsername())
                .activated(dto.isActivated())
                .expirationDateTime(dto.getExpirationDateTime())
                .build();
        entity.setId(dto.getTokenID());
        return entity;
    }
}
