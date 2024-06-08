package com.example.temasapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;

    // Variable estática para mantener el tema seleccionado
    static int selectedTheme = R.style.Tema_basico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Aplicar el tema seleccionado
        setTheme(selectedTheme);

        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);

        setupSpinner();
    }

    private void setupSpinner() {
        String[] themeNames = getResources().getStringArray(R.array.themes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, themeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedThemeName = (String) parentView.getItemAtPosition(position);
                Log.d("MainActivity", "Selected Theme: " + selectedThemeName);

                int newTheme;
                switch (selectedThemeName) {
                    case "Seleccionar":
                        // No hacer nada si se selecciona "Seleccionar"
                        return;
                    case "Tema_basico":
                        newTheme = R.style.Tema_basico;
                        break;
                    case "Tema_morado":
                        newTheme = R.style.Tema_morado;
                        break;
                    case "Tema_naranja":
                        newTheme = R.style.Tema_naranja;
                        break;
                    case "Tema_azulado":
                        newTheme = R.style.Tema_azulado;
                        break;
                    default:
                        return;
                }

                // Verificar si el nuevo tema es diferente al tema actual
                if (newTheme != selectedTheme) {
                    selectedTheme = newTheme;
                    // Aplicar el nuevo tema
                    recreateWithTheme(selectedTheme);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada si no se selecciona nada
            }
        });
    }

    // Método para recrear la actividad con un nuevo tema
    private void recreateWithTheme(int themeId) {
        setTheme(themeId);
        recreate(); // Recrear la actividad para aplicar el nuevo tema a toda la interfaz
    }
}

