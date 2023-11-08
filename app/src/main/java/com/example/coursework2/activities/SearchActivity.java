package com.example.coursework2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursework2.R;
import com.example.coursework2.adapters.ContactAdapter;
import com.example.coursework2.adapters.SearchAdapter;
import com.example.coursework2.database.AppDatabase;
import com.example.coursework2.models.Hike;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    List<Hike> hikes;

    private EditText editTextSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.recyclerViewSearch);
        editTextSearch = findViewById(R.id.search_text);
        btnSearch = findViewById(R.id.searchBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //List<Hike> hikes = appDatabase.hikeDao().getAllHikes();
//
//        searchAdapter = new SearchAdapter(hikes);
//        recyclerView.setAdapter(searchAdapter);

        // For Search Button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editTextSearch.getText().toString().trim();
                hikes = appDatabase.hikeDao().searchHikesByName(query);
                searchAdapter = new SearchAdapter(hikes);
                recyclerView.setAdapter(searchAdapter);
            }
        });


        Button add = findViewById(R.id.add_screen3);
        Button home = findViewById(R.id.home_screen3);
        Button search = findViewById(R.id.search_screen3);
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