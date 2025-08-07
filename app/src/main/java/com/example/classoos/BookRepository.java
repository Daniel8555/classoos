package com.example.classoos;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {

    private ApiService apiService;
    private BookDao bookDao;
    private LiveData<List<Book>> booksFromDb;

    private static final String BASE_URL = "https://my.dev.classoos.com/rest_api.php/";

    public BookRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        bookDao = db.bookDao();
        booksFromDb = bookDao.getAllBooks();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<List<Book>> getBooks() {
        return booksFromDb;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void fetchAndSaveBooks() {
        apiService.getBooks(
                "Bearer 45877fb82a853af70fd4d60cbf2a55f2fd277700",
                "application/json",
                "1.0",
                20,
                "textbooks",
                "DESC",
                "version_date",
                1
        ).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                    List<ApiResponse.BookItem> apiBooks = response.body().data.bookList;
                    if (apiBooks == null || apiBooks.isEmpty()) {
                        return;
                    }
                    List<Book> books = new ArrayList<>();
                    for (ApiResponse.BookItem item : apiBooks) {
                        books.add(new Book(
                                item.id,
                                item.subject,
                                item.title,
                                item.author,
                                item.typeName,
                                item.coverUrl
                        ));
                    }
                    CompletableFuture.runAsync(() -> {
                        bookDao.clearAll();
                        bookDao.insertBooks(books);});

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }
}
