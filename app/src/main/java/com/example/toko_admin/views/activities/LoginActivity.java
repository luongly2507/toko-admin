package com.example.toko_admin.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.toko_admin.R;
import com.example.toko_admin.databinding.ActivityLoginBinding;
import com.example.toko_admin.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        setContentView(binding.getRoot());

    }
}