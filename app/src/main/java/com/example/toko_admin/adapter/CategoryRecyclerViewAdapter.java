package com.example.toko_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toko_admin.R;
import com.example.toko_admin.models.Category;
import com.example.toko_admin.repositories.CategoryRepository;
import com.example.toko_admin.services.CategoryResponseListener;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categories;
    private CategoryRepository categoryRepository;

    public CategoryRecyclerViewAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        categoryRepository = new CategoryRepository();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryParentName.setText(category.getName());

        holder.editButton.setOnClickListener(view -> {
            holder.categoryParentName.setEnabled(true);
            holder.categoryParentName.requestFocus();
            holder.categoryParentName.setSelection(holder.categoryParentName.length());
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(holder.categoryParentName, InputMethodManager.SHOW_IMPLICIT);
            holder.categoryParentName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        imm.hideSoftInputFromWindow(holder.categoryParentName.getWindowToken(), 0);
                        holder.categoryParentName.clearFocus();

                        //update
                        category.setName(holder.categoryParentName.getText().toString());
                        categoryRepository.updateCategory(category, new CategoryResponseListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure() {
                                Toast.makeText(context, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    return false;
                }
            });
        });


        holder.categoryChildrenRecyclerView.setAdapter(new SubCategoryRecyclerViewAdapter(context, category.getChildren()));
        holder.categoryChildrenRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo : start activity wwith result and refresh the fucking activity + change the addcategory to dialog fragment
                categoryRepository.deleteCategory(category, new CategoryResponseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        categories.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(context, "Không thể xóa danh mục này!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText categoryParentName;
        private RecyclerView categoryChildrenRecyclerView;
        private ImageButton editButton;
        private ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryParentName = itemView.findViewById(R.id.textviewCategoryName);
            categoryChildrenRecyclerView = itemView.findViewById(R.id.recycler_view_sub_category);
            editButton = itemView.findViewById(R.id.imageButtonEdit);
            deleteButton = itemView.findViewById(R.id.imageButtonDelete);
        }
    }
}
