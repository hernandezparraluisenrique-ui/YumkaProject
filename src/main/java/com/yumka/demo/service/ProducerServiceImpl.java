package com.yumka.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumka.demo.dto.ProducerRequest;
import com.yumka.demo.dto.ProducerResponse;
import com.yumka.demo.mapper.ProducerMapper;
import com.yumka.demo.model.Producer;
import com.yumka.demo.model.UserRef;
import com.yumka.demo.repository.ProducerRepository;
import com.yumka.demo.repository.UserRefRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository repository;
    private final UserRefRepository userRepository;

 @Override
    @Transactional
    public ProducerResponse create(ProducerRequest request) {
        // 1. Verificación directa (UUID a UUID)
        if (repository.existsByUserId(request.getUserId())) {
            throw new IllegalStateException("User already has a producer profile");
        }

        // 2. Buscar usuario (El repo ahora debe recibir UUID)
        UserRef user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 3. Mapeo y Guardado
        Producer entity = ProducerMapper.toEntity(request, user);
        return ProducerMapper.toResponse(repository.save(entity));
    }

    @Override
    public ProducerResponse findById(UUID id) {
        // Directo: UUID -> Repository
        Producer entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producer not found"));

        return ProducerMapper.toResponse(entity);
    }

    @Override
    public List<ProducerResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        return repository.findAll(pageReq)
                .getContent()
                .stream()
                .map(ProducerMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProducerResponse> searchByName(String name) {
        // Llama al método que definimos en el Repo con LOWER y CONCAT
        return repository.searchByBusinessName(name)
                .stream()
                .map(ProducerMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProducerResponse> findByLocation(String location) {
        return repository.findByLocation(location)
                .stream()
                .map(ProducerMapper::toResponse)
                .toList();
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        // Directo: UUID -> Repository
        return repository.existsByUserId(userId);
    }
}
