package com.example.toko_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toko_admin.R;
import com.example.toko_admin.models.Category;
import com.example.toko_admin.repositories.CategoryRepository;
import com.example.toko_admin.services.CategoryResponseListener;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryRecyclerViewAdapter extends RecyclerView.Adapter<SubCategoryRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Category> listCategoryChildren;
    private CategoryRepository categoryRepository;

    public SubCategoryRecyclerViewAdapter(Context context, List<Category> listCategoryChildren) {
        this.context = context;
        this.listCategoryChildren =  listCategoryChildren;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view_sub_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        categoryRepository = new CategoryRepository();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = listCategoryChildren.get(position);
        String name = listCategoryChildren.get(position).getName();
        holder.categoryChildrenName.setText(name);

        holder.editButton.setOnClickListener(view -> {
            holder.categoryChildrenName.setEnabled(true);
            holder.categoryChildrenName.requestFocus();
            holder.categoryChildrenName.setSelection(holder.categoryChildrenName.length());
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(holder.categoryChildrenName, InputMethodManager.SHOW_IMPLICIT);
            holder.categoryChildrenName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        imm.hideSoftInputFromWindow(holder.categoryChildrenName.getWindowToken(), 0);
                        holder.categoryChildrenName.clearFocus();

                        //update
                        category.setName(holder.categoryChildrenName.getText().toString());
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
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryRepository.deleteCategory(category, new CategoryResponseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        listCategoryChildren.remove(position);
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
        if (listCategoryChildren != null) {
            return listCategoryChildren.size();
        }
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText categoryChildrenName;
        private ImageButton editButton;
        private ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryChildrenName = itemView.findViewById(R.id.textviewSubCategoryName);
            editButton = itemView.findViewById(R.id.imageButtonEdit);
            deleteButton = itemView.findViewById(R.id.imageButtonDelete);
        }
    }
}
