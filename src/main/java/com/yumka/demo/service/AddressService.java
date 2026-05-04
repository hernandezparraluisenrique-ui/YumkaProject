package com.yumka.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.AddressRequest;
import com.yumka.demo.dto.AddressResponse;

import jakarta.transaction.Transactional;


@Service
@Transactional
public interface AddressService {


     AddressResponse create(AddressRequest request);

    List<AddressResponse> findByUser(UUID userId);

    AddressResponse update(UUID id, AddressRequest request);

    void delete(UUID id);
}
