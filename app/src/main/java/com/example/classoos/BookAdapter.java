package com.example.classoos;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.title);
        holder.subjectName.setText(book.subject_name);
        holder.author.setText(book.author);
        holder.typeName.setText(book.type_name);

        String snippet = book.title.length() > 20 ? book.title.substring(0, 20) + "..." : book.title;
        holder.snippet.setText(snippet);

        Glide.with(holder.cover.getContext())
                .load(book.cover_url)
                .into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView subjectName, title, author, typeName, snippet;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.coverImageView);
            subjectName = itemView.findViewById(R.id.subjectNameTextView);
            title = itemView.findViewById(R.id.titleTextView);
            author = itemView.findViewById(R.id.authorTextView);
            typeName = itemView.findViewById(R.id.typeNameTextView);
            snippet = itemView.findViewById(R.id.snippetTextView);
        }
    }
}
