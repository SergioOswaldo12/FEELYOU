package com.example.feelyou;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_libros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libros);

    }

        public void abrirLibro1(View view) {
            // Reemplaza con enlace a PDF, o Activity interna si deseas abrirlo dentro de la app
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.casadellibro.com/libros/psicologia-y-pedagogia/psicologia/130006000")));
        }
    }
