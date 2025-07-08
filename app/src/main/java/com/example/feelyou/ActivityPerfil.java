package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityPerfil extends AppCompatActivity {

    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnEditar = findViewById(R.id.btn_editar);

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityPerfil.this, activity_editar_perfil.class);
            startActivity(intent);
        });
    }
}