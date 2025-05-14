package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class recuperarContrasena extends AppCompatActivity {

    EditText oetteEmail;
    Button obtnAtras, obtnEnviar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recuperar_contrasena);

        obtnAtras = findViewById(R.id.btnAtras);

        obtnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recuperarContrasena.this, MainActivity.class);
                startActivity(intent);
            }
        });

        oetteEmail = findViewById(R.id.etteEmail);
        obtnEnviar = findViewById(R.id.btnEnviar);
        mAuth = FirebaseAuth.getInstance();


        obtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = oetteEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(recuperarContrasena.this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(recuperarContrasena.this, "Correo de recuperación enviado", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(recuperarContrasena.this, "Error al enviar el correo. Verifica que el correo esté registrado.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}