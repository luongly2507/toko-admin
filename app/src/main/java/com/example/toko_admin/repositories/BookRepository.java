package com.example.toko_admin.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.toko_admin.payload.response.BookResponse;
import com.example.toko_admin.payload.response.PageBookResponse;
import com.example.toko_admin.services.BookService;
import com.example.toko_admin.utils.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private BookService bookService;
    private MutableLiveData<List<BookResponse>> bookResponseLiveData;
    private int totalPages = 0;
    public BookRepository()
    {
        bookService = ApiService.getBookService();
        bookResponseLiveData = new MutableLiveData<>();
    }

    public void getAllBooksByPage(int page)
    {
        bookService.getAllBooksByPage(page).enqueue(new Callback<PageBookResponse>() {
            @Override
            public void onResponse(Call<PageBookResponse> call, Response<PageBookResponse> response) {
                if (response.body() != null) {
                    bookResponseLiveData.postValue(response.body().getContent());
                    totalPages = response.body().getTotalPages();
                }
            }

            @Override
            public void onFailure(Call<PageBookResponse> call, Throwable t) {
                System.out.println("Fail: " + t);
                bookResponseLiveData.postValue(null);
                totalPages = 0;
            }
        });
    }
    public LiveData<List<BookResponse>> getBookResponseLiveData()
    {
        return this.bookResponseLiveData;
    }
    public int getTotalPages()
    {
        return totalPages;
    }
}
