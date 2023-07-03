package com.example.toko_admin.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String role;
}
