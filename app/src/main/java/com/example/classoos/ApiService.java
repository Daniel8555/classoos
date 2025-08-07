package com.example.classoos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users/me/contents")
    Call<ApiResponse> getBooks(
            @Header("Authorization") String auth,
            @Header("Content-Type") String contentType,
            @Header("Kalsefer-API") String kalseferApi,
            @Query("batch") int batch,
            @Query("book_type") String bookType,
            @Query("order_by_direction") String orderByDir,
            @Query("order_by_field") String orderByField,
            @Query("page") int page
    );
}
