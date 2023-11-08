package com.example.coursework2.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework2.R;
import com.example.coursework2.models.Hike;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ContactViewHolder> {

    private List<Hike> hikes;

    public SearchAdapter(List<Hike> hikes) {
        this.hikes = hikes;
    }

    @NonNull
    @Override
    public SearchAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.coursework2.R.layout.search_contact_card, parent, false);
        return new SearchAdapter.ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Hike hike = hikes.get(position);
        holder.hikeNameSearch.setText(hike.hike_name);
        holder.hikeLocationSearch.setText(hike.location);
        holder.hikeDateSearch.setText(hike.date);
    }

    @Override
    public int getItemCount() {return hikes.size();}

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView hikeNameSearch, hikeLocationSearch, hikeDateSearch;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            hikeNameSearch = itemView.findViewById(R.id.hikeNameSearch);
            hikeLocationSearch = itemView.findViewById(R.id.hikeLocationSearch);
            hikeDateSearch = itemView.findViewById(R.id.hikeDateSearch);
        }
    }
}
