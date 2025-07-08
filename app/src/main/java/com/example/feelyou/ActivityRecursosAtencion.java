package com.example.feelyou;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityRecursosAtencion extends AppCompatActivity {

    EditText etBuscar;
    LinearLayout layout;
    List<String[]> contactosOriginales;
    List<String[]> contactosFiltrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursos_atencion);

        etBuscar = findViewById(R.id.et_buscar);
        layout = findViewById(R.id.layout_contactos);

        contactosOriginales = new ArrayList<>();
        contactosOriginales.add(new String[]{"Línea 113 Salud Mental", "113"});
        contactosOriginales.add(new String[]{"Centro de Apoyo Psicológico Piura", "073328947"});
        contactosOriginales.add(new String[]{"Hospital Santa Rosa - Emergencias", "073284561"});
        contactosOriginales.add(new String[]{"Psicólogos Unidos Piura", "987654321"});
        contactosOriginales.add(new String[]{"Apoyo Emocional 24/7 MINSA", "080010828"});

        // Al inicio mostramos todos
        mostrarContactos(contactosOriginales);

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence texto, int start, int before, int count) {
                filtrarContactos(texto.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void filtrarContactos(String texto) {
        contactosFiltrados = new ArrayList<>();

        for (String[] contacto : contactosOriginales) {
            if (contacto[0].toLowerCase().contains(texto.toLowerCase()) ||
                    contacto[1].contains(texto)) {
                contactosFiltrados.add(contacto);
            }
        }

        mostrarContactos(contactosFiltrados);
    }

    private void mostrarContactos(List<String[]> contactos) {
        layout.removeAllViews();

        for (String[] contacto : contactos) {
            View tarjeta = getLayoutInflater().inflate(R.layout.tarjeta_contacto, layout, false);

            TextView nombre = tarjeta.findViewById(R.id.tv_nombre);
            TextView numero = tarjeta.findViewById(R.id.tv_numero);
            Button btnLlamar = tarjeta.findViewById(R.id.btn_llamar);

            nombre.setText(contacto[0]);
            numero.setText("Tel: " + contacto[1]);

            String numeroTel = contacto[1];
            btnLlamar.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numeroTel));
                startActivity(intent);
            });

            layout.addView(tarjeta);
        }
    }
}

