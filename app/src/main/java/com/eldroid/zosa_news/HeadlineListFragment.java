package com.eldroid.zosa_news;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class HeadlineListFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Example news headlines from the Philippines
        String[] headlines = {
                "Typhoon Signal Raised Over Parts of Luzon",
                "Inflation Rate in the Philippines Eases in August",
                "New Infrastructure Projects Launched in Metro Manila"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, headlines);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // Example news content based on selected headline
        String[] contents = {
                "PAGASA has raised a Typhoon Signal as tropical storm 'Egay' approaches the northern regions of the Philippines. The storm is expected to bring heavy rains and strong winds, prompting local authorities to prepare for potential evacuations. Residents are advised to secure their properties and stay tuned for updates from local weather stations. Emergency services are on alert, and schools in affected areas may suspend classes as a precaution.",
                "The inflation rate in the Philippines has eased slightly according to recent reports. Experts attribute this decrease to stabilizing food prices and improved supply chains. However, concerns remain regarding the overall cost of living, particularly for low-income households. Analysts suggest that continued monitoring is essential to ensure that inflation remains manageable and does not adversely affect consumer purchasing power.",
                "The government has launched new infrastructure projects aimed at improving connectivity and transportation in Metro Manila. These projects include the construction of new roads, bridges, and public transport facilities. Officials believe these developments will significantly reduce traffic congestion and boost economic growth in the region. Public consultations are being held to gather community input and address any concerns."
        };

        String[] titles = {
                "Typhoon Signal Raised Over Parts of Luzon",
                "Inflation Rate in the Philippines Eases",
                "New Infrastructure Projects Launched in Metro Manila"
        };

        String[] dates = {
                "September 20, 2024, 10:00 AM",
                "September 18, 2024, 09:45 AM",
                "September 17, 2024, 11:15 AM"
        };

        String[] authors = {
                "Jane Doe",
                "Emily Johnson",
                "Michael Brown"
        };

        // Example image URLs or resource IDs
        int[] images = {
                R.drawable.typhoon_image,  // Replace with your image resource
                R.drawable.inflation_image, // Replace with your image resource
                R.drawable.infra_image      // Replace with your image resource
        };

        String selectedContent = contents[position];
        String selectedTitle = titles[position];
        String selectedDate = dates[position];
        String selectedAuthor = authors[position];
        int selectedImage = images[position];

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape mode, show both fragments
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                    .findFragmentById(R.id.fragment_container_news);
            if (newsContentFragment == null) {
                newsContentFragment = new NewsContentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_news, newsContentFragment);
                transaction.commit();
            }
            newsContentFragment.updateContent(selectedTitle, selectedContent, selectedDate, selectedAuthor, selectedImage);
        } else {
            // In portrait mode, replace the headline list with the content fragment
            NewsContentFragment newsContentFragment = new NewsContentFragment();
            Bundle args = new Bundle();
            args.putString("title", selectedTitle);
            args.putString("content", selectedContent);
            args.putString("date", selectedDate);
            args.putString("author", selectedAuthor);
            args.putInt("image", selectedImage);
            newsContentFragment.setArguments(args);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_headlines, newsContentFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }



}
