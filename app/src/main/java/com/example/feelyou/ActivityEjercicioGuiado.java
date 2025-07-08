package com.example.feelyou;

import android.content.Intent;
import android.media.MediaPlayer;
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

public class ActivityEjercicioGuiado extends AppCompatActivity {
    private TextView txtContador;
    private Button btnSiguiente;
    private MediaPlayer mediaPlayer;
    private CountDownTimer timer;
    private LottieAnimationView animacion;
    private boolean ejercicioFinalizado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio_guiado);

        txtContador = findViewById(R.id.contador);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        animacion = findViewById(R.id.animacionLottie);

        // Reproducir audio
        mediaPlayer = MediaPlayer.create(this, R.raw.meditguia);

        if (mediaPlayer != null) {
            int duracionMs = mediaPlayer.getDuration();

            // Temporizador sincronizado con el audio
            timer = new CountDownTimer(duracionMs, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int minutos = (int) (millisUntilFinished / 1000) / 60;
                    int segundos = (int) (millisUntilFinished / 1000) % 60;
                    txtContador.setText(String.format("%02d:%02d", minutos, segundos));
                }

                @Override
                public void onFinish() {
                    txtContador.setText("00:00");
                    ejercicioFinalizado = true;
                    Toast.makeText(ActivityEjercicioGuiado.this, "¡Ejercicio completado!", Toast.LENGTH_SHORT).show();
                }
            };

            mediaPlayer.start();
            timer.start();
        }

        // Botón siguiente siempre está habilitado
        btnSiguiente.setEnabled(true);
        btnSiguiente.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            if (timer != null) {
                timer.cancel();
            }

            Intent intent = new Intent(ActivityEjercicioGuiado.this, ActivityGuiaDos.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (timer != null) {
            timer.cancel();
        }
    }
}