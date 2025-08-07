package com.example.classoos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey
    public int id;

    public String subject_name;
    public String title;
    public String author;
    public String type_name;
    public String cover_url;

    public Book(int id, String subject_name, String title, String author, String type_name, String cover_url) {
        this.id = id;
        this.subject_name = subject_name;
        this.title = title;
        this.author = author;
        this.type_name = type_name;
        this.cover_url = cover_url;
    }
}
