package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ejercicios extends AppCompatActivity {
    private CardView card1;
    private CardView card2;
    private CardView card3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicios);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        card1.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(ejercicios.this, VideoEjercicio1.class);
                intent.putExtra("userEmail", email);
                intent.putExtra("modulo", "TODO EL CUERPO");
                startActivity(intent);
            }
        });

        card2.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(ejercicios.this, activity_ejercicio_meditacion.class);
                intent.putExtra("userEmail", email);
                intent.putExtra("modulo2", "Meditación");
                startActivity(intent);
            }
        });

        card3.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(ejercicios.this, ActivityEjercicioGuiado.class);
                intent.putExtra("userEmail", email);
                intent.putExtra("modulo2", "EjerciciosGuiados");
                startActivity(intent);
            }
        });
    }
}