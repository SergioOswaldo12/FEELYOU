package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityDiario extends AppCompatActivity {
    EditText etEntradaDiario;
    TextView tvContenidoGuardado;
    Button btnGuardar, btnLeer, btnSeguir, btnSalir;

    FirebaseFirestore db;
    FirebaseAuth auth;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diario);
        etEntradaDiario = findViewById(R.id.etEntradaDiario);
        tvContenidoGuardado = findViewById(R.id.tvContenidoGuardado);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLeer = findViewById(R.id.btnLeer);
        btnSeguir = findViewById(R.id.btnSeguir);
        btnSalir = findViewById(R.id.btnSalir);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        btnGuardar.setOnClickListener(v -> guardarEntrada());

        btnLeer.setOnClickListener(v -> leerEntradas());

        btnSeguir.setOnClickListener(v -> {
            etEntradaDiario.setEnabled(true);
            etEntradaDiario.requestFocus();
        });

        btnSalir.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityDiario.this, menu.class); // cambia a tu activity principal
            startActivity(intent);
            finish();
        });
    }

    private void guardarEntrada() {
        String texto = etEntradaDiario.getText().toString().trim();
        if (texto.isEmpty()) {
            Toast.makeText(this, "Escribe algo antes de guardar.", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> entrada = new HashMap<>();
        entrada.put("usuario", userId);
        entrada.put("mensaje", texto);
        entrada.put("fecha", new Date());

        db.collection("diarios")
                .add(entrada)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
                    etEntradaDiario.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                });
    }

    private void leerEntradas() {
        db.collection("diarios")
                .whereEqualTo("usuario", userId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    StringBuilder contenido = new StringBuilder();
                    for (var doc : queryDocumentSnapshots) {
                        String fecha = doc.getDate("fecha").toString();
                        String mensaje = doc.getString("mensaje");
                        contenido.append("📅 ").append(fecha).append("\n")
                                .append(mensaje).append("\n\n");
                    }
                    tvContenidoGuardado.setText(contenido.toString());
                    etEntradaDiario.setEnabled(false);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al leer entradas", Toast.LENGTH_SHORT).show();
                });
    }
}