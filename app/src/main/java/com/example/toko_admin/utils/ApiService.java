package com.example.toko_admin.utils;

import com.example.toko_admin.services.AuthenticationService;
import com.example.toko_admin.services.BookService;
import com.example.toko_admin.services.CategoryService;
import com.example.toko_admin.services.UserService;

public class ApiService {
    public static final String SERVICE_BASE_URL = "http://192.168.1.9:3000/";

    public static CategoryService getCategoryService() {
        return RetrofitClient.getClient(SERVICE_BASE_URL).create(CategoryService.class);
    }
    public static AuthenticationService getAuthenticationService(){
        return RetrofitClient.getClient(SERVICE_BASE_URL).create(AuthenticationService.class);
    }
    public static UserService getUserService(){
        return RetrofitClient.getClient(SERVICE_BASE_URL).create(UserService.class);
    }
    public static BookService getBookService()
    {
        return RetrofitClient.getClient(SERVICE_BASE_URL).create(BookService.class);
    }
}
