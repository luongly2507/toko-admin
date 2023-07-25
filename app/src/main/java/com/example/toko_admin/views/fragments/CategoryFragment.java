package com.example.toko_admin.views.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toko_admin.adapter.CategoryRecyclerViewAdapter;
import com.example.toko_admin.databinding.FragmentCategoryBinding;
import com.example.toko_admin.models.Category;
import com.example.toko_admin.viewmodels.AddCategoryViewModel;
import com.example.toko_admin.viewmodels.CategoryViewModel;
import com.example.toko_admin.views.activities.AddCategoryActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private CategoryViewModel categoryViewModel;
    private RecyclerView categoryRecyclerView;
    private ArrayList<Category> categoryArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoryViewModel galleryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryRecyclerView = binding.recyclerViewCategory;
        reloadRecyclerView();
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        reloadRecyclerView();
                    }
                });
        binding.addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddCategoryActivity.class);
                intent.putExtra("CategoryList", categoryArrayList);
                launcher.launch(intent);
            }
        });


        return root;
    }
    public void reloadRecyclerView() {
        categoryViewModel.getAllCategories();
        categoryViewModel.getCategoryResponsesLiveData().observe(getViewLifecycleOwner(), categoryResponses -> {
            categoryArrayList = (ArrayList<Category>) categoryResponses;
            for (Category category :
                    categoryArrayList) {
                if (category.getChildren() != null) {
                    for (Category childCategory :
                            category.getChildren()) {
                        childCategory.setParent(category.getId());
                    }
                }
            }
            categoryArrayList.sort((category, t1) -> category.getName().compareTo(t1.getName()));
            categoryRecyclerView.setAdapter(new CategoryRecyclerViewAdapter(this.getContext(), categoryArrayList));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}