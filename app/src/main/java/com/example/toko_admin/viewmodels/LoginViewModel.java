package com.example.toko_admin.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    public MutableLiveData<String> passwordErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> usernameErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> loginErrorMessage = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
