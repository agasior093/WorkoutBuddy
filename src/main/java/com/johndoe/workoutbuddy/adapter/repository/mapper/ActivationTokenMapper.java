package com.johndoe.workoutbuddy.adapter.repository.mapper;

import com.johndoe.workoutbuddy.adapter.repository.entity.ActivationTokenEntity;
import com.johndoe.workoutbuddy.domain.user.dto.ActivationTokenDto;

public class ActivationTokenMapper {

    public ActivationTokenDto tokenToDto(ActivationTokenEntity token) {
        return ActivationTokenDto.builder()
                .uuid(token.getUuid())
                .username(token.getUsername())
                .activated(token.isActivated())
                .expirationDateTime(token.getExpirationDateTime())
                .build();
    }

    public ActivationTokenEntity tokenToEntity(ActivationTokenDto dto) {
        return ActivationTokenEntity.builder()
                .uuid(dto.getUuid())
                .username(dto.getUsername())
                .activated(dto.isActivated())
                .expirationDateTime(dto.getExpirationDateTime())
                .build();
    }
}
