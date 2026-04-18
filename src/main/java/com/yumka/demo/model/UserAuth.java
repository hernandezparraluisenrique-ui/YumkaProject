package com.yumka.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth {
    @Id
    private String id;

    private String googleId;
    private String email;
    private String name;
    private String photo;
    private String role;
    private LocalDateTime createdAt;
}
