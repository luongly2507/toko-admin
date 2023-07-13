package com.example.toko_admin.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.toko_admin.payload.response.BookResponse;
import com.example.toko_admin.repositories.BookRepository;

import java.util.List;

public class BookViewModel extends ViewModel {
    private LiveData<List<BookResponse>> bookResponseLiveData;
    private BookRepository bookRepository;
    public LiveData<Integer> totalPages;

    public BookViewModel() {
        bookRepository = new BookRepository();
        bookResponseLiveData = bookRepository.getBookResponseLiveData();
        totalPages = bookRepository.getTotalPagesLiveData();

    }
    public void getAllBooksByPage(int page) {bookRepository.getAllBooksByPage(page);}
    public LiveData<List<BookResponse>> getBookResponseLiveData() {
        return bookResponseLiveData;
    }
    public boolean getMoreBook(int pageNumber)
    {
        this.getAllBooksByPage(pageNumber);
        return true;
    }

    public void getAllBookByTitle(String title ,String language, String sort , int pageNumber)
    {
        bookRepository.getAllBooksByTitle(title ,language, sort , pageNumber);

    }

}