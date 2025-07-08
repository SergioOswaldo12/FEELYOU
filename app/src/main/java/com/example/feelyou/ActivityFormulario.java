package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ActivityFormulario extends AppCompatActivity {

    private EditText[] preguntas = new EditText[10];
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario);

        // Vincular los EditText por ID
        preguntas[0] = findViewById(R.id.pregunta1);
        preguntas[1] = findViewById(R.id.pregunta2);
        preguntas[2] = findViewById(R.id.pregunta3);
        preguntas[3] = findViewById(R.id.pregunta4);
        preguntas[4] = findViewById(R.id.pregunta5);
        preguntas[5] = findViewById(R.id.pregunta6);
        preguntas[6] = findViewById(R.id.pregunta7);
        preguntas[7] = findViewById(R.id.pregunta8);
        preguntas[8] = findViewById(R.id.pregunta9);
        preguntas[9] = findViewById(R.id.pregunta10);

        btnEnviar = findViewById(R.id.btnEnviarRespuestas);
        btnEnviar.setOnClickListener(v -> guardarRespuestas());
    }

    private void guardarRespuestas() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_LONG).show();
            return;
        }

        String correo = auth.getCurrentUser().getEmail();

        Map<String, Object> datos = new HashMap<>();
        datos.put("usuario", correo);

        // Agregar respuestas
        for (int i = 0; i < preguntas.length; i++) {
            datos.put("respuesta_" + (i + 1), preguntas[i].getText().toString().trim());
        }

        // Agregar fecha y hora
        String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String hora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        datos.put("fecha", fecha);
        datos.put("hora", hora);

        // Guardar en Firebase
        db.collection("modulos")
                .document("modulo_1")
                .collection("ejercicio_1")
                .add(datos)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "¡Gracias por tu respuesta!", Toast.LENGTH_LONG).show();
                    // Ir a la pantalla principal de módulos
                    Intent intent = new Intent(ActivityFormulario.this, ejercicios.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Cierra el formulario actual
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                    Toast.makeText(this, "Error al guardar. Intenta de nuevo.", Toast.LENGTH_LONG).show();
                });
    }
}
