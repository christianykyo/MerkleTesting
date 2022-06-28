package com.example.merkletesting.Model;

import com.example.merkletesting.Model.Carts.Cart;
import com.example.merkletesting.Model.Users.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIEndPoints {
    @FormUrlEncoded
    @POST("auth/login")
    Call<User> login(
            @Field("username") String username,
            @Field("password") String password);

    @GET("users")
    Call<List<User>> users();

    @GET("carts")
    Call<List<Cart>> carts();
}
