package com.example.temasapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button ThemeButton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("theme_prefs", MODE_PRIVATE);
        int selectedTheme = preferences.getInt("selected_theme", R.style.AppTheme);
        setTheme(selectedTheme);

        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        ThemeButton = findViewById(R.id.btnApplyTheme);

        setupSpinner();

        ThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Button clicked");
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("selected_theme");
                editor.apply();

                Log.d("MainActivity", "Default theme applied");

                recreate();
            }
        });
    }

    private void setupSpinner() {
        String[] themeNames = getResources().getStringArray(R.array.themes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, themeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedTheme = (String) parentView.getItemAtPosition(position);
                Log.d("MainActivity", "Selected Theme: " + selectedTheme);

                int themeId;
                switch (selectedTheme) {
                    case "Theme1":
                        themeId = R.style.Theme1;
                        break;
                    case "Theme2":
                        themeId = R.style.Theme2;
                        break;
                    case "Theme3":
                        themeId = R.style.Theme3;
                        break;
                    default:

                        return;
                }

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("selected_theme", themeId);
                editor.apply();

                recreate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
