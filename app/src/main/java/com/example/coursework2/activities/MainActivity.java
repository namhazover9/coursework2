package com.example.coursework2.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework2.R;
import com.example.coursework2.database.AppDatabase;
import com.example.coursework2.models.Hike;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private String[] levelStatus = {"High", "Medium", "Low"};
    Spinner spinner;
    TextView dateControl;


    // DatePicker Fragment
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((MainActivity) getActivity()).updateDOB(dob);
        }
    }

    public void updateDOB(LocalDate dob) {
        TextView dobControl = findViewById(R.id.date_control);
        dobControl.setText(dob.toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        // For Spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levelStatus);
        spinner.setAdapter(dataAdapter); // Connect adapter to spinner


        dateControl = findViewById(R.id.date_control);
        dateControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        Button add = findViewById(R.id.add_screen);
        Button home = findViewById(R.id.home_screen);
        Button search = findViewById(R.id.search_screen);
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

    private void saveDetails() {
        // For EditText
        EditText nameTxt = findViewById(R.id.name_text);
        EditText locationTxt = findViewById(R.id.location_text);
        dateControl = findViewById(R.id.date_control);
        EditText lengthTxt = findViewById(R.id.length_text);
        EditText descTxt = findViewById(R.id.desc_text);

        // For Spinner
        Spinner spinner = findViewById(R.id.spinner);
        String selectedSpinnerLevel = spinner.getSelectedItem().toString();

        // For RadioButton
        RadioGroup radioGroup = findViewById(R.id.rg_parking);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButtonId);
        String setRadioParkingText = radioButton.getText().toString();


        String name = nameTxt.getText().toString();
        String location = locationTxt.getText().toString();
        String date = dateControl.getText().toString();
        float length = Float.parseFloat(lengthTxt.getText().toString());
        String description = descTxt.getText().toString();

        Hike hike = new Hike();
        hike.hike_name = name;
        hike.location = location;
        hike.date = date;
        hike.parking = setRadioParkingText;
        hike.hike_length = length;
        hike.hike_level = selectedSpinnerLevel;
        hike.description = description;


        long hikeId = appDatabase.hikeDao().insertHike(hike);

        Toast.makeText(this, "Hike has been created with id: " + hikeId,
                Toast.LENGTH_LONG
        ).show();
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