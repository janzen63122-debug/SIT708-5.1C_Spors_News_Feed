package com.example.sit708_51c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsItem> newsList;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }


    public NewsAdapter(List<NewsItem> newsList, OnItemClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_card, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem currentItem = newsList.get(position);


        holder.titleText.setText(currentItem.getTitle());
        holder.descText.setText(currentItem.getDescription());
        holder.newsImage.setImageResource(currentItem.getImageResId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(currentItem);
                }
            }
        });
    }


    public void updateList(List<NewsItem> newList) {
        this.newsList = newList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView titleText;
        TextView descText;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            // matching
            newsImage = itemView.findViewById(R.id.newsImageView);
            titleText = itemView.findViewById(R.id.newsTitleTextView);
            descText = itemView.findViewById(R.id.newsDescTextView);
        }
    }
}