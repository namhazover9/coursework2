package com.example.coursework2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursework2.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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