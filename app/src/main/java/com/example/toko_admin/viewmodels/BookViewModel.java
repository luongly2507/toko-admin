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

    public BookViewModel() {
        bookRepository = new BookRepository();
        bookResponseLiveData = bookRepository.getBookResponseLiveData();
    }
    public void getAllBooksByPage(int page) {bookRepository.getAllBooksByPage(page);}
    public LiveData<List<BookResponse>> getBookResponseLiveData() {
        return bookResponseLiveData;
    }
    public boolean getMoreBook(int pageNumber)
    {
        if(pageNumber + 1 < bookRepository.getTotalPages())
        {
            this.getAllBooksByPage(pageNumber);
            return true;
        }
        return false;
    }
}