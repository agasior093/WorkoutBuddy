package com.johndoe.workoutbuddy.adapter.repository.entity;

import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

public class ActivationTokenMapper {

    public ActivationTokenDto tokenToDto(ActivationTokenEntity token) {
        return ActivationTokenDto.builder()
                .tokenID(token.getTokenID())
                .username(token.getUsername())
                .activated(token.isActivated())
                .expirationDateTime(token.getExpirationDateTime())
                .build();
    }

    public ActivationTokenEntity tokenToEntity(ActivationTokenDto dto) {
        return ActivationTokenEntity.builder()
                .tokenID(dto.getTokenID())
                .username(dto.getUsername())
                .activated(dto.isActivated())
                .expirationDateTime(dto.getExpirationDateTime())
                .build();
    }
}
