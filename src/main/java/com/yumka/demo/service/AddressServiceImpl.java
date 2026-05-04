package com.yumka.demo.service;

import com.yumka.demo.dto.AddressRequest;
import com.yumka.demo.dto.AddressResponse;
import com.yumka.demo.mapper.AddressMapper;
import com.yumka.demo.model.Address;
import com.yumka.demo.model.UserRef;
import com.yumka.demo.repository.AddressRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import com.yumka.demo.repository.UserRepository;



@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse create(AddressRequest request) {

        UserRef user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Address entity = AddressMapper.toEntity(request, user);

        return AddressMapper.toResponse(repository.save(entity));
    }

    @Override
    public List<AddressResponse> findByUser(UUID userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public AddressResponse update(UUID id, AddressRequest request) {

        Address entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        UserRef user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        AddressMapper.copyToEntity(request, entity, user);

        return AddressMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Address not found");
        }
        repository.deleteById(id);
    }
}
