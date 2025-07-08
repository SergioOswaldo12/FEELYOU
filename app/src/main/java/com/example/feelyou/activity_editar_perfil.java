package com.example.feelyou;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class activity_editar_perfil extends AppCompatActivity {

    private EditText etNombre, etApellido, etCorreo, etFechaNacimiento, etCiudad;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etCorreo = findViewById(R.id.et_correo);
        etFechaNacimiento = findViewById(R.id.et_fecha_nacimiento);
        etCiudad = findViewById(R.id.et_ciudad);
        btnGuardar = findViewById(R.id.btn_guardar);

        // Selector de fecha
        etFechaNacimiento.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year1, month1, dayOfMonth) -> {
                        String fecha = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        etFechaNacimiento.setText(fecha);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        btnGuardar.setOnClickListener(v -> {
            // Aquí puedes guardar los datos en SharedPreferences, Firebase, etc.
            Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish(); // cerrar esta pantalla
        });
    }
}