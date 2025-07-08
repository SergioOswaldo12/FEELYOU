package com.example.feelyou;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;


public class subirmusic extends AppCompatActivity {

    private static final int PICK_AUDIO = 1;
    private static final int PICK_IMAGE = 2;

    private Uri selectedAudioUri;
    private Uri selectedImageUri;

    private ImageView imgPreview;
    private Button btnSeleccionarImagen, btnSeleccionarCancion, btnSubir;
    private EditText nombreCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subirmusic);

        imgPreview = findViewById(R.id.imgPreview);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnSeleccionarCancion = findViewById(R.id.btnSeleccionarCancion);
        btnSubir = findViewById(R.id.btnSubir);
        nombreCancion = new EditText(this);  // O usa un campo en el XML
        nombreCancion = findViewById(R.id.etNombreCancion);


        btnSeleccionarImagen.setOnClickListener(v -> seleccionarImagen());
        btnSeleccionarCancion.setOnClickListener(v -> seleccionarAudio());
        btnSubir.setOnClickListener(v -> subirCancion());
    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    private void seleccionarAudio() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_AUDIO);
    }

    private void subirCancion() {
        String nombre = nombreCancion.getText().toString().trim();
        if (selectedAudioUri == null || selectedImageUri == null || nombre.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            File audioFile = copiarArchivoLocal(selectedAudioUri, nombre + ".mp3");
            File imageFile = copiarArchivoLocal(selectedImageUri, nombre + ".jpg");

            Cancion cancion = new Cancion(nombre, imageFile.getAbsolutePath(), audioFile.getAbsolutePath());

            List<Cancion> lista = CancionStorage.cargarCanciones(this);
            lista.add(cancion);
            CancionStorage.guardarCanciones(this, lista);

            Toast.makeText(this, "Canción subida", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al subir canción", Toast.LENGTH_SHORT).show();
        }
    }

    private File copiarArchivoLocal(Uri uri, String fileName) throws Exception {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        File file = new File(getFilesDir(), fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_AUDIO) {
                selectedAudioUri = data.getData();
            } else if (requestCode == PICK_IMAGE) {
                selectedImageUri = data.getData();
                imgPreview.setImageURI(selectedImageUri);
            }
        }
    }

}