package com.example.listycity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String currentSelectedCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Link cities to Listview
        cityList = findViewById(R.id.city_list);
        String []cities =  {"Edmonton", "Vancouver", "Montreal", "Berlin", "Toronto", "Tokyo", "Las Vegas", "Boston", "Saskatoon"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        // Create City List item click listener
        cityList.setOnItemClickListener(
                (parent, view, position, id) -> {
                    // Fetch clicked city
                    currentSelectedCity = cityAdapter.getItem(position);

                    // Create edit field
                    EditText cityEdit = new EditText(MainActivity.this);
                    cityEdit.setText(currentSelectedCity);
                    cityEdit.setSelection(currentSelectedCity.length());

                    // Create Alert Dialog
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Add/edit city");
                    dialog.setView(cityEdit);

                    // Create confirmation/cancellation buttons
                    dialog.setPositiveButton("Confirm",
                            (dialogTemp, which) ->
                            {
                                dataList.set(position, cityEdit.getText().toString().trim());
                                cityAdapter.notifyDataSetChanged();
                            });

                    dialog.setNegativeButton("Cancel",
                            (dialog1, which) ->
                                    dialog1.dismiss());
                    dialog.show();
                });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}