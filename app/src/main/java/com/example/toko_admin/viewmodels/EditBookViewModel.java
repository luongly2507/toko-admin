package com.example.toko_admin.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class EditBookViewModel extends AndroidViewModel {
    public MutableLiveData<String> titleErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> costErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> priceErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> publisherErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> languageErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> authorsErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> descriptionErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> publishcationDateErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> cost = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();
    public MutableLiveData<String> publisher = new MutableLiveData<>();
    public MutableLiveData<String> language = new MutableLiveData<>();
    public MutableLiveData<String> authors = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<Date> publishcationDate = new MutableLiveData<>();

    public EditBookViewModel(@NonNull Application application) {
        super(application);
    }
}
