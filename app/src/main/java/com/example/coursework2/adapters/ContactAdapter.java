package com.example.coursework2.adapters;

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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Hike> hikes;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Hike hike);
    }
    public ContactAdapter(List<Hike> hikes, OnDeleteClickListener onDeleteClickListener) {

        this.hikes = hikes;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.coursework2.R.layout.item_contact_card, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Hike hike = hikes.get(position);
        holder.hikeName.setText(hike.hike_name);
        holder.hikeLocation.setText(hike.location);
        holder.hikeDate.setText(hike.date);

        holder.deleteHike.setOnClickListener(v -> {
            if(onDeleteClickListener != null){
                onDeleteClickListener.onDeleteClick(hikes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {return hikes.size();}

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView hikeName, hikeLocation, hikeDate;
        Button deleteHike;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            hikeName = itemView.findViewById(R.id.hikeName);
            hikeLocation = itemView.findViewById(R.id.hikeLocation);
            hikeDate = itemView.findViewById(R.id.hikeDate);
            deleteHike = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
