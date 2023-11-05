package com.example.coursework2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coursework2.R;

public class DetailActivity extends AppCompatActivity {

    private String[] levelStatus = {"High", "Medium", "Low"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String hikeName = intent.getStringExtra("hikeName");
        String hikeLocation = intent.getStringExtra("hikeLocation");
        String hikeDate = intent.getStringExtra("hikeDate");
//        String hikeParking = intent.getStringExtra("hikeParking");
//        String hikeLength = intent.getStringExtra("hikeDate");
//        String hikeLevel = intent.getStringExtra("hikeLevel");
//        String hikeDescription = intent.getStringExtra("hikeDescription");


        // Hiển thị thông tin lên giao diện người dùng
        TextView nameDetailTV = findViewById(R.id.nameDetail_text);
        TextView locationDetailTV = findViewById(R.id.locationDetail_text);
        TextView dateDetailTV = findViewById(R.id.dateDetail_control);

                            // For Radio Button
//        RadioButton yesDetailRB = findViewById(R.id.rbDetail_yes);
//        RadioButton noDetailRB = findViewById(R.id.rbDetail_no);
//
//        TextView lengthDetailTV = findViewById(R.id.lengthDetail_text);
//        Spinner spinnerDetail = findViewById(R.id.spinnerDetail);
//        TextView descriptionDetailTV = findViewById(R.id.descDetail_text);
//
//        // For Spinner
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levelStatus);
//        spinnerDetail.setAdapter(spinnerAdapter); // Connect adapter to spinner
//        int levelPosition = spinnerAdapter.getPosition(hikeLevel);


        nameDetailTV.setText(hikeName);
        locationDetailTV.setText(hikeLocation);
        dateDetailTV.setText(hikeDate);

//        if(hikeParking != null){
//            if(hikeParking.equals("Yes")){
//                yesDetailRB.setChecked(true);
//            } else if (hikeParking.equals("No")) {
//                noDetailRB.setChecked(true);
//            }
//        }
//
//        lengthDetailTV.setText(hikeLength);
//
//        if (levelPosition >= 0) {
//            spinnerDetail.setSelection(levelPosition);
//        }
//
//        descriptionDetailTV.setText(hikeDescription);
        // Hiển thị các thông tin khác nếu cần
    }
}