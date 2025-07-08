package com.example.feelyou;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityQuienesSomos extends AppCompatActivity {

    TextView tvContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quienes_somos);

        tvContenido = findViewById(R.id.tv_contenido);

        // Animación simple de entrada
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(1000);
        tvContenido.startAnimation(fadeIn);

        // Contenido
        String texto = "FELL YOU es una aplicación creada para mejorar el bienestar emocional de las personas, especialmente "
                + "en regiones con acceso limitado a apoyo psicológico como Piura.\n\n"
                + "A través del uso de tecnología móvil, buscamos acercar herramientas de apoyo emocional de "
                + "forma accesible, moderna y efectiva.\n\n"
                + "Nuestra misión es brindar un acompañamiento cercano y confiable para quienes enfrentan ansiedad, estrés o depresión, "
                + "contribuyendo al desarrollo de una sociedad más saludable y resiliente.";

        tvContenido.setText(texto);
    }
}
