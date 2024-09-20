package com.eldroid.zosa_news;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Check if the device is in landscape mode
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentTransaction.replace(R.id.fragment_container_headlines, new HeadlineListFragment());
                fragmentTransaction.replace(R.id.fragment_container_news, new NewsContentFragment());
            } else {
                fragmentTransaction.replace(R.id.fragment_container_headlines, new HeadlineListFragment());
            }

            fragmentTransaction.commit();
        }
    }
}
