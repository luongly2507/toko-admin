package com.example.toko_admin.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private UUID id;
    private String name;
    private List<Category> children;
    private UUID parent;

    @NonNull
    @Override
    public String toString() {
        return name;
    }

}
