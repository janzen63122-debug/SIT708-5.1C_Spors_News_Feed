package com.example.sit708_51c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetailFragment extends Fragment {

    private ImageView detailImage;
    private TextView detailTitle, detailDesc;
    private Button bookmarkButton;
    private RecyclerView relatedRecyclerView;
    private NewsAdapter relatedAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        detailImage = view.findViewById(R.id.detailImageView);
        detailTitle = view.findViewById(R.id.detailTitleTextView);
        detailDesc = view.findViewById(R.id.detailDescTextView);
        bookmarkButton = view.findViewById(R.id.bookmarkButton);
        relatedRecyclerView = view.findViewById(R.id.relatedRecyclerView);


        if (getArguments() != null) {
            String title = getArguments().getString("TITLE");
            String desc = getArguments().getString("DESC");
            int imageRes = getArguments().getInt("IMAGE");


            detailTitle.setText(title);
            detailDesc.setText(desc);
            detailImage.setImageResource(imageRes);
        }


        bookmarkButton.setOnClickListener(v -> {

            SharedPreferences prefs = requireActivity().getSharedPreferences("MyBookmarks", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();


            Set<String> savedTitles = prefs.getStringSet("bookmarked_titles", new HashSet<>());


            Set<String> updatedTitles = new HashSet<>(savedTitles);


            if (getArguments() != null) {
                String currentTitle = getArguments().getString("TITLE");
                updatedTitles.add(currentTitle);


                editor.putStringSet("bookmarked_titles", updatedTitles);
                editor.apply();

                Toast.makeText(getContext(), "Story Bookmarked!", Toast.LENGTH_SHORT).show();
            }
        });

        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<NewsItem> relatedData = generateRelatedData();

        relatedAdapter = new NewsAdapter(relatedData, item -> {

            DetailFragment newDetailFragment = new DetailFragment();


            Bundle bundle = new Bundle();
            bundle.putString("TITLE", item.getTitle());
            bundle.putString("DESC", item.getDescription());
            bundle.putInt("IMAGE", item.getImageResId());
            bundle.putString("CATEGORY", item.getCategory());
            newDetailFragment.setArguments(bundle);


            if (getParentFragmentManager() != null) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, newDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        relatedRecyclerView.setAdapter(relatedAdapter);

        return view;
    }

    private List<NewsItem> generateRelatedData() {
        List<NewsItem> list = new ArrayList<>();
        list.add(new NewsItem("More on this sport", "Another interesting article related to this category.", R.drawable.sports_1, "General"));
        list.add(new NewsItem("Player Interview", "Exclusive interview with the star of the match.", R.drawable.interview_1, "General"));
        return list;
    }
}