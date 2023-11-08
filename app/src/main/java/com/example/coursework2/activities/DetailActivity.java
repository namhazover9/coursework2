package com.example.coursework2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework2.R;
import com.example.coursework2.database.AppDatabase;
import com.example.coursework2.models.Hike;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    List<Hike> hikes;
    private String[] levelStatus = {"High", "Medium", "Low"};
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries().build();

        hikes = appDatabase.hikeDao().getAllHikes();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

//        String hikeName = intent.getStringExtra("hikeName");
//        String hikeLocation = intent.getStringExtra("hikeLocation");
//        String hikeDate = intent.getStringExtra("hikeDate");
//        String hikeParking = intent.getStringExtra("hikeParking");
//        String hikeLength = intent.getStringExtra("hikeLength");
//        String hikeLevel = intent.getStringExtra("hikeLevel");
//        String hikeDescription = intent.getStringExtra("hikeDescription");


        EditText nameDetail = findViewById(R.id.nameDetail_text);
        EditText locationDetail = findViewById(R.id.locationDetail_text);
        TextView dateDetail = findViewById(R.id.dateDetail_control);

        // For Radio Button
        RadioButton yesDetailRB = findViewById(R.id.rbDetail_yes);
        RadioButton noDetailRB = findViewById(R.id.rbDetail_no);

        EditText lengthDetail = findViewById(R.id.lengthDetail_text);
        Spinner spinnerDetail = findViewById(R.id.spinnerDetail);
        EditText descriptionDetail = findViewById(R.id.descDetail_text);

        // For Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levelStatus);
        spinnerDetail.setAdapter(spinnerAdapter); // Connect adapter to spinner
        int levelPosition = spinnerAdapter.getPosition(extras.getString("hikeLevel"));


        nameDetail.setText(extras.getString("hikeName"));
        locationDetail.setText(extras.getString("hikeLocation"));
        dateDetail.setText(extras.getString("hikeDate"));

        if(extras.getString("hikeParking") != null){
            if(extras.getString("hikeParking").equals("Yes")){
                yesDetailRB.setChecked(true);
            } else if (extras.getString("hikeParking").equals("No")) {
                noDetailRB.setChecked(true);
            }
        }

        lengthDetail.setText(String.valueOf(extras.getFloat("hikeLength")));

        if (levelPosition >= 0) {
            spinnerDetail.setSelection(levelPosition);
        }

        descriptionDetail.setText(extras.getString("hikeDescription"));


        // ..
        Button editHike = findViewById(R.id.editBtnDetail);
        editHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editHike(extras.getLong("hikeId"));
            }
        });
    }

    private void editHike(long currentHikeId) {
        EditText nameDetail = findViewById(R.id.nameDetail_text);
        EditText locationDetail = findViewById(R.id.locationDetail_text);
        TextView dateDetail = findViewById(R.id.dateDetail_control);
        EditText lengthDetail = findViewById(R.id.lengthDetail_text);
        EditText descDetail = findViewById(R.id.descDetail_text);

        // For Spinner
        Spinner spinnerDetail = findViewById(R.id.spinnerDetail);
        String selectedSpinnerLevel = spinnerDetail.getSelectedItem().toString();

        // For RadioButton
        RadioGroup parkingDetailRG = findViewById(R.id.rgDetail_parking);
        int selectedRadioButtonId = parkingDetailRG.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButtonId);
        String setRadioParkingText = radioButton.getText().toString();

        String updatedName = nameDetail.getText().toString();
        String updatedLocation = locationDetail.getText().toString();
        String updatedDate = dateDetail.getText().toString();
        float updatedLength = Float.parseFloat(lengthDetail.getText().toString());
        String updatedDescription = descDetail.getText().toString();

        Hike currentHike = appDatabase.hikeDao().getHikeById(currentHikeId);

        currentHike.hike_name = updatedName;
        currentHike.location = updatedLocation;
        currentHike.date = updatedDate;
        currentHike.parking = setRadioParkingText;
        currentHike.hike_level = selectedSpinnerLevel;
        currentHike.hike_length = updatedLength;
        currentHike.description = updatedDescription;

        // Update the Hike object in the database
        appDatabase.hikeDao().updateHike(currentHike);

        // Show a success message
        AlertDialog.Builder successBuilder = new AlertDialog.Builder(DetailActivity.this);
        successBuilder.setTitle("Success");
        successBuilder.setMessage("Hike is updated.");
        successBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent= new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        successBuilder.show();
    }

}