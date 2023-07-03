package com.example.toko_admin.services;

import com.example.toko_admin.payload.response.PageBookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {
    @GET("api/v1/books")
    Call<PageBookResponse> getAllBooksByPage(@Query("page") int pageNumber);
}
