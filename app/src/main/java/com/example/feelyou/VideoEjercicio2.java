package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoEjercicio2 extends AppCompatActivity {
    private VideoView videoView;
    private ProgressBar progressBar;
    private Button btnSiguiente;
    private TextView descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_ejercicio2);
        videoView = findViewById(R.id.videoView1);
        progressBar = findViewById(R.id.progressBar);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        descripcion = findViewById(R.id.textDescripcion);

        descripcion.setText("Este ejercicio te guía a una sesión que te servirá para mitigar el estrés, lo cual este rutina se debe repetir 4 veces al día.");

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_2);
        videoView.setMediaController(new MediaController(this));

        videoView.setOnPreparedListener(mp -> {
            progressBar.setMax(videoView.getDuration());
            videoView.start();

            new Thread(() -> {
                while (videoView.isPlaying()) {
                    progressBar.setProgress(videoView.getCurrentPosition());
                    try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }).start();
        });

        btnSiguiente.setOnClickListener(v -> {
            Intent intent = new Intent(this, VideoEjercicio3.class);
            startActivity(intent);
        });
    }
}