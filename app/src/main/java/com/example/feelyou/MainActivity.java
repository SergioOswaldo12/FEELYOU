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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button obtnRecuperarp, obtncrear, obtnIniciarSesion;
    EditText oeditTextTextEmailAddress, oeditTextTextPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        obtnRecuperarp = findViewById(R.id.btnRecuperarp);

        obtnRecuperarp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, recuperarContrasena.class);
                startActivity(intent);
            }
        });

        obtncrear = findViewById(R.id.btncrear);

        obtncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, crearCuenta.class);
                startActivity(intent1);
            }
        });

        obtnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        oeditTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        oeditTextTextPassword = findViewById(R.id.editTextTextPassword);

        // Botón iniciar sesión
        obtnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = oeditTextTextEmailAddress.getText().toString().trim();
                String password = oeditTextTextPassword.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser(email, password);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(MainActivity.this, menu.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Error al iniciar sesión. Verifique sus datos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}