package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoEjercicio3 extends AppCompatActivity {

    private VideoView videoView;
    private ProgressBar progressBar;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_ejercicio3);
        videoView = findViewById(R.id.videoView2);
        progressBar = findViewById(R.id.progressBar);
        descripcion = findViewById(R.id.textDescripcion);

        // Ocultamos el botón "Siguiente" porque no existe en este layout
        findViewById(R.id.btnSiguiente).setVisibility(android.view.View.GONE);

        descripcion.setText("Para cerrar la rutina es importante tambien realizar estos ejercicios cortos que también te ayudarán a aliviar el éstres, tal como lo hace el instructor.");

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_3);
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

        videoView.setOnCompletionListener(mp -> {
            Intent intent = new Intent(this, ActivityFormulario.class);
            startActivity(intent);
        });
    }
}