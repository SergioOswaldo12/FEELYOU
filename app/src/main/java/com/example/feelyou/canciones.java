package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class canciones extends AppCompatActivity {

    private Button btnAdminMusic;
    private final String ADMIN_EMAIL = "jjuarezsa01@ucvvirtual.edu.pe"; // CAMBIA esto por el correo del administrador
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_canciones);

        btnAdminMusic = findViewById(R.id.btnadminmusic);
        btnAdminMusic.setVisibility(View.GONE); // Ocultar por defecto

        String userEmail = getIntent().getStringExtra("userEmail");

        if (userEmail != null && userEmail.equalsIgnoreCase(ADMIN_EMAIL)) {
            btnAdminMusic.setVisibility(View.VISIBLE); // Mostrar solo si es el admin
        }

        btnAdminMusic = findViewById(R.id.btnadminmusic);
        btnAdminMusic.setOnClickListener(view -> {
            Intent intent = new Intent(canciones.this, subirmusic.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerCanciones);
        List<Cancion> canciones = CancionStorage.cargarCanciones(this);
        CancionAdapter adapter = new CancionAdapter(this, canciones);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onResume() {
        super.onResume();
        // Vuelve a cargar el RecyclerView con las canciones actualizadas
        RecyclerView recyclerView = findViewById(R.id.recyclerCanciones);
        List<Cancion> canciones = CancionStorage.cargarCanciones(this);
        CancionAdapter adapter = new CancionAdapter(this, canciones);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}