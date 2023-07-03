package com.example.toko_admin.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.toko_admin.adapter.BookRecyclerViewAdapter;
import com.example.toko_admin.databinding.FragmentBookBinding;
import com.example.toko_admin.payload.response.BookResponse;
import com.example.toko_admin.viewmodels.BookViewModel;

import java.util.List;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;
    private BookRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookViewModel homeViewModel =
                new ViewModelProvider(this).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        binding.recyclerViewBook.setLayoutManager(new GridLayoutManager(getActivity() , 2));
        homeViewModel.getAllBooksByPage(0);
        homeViewModel.getBookResponseLiveData().observe(getActivity(), new Observer<List<BookResponse>>() {
            @Override
            public void onChanged(List<BookResponse> bookResponses) {
                if(bookResponses != null)
                {
                    adapter = new BookRecyclerViewAdapter(bookResponses);
                    binding.recyclerViewBook.setAdapter(adapter);
                }
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