package com.example.coursework2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursework2.R;
import com.example.coursework2.adapters.ContactAdapter;
import com.example.coursework2.database.AppDatabase;
import com.example.coursework2.models.Hike;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ContactAdapter.OnDeleteClickListener{

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    List<Hike> hikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hikes = appDatabase.hikeDao().getAllHikes();
        adapter = new ContactAdapter(hikes, this);
        recyclerView.setAdapter(adapter);


        Button add = findViewById(R.id.add_screen2);
        Button home = findViewById(R.id.home_screen2);
        Button search = findViewById(R.id.search_screen2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {viewAdd();}
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {viewHome();}
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {viewSearch();
            }
        });
    }

    @Override
    public void onDeleteClick(Hike hike) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this hike?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Remove from the database
                    appDatabase.hikeDao().deleteHike(hike);
                    // Update the list
                    hikes.remove(hike);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null).show();
    }

    private void viewAdd() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void viewHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    private void viewSearch(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}