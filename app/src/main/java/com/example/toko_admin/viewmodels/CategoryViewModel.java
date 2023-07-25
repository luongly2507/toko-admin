package com.example.toko_admin.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.toko_admin.models.Category;
import com.example.toko_admin.repositories.CategoryRepository;
import com.example.toko_admin.services.CategoryResponseListener;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> categoriesLiveData;


    public CategoryViewModel() {
        categoryRepository = new CategoryRepository();
        categoriesLiveData = categoryRepository.getCategoriesLiveData();
    }

    public void getAllCategories() {
        categoryRepository.getAllCategories();
    }

    public LiveData<List<Category>> getCategoryResponsesLiveData() {
        return categoriesLiveData;
    }
}