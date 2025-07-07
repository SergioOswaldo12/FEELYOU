package com.example.feelyou;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Activity_calendario extends AppCompatActivity {
    MaterialCalendarView calendarView;
    CalendarDay selectedDay;

    EditText etTitulo, etDescripcion, etHora;

    Map<String, List<Map<String, String>>> mapaRecordatorios = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendarView = findViewById(R.id.materialCalendarView);
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etHora = findViewById(R.id.etHora);

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            selectedDay = date;
            String fechaKey = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.getDate());

            if (mapaRecordatorios.containsKey(fechaKey)) {
                mostrarDialogoDeRecordatorios(mapaRecordatorios.get(fechaKey));
            }
        });

        findViewById(R.id.btnAgendar).setOnClickListener(v -> guardarRecordatorio());
    }

    private void guardarRecordatorio() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String hora = etHora.getText().toString();

        if (selectedDay == null) {
            Toast.makeText(this, "Seleccione una fecha en el calendario", Toast.LENGTH_SHORT).show();
            return;
        }

        String fechaKey = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDay.getDate());

        Map<String, String> nuevoRecordatorio = new HashMap<>();
        nuevoRecordatorio.put("titulo", titulo);
        nuevoRecordatorio.put("descripcion", descripcion);
        nuevoRecordatorio.put("hora", hora);

        if (!mapaRecordatorios.containsKey(fechaKey)) {
            mapaRecordatorios.put(fechaKey, new ArrayList<>());
        }

        mapaRecordatorios.get(fechaKey).add(nuevoRecordatorio);
        calendarView.addDecorator(new DiaConRecordatorioDecorator(selectedDay));

        Toast.makeText(this, "Recordatorio agregado", Toast.LENGTH_SHORT).show();

        etTitulo.setText("");
        etDescripcion.setText("");
        etHora.setText("");
    }

    private void mostrarDialogoDeRecordatorios(List<Map<String, String>> recordatorios) {
        StringBuilder mensaje = new StringBuilder();
        for (Map<String, String> rec : recordatorios) {
            mensaje.append("📝 ").append(rec.get("titulo")).append("\n");
            mensaje.append("⏰ ").append(rec.get("hora")).append("\n");
            mensaje.append("🧾 ").append(rec.get("descripcion")).append("\n\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("Recordatorios del día")
                .setMessage(mensaje.toString())
                .setPositiveButton("Cerrar", null)
                .show();
    }

    private void marcarFechasConEventos() {
        for (String fechaStr : mapaRecordatorios.keySet()) {
            try {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(fechaStr);
                CalendarDay day = CalendarDay.from(fecha);
                calendarView.addDecorator(new DiaConRecordatorioDecorator(day));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}