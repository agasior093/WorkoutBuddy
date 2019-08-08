package com.johndoe.workoutbuddy.adapter.repository;

import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationToken;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

class ActivationTokenMapper {

    ActivationTokenDto tokenToDto(ActivationToken token) {
        return ActivationTokenDto.builder()
                .uuid(token.getUuid())
                .username(token.getUsername())
                .activated(token.isActivated())
                .expirationDateTime(token.getExpirationDateTime())
                .build();
    }

    ActivationToken tokenToEntity(ActivationTokenDto dto) {
        return ActivationToken.builder()
                .uuid(dto.getUuid())
                .username(dto.getUsername())
                .activated(dto.isActivated())
                .expirationDateTime(dto.getExpirationDateTime())
                .build();
    }
}
