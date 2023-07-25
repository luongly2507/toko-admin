package com.example.toko_admin.services;

import com.example.toko_admin.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("api/v1/categories")
    Call<List<Category>> getAllCategories();
    @POST("api/v1/categories")
    Call<Category> createCategory (@Body Category category);
    @DELETE("api/v1/categories/{id}")
    Call<Category> deleteCategory (@Path("id") String id);
    @PUT("api/v1/categories/{id}")
    Call<Category> updateCategory (@Path("id") String id, @Body Category category);

}
