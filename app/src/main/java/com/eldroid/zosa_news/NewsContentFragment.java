package com.eldroid.zosa_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    private TextView titleTextView;
    private TextView contentTextView;
    private TextView dateTextView;
    private TextView authorTextView;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);
        titleTextView = view.findViewById(R.id.news_title);
        contentTextView = view.findViewById(R.id.news_content);
        dateTextView = view.findViewById(R.id.news_date);
        authorTextView = view.findViewById(R.id.news_author);
        imageView = view.findViewById(R.id.news_image);

        // Load content passed from HeadlineListFragment (for portrait mode)
        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String content = getArguments().getString("content");
            String date = getArguments().getString("date");
            String author = getArguments().getString("author");
            int imageResource = getArguments().getInt("image");

            titleTextView.setText(title);
            contentTextView.setText(content);
            dateTextView.setText(date);
            authorTextView.setText("Published by: " + author);
            imageView.setImageResource(imageResource); // Set the image
        }

        return view;
    }

    // Called from HeadlineListFragment to update content in landscape mode
    public void updateContent(String title, String content, String date, String author, int imageResource) {
        if (titleTextView != null && contentTextView != null && dateTextView != null && authorTextView != null && imageView != null) {
            titleTextView.setText(title);
            contentTextView.setText(content);
            dateTextView.setText(date);
            authorTextView.setText("Published by: " + author);
            imageView.setImageResource(imageResource); // Update the image
        }
    }
}


