package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class activity_ejercicio_meditacionsegunda extends AppCompatActivity {
    private TextView txtContador;
    private Button btnAutoevaluacion;
    private LottieAnimationView animacionLottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio_meditacionsegunda);
        txtContador = findViewById(R.id.txtContador);
        btnAutoevaluacion = findViewById(R.id.btnAutoevaluacion);
        animacionLottie = findViewById(R.id.animacionLottie);

        // Iniciar temporizador de 10 minutos
        new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                txtContador.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                txtContador.setText("00:00");
                Toast.makeText(activity_ejercicio_meditacionsegunda.this, "¡Tiempo finalizado!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        // Ir al formulario de autoevaluación
        btnAutoevaluacion.setOnClickListener(v -> {
            Intent intent = new Intent(activity_ejercicio_meditacionsegunda.this, ActivityFormularioMeditacion.class); // formulario sobre ambos ejercicios
            startActivity(intent);
            finish();
        });
    }
}