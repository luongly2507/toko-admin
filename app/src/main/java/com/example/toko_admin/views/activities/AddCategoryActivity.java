package com.example.toko_admin.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.toko_admin.databinding.ActivityAddCategoryBinding;

import com.example.toko_admin.R;
import com.example.toko_admin.models.Category;
import com.example.toko_admin.services.CategoryResponseListener;
import com.example.toko_admin.viewmodels.AddCategoryViewModel;
import com.example.toko_admin.viewmodels.CategoryViewModel;
import com.example.toko_admin.views.fragments.CategoryFragment;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.val;

public class AddCategoryActivity extends AppCompatActivity {

    private AddCategoryViewModel addCategoryViewModel;
    private ArrayList<Category> categoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ActivityAddCategoryBinding binding = ActivityAddCategoryBinding.inflate(getLayoutInflater());

        addCategoryViewModel = new ViewModelProvider(this).get(AddCategoryViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setAddCategoryViewModel(addCategoryViewModel);
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(v-> finish());

        Intent intent = getIntent();
        categoryArrayList = (ArrayList<Category>) intent.getSerializableExtra("CategoryList");
        if (categoryArrayList != null) {
            Category category = new Category(UUID.randomUUID(), "None", null, null);
            categoryArrayList.add(0, category);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryArrayList);
            binding.categorySpinner.setAdapter(arrayAdapter);
        }

        binding.addCategoryButton.setOnClickListener(view -> {
            String categoryName = binding.categoryEditText.getText().toString();
            UUID parent;
            if (binding.categorySpinner.getSelectedItem().toString() == "None") {
                parent = null;
            } else {
                parent = ((Category) binding.categorySpinner.getSelectedItem()).getId();
            }
            Category category = new Category(null, categoryName, null, parent);
            addCategoryViewModel.createCategory(category, new CategoryResponseListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(AddCategoryActivity.this, "Thêm danh mục thành công!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }

                @Override
                public void onFailure() {
                    Toast.makeText(AddCategoryActivity.this, "Thêm danh mục thất bại (hoặc đã tồn tại danh mục này)! Vui lòng thừ lại sau! ", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                }
            });
        });
    }
}