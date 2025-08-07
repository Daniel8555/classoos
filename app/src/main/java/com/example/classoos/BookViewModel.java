package com.example.classoos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BookViewModel extends ViewModel {

    private BookRepository repository;
    private LiveData<List<Book>> books;

    public BookViewModel(BookRepository repository) {
        this.repository = repository;
        books = repository.getBooks();
        books.observeForever(list -> {
            if (list == null || list.isEmpty()) {
                repository.fetchAndSaveBooks();
            }
        });
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

}
