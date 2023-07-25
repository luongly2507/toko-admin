package com.example.toko_admin.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.toko_admin.models.Category;
import com.example.toko_admin.repositories.CategoryRepository;
import com.example.toko_admin.services.CategoryResponseListener;

public class AddCategoryViewModel extends ViewModel {
    CategoryRepository categoryRepository;

    public AddCategoryViewModel() {
        this.categoryRepository = new CategoryRepository();
    }

    public void createCategory(Category category, CategoryResponseListener categoryResponseListener) {
        categoryRepository.createCategory(category, categoryResponseListener);
    }
}
