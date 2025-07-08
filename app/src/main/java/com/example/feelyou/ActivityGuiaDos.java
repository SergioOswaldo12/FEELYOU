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

public class ActivityGuiaDos extends AppCompatActivity {

    private TextView txtContador;
    private Button btnIrFormulario;
    private MediaPlayer mediaPlayer;
    private CountDownTimer timer;
    private LottieAnimationView animacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guia_dos);
        txtContador = findViewById(R.id.contador);
        btnIrFormulario = findViewById(R.id.btnIrForm);
        animacion = findViewById(R.id.animacionLottie);

        btnIrFormulario.setEnabled(true); // Puedes poner false si deseas bloquear hasta terminar

        mediaPlayer = MediaPlayer.create(this, R.raw.meditaudio); // Cambia el nombre de audio si es otro
        int duracionMs = mediaPlayer.getDuration();

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
                Toast.makeText(ActivityGuiaDos.this, "¡Ejercicio completado!", Toast.LENGTH_SHORT).show();
            }
        };

        mediaPlayer.start();
        timer.start();

        btnIrFormulario.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

            if (timer != null) {
                timer.cancel();
            }

            startActivity(new Intent(ActivityGuiaDos.this, ActivityFormularioGuia.class));
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