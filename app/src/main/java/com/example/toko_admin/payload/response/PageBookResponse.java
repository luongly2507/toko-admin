package com.example.toko_admin.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageBookResponse {
    private List<BookResponse> content;
    private int totalPages;
}
