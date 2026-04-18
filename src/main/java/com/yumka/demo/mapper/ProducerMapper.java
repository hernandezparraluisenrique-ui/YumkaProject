package com.yumka.demo.mapper;

import java.time.LocalDateTime;

import com.yumka.demo.dto.ProducerRequest;
import com.yumka.demo.dto.ProducerResponse;
import com.yumka.demo.model.Producer;
import com.yumka.demo.model.UserRef;

public class ProducerMapper {
    public static ProducerResponse toResponse(Producer entity) {
        if (entity == null) return null;

        return ProducerResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .businessName(entity.getBusinessName())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .build();
    }

    public static Producer toEntity(ProducerRequest dto, UserRef user) {
        if (dto == null) return null;

        return Producer.builder()
                .user(user)
                .businessName(dto.getBusinessName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void copyToEntity(ProducerRequest dto, Producer entity, UserRef user) {
        if (dto == null || entity == null) return;

        entity.setUser(user);
        entity.setBusinessName(dto.getBusinessName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
    }
}
