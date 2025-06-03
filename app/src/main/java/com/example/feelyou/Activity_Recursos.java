package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Recursos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursos);

        findViewById(R.id.btnLibros).setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_libros.class));
        });

        findViewById(R.id.btnVideos).setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_videos.class));
        });
    }
}