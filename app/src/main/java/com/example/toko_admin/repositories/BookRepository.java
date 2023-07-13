package com.example.toko_admin.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.toko_admin.payload.response.BookResponse;
import com.example.toko_admin.payload.response.Page;
import com.example.toko_admin.services.BookService;
import com.example.toko_admin.utils.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private BookService bookService;
    private MutableLiveData<List<BookResponse>> bookResponseLiveData;
    private MutableLiveData<Integer> totalPages;
    public BookRepository()
    {
        bookService = ApiService.getBookService();
        bookResponseLiveData = new MutableLiveData<>();
    }

    public void getAllBooksByPage(int page)
    {
        bookService.getAllBooksByPage(page).enqueue(new Callback<Page<BookResponse>>() {
            @Override
            public void onResponse(Call<Page<BookResponse>> call, Response<Page<BookResponse>> response) {
                if (response.body() != null) {
                    bookResponseLiveData.postValue(response.body().getContent());
                    totalPages.postValue(response.body().getTotalPages());
                }
            }

            @Override
            public void onFailure(Call<Page<BookResponse>> call, Throwable t) {
                System.out.println("Fail: " + t);
                bookResponseLiveData.postValue(null);
                totalPages.postValue(0);
            }
        });
    }
    public LiveData<List<BookResponse>> getBookResponseLiveData()
    {
        return this.bookResponseLiveData;
    }
    public LiveData<Integer> getTotalPagesLiveData()
    {
        return totalPages;
    }

    public void getAllBooksByTitle(String title ,String language ,  String sort , int pageNumber)
    {
        bookService.getAllBookByTitle(title ,language , sort , pageNumber).enqueue(new Callback<Page<BookResponse>>() {
            @Override
            public void onResponse(Call<Page<BookResponse>> call, Response<Page<BookResponse>> response) {
                if(response.body() != null)
                {
                    bookResponseLiveData.postValue(response.body().getContent());
                    totalPages.postValue(response.body().getTotalPages());
                }
            }

            @Override
            public void onFailure(Call<Page<BookResponse>> call, Throwable t) {
                System.out.println("Fail: " + t);
                bookResponseLiveData.postValue(null);
                totalPages.postValue(0);
            }
        });
    }

    public void getAllBooks()
    {
        bookService.getAllBooks().enqueue(new Callback<Page<BookResponse>>() {
            @Override
            public void onResponse(Call<Page<BookResponse>> call, Response<Page<BookResponse>> response) {
                if (response.body() != null) {
                    bookResponseLiveData.postValue(response.body().getContent());
                    totalPages.postValue(response.body().getTotalPages());
                }
            }

            @Override
            public void onFailure(Call<Page<BookResponse>> call, Throwable t) {
                System.out.println("Fail: " + t);
                bookResponseLiveData.postValue(null);
                totalPages.postValue(0);
            }
        });
    }

}
