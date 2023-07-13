package com.example.toko_admin.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.toko_admin.adapter.BookRecyclerViewAdapter;
import com.example.toko_admin.databinding.FragmentBookBinding;
import com.example.toko_admin.payload.response.BookResponse;
import com.example.toko_admin.viewmodels.BookViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;
    private BookRecyclerViewAdapter adapter;
    private String language = null;
    private String sort = null;
    private int pageNumber = 0;
    private int oldPageNumber = 0;
    private List<BookResponse> bookList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookViewModel bookViewModel =
                new ViewModelProvider(this).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        binding.recyclerViewBookResult.setLayoutManager(new GridLayoutManager(getActivity() , 2));
        bookViewModel.getAllBooksByPage(0);

        bookViewModel.getBookResponseLiveData().observe(requireActivity(), new Observer<List<BookResponse>>() {
            @Override
            public void onChanged(List<BookResponse> bookResponses) {
                if(bookResponses != null)
                {
                    adapter = new BookRecyclerViewAdapter(bookResponses);
                    binding.recyclerViewBookResult.setAdapter(adapter);
                }
            }
        });

        bookViewModel.totalPages.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer - 1 <= pageNumber)
                {
                    binding.textViewNothing.setVisibility(View.VISIBLE);
                }
                else binding.buttonMore.setVisibility(View.VISIBLE);
            }
        });

        bookViewModel.getBookResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<BookResponse>>() {
            @Override
            public void onChanged(List<BookResponse> bookResponses) {
                if(pageNumber != oldPageNumber)
                {
                    oldPageNumber = pageNumber;
                    if(bookResponses != null) bookList.addAll(bookResponses);
                }
                else bookList = bookResponses;
                adapter = new BookRecyclerViewAdapter(bookList);
                binding.recyclerViewBookResult.setAdapter(adapter);
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != null && !query.isBlank())
                {
                    bookViewModel.getAllBookByTitle(query , language , sort , pageNumber);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        // Để xóa kết quả tìm kiếm và hiển thị tất cả các sách,
        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bookViewModel.getAllBooksByPage(0);
                return false;
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}