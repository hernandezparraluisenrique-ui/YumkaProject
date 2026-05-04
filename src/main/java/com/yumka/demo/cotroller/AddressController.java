package com.yumka.demo.cotroller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yumka.demo.dto.AddressRequest;
import com.yumka.demo.dto.AddressResponse;
import com.yumka.demo.service.AddressService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    // 🔹 Crear dirección
    @PostMapping
    public AddressResponse create(@RequestBody @Valid AddressRequest req) {
        return service.create(req);
    }

    // 🔹 Obtener direcciones de un usuario
    @GetMapping("/user/{userId}")
    public List<AddressResponse> findByUser(@PathVariable UUID userId) {
        return service.findByUser(userId);
    }

    // 🔹 Actualizar dirección
    @PutMapping("/{id}")
    public AddressResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid AddressRequest req
    ) {
        return service.update(id, req);
    }

    // 🔹 Eliminar dirección
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
