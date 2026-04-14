package com.example.sit708_51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<NewsItem> originalTopStories;
    private List<NewsItem> originalLatestNews;
    private RecyclerView topStoriesRecyclerView;
    private RecyclerView latestNewsRecyclerView;
    private NewsAdapter topStoriesAdapter;
    private NewsAdapter latestNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        topStoriesRecyclerView = view.findViewById(R.id.topStoriesRecyclerView);
        latestNewsRecyclerView = view.findViewById(R.id.latestNewsRecyclerView);


        androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.searchView);

        Button viewBookmarksBtn = view.findViewById(R.id.viewBookmarksButton);
        viewBookmarksBtn.setOnClickListener(v -> {
            if (getParentFragmentManager() != null) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BookmarkFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        latestNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        originalTopStories = generateTopStoriesData();
        originalLatestNews = generateLatestNewsData();


        NewsAdapter.OnItemClickListener clickListener = new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem item) {
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
            }
        };


        topStoriesAdapter = new NewsAdapter(originalTopStories, clickListener);
        latestNewsAdapter = new NewsAdapter(originalLatestNews, clickListener);

        topStoriesRecyclerView.setAdapter(topStoriesAdapter);
        latestNewsRecyclerView.setAdapter(latestNewsAdapter);

        // search bar
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterLists(newText);
                return true;
            }
        });

        return view;
    }

    private void filterLists(String text) {
        List<NewsItem> filteredTop = new ArrayList<>();
        List<NewsItem> filteredLatest = new ArrayList<>();

        // If the search bar is empty, show everything
        if (text.isEmpty()) {
            filteredTop.addAll(originalTopStories);
            filteredLatest.addAll(originalLatestNews);
        } else {

            String filterPattern = text.toLowerCase().trim();


            for (NewsItem item : originalTopStories) {

                if (item.getCategory().toLowerCase().contains(filterPattern)) {
                    filteredTop.add(item);
                }
            }


            for (NewsItem item : originalLatestNews) {
                if (item.getCategory().toLowerCase().contains(filterPattern)) {
                    filteredLatest.add(item);
                }
            }
        }


        topStoriesAdapter.updateList(filteredTop);
        latestNewsAdapter.updateList(filteredLatest);
    }


    private List<NewsItem> generateTopStoriesData() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("Grand Final Thriller", "An amazing match that went down to the wire in the final quarter.", R.drawable.afl_1, "Football"));
        list.add(new NewsItem("New World Record", "Swimmer breaks the 100m freestyle record by a full second.", R.drawable.swim_1, "Swimming"));
        list.add(new NewsItem("Tennis Open Finals", "Underdog takes the championship in straight sets.", R.drawable.tennis_1, "Tennis"));
        return list;
    }

    private List<NewsItem> generateLatestNewsData() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("Basketball Playoffs", "Local team secures a spot in the semi-finals after a stunning comeback.", R.drawable.basketball_1, "Basketball"));
        list.add(new NewsItem("Cricket Test Match", "Rain delays play on day 3, leaving the match in a tense draw.", R.drawable.cricket_1, "Cricket"));
        list.add(new NewsItem("Cycling Tour", "Mountain stage proves too difficult for the current race leader.", R.drawable.cycling_1, "Cycling"));
        list.add(new NewsItem("Formula 1 Updates", "New aerodynamic packages introduced for the upcoming race weekend.", R.drawable.motorsport_1, "Motorsport"));
        return list;
    }
}