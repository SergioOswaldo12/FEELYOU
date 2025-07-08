package com.example.feelyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menu extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iconoMenu;

    private LinearLayout btnIrMusica;
    private LinearLayout btnIrRecursos;

    private LinearLayout btnIrCalendario;

    private LinearLayout btnIrEjercicios;

    private LinearLayout btnIrDiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        iconoMenu = findViewById(R.id.icono_menu);


        iconoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        btnIrMusica = findViewById(R.id.anim_canciones);
        btnIrMusica.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(menu.this, canciones.class);
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

        btnIrRecursos = findViewById(R.id.anim_recursos);
        btnIrRecursos.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(menu.this, Activity_Recursos.class);
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

        btnIrCalendario = findViewById(R.id.anim_calendario);
        btnIrCalendario.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(menu.this, Activity_calendario.class);
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

        btnIrEjercicios = findViewById(R.id.anim_ejercicios);
        btnIrEjercicios.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(menu.this, ejercicios.class);
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

        btnIrDiario = findViewById(R.id.anim_diario);
        btnIrDiario.setOnClickListener(view -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                Intent intent = new Intent(menu.this, ActivityDiario.class);
                intent.putExtra("userEmail", email);
                startActivity(intent);
            }
        });

    }
}


