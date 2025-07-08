package com.example.feelyou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menu extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iconoMenu;
    private NavigationView navigationView;

    private LinearLayout btnIrMusica;
    private LinearLayout btnIrRecursos;

    private LinearLayout btnIrCalendario;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        iconoMenu = findViewById(R.id.icono_menu);
        ImageView imgUser = findViewById(R.id.iduser);

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

        // Manejar clics del menú
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_perfil) {
                startActivity(new Intent(this, ActivityPerfil.class));
            } else if (id == R.id.nav_quienes_somos) {
                startActivity(new Intent(this, ActivityQuienesSomos.class));
            } else if (id == R.id.nav_ayuda) {
                startActivity(new Intent(this, ActivityNecesitoAyuda.class));
            } else if (id == R.id.nav_recursos) {
                startActivity(new Intent(this, ActivityRecursosAtencion.class));
            }

            return true;
        });

        // Abrir el menú cuando se toca el icono
        iconoMenu.setOnClickListener(v -> {
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END);
            } else {
                drawerLayout.closeDrawer(GravityCompat.END);
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

        imgUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityPerfil.class);
            startActivity(intent);
        });

    }
}


