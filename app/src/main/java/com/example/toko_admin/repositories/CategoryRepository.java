package com.example.toko_admin.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.toko_admin.models.Category;
import com.example.toko_admin.services.CategoryResponseListener;
import com.example.toko_admin.services.CategoryService;
import com.example.toko_admin.utils.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private CategoryService categoryService;
    private MutableLiveData<List<Category>> categoriesLiveData;

    public CategoryRepository() {
        categoriesLiveData = new MutableLiveData<>();
        categoryService = ApiService.getCategoryService();
    }

    public void getAllCategories() {
        categoryService.getAllCategories().enqueue(
                new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        System.out.println(response.body());
                        if (response.body() != null) {
                            categoriesLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        t.printStackTrace();
                        categoriesLiveData.postValue(null);
                    }
                }
        );
    }
    public void createCategory(Category category, CategoryResponseListener categoryResponseListener) {
        categoryService.createCategory(category).enqueue(
                new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        categoryResponseListener.onFailure();
                        System.out.println("Success!");
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        categoryResponseListener.onSuccess();
                        System.out.println("Failed");
                        t.printStackTrace();
                    }
                }
        );
    }
    public void deleteCategory(Category category, CategoryResponseListener categoryResponseListener) {
        if (category.getChildren() == null) {
            categoryService.deleteCategory(category.getId().toString()).enqueue(
                    new Callback<Category>() {
                        @Override
                        public void onResponse(Call<Category> call, Response<Category> response) {
                            if (response.isSuccessful()) {
                                categoryResponseListener.onSuccess();
                            } else {
                                categoryResponseListener.onFailure();
                            }
                            System.out.print("Success");
                        }

                        @Override
                        public void onFailure(Call<Category> call, Throwable t) {
                            categoryResponseListener.onFailure();
                            System.out.print("Failed");
                        }
                    }
            );
        }
        else {categoryResponseListener.onFailure();}
    }
    public void updateCategory(Category category, CategoryResponseListener categoryResponseListener) {
        categoryService.updateCategory(category.getId().toString(), category).enqueue(
                new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) { categoryResponseListener.onSuccess();}
                        else {categoryResponseListener.onFailure();}
                        System.out.print("Success");
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {
                        categoryResponseListener.onFailure();
                        System.out.print("Failed");
                    }
                }
        );
    }
    public LiveData<List<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}
