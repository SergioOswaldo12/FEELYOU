package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;


public class activity_ejercicio_meditacion extends AppCompatActivity {
    private TextView txtContador;
    private Button btnSiguiente;
    private LottieAnimationView animacionLottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio_meditacion);
// Referencias UI
        txtContador = findViewById(R.id.txtContador);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        animacionLottie = findViewById(R.id.animacionLottie);

        // Iniciar temporizador de 10 minutos (600000 ms)
        new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String tiempo = String.format("%02d:%02d", minutes, seconds);
                txtContador.setText(tiempo);
            }

            @Override
            public void onFinish() {
                txtContador.setText("00:00");
                Toast.makeText(activity_ejercicio_meditacion.this, "¡Tiempo completado!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        // Acción del botón "Siguiente"
        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(activity_ejercicio_meditacion.this, activity_ejercicio_meditacionsegunda.class); // Cambia si es otra activity
            startActivity(intent);
            finish();
        });
    }
}