package com.example.classoos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse {
    public DataWrapper data;

    public class DataWrapper {
        @SerializedName("data")
        public List<BookItem> bookList;
    }

    public class BookItem {
        public String author;
        @SerializedName("cover_url")
        public String coverUrl;
        @SerializedName("subject_name")
        public String subject;
        public int id;
        public String title;
        @SerializedName("type_name")
        public String typeName;
    }
}
