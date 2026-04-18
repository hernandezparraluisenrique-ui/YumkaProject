package com.yumka.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;   
import lombok.Setter;
import java.util.UUID;



@Entity
@Table(name = "favorites")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private UserRef user;

    @ManyToOne
    private Product product;

    private LocalDateTime createdAt;
}
