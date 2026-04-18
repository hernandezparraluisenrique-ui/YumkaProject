package com.yumka.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumka.demo.dto.ProducerRequest;
import com.yumka.demo.dto.ProducerResponse;

@Service
@Transactional
public interface  ProducerService {
    ProducerResponse create(ProducerRequest request);

    ProducerResponse findById(UUID id);

    List<ProducerResponse> findAll(int page, int pageSize);

    List<ProducerResponse> searchByName(String name);

    List<ProducerResponse> findByLocation(String location);

    boolean existsByUserId(UUID userId);
}
