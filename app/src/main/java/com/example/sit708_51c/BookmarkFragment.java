package com.example.sit708_51c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookmarkFragment extends Fragment {

    private RecyclerView bookmarksRecyclerView;
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);


        bookmarksRecyclerView = view.findViewById(R.id.bookmarksRecyclerView);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        SharedPreferences prefs = requireActivity().getSharedPreferences("MyBookmarks", Context.MODE_PRIVATE);
        Set<String> savedTitles = prefs.getStringSet("bookmarked_titles", new HashSet<>());


        List<NewsItem> allDummyData = getAllDummyData();
        List<NewsItem> bookmarkedStories = new ArrayList<>();


        for (NewsItem item : allDummyData) {
            if (savedTitles.contains(item.getTitle())) {
                bookmarkedStories.add(item);
            }
        }


        adapter = new NewsAdapter(bookmarkedStories, item -> {

            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TITLE", item.getTitle());
            bundle.putString("DESC", item.getDescription());
            bundle.putInt("IMAGE", item.getImageResId());
            bundle.putString("CATEGORY", item.getCategory());
            detailFragment.setArguments(bundle);

            if (getParentFragmentManager() != null) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        bookmarksRecyclerView.setAdapter(adapter);

        return view;
    }


    private List<NewsItem> getAllDummyData() {
        List<NewsItem> list = new ArrayList<>();

        // Top Stories
        list.add(new NewsItem("Grand Final Thriller", "An amazing match that went down to the wire in the final quarter.", R.drawable.afl_1, "Football"));
        list.add(new NewsItem("New World Record", "Swimmer breaks the 100m freestyle record by a full second.", R.drawable.swim_1, "Swimming"));
        list.add(new NewsItem("Tennis Open Finals", "Underdog takes the championship in straight sets.", R.drawable.tennis_1, "Tennis"));

        // Latest News
        list.add(new NewsItem("Basketball Playoffs", "Local team secures a spot in the semi-finals after a stunning comeback.", R.drawable.basketball_1, "Basketball"));
        list.add(new NewsItem("Cricket Test Match", "Rain delays play on day 3, leaving the match in a tense draw.", R.drawable.cricket_1, "Cricket"));
        list.add(new NewsItem("Cycling Tour", "Mountain stage proves too difficult for the current race leader.", R.drawable.cycling_1, "Cycling"));
        list.add(new NewsItem("Formula 1 Updates", "New aerodynamic packages introduced for the upcoming race weekend.", R.drawable.motorsport_1, "Motorsport"));

        return list;
    }
}