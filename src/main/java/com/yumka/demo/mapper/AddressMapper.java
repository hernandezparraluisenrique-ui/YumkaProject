package com.yumka.demo.mapper;

import java.time.LocalDateTime;

import com.yumka.demo.dto.AddressRequest;
import com.yumka.demo.dto.AddressResponse;
import com.yumka.demo.model.Address;
import com.yumka.demo.model.UserRef;



public class AddressMapper {
    public static AddressResponse toResponse(Address entity) {
        if (entity == null) return null;

        return AddressResponse.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .city(entity.getCity())
                .postalCode(entity.getPostalCode())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .build();
    }

    public static Address toEntity(AddressRequest dto, UserRef user) {
        if (dto == null) return null;

        return Address.builder()
                .user(user)
                .address(dto.getAddress())
                .city(dto.getCity())
                .postalCode(dto.getPostalCode())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void copyToEntity(AddressRequest dto, Address entity, UserRef user) {
        if (dto == null || entity == null) return;

        entity.setUser(user);
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setPostalCode(dto.getPostalCode());
    }
}
